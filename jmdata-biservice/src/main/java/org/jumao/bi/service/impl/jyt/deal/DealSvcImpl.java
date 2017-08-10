package org.jumao.bi.service.impl.jyt.deal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.jyt.deal.IDealSvc;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;

public class DealSvcImpl implements IDealSvc {

    @Autowired
    private JdbcTemplate       jdbcTemplate;
    public static final String Create_Time = "create_time";
    public static final String Order_Total = "ordertotal";
    public static final String Pay_Total   = "paytotal";
    public static final String O_Alias     = "o";

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#operate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response operate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        StringBuffer sql = new StringBuffer(
                "SELECT count(*) count,ifnull(sum(weight),0) weight,ifnull(round(sum(total_price)/10000.000,2),0) total_price");
        sql.append(" FROM jmbi_lg_order ").append(" WHERE delete_flag = '0' AND active_flag = '0' AND order_type IN ('1', '2') ");

        StringBuffer paySql = new StringBuffer("SELECT count(*) payment_count,ifnull(round(sum(pay_amt)/10000.000,2),0) payment_money ");
        paySql.append(" FROM jmbi_ep_trade ").append(" WHERE status in (0,1,2,3,4) and is_delete = 0 and industry_id=1004 ");

        String subYearSql = " and create_time like '" + org.jumao.bi.utis.DateUtils.getYear(null) + "%'";
        String yearSql = sql.toString() + subYearSql;
        String yearPaySql = paySql.toString() + subYearSql;

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(jdbcTemplate.queryForMap(sql.append(subSql).toString()));
        data.get(0).putAll(jdbcTemplate.queryForMap(paySql.append(subSql).toString()));
        data.add(jdbcTemplate.queryForMap(yearSql));
        data.get(1).putAll(jdbcTemplate.queryForMap(yearPaySql));

        // 转化成横向列表
        data.add(getRowMap(data.get(Key.Num0), data.get(Key.Num1), "订单量", "count"));
        data.add(getRowMap(data.get(Key.Num0), data.get(Key.Num1), "载重（T）", "weight"));
        data.add(getRowMap(data.get(Key.Num0), data.get(Key.Num1), "金额（万元）", "total_price"));
        data.add(getRowMap(data.get(Key.Num0), data.get(Key.Num1), "支付笔数", "payment_count"));
        data.add(getRowMap(data.get(Key.Num0), data.get(Key.Num1), "支付金额（万元）", "payment_money"));

        return Response.ok(data.subList(Key.Num2, data.size())).build();
    }

    private Map<String, Object> getRowMap(Map<String, Object> map, Map<String, Object> yearMap, String name, String key) {
        Map<String, Object> rowMap = new HashMap<String, Object>();
        rowMap.put("name", name);
        rowMap.put("number", map.get(key));
        rowMap.put("totalNumber", yearMap.get(key));
        return rowMap;
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#translate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response translate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition("n", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition("n", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        StringBuffer sql = new StringBuffer("select count(distinct n.company_id) ");// 下单uv
        sql.append(" from jmbi_lg_needs n,jmbi_lg_company c").append(" where c.id = n.company_id ").append(subSql);
        setData(seriesData, sql.toString(), "需求UV");
        sql.setLength(0);
        sql.append("SELECT count(distinct o.buyer_company_id) ").append("FROM jmbi_lg_order o, jmbi_lg_company c ")
                .append(" WHERE c.id = o.buyer_company_id AND c.is_virtual_company in (0,1)")
                .append(" AND EXISTS (select 1 from jmbi_lg_needs n where n.id = o.needs_id ").append(subSql).append(")");
        setData(seriesData, sql.toString().concat(" and o.order_type in ('0','1')"), "意向UV");
        setData(seriesData, sql.toString().concat(" and o.order_type in ('1')"), "订单UV");
        setData(seriesData, sql.toString().concat(" and o.pay_status in ('1','2','3','4')"), "支付UV");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("需求UV", "意向UV", "订单UV", "支付UV"));
        pieCharts.setTitle("交易转化跟踪");
        pieCharts.setSeriesName("交易转化跟踪");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void setData(List<SeriesData> seriesDataList, String placeSql, String name) {
        long count = jdbcTemplate.queryForObject(placeSql, Long.class);
        SeriesData seriesData = new SeriesData();
        seriesData.setName(name);
        seriesData.setValue(BigDecimal.valueOf(count));
        seriesDataList.add(seriesData);
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#settle(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response settle(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT COUNT(pay_type) count,pay_type from ");
        sql.append("jmbi_lg_order").append(" where pay_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by pay_type ").append("order by pay_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "pay_type", "结算方式占比", "结算方式占比");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#lineType(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response lineType(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT COUNT(line_type) count,line_type from ");
        sql.append("jmbi_lg_order").append(" where line_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by line_type ").append("order by line_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "line_type", "运输方式", "运输方式");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#orderSource(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response orderSource(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT count(1) count,o.source platform from ");
        sql.append("jmbi_lg_order o ").append(" where  1=1 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by platform ").append("order by platform");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "platform", "订单来源", "订单来源");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#orderType(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response orderType(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT count(1) count,o.order_type from ");
        sql.append("jmbi_lg_order o,jmbi_lg_company c").append(" where c.id = o.buyer_company_id AND o.order_type != '0' ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append(" group by o.order_type ").append("order by o.order_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "order_type", "订单类型占比", "订单类型占比");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#areaAmount(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public Response areaAmount(String currencyCode, String lineType, String startDate, String endDate, String sortField) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition(O_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(lineType)) {
            subSql += StringUtils.getSqlCondition(O_Alias, "line_type", lineType, CompareOp.EQUAL);
        }

        StringBuffer sql = new StringBuffer(
                "SELECT t.address,a.area_name,COUNT(*) ordernum,sum(t.ordertotal) ordertotal,sum(t.paynum) paynum,sum(t.paytotal) paytotal ");
        sql.append(
                "FROM (SELECT o.order_no order_no,IFNULL(o.total_price, 0.00) ordertotal,strleft(cast(c.area as string), 5) address,o.create_time,o.line_type,")
                .append("if(o.pay_type = '0',0,1) paynum,if(o.pay_type = '0',0.00,o.total_price) paytotal ")
                .append("FROM jmbi_lg_order o,jmbi_lg_company c ")
                .append("WHERE o.buyer_company_id = c.id AND o.active_flag = '0' AND c.is_virtual_company = 1 AND c.area IS NOT NULL ")
                .append(subSql).append(") t").append(" LEFT JOIN jmbi_lg_base_area a ON t.address = cast(a.area_code as string) ")
                .append(" GROUP BY t.address,a.area_name ");
        if (StringUtils.isNotBlank(sortField)) {
            sql.append(" order by ").append(sortField).append(Key.Blank).append(Direction.DESC);// 方便取top
            // ten
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        // 处理地区名称
        String areaName = null;
        String keyAreaName = "area_name";
        for (Map<String, Object> map : data) {
            if (map.get(keyAreaName) == null) {
                continue;
            }
            if (map.get(Order_Total) != null) {
                map.put(Order_Total,
                        NumberUtils.createBigDecimal(map.get(Order_Total).toString()).divide(BigDecimal.valueOf(Key.Num10000), Key.Num2,
                                BigDecimal.ROUND_HALF_EVEN));
            }
            if (map.get(Pay_Total) != null) {
                map.put(Pay_Total,
                        NumberUtils.createBigDecimal(map.get(Pay_Total).toString()).divide(BigDecimal.valueOf(Key.Num10000), Key.Num2,
                                BigDecimal.ROUND_HALF_EVEN));
            }
            areaName = map.get(keyAreaName).toString();
            if (areaName.startsWith("内蒙古") || areaName.startsWith("黑龙江")) {
                areaName = areaName.substring(Key.Num0, Key.Num3);
            } else {
                areaName = areaName.substring(Key.Num0, Key.Num2);
            }
            map.put(keyAreaName, areaName);
        }
        return Response.ok(data).build();
    }

    /**
     * @see org.jumao.bi.service.jyt.deal.IDealSvc#payChannel(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response payChannel(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT c.PAY_CHANNEL_NO,c.PAY_CHANNEL_NAME,sum(t.pay_amt) pay_amt ");
        sql.append(" FROM jmbi_ep_trade t, jmbi_epay_channel c").append(
                " where t.channel_no = c.pay_channel_no and t.status in (0,1,2,3,4)  and t.is_delete = 0 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition("t", Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition("t", Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and t.industry_id = ").append(StringUtils.left(platform, Key.Num4));
        }
        sql.append(" GROUP BY c.PAY_CHANNEL_NO, c.PAY_CHANNEL_NAME");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        SeriesData serData = null;
        for (Map<String, Object> map : data) {
            serData = new SeriesData();
            serData.setName(map.get("PAY_CHANNEL_NAME").toString());
            serData.setValue(NumberUtils.createBigDecimal(map.get("pay_amt").toString()).divide(BigDecimal.valueOf(Key.Num10000), Key.Num2,
                    BigDecimal.ROUND_HALF_EVEN));
            serData.setCode(map.get("PAY_CHANNEL_NO").toString());
            seriesData.add(serData);
            legendData.add(map.get("PAY_CHANNEL_NAME").toString());
        }

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("支付渠道金额占比");
        pieCharts.setSeriesName("支付渠道金额占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }
}

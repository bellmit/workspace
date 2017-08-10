package org.jumao.bi.service.impl.trade.deal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.trade.deal.IDealSvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;

public class DealSvcImpl implements IDealSvc {

    @Autowired
    private JdbcTemplate       jdbcTemplate;
    public static final String Platform_Id_Jumore = "100100";            // 聚贸总平台ID
    public static final String O_Alias            = "o";                 // 别名o
    public static final String Industry_Id        = "industry_id";       // 行业ID
    public static final String And                = " and ";
    public static final String Jmbi_Trade_Order   = " jmbi_trade_order ";
    public static final String blank              = " ";

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#operate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response operate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and c.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and c.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }
        StringBuffer sql = new StringBuffer(
                " SELECT count(c.order_id) order_count,ifnull(round(sum(c.pay_money)/10000.000,2),0) order_money");
        sql.append(" FROM jmbi_trade_order c").append(" WHERE c.order_state in (20,30,40,50,70) ");
        StringBuffer paySql = new StringBuffer(
                "SELECT count(c.payment_id) payment_count,ifnull(round(sum(c.pay_money)/10000.000,2),0) payment_money ");
        paySql.append(" FROM jmbi_trade_payment c,jmbi_trade_order o ").append(" WHERE c.order_id = o.order_id AND c.pay_flag = 1 ");
        String subYearSql = " and c.create_time like '" + org.jumao.bi.utis.DateUtils.getYear(null) + "%'";
        String yearSql = sql.toString() + subYearSql;
        String yearPaySql = paySql.toString() + subYearSql;

        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            sql.append(And).append("c.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
            paySql.append(And).append(O_Alias).append(".").append(Industry_Id).append("= ").append(PlatFormUtil.getPlatformV(platform));
            yearSql += " and c.industry_id = " + PlatFormUtil.getPlatformV(platform);
            yearPaySql += And + O_Alias + "." + Industry_Id + " = " + PlatFormUtil.getPlatformV(platform);
        }
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(jdbcTemplate.queryForMap(sql.append(subSql).toString()));
        data.get(0).putAll(jdbcTemplate.queryForMap(paySql.append(subSql).toString()));
        data.add(jdbcTemplate.queryForMap(yearSql));
        data.get(1).putAll(jdbcTemplate.queryForMap(yearPaySql));

        return Response.ok(data).build();
    }

    private String formatDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = StringUtils.left(date, Key.Num4) + "-" + StringUtils.substring(date, Key.Num4, Key.Num6) + "-"
                    + StringUtils.right(date, Key.Num2);
        }
        return date;
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#translate(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response translate(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }

        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            subSql += " and o.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }

        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        StringBuffer sql = new StringBuffer("select count(distinct c.comp_id)");// 下单uv
        sql.append(" from jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u")
                .append(" where o.member_id=u.user_id and u.company_id=c.comp_id").append(subSql);
        setData(seriesData, sql.toString(), "下单UV");
        setData(seriesData, sql.toString().concat(" and o.order_state in (20,30,40,50,70)"), "确认订单UV");
        setData(seriesData, sql.toString().concat(" and o.contract_status=40"), "签订合同UV");
        setData(seriesData, sql.toString().concat(" and o.order_state in (40,50,70)"), "订单支付UV");

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(Arrays.asList("下单UV", "确认订单UV", "签订合同UV", "订单支付UV"));
        pieCharts.setTitle("交易转化跟踪");
        pieCharts.setSeriesName("交易转化跟踪");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void setData(List<SeriesData> seriesData, String placeSql, String name) {
        long count = jdbcTemplate.queryForObject(placeSql, Long.class);
        SeriesData sData = new SeriesData();
        sData.setName(name);
        sData.setValue(BigDecimal.valueOf(count));
        seriesData.add(sData);
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#pickup(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response pickup(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select o.shipping_type,count(distinct c.comp_id) count");
        sql.append(" from jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u").append(
                " where o.member_id=u.user_id and u.company_id=c.comp_id and o.shipping_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by o.shipping_type").append(" order by o.shipping_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "shipping_type", "提货方式用户占比", "提货方式用户占比");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#delivery(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response delivery(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.order_id,datediff(a.time1,b.time2) days ");
        sql.append("FROM (SELECT o.order_id,create_time time1 FROM jmbi_trade_order_track o WHERE o.memo = '订单确认') a,")
                .append("(SELECT o.order_id, o.create_time time2 FROM jmbi_trade_order_track o WHERE o.memo = '发货') b,jmbi_trade_order o ")
                .append(" WHERE a.order_id = b.order_id AND o.order_id = a.order_id");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        section(data, seriesData, legendData);

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("交货时间占比");
        pieCharts.setSeriesName("交货时间占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    private void section(List<Map<String, Object>> data, List<SeriesData> seriesData, List<String> legendData) {
        Map<String, Long> dataMap = new HashMap<String, Long>();
        long days = 0;
        String dataKey = null;
        for (Map<String, Object> map : data) {
            days = NumberUtils.toLong(map.get("days").toString(), 0);
            if (days >= Key.Num10) {
                dataKey = "1";
            } else if (days >= Key.Num7 && days < Key.Num10) {
                dataKey = "2";
            } else if (days >= Key.Num5 && days < Key.Num7) {
                dataKey = "3";
            } else if (days >= Key.Num3 && days < Key.Num5) {
                dataKey = "4";
            } else {
                dataKey = "5";
            }
            if (dataMap.get(dataKey) == null) {
                dataMap.put(dataKey, 1L);
            } else {
                dataMap.put(dataKey, dataMap.get(dataKey) + 1L);
            }
        }

        List<String> keyList = new ArrayList<String>(dataMap.keySet());
        Collections.sort(keyList);
        SeriesData sData = null;
        BigDecimal value = BigDecimal.ZERO;
        for (String key : keyList) {
            value = BigDecimal.valueOf(dataMap.get(key));
            if ("1".equals(key)) {
                key = "10-15天以内";
            } else if ("2".equals(key)) {
                key = "7-10天以内";
            } else if ("3".equals(key)) {
                key = "5-7天以内";
            } else if ("4".equals(key)) {
                key = "3-5天以内";
            } else if ("5".equals(key)) {
                key = "3天以内";
            }
            sData = new SeriesData();
            sData.setName(key);
            sData.setValue(value);
            seriesData.add(sData);
            legendData.add(key);
        }
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#receipt(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response receipt(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.order_id,datediff(a.time1,b.time2) days ");
        sql.append("FROM (SELECT o.order_id,create_time time1 FROM jmbi_trade_order_track o WHERE o.memo = '发货') a,")
                .append("(SELECT o.order_id, o.create_time time2 FROM jmbi_trade_order_track o WHERE o.memo = '确认收货') b,jmbi_trade_order o ")
                .append(" WHERE a.order_id = b.order_id AND o.order_id = a.order_id");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        this.section(data, seriesData, legendData);

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("确认收货时间");
        pieCharts.setSeriesName("确认收货货时间");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#settle(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response settle(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select clearing_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from ").append(Jmbi_Trade_Order).append(" where clearing_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by clearing_type ").append("order by clearing_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "clearing_type", "结算方式占比", "结算方式占比");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#pay(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response pay(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select payment_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from ").append(Jmbi_Trade_Order).append(" where payment_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" GROUP BY payment_type").append(" order by payment_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "payment_type", "支付方式", "支付方式");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#contract(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response contract(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select contract_type,count(distinct order_id) count,sum(pay_money)");
        sql.append(" from jmbi_trade_order ").append(" where contract_type is not NULL ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            sql.append(" and industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" GROUP BY contract_type").append(" order by contract_type");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "contract_type", "合同方式", "合同方式");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#areaAmount(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public Response areaAmount(String role, String startDate, String endDate, String platform, String orderby) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }

        if (!Platform_Id_Jumore.equalsIgnoreCase(platform) && StringUtils.isNotBlank(platform)) {
            subSql += " and o.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }
        StringBuffer sql = new StringBuffer("SELECT ordernum,paytotal,address_province,area_name ");
        if ("seller".equalsIgnoreCase(role)) {// 卖家
            sql.append("FROM (SELECT count(DISTINCT order_id) ordernum,sum(pay_money) paytotal,c.address_province ")
                    .append("FROM jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_shop s ")
                    .append("WHERE o.store_id = s.shop_id AND s.comp_id = c.comp_id AND c.address_province IS NOT NULL ").append(subSql)
                    .append(" GROUP BY address_province ) t ")
                    .append("LEFT JOIN jmbi_trade_operation_area a ON t.address_province = a.AREA_CODE");
        } else if ("buyer".equalsIgnoreCase(role)) {// 买家
            sql.append("FROM (SELECT count(DISTINCT order_id) ordernum,sum(pay_money) paytotal,c.address_province ")
                    .append("FROM jmbi_trade_order o,jmbi_trade_company c,jmbi_trade_center_user u")
                    .append("  WHERE o.member_id = u.user_id AND u.company_id = c.comp_id AND c.address_province IS NOT NULL ")
                    .append(subSql).append(" GROUP BY address_province ) t ")
                    .append("LEFT JOIN jmbi_trade_operation_area a ON t.address_province = a.AREA_CODE");
        } else {
            return Response.ok().build();
        }
        if (StringUtils.isNotBlank(orderby)) {
            sql.append(" order by t.").append(orderby).append(blank).append(Direction.DESC);// 方便取top
            // ten
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        // 处理地区名称
        String areaName = null;
        String keyAreaName = "area_name";
        String keyPayToal = "paytotal";
        for (Map<String, Object> map : data) {
            if (map.get(keyAreaName) == null) {
                continue;
            }
            if (map.get(keyPayToal) != null) {
                map.put("paytotal",
                        NumberUtils.createBigDecimal(map.get(keyPayToal).toString()).divide(BigDecimal.valueOf(Key.Num10000), Key.Num2,
                                BigDecimal.ROUND_HALF_EVEN));
            }
            areaName = map.get(keyAreaName).toString();
            if (areaName.startsWith("内蒙古") || areaName.startsWith("黑龙江")) {
                areaName = areaName.substring(0, Key.Num3);
            } else {
                areaName = areaName.substring(0, 2);
            }
            map.put(keyAreaName, areaName);
        }
        return Response.ok(data).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#payChannel(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response payChannel(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT c.PAY_CHANNEL_NO,c.PAY_CHANNEL_NAME,count(DISTINCT t.order_no),sum(t.pay_amt) pay_amt ");
        sql.append(" FROM jmbi_ep_trade t, jmbi_epay_channel c,jmbi_trade_order o").append(
                " where t.channel_no = c.pay_channel_no AND t.order_no = o.order_no ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and t.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and t.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" GROUP BY c.PAY_CHANNEL_NO, c.PAY_CHANNEL_NAME");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        SeriesData sData = null;
        for (Map<String, Object> map : data) {
            sData = new SeriesData();
            sData.setName(map.get("PAY_CHANNEL_NAME").toString());
            sData.setValue(NumberUtils.createBigDecimal(map.get("pay_amt").toString()).divide(BigDecimal.valueOf(Key.Num10000), Key.Num2,
                    BigDecimal.ROUND_HALF_EVEN));
            seriesData.add(sData);
            legendData.add(map.get("PAY_CHANNEL_NAME").toString());
        }

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("支付渠道金额占比");
        pieCharts.setSeriesName("支付渠道金额占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#paymentDays(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response paymentDays(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT o.order_id,datediff(t.time1,o.create_time) days ");
        sql.append("FROM jmbi_trade_order_track  o ")
                .append("LEFT JOIN (SELECT order_id,max(create_time) AS time1 FROM jmbi_trade_payment WHERE pay_flag = 1 GROUP BY order_id) t ON o.order_id = t.order_id ")
                .append("LEFT JOIN jmbi_trade_order r ON o.order_id=r.order_id").append(" WHERE o.memo = '订单确认' AND t.time1 IS NOT NULL");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and r.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and r.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and r.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        List<SeriesData> seriesData = new ArrayList<SeriesData>();
        List<String> legendData = new ArrayList<String>();
        section(data, seriesData, legendData);

        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("付款天数用户占比");
        pieCharts.setSeriesName("付款天数用户占比");
        pieCharts.setSeriesData(seriesData);
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#industryAmount(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response industryAmount(String startDate, String endDate, String sortField) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT count(DISTINCT o.order_id) ordernum,sum(o.pay_money) paytotal,o.industry_id ");
        sql.append("FROM jmbi_trade_order o where 1=1 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME)
                    .append(DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME)
                    .append(DateUtils.SINGLE_QUOTE);
        }
        sql.append("group by o.industry_id ");
        if (StringUtils.isNotBlank(sortField)) {
            sql.append(" order by ").append(sortField).append(" ").append(Direction.DESC);
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());

        Map<String, Object> map = PieCharts.setPieCharts("", "industry_id", sortField, data, "订单分析（行业）", "订单分析（行业）");
        return Response.ok(map).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#industryPieCharts(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public Response industryPieCharts(String startDate, String endDate, String code, String key) throws Exception {
        StringBuffer sql = new StringBuffer("select industry_id,count(DISTINCT order_id) count,sum(pay_money)");
        sql.append(" from jmbi_trade_order ").append(" where 1=1 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        sql.append(" and ").append(key).append("=").append(code);
        sql.append(" GROUP BY industry_id").append(" order by count ").append(Direction.DESC);
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        PieCharts pieCharts = PieCharts.setPieCharts(data, "industry_id", "", "");
        return Response.ok(pieCharts).build();
    }

    /**
     * @see org.jumao.bi.service.trade.deal.IDealSvc#categoryAmount(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public Response categoryAmount(String startDate, String endDate, String industryId, String sortField) throws Exception {
        StringBuffer sql = new StringBuffer(
                "SELECT count(DISTINCT o.order_id) ordernum,sum(d.item_price*d.item_amount) paytotal,g.goods_category_grade1_id,t.goods_cate_name ");
        sql.append("FROM jmbi_trade_order o,jmbi_trade_order_detail d, jmbi_trade_goods g,jmbi_trade_goods_category t ").append(
                "where o.order_id = d.order_id  AND d.goods_id = g.goods_id AND g.goods_category_grade1_id=t.goods_cate_id ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate)).append(DateUtils.FIRST_TIME)
                    .append(DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate)).append(DateUtils.LAST_TIME)
                    .append(DateUtils.SINGLE_QUOTE);
        }
        if (StringUtils.isNotBlank(industryId)) {
            sql.append(" and o.industry_id = ").append(industryId).append(" and t.industry_id = ").append(industryId);
        }
        sql.append(" group by g.goods_category_grade1_id,t.goods_cate_name ");
        if (StringUtils.isNotBlank(sortField)) {
            sql.append(" order by ").append(sortField).append(" ").append(Direction.DESC);
        }
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        Map<String, Object> dataMap = PieCharts.setPieCharts("goods_cate_name", "goods_category_grade1_id", sortField, data, "各类目占比",
                "各类目占比");
        return Response.ok(dataMap.get("pieCharts")).build();
    }
}

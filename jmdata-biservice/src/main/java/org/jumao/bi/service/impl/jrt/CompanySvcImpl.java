package org.jumao.bi.service.impl.jrt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.jumao.bi.entites.LineCharts;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.service.jrt.ICompanySvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CompanySvcImpl implements ICompanySvc {

    @Autowired
    private JdbcTemplate       jdbcTemplate;
    public static final String Create_Time = "create_time";
    public static final String A_Alias     = "a";

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#type()
     */
    public Response type() throws Exception {
        StringBuffer sql = new StringBuffer("SELECT IFNULL(b.option_name, '其他') name,count(a.id) ordernum");
        sql.append(" from jmbi_jrt_company a")
                .append(" LEFT JOIN jmbi_jrt_data_option b ON a.company_type = b.option_code AND b.classify_code = 'enterprise_type'")
                .append(" where a.identity = 0 AND a.code_status = 2 AND a.delete_flag = 0 ").append(" group by name ");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        Map<String, Object> map = PieCharts.setPieCharts("name", null, null, data, "入驻企业类别", "入驻企业类别");
        return Response.ok(map).build();
    }

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#productType()
     */
    public Response productType() throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.product_type_name name,count(*) ordernum");
        sql.append(" FROM jmbi_jrt_product a").append(" WHERE a. STATUS = 2 AND a.is_shelf = 1 AND a.product_type IS NOT NULL")
                .append(" GROUP BY name");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        Map<String, Object> map = PieCharts.setPieCharts("name", null, null, data, "上架产品类型", "上架产品类型");
        return Response.ok(map).build();
    }

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#area()
     */
    public Response area() throws Exception {
        StringBuffer sql = new StringBuffer("SELECT b.area_name,count(a.id) count");
        sql.append(" FROM jmbi_jrt_company a,jmbi_public_data_base_area b")
                .append(" WHERE a.identity = 0 AND a.code_status = 2 AND a.delete_flag = 0 AND b.area_code = strleft(a.area, 5) ")
                .append(" GROUP BY b.area_name ").append(" order by count desc");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql.toString());
        // 处理地区名称
        String areaName = null;
        String keyAreaName = "area_name";
        BigDecimal total = BigDecimal.ZERO;
        for (Map<String, Object> map : data) {
            if (map.get(keyAreaName) == null) {
                continue;
            }
            areaName = map.get(keyAreaName).toString();
            if (areaName.startsWith("内蒙古") || areaName.startsWith("黑龙江")) {
                areaName = areaName.substring(Key.Num0, Key.Num3);
            } else {
                areaName = areaName.substring(Key.Num0, Key.Num2);
            }
            total = total.add(NumberUtils.createBigDecimal(map.get("count").toString()));
            map.put(keyAreaName, areaName);
        }
        // 处理占比
        int size = data.size() > Key.Num10 ? Key.Num10 : data.size();
        int i = Key.Num0;
        for (Map<String, Object> map : data) {
            map.put("proportion",
                    NumberUtils.createBigDecimal(map.get("count").toString()).divide(total, Key.Num2, BigDecimal.ROUND_HALF_EVEN));
            i++;
            if (i >= size) {
                break;
            }
        }

        return Response.ok(data).build();
    }

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#enterTrend(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response enterTrend(String startDate, String endDate, String enterpriseType) throws Exception {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<String> xAxisData = getxAxisDataList(startDate, endDate, map);
        String groupBy = "strleft(cast(a.create_time as string),10)";
        if (endDate.equalsIgnoreCase(startDate)) {
            groupBy = "substr(cast(a.create_time as string),12,5)";
        }
        StringBuffer sql = new StringBuffer("select ");
        sql.append(groupBy).append("  as operate_date, count(a.id) count from jmbi_jrt_company a ")
                .append(" where a.identity = 0 AND a.code_status = 2 AND a.delete_flag = 0");

        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        if ("0".equalsIgnoreCase(enterpriseType)) {
            sql.append(" AND a.company_type not in  ('51','52','53')");
        } else {
            sql.append(" AND a.company_type = '").append(enterpriseType).append("'");
        }
        sql.append(" group by ").append(groupBy);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        LineCharts lineCharts = LineCharts.setLineCharts(xAxisData, rows, "operate_date", "count", map);
        lineCharts.setUnit("家");
        return Response.ok(lineCharts).build();
    }

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#shelfTrend(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public Response shelfTrend(String startDate, String endDate, String productType) throws Exception {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<String> xAxisData = getxAxisDataList(startDate, endDate, map);
        String groupBy = "strleft(cast(a.create_time as string),10)";
        if (endDate.equalsIgnoreCase(startDate)) {
            groupBy = "substr(cast(a.create_time as string),12,5)";
        }
        StringBuffer sql = new StringBuffer("select ");
        sql.append(groupBy).append("  as operate_date, count(a.id) count from jmbi_jrt_product a ")
                .append(" where a.STATUS = 2 AND a.is_shelf = 1 ");
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(productType)) {
            sql.append(" AND a.product_type = ").append(productType);
        }

        sql.append(subSql).append(" group by operate_date ");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        LineCharts lineCharts = LineCharts.setLineCharts(xAxisData, rows, "operate_date", "count", map);
        lineCharts.setUnit("个");
        return Response.ok(lineCharts).build();
    }

    private List<String> getxAxisDataList(String startDate, String endDate, Map<String, BigDecimal> map) {
        List<String> list = new ArrayList<String>();
        Date date1 = org.jumao.bi.utis.DateUtils.parseDate(startDate, DateUtils.DAY_FORMAT_YYYYMMDD);
        Date date2 = org.jumao.bi.utis.DateUtils.parseDate(endDate, DateUtils.DAY_FORMAT_YYYYMMDD);

        if (startDate.equals(endDate) && StringUtils.isNotBlank(startDate)) {
            for (int i = 0; i <= 23; i++) {
                map.put(StringUtils.leftPad(i + "", Key.Num2, "0") + ":00", BigDecimal.ZERO);
                list.add(StringUtils.leftPad(i + "", Key.Num2, "0") + ":00");
            }
        } else {
            while (date1.getTime() <= date2.getTime()) {
                map.put(org.jumao.bi.utis.DateUtils.dateToString(date1, DateUtils.DAY_FORMAT), BigDecimal.ZERO);
                list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy-MM-dd"));
                date1 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 1);
            }
        }
        return list;
    }

    /**
     * @see org.jumao.bi.service.jrt.ICompanySvc#shelfProductType(java.lang.String,
     *      java.lang.String)
     */
    public Response shelfProductType(String startDate, String endDate) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT a.product_type,max(a.product_type_name) product_type_name ");
        sql.append(" FROM jmbi_jrt_product a").append(" WHERE  a.STATUS = 2 AND a.is_shelf = 1");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL));
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL));
        }
        sql.append("GROUP BY a.product_type");

        return Response.ok(jdbcTemplate.queryForList(sql.toString())).build();
    }

}

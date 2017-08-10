package org.jumao.bi.service.impl.trade.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jumao.bi.entites.AreaBarCharts;
import org.jumao.bi.entites.LineCharts;
import org.jumao.bi.service.trade.shop.IShopSvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ShopSvcImpl implements IShopSvc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Response openTrend(String startDate, String endDate) throws Exception {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<String> xAxisData = getxAxisDataList(startDate, endDate, map);
        String groupBy = "strleft(operate_time,10)";
        if (endDate.equalsIgnoreCase(startDate)) {
            groupBy = "substr(operate_time,12,5)";
        }
        StringBuffer sql = new StringBuffer("select ");
        sql.append(groupBy).append("  as operate_date, count(distinct shop_id) count from jmbi_trade_shop_operate where operate_type=0 ");

        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and operate_time >= '").append(this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and operate_time <= '").append(this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);

        }
        sql.append(" group by ").append(groupBy).append(" order by operate_date");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        String operateDate = null;
        for (Map<String, Object> row : rows) {
            operateDate = row.get("operate_date").toString();
            if (!xAxisData.contains(operateDate) && startDate.equals(endDate)) {
                xAxisData.add(operateDate);
            }
            map.put(operateDate, NumberUtils.createBigDecimal(row.get("count").toString()));
        }

        // 构造数据
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> keyList = new ArrayList<String>(map.keySet());
        Collections.sort(keyList);
        Collections.sort(xAxisData);
        for (String key1 : keyList) {
            seriesData.add(map.get(key1));
        }

        LineCharts lineChart = new LineCharts();
        lineChart.setxAxisData(xAxisData);
        lineChart.setSeriesData(seriesData);
        return Response.ok(lineChart).build();
    }

    private List<String> getxAxisDataList(String startDate, String endDate, Map<String, BigDecimal> map) {
        List<String> list = new ArrayList<String>();
        Date date1 = org.jumao.bi.utis.DateUtils.parseDate(startDate, DateUtils.DAY_FORMAT_YYYYMMDD);
        Date date2 = org.jumao.bi.utis.DateUtils.parseDate(endDate, DateUtils.DAY_FORMAT_YYYYMMDD);

        if (startDate.equals(endDate) && StringUtils.isNotBlank(startDate)) {
            for (int i = 0; i <= 23; i++) {
                map.put(StringUtils.leftPad(i + "", 2, "0") + ":00", BigDecimal.ZERO);
                list.add(StringUtils.leftPad(i + "", 2, "0") + ":00");
            }
        } else {
            while (date1.getTime() <= date2.getTime()) {
                map.put(org.jumao.bi.utis.DateUtils.dateToString(date1, DateUtils.DAY_FORMAT), BigDecimal.ZERO);
                list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年MM月dd日"));
                date1 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 1);
            }
        }
        return list;
    }

    public Response perfect(String startDate, String endDate) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and s.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and s.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        StringBuffer sql = new StringBuffer();
        Long count = 0L;
        // 企业动态
        sql.append("SELECT count(DISTINCT s.shop_id) FROM ")
                .append(" jmbi_trade_shop s, jmbi_trade_comp_dynamic d,  jmbi_trade_company c ")
                .append(" where s.comp_id=c.comp_id and d.comp_id=c.comp_id").append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));
        // 产品分类
        sql.setLength(0);
        sql.append("SELECT count(DISTINCT s.shop_id) FROM ").append(" jmbi_trade_shop s, jmbi_trade_shop_goods_category c ")
                .append(" WHERE s.shop_id = c.shop_id ").append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));
        // 企业介绍
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.summary is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        // 背景图
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.background is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        // LOGO
        sql.setLength(0);
        sql.append("select count(distinct s.shop_id) from ").append(" jmbi_trade_shop s").append(" WHERE s.logo is not NULL")
                .append(subSql);
        count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
        seriesData.add(BigDecimal.valueOf(count));

        List<String> yAxisData = new ArrayList<String>(Arrays.asList("企业动态", "产品分类", "企业介绍", "背景图", "LOGO"));
        AreaBarCharts charts = new AreaBarCharts();
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response releaseRank(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select c.comp_id,c.comp_name, count(DISTINCT g.goods_id) as total ");
        sql.append(" from jmbi_trade_goods g").append(" LEFT JOIN jmbi_trade_company c ON g.company_id = c.comp_id ").append(" where 1=1 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and g.create_time >= '").append(this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and g.create_time <= '").append(this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and g.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by c.comp_id,c.comp_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(DesensitizationUtils.getDesStr(row.get("comp_name").toString()));
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("商品发布数排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response categoryRank(String startDate, String endDate) throws Exception {
        StringBuffer sql = new StringBuffer("SELECT g.shop_id, count(DISTINCT shop_cate_id) total,comp_name ");
        sql.append(" FROM jmbi_trade_shop_goods_category g,jmbi_trade_shop p,jmbi_trade_company c ").append(
                " where g.shop_id = p.shop_id AND p.comp_id = c.comp_id AND g.delete_flag = 0 ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and g.create_time >= '").append(this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and g.create_time <= '").append(this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }
        sql.append(" group by g.shop_id,c.comp_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(DesensitizationUtils.getDesStr(row.get("comp_name").toString()));
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("店铺分类排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    public Response dealRank(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer("select c.comp_id,c.comp_name,sum(pay_money) as total ");
        sql.append(" from jmbi_trade_order o ").append(" INNER JOIN jmbi_trade_shop s ON o.store_id = s.shop_id")
                .append(" INNER JOIN jmbi_trade_company c ON s.comp_id = c.comp_id")
                .append(" where o.order_state IN (20, 30, 40, 50, 70) ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and o.create_time >= '").append(this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and o.create_time <= '").append(this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and o.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by c.comp_id,c.comp_name ").append(" ORDER BY total DESC ").append(" limit 5");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

        List<String> yAxisData = new ArrayList<String>();
        for (Map<String, Object> row : rows) {
            yAxisData.add(DesensitizationUtils.getDesStr(row.get("comp_name").toString()));
            seriesData.add(NumberUtils.createBigDecimal(row.get("total").toString()).divide(BigDecimal.valueOf(10000), 2,
                    BigDecimal.ROUND_HALF_EVEN));
        }

        AreaBarCharts charts = new AreaBarCharts();
        charts.setTitle("店铺成交排行");
        charts.setyAxisData(yAxisData);
        charts.setSeriesData(seriesData);
        return Response.ok(charts).build();
    }

    private String formatDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = StringUtils.left(date, 4) + "-" + StringUtils.substring(date, 4, 6) + "-" + StringUtils.right(date, 2);
        }
        return date;
    }

    public Response releaseList(String startDate, String endDate, String platform) throws Exception {
        StringBuffer sql = new StringBuffer(
                "select g.company_id,g.company_name,CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' WHEN 3 THEN '工贸一体' ELSE  '其他' END AS 'gsxz',count(DISTINCT g.goods_id) as total");
        sql.append(" from jmbi_trade_goods g,jmbi_trade_company c  ").append(" where g.company_id = c.comp_id ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and g.create_time >= '").append(this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and g.create_time <= '").append(this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE);
        }

        if (StringUtils.isNotBlank(platform)) {
            sql.append(" and g.industry_id = ").append(PlatFormUtil.getPlatformV(platform));
        }
        sql.append(" group by company_id,company_name,gsxz ").append(" ORDER BY total DESC ").append(" limit 10");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString());
        for (Map<String, Object> map : rows) {
            map.put("company_name", DesensitizationUtils.getDesStr(map.get("company_name").toString()));
        }
        return Response.ok(rows).build();
    }

    public Response categoryList(String startDate, String endDate) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and g.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and g.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }
        StringBuffer sql = new StringBuffer(
                "SELECT CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' ELSE  '工贸一体' END AS gsxz, g.shop_id, count(DISTINCT g.shop_cate_id) shop_cate_sum, c.comp_name, c.comp_id");
        sql.append(" from jmbi_trade_shop_goods_category g, jmbi_trade_shop p, jmbi_trade_company c")
                .append(" WHERE g.shop_id = p.shop_id AND p.comp_id = c.comp_id AND g.delete_flag = 0 ").append(subSql)
                .append(" GROUP BY g.shop_id, c.comp_name, c.comp_id,gsxz").append(" ORDER BY shop_cate_sum DESC").append(" limit 10");
        List<Map<String, Object>> cRows = jdbcTemplate.queryForList(sql.toString());// 公司top10
        sql.setLength(0);
        sql.append("SELECT count(goods_id) goods_num, g.company_id, c.category_name category_name")
                .append(" FROM jmbi_trade_shop_goods_category c")
                .append(" INNER JOIN jmbi_trade_goods g ON c.shop_cate_id = g.goods_category_grade1_id").append(subSql)
                .append(" GROUP BY g.company_id,c.shop_cate_id,c.category_name  ").append("order by g.company_id,goods_num DESC");
        List<Map<String, Object>> dRows = jdbcTemplate.queryForList(sql.toString());// 店铺每个分类下的商品数
        Map<String, String> cateMap = new HashMap<String, String>();
        String cateName = null;// 分类名称
        String companyId = null;// 店铺ID
        String tmp = null;
        for (Map<String, Object> map : dRows) {
            companyId = map.get("company_id").toString();
            cateName = map.get("category_name").toString();
            tmp = cateMap.get(companyId);
            if (StringUtils.isBlank(tmp)) {
                cateMap.put(companyId, cateName);
            } else {
                if (tmp.split(",").length < 3) {
                    cateMap.put(companyId, tmp + "," + cateName);
                }
            }
        }

        for (Map<String, Object> map : cRows) {
            map.put("category_name", cateMap.get(map.get("comp_id").toString()));
            map.put("comp_name", DesensitizationUtils.getDesStr(map.get("comp_name").toString()));
        }

        return Response.ok(cRows).build();
    }

    public Response dealList(String startDate, String endDate, String platform) throws Exception {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += " and o.create_time >= '" + this.formatDate(startDate) + DateUtils.FIRST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += " and o.create_time <= '" + this.formatDate(endDate) + DateUtils.LAST_TIME + DateUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(platform)) {
            subSql += " and o.industry_id = " + PlatFormUtil.getPlatformV(platform);
        }
        StringBuffer sql = new StringBuffer(
                "SELECT o.store_id, c.comp_id, c.comp_name,sum(pay_money) AS money_total, count(DISTINCT o.order_id) order_num,");
        sql.append(" CASE c.comp_category WHEN 1 THEN '工厂' WHEN 2 THEN '贸易商' WHEN 3 THEN '工贸一体' ELSE  '其他' END AS gsxz")
                .append(" FROM jmbi_trade_order o ").append(" INNER JOIN jmbi_trade_shop s ON o.store_id = s.shop_id ")
                .append(" INNER JOIN jmbi_trade_company c ON s.comp_id = c.comp_id ")
                .append(" WHERE  o.order_state IN (20, 30, 40, 50, 70) ").append(subSql)
                .append(" GROUP BY o.store_id, c.comp_id, c.comp_name,gsxz").append(" ORDER BY money_total DESC ").append(" LIMIT 10");
        List<Map<String, Object>> cRows = jdbcTemplate.queryForList(sql.toString());// 公司top10
        sql.setLength(0);
        sql.append("SELECT g.company_id,sum(o.pay_money) pay_menoy_total,d.goods_name").append(" FROM jmbi_trade_order o")
                .append(" INNER JOIN jmbi_trade_order_detail d ON o.order_id = d.order_id")
                .append(" INNER JOIN jmbi_trade_goods g ON d.goods_id = g.goods_id").append(" WHERE o.order_state IN (20, 30, 40, 50, 70)")
                .append(subSql).append(" GROUP BY g.company_id, d.goods_name").append(" ORDER BY g.company_id,pay_menoy_total DESC");

        List<Map<String, Object>> dRows = jdbcTemplate.queryForList(sql.toString());// 店铺每个分类下的商品数
        Map<String, String> cateMap = new HashMap<String, String>();
        String goodsName = null;// 商品名称
        String companyId = null;// 公司ID
        String tmp = null;
        for (Map<String, Object> map : dRows) {
            companyId = map.get("company_id").toString();
            if (map.get("goods_name") == null) {
                continue;
            }
            goodsName = map.get("goods_name").toString();
            tmp = cateMap.get(companyId);
            if (StringUtils.isBlank(tmp)) {
                cateMap.put(companyId, goodsName);
            } else {
                if (tmp.split(",").length < 3) {
                    cateMap.put(companyId, tmp + "," + goodsName);
                }
            }
        }

        for (Map<String, Object> map : cRows) {
            map.put("goods_name", cateMap.get(map.get("comp_id").toString()));
            map.put("comp_name", DesensitizationUtils.getDesStr(map.get("comp_name").toString()));
            if (map.get("money_total") != null) {
                map.put("money_total",
                        NumberUtils.createBigDecimal(map.get("money_total").toString()).divide(BigDecimal.valueOf(10000), 2,
                                BigDecimal.ROUND_HALF_EVEN));
            }
        }

        return Response.ok(cRows).build();
    }
}

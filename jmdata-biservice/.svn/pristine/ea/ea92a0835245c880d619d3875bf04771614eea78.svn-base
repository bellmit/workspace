package org.jumao.bi.service.impl.jdt.overview;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.constant.MapConstants;
import org.jumao.bi.entites.PieCharts;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.jdt.overview.IOverviewSvc;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class OverviewSvcImpl extends JdbcDaoSupport implements IOverviewSvc {

    @Autowired
    private IHBaseDao    hBaseDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setJb(JdbcTemplate jb) {
        super.setJdbcTemplate(jb);
    }

    public Response getOverview() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Result result = hBaseDao.getResult("jmbi:overview", "jdt");
        DecimalFormat df = new DecimalFormat(",###,##0.0000"); // 保留四位小数
        if (result != null) {
            byte[] amount = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("amount"));
            byte[] customerPay = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("customerPay"));
            byte[] partnerIncome = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("partnerIncome"));
            if (amount != null) {
                map.put("amount", df.format(Bytes.toBigDecimal(amount).divide(BigDecimal.valueOf(10000), 4, BigDecimal.ROUND_HALF_EVEN)));// 单位万美元
                map.put("count", Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("count"))));
            } else {
                map.put("amount", 0);// 单位万美元
                map.put("count", 0);
            }
            if (customerPay != null) {
                map.put("customerPay", df.format(Bytes.toBigDecimal(customerPay).setScale(4).divide(BigDecimal.valueOf(10000))));// 万元
            } else {
                map.put("customerPay", 0);
            }

            if (partnerIncome != null) {
                map.put("partnerIncome", df.format(Bytes.toBigDecimal(partnerIncome).setScale(4).divide(BigDecimal.valueOf(10000))));// 万元
            } else {
                map.put("partnerIncome", 0);
            }
            map.put("customerCount", Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("customerCount"))));
            map.put("partnerCount", Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("partnerCount"))));
        }
        return Response.ok(map).build();
    }

    public Response starLevel(String area) throws Exception {
        String startRow = null;
        String endRow = null;
        if (StringUtils.isNotBlank(area)) {
            startRow = area + "000";
            endRow = area + "999";
        }

        List<Result> resultList = hBaseDao.getRows("jmbi:overview", startRow, endRow);
        String flag = null;
        String rowKey = null;
        String[] tmp = new String[2];// tmp[0]:地区;tmp[1]:星级
        BigDecimal count = BigDecimal.ZERO;
        Map<String, BigDecimal> starMap = new HashMap<String, BigDecimal>();
        starMap.put("441", BigDecimal.ZERO);
        starMap.put("442", BigDecimal.ZERO);
        starMap.put("443", BigDecimal.ZERO);
        starMap.put("444", BigDecimal.ZERO);
        starMap.put("445", BigDecimal.ZERO);
        for (Result result : resultList) {
            flag = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("flag")));
            if (!"star".equals(flag)) {
                continue;
            }
            count = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("count")));
            rowKey = Bytes.toString(result.getRow());
            tmp = rowKey.split("_");
            starMap.put(tmp[1], starMap.get(tmp[1]).add(count));
        }

        // 处理数据
        List<String> legendData = new ArrayList<String>();
        List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
        List<String> keyList = new ArrayList<String>(starMap.keySet());
        Collections.sort(keyList);
        SeriesData sData = null;
        for (String key : keyList) {
            sData = new SeriesData();
            sData.setValue(starMap.get(key));
            sData.setName(MapConstants.STAR_LEVEL_MAP.get(key));
            seriesDataList.add(sData);
            legendData.add(MapConstants.STAR_LEVEL_MAP.get(key));
        }
        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("拍档企业等级分布");
        pieCharts.setSeriesName("拍档企业等级分布");
        pieCharts.setSeriesData(seriesDataList);
        return Response.ok(pieCharts).build();
    }

    public Response partner(String area) throws Exception {
        String startRow = "860";
        String endRow = "861";
        if (StringUtils.isNotBlank(area)) {
            startRow = area + "000";
            endRow = area + "999";
        }
        Map<String, String> areaMap = getAreaMap();
        List<Result> resultList = hBaseDao.getRows("jmbi:overview", startRow, endRow);
        String flag = null;
        List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
        SeriesData seriesData = null;
        BigDecimal others = BigDecimal.ZERO;// 其它
        BigDecimal partnerCount = BigDecimal.ZERO;
        for (Result result : resultList) {
            flag = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("flag")));
            if (!"area".equals(flag)) {
                continue;
            }
            partnerCount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("partnerCount")));
            others = others.add(partnerCount);
            seriesData = new SeriesData();
            seriesData.setName(areaMap.get(Bytes.toString(result.getRow())));
            seriesData.setValue(partnerCount);
            seriesDataList.add(seriesData);
        }
        Collections.sort(seriesDataList, new SeriesData());
        int size = seriesDataList.size();
        size = size > 5 ? 5 : size;
        seriesDataList = seriesDataList.subList(0, size);
        List<String> legendData = new ArrayList<String>();
        for (SeriesData sData : seriesDataList) {
            others = others.subtract(sData.getValue());
            legendData.add(sData.getName());
        }
        if (others.compareTo(BigDecimal.ZERO) == 1) {// 大于0
            seriesData = new SeriesData();
            seriesData.setName("其他");
            seriesData.setValue(others);
            legendData.add("其他");
            seriesDataList.add(seriesData);

        }
        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("拍档企业地域分布");
        pieCharts.setSeriesName("拍档企业地域分布");
        pieCharts.setSeriesData(seriesDataList);
        return Response.ok(pieCharts).build();
    }

    public Response customer(String area) throws Exception {
        String startRow = "860";
        String endRow = "861";
        if (StringUtils.isNotBlank(area)) {
            startRow = area + "000";
            endRow = area + "999";
        }
        Map<String, String> areaMap = getAreaMap();
        List<Result> resultList = hBaseDao.getRows("jmbi:overview", startRow, endRow);
        String flag = null;
        List<SeriesData> seriesDataList = new ArrayList<SeriesData>();
        SeriesData seriesData = null;
        BigDecimal others = BigDecimal.ZERO;// 其它
        BigDecimal customerCount = BigDecimal.ZERO;
        for (Result result : resultList) {
            flag = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("flag")));
            if (!"area".equals(flag)) {
                continue;
            }
            customerCount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("customerCount")));
            others = others.add(customerCount);
            seriesData = new SeriesData();
            seriesData.setName(areaMap.get(Bytes.toString(result.getRow())));
            seriesData.setValue(customerCount);
            seriesDataList.add(seriesData);
        }
        Collections.sort(seriesDataList, new SeriesData());
        int size = seriesDataList.size();
        size = size > 5 ? 5 : size;
        seriesDataList = seriesDataList.subList(0, size);
        List<String> legendData = new ArrayList<String>();
        for (SeriesData sData : seriesDataList) {
            legendData.add(sData.getName());
            others = others.subtract(sData.getValue());
        }

        if (others.compareTo(BigDecimal.ZERO) == 1) {// 大于0
            seriesData = new SeriesData();
            seriesData.setName("其他");
            seriesData.setValue(others);
            legendData.add("其他");
            seriesDataList.add(seriesData);
        }
        PieCharts pieCharts = new PieCharts();
        pieCharts.setLegendData(legendData);
        pieCharts.setTitle("客户企业地域分布");
        pieCharts.setSeriesName("客户企业地域分布");
        pieCharts.setSeriesData(seriesDataList);
        return Response.ok(pieCharts).build();
    }

    private Map<String, String> getAreaMap() throws Exception {
        List<Result> resultList = hBaseDao.getRows("jmbi:baseArea", "", "");
        Map<String, String> areaMap = new HashMap<String, String>();
        String key = null;
        String value = null;
        for (Result result : resultList) {
            key = Bytes.toString(result.getRow());
            value = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("areaName")));
            if (key.equalsIgnoreCase(value)) {
                continue;
            }
            areaMap.put(key, value);
        }
        return areaMap;
    }
}

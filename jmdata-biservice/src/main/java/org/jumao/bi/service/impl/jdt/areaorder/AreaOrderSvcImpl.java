package org.jumao.bi.service.impl.jdt.areaorder;

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
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.LineAndBarCharts;
import org.jumao.bi.entites.MapDataBean;
import org.jumao.bi.entites.Series;
import org.jumao.bi.entites.SeriesData;
import org.jumao.bi.service.jdt.areaorder.IAreaOrderSvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaOrderSvcImpl implements IAreaOrderSvc {

    @Autowired
    private IHBaseDao hBaseDao;

    public Response trend(String flag, String timeType, String startDate, String endDate, String customsCode, int interval)
            throws Exception {
        String startRow = null;
        String endRow = null;
        if (StringUtils.isNotBlank(startDate)) {
            startRow = startDate + "_";
        }
        if (StringUtils.isNotBlank(endDate)) {
            endRow = endDate + "a";
        }
        String rowKey = null;
        String[] tmp = new String[2];// tmp[0]:日期;tmp[1]:进出口;tmp[2]:关区
        Series eSeries = new Series();
        Series iSeries = new Series();
        Series tSeries = new Series();
        Map<String, BigDecimal> eMap = new HashMap<String, BigDecimal>();// 出口数据
        Map<String, BigDecimal> iMap = new HashMap<String, BigDecimal>();// 进口数据
        List<String> xAxisData = getxAxisDataList(startDate, endDate, timeType, eMap, interval);
        iMap.putAll(eMap);
        List<Result> resultList = null;
        if (StringUtils.isBlank(customsCode) || "all".equalsIgnoreCase(customsCode)) {
            resultList = hBaseDao.getRows("jmbi:areaOrder", startRow, endRow);
        } else {
            List<String> arr = new ArrayList<String>();
            arr.add("info,customsCode," + customsCode);
            resultList = hBaseDao.getRowsByKeyAndFilterLike("jmbi:areaOrder", startRow, endRow, arr);
        }
        BigDecimal count = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        String key = null;
        for (Result result : resultList) {
            rowKey = Bytes.toString(result.getRow());
            tmp = rowKey.split("_");
            key = formatDate(tmp[0], timeType, interval);
            if ("1".equals(tmp[1]) || "2".equals(tmp[1])) {
                count = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("count")));
                amount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("amount")));
                if ("1".equalsIgnoreCase(flag)) {// 数量
                    if ("1".equals(tmp[1])) {// 进口
                        iMap.put(key, iMap.get(key).add(count));
                    } else {
                        eMap.put(key, eMap.get(key).add(count));
                    }
                } else {// 货值
                    amount = amount.divide(BigDecimal.valueOf(10000), 4, BigDecimal.ROUND_HALF_EVEN);
                    if ("1".equals(tmp[1])) {// 进口
                        iMap.put(key, iMap.get(key).add(amount));
                    } else {
                        eMap.put(key, eMap.get(key).add(amount));
                    }
                }
            }
        }
        // 处理Y轴数据
        List<Series> series = new ArrayList<Series>();
        eSeries.setName("出口");
        eSeries.setType("bar");
        eSeries.setData(new ArrayList<BigDecimal>());
        series.add(eSeries);
        iSeries.setName("进口");
        iSeries.setType("bar");
        iSeries.setData(new ArrayList<BigDecimal>());
        series.add(iSeries);
        tSeries.setName("总计");
        tSeries.setType("line");
        tSeries.setData(new ArrayList<BigDecimal>());
        series.add(tSeries);
        List<String> keyList = new ArrayList<String>(eMap.keySet());
        Collections.sort(keyList);
        for (String key1 : keyList) {
            eSeries.getData().add(eMap.get(key1));
            iSeries.getData().add(iMap.get(key1));
            tSeries.getData().add(eMap.get(key1).add(iMap.get(key1)));
        }

        String[] legendData = { "出口", "进口", "总计" };
        LineAndBarCharts data = new LineAndBarCharts();
        data.setLegendData(legendData);
        data.setxAxisData(xAxisData);
        data.setSeries(series);
        return Response.ok(data).build();
    }

    private List<String> getxAxisDataList(String startDate, String endDate, String timeType, Map<String, BigDecimal> eMap, int interval) {
        List<String> list = new ArrayList<String>();
        Date date1 = org.jumao.bi.utis.DateUtils.parseDate(startDate, DateUtils.DAY_FORMAT_YYYYMMDD);
        Date date2 = org.jumao.bi.utis.DateUtils.parseDate(endDate, DateUtils.DAY_FORMAT_YYYYMMDD);
        if ("week".equals(timeType)) {
            if (interval == 0) {
                while (date1.getTime() <= date2.getTime()) {
                    eMap.put(org.jumao.bi.utis.DateUtils.dateToString(date1, DateUtils.DAY_FORMAT_YYYYMMDD), BigDecimal.ZERO);
                    date1 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 1);
                }
                list = new ArrayList<String>(Arrays.asList("周日", "周一", "周二", "周三", "周四", "周五", "周六"));
            } else {
                while (date1.getTime() <= date2.getTime()) {
                    eMap.put(org.jumao.bi.utis.DateUtils.getWeekAndYear(date1, 0), BigDecimal.ZERO);
                    list.add(org.jumao.bi.utis.DateUtils.getWeekAndYear(date1, 0));
                    date1 = org.apache.commons.lang3.time.DateUtils.addWeeks(date1, 1);
                }
            }
        } else if ("month".equals(timeType)) {
            if (interval == 0) {
                while (date1.getTime() <= date2.getTime()) {
                    eMap.put(org.jumao.bi.utis.DateUtils.dateToString(date1, DateUtils.DAY_FORMAT_YYYYMMDD), BigDecimal.ZERO);
                    list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "dd日"));
                    date1 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 1);
                }
            } else {
                while (date1.getTime() <= date2.getTime()) {
                    list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年MM月"));
                    eMap.put(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyyMM"), BigDecimal.ZERO);
                    date1 = org.apache.commons.lang3.time.DateUtils.addMonths(date1, 1);
                }
            }

        } else if ("quarter".equals(timeType)) {
            if (interval == 0) {
                while (date1.getTime() <= date2.getTime()) {
                    list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年MM月"));
                    eMap.put(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyyMM"), BigDecimal.ZERO);
                    date1 = org.apache.commons.lang3.time.DateUtils.addMonths(date1, 1);
                }
            } else {
                while (date1.getTime() <= date2.getTime()) {
                    list.add(org.jumao.bi.utis.DateUtils.getYear(date1) + "年" + org.jumao.bi.utis.DateUtils.getSeason(date1) + "季");
                    eMap.put(org.jumao.bi.utis.DateUtils.getYear(date1) + "" + org.jumao.bi.utis.DateUtils.getSeason(date1),
                            BigDecimal.ZERO);
                    date1 = org.apache.commons.lang3.time.DateUtils.addMonths(date1, 3);
                }
            }

        } else if ("year".equals(timeType)) {
            if (interval == 0) {
                list = new ArrayList<String>(Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"));
                String year = StringUtils.left(startDate, 4);
                eMap.put(year + "01", BigDecimal.ZERO);
                eMap.put(year + "02", BigDecimal.ZERO);
                eMap.put(year + "03", BigDecimal.ZERO);
                eMap.put(year + "04", BigDecimal.ZERO);
                eMap.put(year + "05", BigDecimal.ZERO);
                eMap.put(year + "06", BigDecimal.ZERO);
                eMap.put(year + "07", BigDecimal.ZERO);
                eMap.put(year + "08", BigDecimal.ZERO);
                eMap.put(year + "09", BigDecimal.ZERO);
                eMap.put(year + "10", BigDecimal.ZERO);
                eMap.put(year + "11", BigDecimal.ZERO);
                eMap.put(year + "12", BigDecimal.ZERO);
            } else {
                while (date1.getTime() <= date2.getTime()) {
                    list.add(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy年"));
                    eMap.put(org.jumao.bi.utis.DateUtils.dateToString(date1, "yyyy"), BigDecimal.ZERO);
                    date1 = org.apache.commons.lang3.time.DateUtils.addMonths(date1, 12);
                }
            }
        }
        return list;
    }

    private String formatDate(String date, String timeType, int interval) {
        Date date1 = org.jumao.bi.utis.DateUtils.parseDate(date, DateUtils.DAY_FORMAT_YYYYMMDD);
        if ("week".equals(timeType)) {
            if (interval != 0) {
                date = org.jumao.bi.utis.DateUtils.getWeekAndYear(date1, 0);
            }
        } else if ("month".equals(timeType)) {
            if (interval != 0) {
                date = StringUtils.left(date, 6);
            }
        } else if ("quarter".equals(timeType)) {
            if (interval == 0) {
                date = StringUtils.left(date, 6);
            } else {
                date = StringUtils.left(date, 4) + org.jumao.bi.utis.DateUtils.getSeason(date1) + "";
            }

        } else if ("year".equals(timeType)) {
            if (interval == 0) {
                date = StringUtils.left(date, 6);
            } else {
                date = StringUtils.left(date, 4);
            }
        }
        return date;
    }

    public Response map(String timeType, String startDate, String endDate) throws Exception {
        String startRow = null;
        String endRow = null;
        if (StringUtils.isNotBlank(startDate)) {
            startRow = startDate + "_";
        }
        if (StringUtils.isNotBlank(endDate)) {
            endRow = endDate + "a";
        }
        String rowKey = null;
        String key = null;
        String category = null;
        String[] tmp = new String[4];// tmp[0]:日期;tmp[1]:关区;tmp[2]:客户ID;tmp[2]:拍档ID
        MapDataBean mapData = null;
        BigDecimal count = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        Map<String, MapDataBean> map = new HashMap<String, MapDataBean>();
        List<Result> resultList = hBaseDao.getRows("jmbi:areaOrder", startRow, endRow);
        for (Result result : resultList) {
            rowKey = Bytes.toString(result.getRow());
            tmp = rowKey.split("_");
            key = StringUtils.left(tmp[1], 2) + "00";
            category = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("category")));
            if ("custom".equalsIgnoreCase(category)) {// 海关
                mapData = map.get(key);
                count = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("count")));
                amount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("amount")));
                if (mapData == null) {
                    mapData = new MapDataBean();
                    mapData.setAmount(amount);
                    mapData.setCount(count);
                    mapData.setCode(StringUtils.left(tmp[1], 2));
                    mapData.setName(ServiceConst.customsMap.get(key));
                    map.put(key, mapData);
                } else {
                    mapData.setAmount(mapData.getAmount().add(amount));
                    mapData.setCount(mapData.getCount().add(count));
                    map.put(key, mapData);
                }
            } else if ("partner".equalsIgnoreCase(category)) {// 拍档
                mapData = map.get(key);
                amount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("payAmount")));
                if (mapData == null) {
                    mapData = new MapDataBean();
                    mapData.setCode(StringUtils.left(tmp[1], 2));
                    mapData.setCustomer(amount);
                    mapData.setPartner(amount);
                    mapData.setName(ServiceConst.customsMap.get(key));
                    map.put(key, mapData);
                } else {
                    mapData.setCustomer(mapData.getCustomer().add(amount));
                    mapData.setPartner(mapData.getPartner().add(amount));
                    map.put(key, mapData);
                }
            }
        }
        resultList = null;
        // 整理数据
        return Response.ok(map.values()).build();
    }

    @Override
    public Response list(String startDate, String endDate, String customsCode) throws Exception {
        String startRow = null;
        String endRow = null;
        if (StringUtils.isNotBlank(startDate)) {
            startRow = startDate + "_";
        }
        if (StringUtils.isNotBlank(endDate)) {
            endRow = endDate + "a";
        }
        String rowKey = null;
        String category = null;
        SeriesData sData1 = null;
        SeriesData sData2 = null;
        String[] tmp = new String[4];// tmp[0]:日期;tmp[1]:关区;tmp[2]:客户ID;tmp[2]:拍档ID
        Map<String, SeriesData> cMap = new HashMap<String, SeriesData>();// 订单数量
        Map<String, SeriesData> aMap = new HashMap<String, SeriesData>();// 订单金额
        Map<String, SeriesData> iMap = new HashMap<String, SeriesData>();// 拍档收入
        Map<String, SeriesData> pMap = new HashMap<String, SeriesData>();// 客户支出
        BigDecimal count = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        String customerCompanyName = null;
        String partnerCompanyName = null;
        List<Result> resultList = null;
        if (StringUtils.isBlank(customsCode) || "all".equalsIgnoreCase(customsCode)) {
            resultList = hBaseDao.getRows("jmbi:areaOrder", startRow, endRow);
        } else {
            List<String> arr = new ArrayList<String>();
            arr.add("info,customsCode," + customsCode);
            resultList = hBaseDao.getRowsByKeyAndFilterLike("jmbi:areaOrder", startRow, endRow, arr);
        }
        for (Result result : resultList) {
            rowKey = Bytes.toString(result.getRow());
            tmp = rowKey.split("_");
            category = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("category")));
            if ("custom".equalsIgnoreCase(category)) {// 海关
                count = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("count")));
                amount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("amount")));
                sData1 = cMap.get(tmp[1]);
                if (sData1 == null) {
                    sData1 = new SeriesData();
                    sData1.setValue(count);
                    sData1.setName(ServiceConst.customsMap.get(tmp[1]));
                    cMap.put(tmp[1], sData1);

                    sData2 = new SeriesData();
                    sData2.setValue(amount);
                    sData2.setName(ServiceConst.customsMap.get(tmp[1]));
                    aMap.put(tmp[1], sData2);
                } else {
                    sData1.setValue(sData1.getValue().add(count));
                    cMap.put(tmp[1], sData1);

                    sData2 = aMap.get(tmp[1]);
                    sData2.setValue(sData2.getValue().add(amount));
                    aMap.put(tmp[1], sData2);
                }
            } else if ("partner".equalsIgnoreCase(category)) {// 拍档
                amount = Bytes.toBigDecimal(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("payAmount")));
                customerCompanyName = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("customerCompanyName")));
                partnerCompanyName = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("partnerCompanyName")));

                // 拍档收入
                sData1 = iMap.get(tmp[3]);
                if (sData1 == null) {
                    sData1 = new SeriesData();
                    sData1.setValue(sData1.getValue().add(amount));
                    sData1.setName(DesensitizationUtils.getDesStr(partnerCompanyName));
                    iMap.put(tmp[3], sData1);
                } else {
                    sData1.setValue(sData1.getValue().add(amount));
                    iMap.put(tmp[3], sData1);
                }

                // 客户支出
                sData2 = pMap.get(tmp[2]);
                if (sData2 == null) {
                    sData2 = new SeriesData();
                    sData2.setValue(sData2.getValue().add(amount));
                    sData2.setName(DesensitizationUtils.getDesStr(customerCompanyName));
                    pMap.put(tmp[2], sData2);
                } else {
                    sData2.setValue(sData2.getValue().add(amount));
                    pMap.put(tmp[2], sData2);
                }
            }
        }

        // 整理数据
        Map<String, List<SeriesData>> map = new HashMap<String, List<SeriesData>>();
        map.put("c", getTopFive(cMap, 1));
        map.put("a", getTopFive(aMap, 10000));
        map.put("i", getTopFive(iMap, 10000));
        map.put("p", getTopFive(pMap, 10000));
        cMap.values();
        resultList = null;
        return Response.ok(map).build();
    }

    private List<SeriesData> getTopFive(Map<String, SeriesData> map, long divisor) {
        List<SeriesData> list = new ArrayList<SeriesData>(map.values());
        Collections.sort(list, new SeriesData());
        int size = list.size();
        if (size > 0) {
            size = size > 5 ? 5 : size;
            list = list.subList(0, size);
        }

        if (divisor != 0) {
            int scale = divisor == 1 ? 0 : 4;
            for (SeriesData sData : list) {
                sData.setValue(sData.getValue().divide(BigDecimal.valueOf(divisor), scale, BigDecimal.ROUND_HALF_EVEN));
            }
        }

        return list;
    }

}

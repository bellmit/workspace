package org.jumao.bi.service.impl.trade.register;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.GoodsDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.PieChart;
import org.jumao.bi.entites.trade.goods.OrderCountsVo;
import org.jumao.bi.entites.trade.goods.PlatformCompVo;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.MultiDimChartResp;
import org.jumao.bi.entites.trade.register.RegLineChart;
import org.jumao.bi.entites.trade.register.DataSrcIncrTable;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.PieMultiSeriesVo;
import org.jumao.bi.entites.trade.register.vo.StrArrData;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.entites.trade.register.vo.MultiDimPie;
import org.jumao.bi.entites.trade.register.vo.TableVo;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.comparator.DateTotalComp;
import org.jumao.bi.utis.comparator.TypeTotalComp;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.threads.GetOrderCountsThread;
import org.jumao.bi.utis.RegMapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by kartty on 2017/5/2.
 */
public class ChartBasicService extends ChartServiceHelper {

    private Logger logger = Logger.getLogger(ChartBasicService.class);
    private DateTotalComp dateTotalComp = new DateTotalComp();
    private TypeTotalComp typeTotalComp = new TypeTotalComp();

    private ExecutorService executor = Executors.newFixedThreadPool(Key.Num10);
    private String createTime = "create_time";
    private String companyName = "company_name";


    @Autowired
    protected GoodsDao goodsDao;

    protected Gson gson = new Gson();


    protected JSONObject getRespResultJObj() throws Exception {
        ResponseResult rr = new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG);
        return new JSONObject(gson.toJson(rr));
    }

    protected String logParamAndCheckDateRange(String platform, String startDate, String endDate) throws Exception {
        RespUtils.checkDateRange(startDate, endDate);
        return StringUtils.joinStr(GeneralUtils.getUpperMethod(), ", platform:", platform,
                ", startDate:", startDate, ", endDate:", endDate);
    }


    protected boolean checkAccurateToHour(String startDate, String endDate) {
        return (startDate != null && startDate.equals(endDate));
    }

    protected void setLineChartUnit(ChartResp chartResp, String unit) {
        chartResp.getLineChart().setUnit(unit);
    }

    /**
     * 处理折线图、柱状图，所有返回数据的日期格式都是 yyyyMMdd
     * @param reFormatToNhAnd_  重新 format 到没有小时和 - 的格式，如 2017010203, 这是前端需要的格式
     */
    protected ChartResp getLineChartFrom(List<TimeTotalVo> list, String title, String startDate, String endDate,
        int divideNum, boolean reFormatToNhAnd_, boolean accurateToHour) throws Exception {
        ChartResp cp = RespUtils.getSuccessChartResp();
        if (list == null) {
            return cp;
        }
        Map<String, String> dateTotalMap = getEmptyDateTotalMap(startDate, endDate, accurateToHour);
        RegLineChart lc = new RegLineChart();
        lc.setTitle(title);

        int size = list.size();
        boolean needDivide = divideNum > 0;

        for (int i = 0; i < size; i++) {
            try {
                TimeTotalVo ttv = list.get(i);
                String time = parseTime(ttv.getCreateTime(), reFormatToNhAnd_, accurateToHour);
                Long total = ttv.getTotal();
                if (total == null) {
                    total = 0L;
                }
                String numStr = null;
                if (total > 0L && needDivide) {
                    numStr = numDivideSth(total, divideNum);
                } else {
                    numStr = String.valueOf(total);
                }
                dateTotalMap.put(time, numStr);
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }

        List<Map.Entry<String, String>> entryList = new ArrayList(dateTotalMap.entrySet());
        Collections.sort(entryList, dateTotalComp);
        String[] xaxisData = new String[dateTotalMap.size()];
        String[] seriesData = new String[dateTotalMap.size()];

        for (int i = 0; i < entryList.size(); i++) {
            Map.Entry<String, String> entry = entryList.get(i);
            String time = entry.getKey();

            if (accurateToHour) {
                time = changeHourFormat(time);
            }
            xaxisData[i] = time;
            seriesData[i] = entry.getValue();
        }
        lc.setxAxisData(xaxisData);
        lc.setSeriesData(seriesData);

        cp.setLineChart(lc);
        return cp;
    }

    /**
     * needn't param: Map<String, String> typeNameMap
     */
//    protected ChartResp getLineChartFrom(List<TimeTotalVo> list, String title, String startDate, String endDate,
//                                         int divideNum, boolean reFormatToNhAnd_, boolean accurateToHour) throws Exception {
//        return getLineChartFrom(list, title, startDate, endDate, divideNum, null, reFormatToNhAnd_, accurateToHour);
//    }

    /**
     * 处理条形图
     */
    protected ChartResp getBarChartFrom(List<GroupByVo> list,
                                        Map<String, String> typeNameMap, String title, boolean isTopX) {
        ChartResp cp = RespUtils.getSuccessChartResp();
        if (list == null) {
            return cp;
        }

        RegLineChart lc = new RegLineChart();
        lc.setTitle(title);

        Map<String, Long> typeTotalMap = new HashMap<String, Long>();

        for (int i = 0; i < list.size(); i++) {
            try {
                GroupByVo gbv = list.get(i);
                String type = gbv.getType();
                if (type == null) {
                    continue;
                }
                String typeName = checkTypeName(type, typeNameMap.get(type));
                if (Key.NVLL.equals(typeName)) {
                    continue;
                }
                if (isTopX && ChartServiceHelper.ELSE_NAME.equals(typeName)) {
                    continue;
                }
                RegMapUtils.fill_key_longMapAddUpVal(typeTotalMap, typeName, gbv.getTotal());
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }

        int size = typeTotalMap.size();
        String[] yaxisData = new String[size];
        String[] seriesData = new String[size];
        String[] legendData = new String[size];

        List<Map.Entry<String, Long>> entries = new ArrayList<Map.Entry<String, Long>>(typeTotalMap.entrySet());
        Collections.sort(entries, typeTotalComp);

        int idx = 0;
        for (Map.Entry<String, Long> ele : entries) {
            yaxisData[idx] = ele.getKey();
            legendData[idx] = ele.getKey();
            seriesData[idx] = String.valueOf(ele.getValue());
            idx++;
        }
        lc.setyAxisData(yaxisData);
        lc.setSeriesData(seriesData);
        lc.setLegendData(legendData);

        cp.setLineChart(lc);
        return cp;
    }

    protected Map<String,String> getProvinceCodeNameMap(List<GroupByVo> list) {
        Map<String, String> provinceCodeNameMap = new HashMap<String, String>();

        for (GroupByVo ele : list) {
            String areaCode = ele.getType();
            if (areaCode == null) {
                continue;
            }
            String areaName = ele.getTypeName();
            if (areaName == null) {
                continue;
            }
            if (areaName.startsWith("广西") || areaName.startsWith("宁夏") || areaName.startsWith("新疆")) {
                provinceCodeNameMap.put(areaCode, areaName.substring(0, 2));

            } else {
                areaName = areaName.replaceAll("(市|省|自治区|特别行政区)", "");
                provinceCodeNameMap.put(areaCode, areaName);
            }
        }

        return provinceCodeNameMap;
    }


    protected void fillEmptyTypeForPieChart(List<GroupByVo> list, Map<String, String> typeNameMap) {
        Set<String> existTypes = new HashSet<String>();
        for (GroupByVo ele : list) {
            existTypes.add(ele.getType());
        }

        for (String type : typeNameMap.keySet()) {
            if (!existTypes.contains(type)) {
                list.add(new GroupByVo(type, type, 0L));
            }
        }
    }


    /**
     * 处理饼图、中国地图
     */
    protected ChartResp getPieChartFromGroupByVo(List<GroupByVo> list,
            Map<String, String> typeNameMap) throws Exception {
        ChartResp cp = RespUtils.getSuccessChartResp();

        PieChart pc = new PieChart();
        int size = list.size();

        long sumCounts = 0L;
        Map<String, Long> nameTotalMap = new HashMap<String, Long>();
        Map<String, GroupByVo> nameGrpVoMap = new HashMap<String, GroupByVo>();
        Map<String, String> nameTypeMap = new HashMap<String, String>();

        for (int i = 0; i < size; i++) {
            try {
                GroupByVo grpByVo = list.get(i);
                String type = grpByVo.getType();
                String name = checkTypeName(type, typeNameMap.get(type));
                if (Key.NVLL.equals(name)) {
                    continue;
                }
                RegMapUtils.fill_key_longMapAddUpVal(nameTotalMap, name, grpByVo.getTotal());
                nameGrpVoMap.put(name, grpByVo);
                nameTypeMap.put(name, type);// 最后用 name 来决定分类，因为 name 可能重复，比如都是 “其他”
                sumCounts += grpByVo.getTotal();
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }

        int idx = 0;
        String[] legendData = new String[nameTotalMap.size()];
        List<Map<String, String>> seriesData = new ArrayList<Map<String, String>>(size);

        for (Map.Entry<String, Long> ele : nameTotalMap.entrySet()) {
            String name = ele.getKey();
            String typeOrId = nameTypeMap.get(name);
            if (typeOrId == null) {
                typeOrId = "-1";
            }
            GroupByVo grpByVo = nameGrpVoMap.get(name);
            Map<String, String> seriesKeyVal = null;
            if (grpByVo == null) {
                seriesKeyVal = new HashMap<String, String>();
            } else {
                seriesKeyVal = grpByVo.getExtraKeyVal();
            }

            seriesKeyVal.put(Key.Type_Or_Id, typeOrId);
            seriesKeyVal.put(Key.NAME, name);
            seriesKeyVal.put(Key.VALUE, String.valueOf(ele.getValue()));
            seriesData.add(seriesKeyVal);

            legendData[idx] = name;
            idx++;
        }

        pc.setLegendData(legendData);
        pc.setSeriesData(seriesData);
        cp.setPieChart(pc);
        cp.setCounts(sumCounts);
        cp.setNowDate(RegDateUtils.formatToNoHourF(new Date()));
        return cp;
    }


    /**
     * 处理 多种数据维度的 饼图、中国地图
     */
    protected MultiDimChartResp getPieForMultiDim(Map<String, ChartResp> legendCpMap) {
        MultiDimChartResp mdcp = new MultiDimChartResp();
        mdcp.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        String[] data = new String[legendCpMap.size()];
        List<PieMultiSeriesVo> series = new ArrayList<PieMultiSeriesVo>(Key.Num64);

        int index = 0;
        for (Map.Entry<String, ChartResp> ele : legendCpMap.entrySet()) {
            String legend = ele.getKey();
            data[index] = legend;

            ChartResp cp = ele.getValue();
            PieChart pieChart = cp.getPieChart();

            PieMultiSeriesVo sv = new PieMultiSeriesVo();
            sv.setName(legend);
            sv.setData(PieChart.getSeriesCb(pieChart.getSeriesData()));
            series.add(sv);

            index++;
        }

        StrArrData legend = new StrArrData();
        legend.setData(data);

        MultiDimPie mdp = new MultiDimPie();
        mdp.setLegend(legend);
        mdp.setSeries(series);
        mdcp.setPieChart(mdp);
        return mdcp;
    }


    /**
     * @param formatToNhAndUl ul = under line
     * @return
     * @throws Exception
     */
    protected ChartResp getTableFromNewRegs(List<DataSrcIncrTable> list, long totalUser, int limit, int offset,
                                            boolean formatToNhAndUl, boolean accurateToHour) throws Exception {
        ChartResp cp = RespUtils.getSuccessChartResp();

        double sumCounts = 0D;
        for (DataSrcIncrTable ele : list) {
            Long total = ele.getTotal();
            if (total == null) {
                total = 0L;
                ele.setTotal(total);
            }
            sumCounts += total;
        }

        int totalSize = list.size();
        List<TableVo> tableData = new ArrayList<TableVo>(totalSize);

        int toIdx = offset + limit;
        if (toIdx > totalSize) {
            toIdx = totalSize;
        }
        List<DataSrcIncrTable> subList = null;
        if (offset >= totalSize) {
            subList = new ArrayList<DataSrcIncrTable>();
        } else {
            subList = list.subList(offset, toIdx);
        }

        for (DataSrcIncrTable ele : subList) {
            try {
                Long total = ele.getTotal();
                String type = ele.getType();
                String rate = dealRatePercent(total, sumCounts, true);

                String timeParsed = parseTime(ele.getCreateTime(), formatToNhAndUl, accurateToHour);
                if (accurateToHour) {
                    timeParsed = changeHourFormat(timeParsed);
                }
                tableData.add(new TableVo(timeParsed,
                        checkTypeName(type, dataSrcNameMap.get(type)),
                        String.valueOf(ele.getTotal()),
                        rate));
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }

        cp.setTableData(tableData);
        cp.setCounts((long) sumCounts);
        cp.setPercent(dealRatePercent(sumCounts, totalUser, true));

        cp.setTotalSize(totalSize);
        return cp;
    }


    protected Map<String,String> getTypeNameMapFrom(List<GroupByVo> list, boolean needDesense) {
        Map<String,String> map = new HashMap<String, String>();

        for (int i = 0; i < list.size(); i++) {
            GroupByVo vo = list.get(i);
            String type = vo.getType();
            String typeName = vo.getTypeName();
            if (needDesense) {
                typeName = DesensitizationUtils.getDesStr(typeName);
            }
            map.put(type, typeName);
        }
        return map;
    }


    /**
     * 感觉有问题，先不处理
     * //数据库中有些 name 和 echart 的不一样
     */
    protected void replaceSomeCodeForGlobal(Map<String, String> typeNameMap) {
//        typeNameMap.put(String.valueOf(Key.Num8), "French Southern and Antarctic Lands");
//        typeNameMap.put(String.valueOf(Key.Num16), "The Bahamas");
//        typeNameMap.put(String.valueOf(Key.Num49), "Republic of the Cong");
//        typeNameMap.put(String.valueOf(Key.Num50), "Democratic Republic of the Congo");
//        typeNameMap.put(String.valueOf(Key.Num70), "Falkland Islands");
//        typeNameMap.put(String.valueOf(Key.Num92), "Guinea Bissau");
//        typeNameMap.put(String.valueOf(Key.Num188), "Republic of Serbia");
//        typeNameMap.put(String.valueOf(Key.Num211), "United Republic of Tanzania");
    }


    protected Map<String,Double> getIndusAvgMoneyMap(String startDate, String endDate) throws Exception {
        Map<String,Double> indusAvgMoneyMap = new HashMap<String, Double>();
        List<GroupByVo> list = goodsDao.getIndustryAvgMoneyBy(startDate, endDate);

        for (GroupByVo ele : list) {
            String industry = PlatFormUtil.getPlatformV(String.valueOf(ele.getType()));
            if (industry == null || Key.NVLL.equals(industry)) {
                continue;
            }
            indusAvgMoneyMap.put(industry, ele.getValD());
        }

        return indusAvgMoneyMap;
    }


    //LoginCompIds 可重复，这些都是浏览记录
    protected Map<String, Set<String>> getIndustryLoginCompIdsMap(
            String startDate, String endDate, Map<String, Long> indusPvMap) {

        Map<String, Set<String>> indusCompIdsMap = new HashMap<String, Set<String>>();
        List<PlatformCompVo> list = goodsDao.getPlatformCompVosBy(startDate, endDate);

        for (PlatformCompVo ele : list) {
            String pf = goodsDao.dealPlatform(ele.getPlatform(), GeneralBasicDao.Len4);
            int pfInt = Integer.parseInt(pf);
            String industry = PlatFormUtil.platformNameMap.get(pfInt);
            if (industry == null || Key.NVLL.equals(industry)) {
                continue;
            }
            RegMapUtils.fill_key_setMap(indusCompIdsMap, industry, ele.getCompanyId());
            RegMapUtils.fill_key_longMapAddUpVal(indusPvMap, industry, 1L);
        }
        return indusCompIdsMap;
    }


    protected Map<String,Long> getIndustryOrderCountsMap(Map<String, Set<String>> indusLoginCompIdsMap, String startDate, String endDate) {
        Map<String, Long> platformOrderCountsMap = new HashMap<String, Long>();
        List<Future<OrderCountsVo>> resultList = new ArrayList<Future<OrderCountsVo>>();

        for (Map.Entry<String, Set<String>> ele : indusLoginCompIdsMap.entrySet()) {
            String industry  = ele.getKey();

            Future<OrderCountsVo> future = executor.submit(new GetOrderCountsThread(industry, goodsDao, ele.getValue(), startDate, endDate));
            resultList.add(future);
            // long orderCounts = goodsDao.getOrderCountsBy(ele.getValue());
        }

        for (Future<OrderCountsVo> ele : resultList) {
            try {
                while (!ele.isDone()) {};

                OrderCountsVo vo = ele.get();
                platformOrderCountsMap.put(vo.getIndustry(), vo.getOrderCounts());
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }
        return platformOrderCountsMap;
    }


    protected JSONArray getResJArr(Map<String, Double> indusAvgMoneyMap, Map<String, Set<String>> indusLoginCompIdsMap,
                                   Map<String, Long> indusOrderCountsMap, Map<String, Long> indusPvMap) {
        JSONArray resJArr = new JSONArray();

        for (Map.Entry<String, Long> ele : indusOrderCountsMap.entrySet()) {
            try {
                String industry = ele.getKey();
                if (industry == null || Key.NVLL.equals(industry)) {
                    LogUtils.writeLogs(logger, StringUtils.joinStr(industry, " is null or 'nvll' "));
                    continue;
                }
                Set<String> compIds = indusLoginCompIdsMap.get(industry);
                Double avgMoney = indusAvgMoneyMap.get(industry);

                if (compIds == null || compIds.isEmpty()) {
                    LogUtils.writeLogs(logger, StringUtils.joinStr(industry, "'s compIds is ", compIds, ", so continue."));
                    continue;
                }
                if (avgMoney == null) {
                    LogUtils.writeLogs(logger, StringUtils.joinStr(industry, "'s avgMoney == null, so continue."));
                    continue;
                }
                double orderCnts = ele.getValue();
                double uv = compIds.size();
                String transRate = dealRatePercent(orderCnts, uv, false);
                double transRateD = Double.parseDouble(transRate);

                Long pv = indusPvMap.get(industry);
                pv = pv == null ? 0L : pv;

                JSONArray jarr = new JSONArray();
                jarr.put(transRateD);
                jarr.put(pv);

                String wanYuan = numDivideSth(avgMoney, Key.Num10000);
                jarr.put(Double.parseDouble(wanYuan));
                jarr.put(industry);

                resJArr.put(jarr);
            } catch (Exception e) {
                LogUtils.writeLogs(logger, e.getMessage());
            }
        }
        return resJArr;
    }


    protected String changeCrtTmFromSqlTo(List<TimeTotalVo> list, boolean isFirstNotLast) throws Exception {
        String createTime = null;
        if (isFirstNotLast) {
            createTime = list.get(0).getCreateTime();
            if (createTime == null) {
                return "20160101";
            }
        } else {
            createTime = list.get(list.size() - 1).getCreateTime();
            if (createTime == null) {
                return RegDateUtils.formatToNoHourAnd_(new Date());
            }
        }
        return RegDateUtils.formatToNoHourAnd_(RegDateUtils.parseToNoHourF(createTime));
    }


    protected void reformatCreateTime(JsonArray tableDataJArr) throws Exception {
        for (int i = 0; i < tableDataJArr.size(); i++) {
            JsonObject obj = tableDataJArr.get(i).getAsJsonObject();
            String crtTm = obj.get(createTime).getAsString();
            String compName = obj.get(companyName).getAsString();

            if (Verifier.isEffectiveStr(crtTm)) {
                crtTm = RegDateUtils.changeToNoHourAnd_(crtTm);
                obj.addProperty(createTime, crtTm);
            }
            if (Verifier.isEffectiveStr(compName)) {
                obj.addProperty(companyName, DesensitizationUtils.getDesStr(compName));
            }
        }
    }


    protected <T> List<T> subList(List<T> list, int limit, int offset) {
        if (limit <= 0 || offset < 0) {
            return new ArrayList<T>();
        }

        int startIdx = offset;
        int endIdx = 0;

        endIdx = startIdx + limit;
        int size = list.size();

        if (startIdx > size - 1) {
            return new ArrayList<T>();
        }
        if (endIdx > list.size()) {
            endIdx = list.size();
        }
        return list.subList(startIdx, endIdx);
    }


    protected double getAllTotalCountsFrom(List<GroupByVo> grpByVos) {
        double allTotalCounts = 0;
        for (GroupByVo ele : grpByVos) {
            allTotalCounts += ele.getTotal();
        }
        return allTotalCounts;
    }


}

package org.jumao.bi.service.impl.en;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.dao.enjrt.EnNetFlowDao;
import org.jumao.bi.entites.en.EnBasicVisitPo;
import org.jumao.bi.entites.en.TotalPvUvVo;
import org.jumao.bi.entites.jrt.financing.EnNetflowOverview;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.RegLineChart;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.en.EnNetFlowSvc;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.EnJrtNfLine;
import org.jumao.bi.utis.enums.EnJrtNfOv;
import org.jumao.bi.utis.enums.EnJrtNfSm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 */
public class EnNetFlowSvcImpl extends ChartBasicService implements EnNetFlowSvc {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EnNetFlowDao enNetFlowDao;

    private String[] FRONT_2_ENFO_FIELDS_ARR = {Key.Pv, Key.Uv};
    private String[] END_2_ENFO_FIELDS_ARR = {Key.Bounce_Rate_, Key.Avg_Session_Dur_};


    @Override
    public Response getOverviews(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<EnNetflowOverview> enos = enNetFlowDao.getEnNetflowOverview(platform, startDate, endDate);
            JSONArray dataJarr = new JSONArray();

            if (enos.size() > 0) {
                JSONObject voJobj = new JSONObject(gson.toJson(enos.get(0)));

                fillOvDataJarr(dataJarr, FRONT_2_ENFO_FIELDS_ARR, voJobj);
                fillNewOldUsersPercent(dataJarr, voJobj);
                fillOvDataJarr(dataJarr, END_2_ENFO_FIELDS_ARR, voJobj);
            }

            JSONObject resJObj = getRespResultJObj();
            resJObj.put(Key.DATA, dataJarr);
            resJObj.put(Key.Now_Date, RegDateUtils.formatToNoHourF(new Date()));
            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    private void fillOvDataJarr(JSONArray resJarr, String[] fieldNames, JSONObject voJobj) throws Exception {
        for (String fieldName : fieldNames) {
            EnJrtNfOv enu = EnJrtNfOv.getEnumByName(fieldName);

            if (!enu.equals(EnJrtNfOv.ELSE) && !enu.equals(EnJrtNfOv.NEW_USERS)) {
                JSONObject cjobj = new JSONObject();
                cjobj.put(Key.ID, enu.getId());
                cjobj.put(Key.NAME, enu.getCnName());

                String val = voJobj.optString(fieldName);
                if (Verifier.isEffectiveStr(val)) {
                    val = siSheWuRuAfterDot(val);
                } else {
                    val = "0";
                }
                cjobj.put(Key.VALUE, val + enu.getUnit());

                resJarr.put(cjobj);
            }
        }
    }


    private void fillNewOldUsersPercent(JSONArray resJarr, JSONObject voJobj) throws Exception {
        double users = voJobj.optDouble(Key.Users);
        double newUsers = voJobj.optDouble(Key.New_Users_);
        double oldUser = users - newUsers;

        resJarr.put(fillPercent(newUsers, users, Key.New_Users_));
        resJarr.put(fillPercent(oldUser, users, Key.Old_Users_));
    }

    private JSONObject fillPercent(double xUsers, double users, String fieldName) throws Exception {
        String num = null;
        if (users > 0 && xUsers > 0) {
            double percent = xUsers / users;
            num = siSheWuRuAfterDot(String.valueOf(percent * 100));
        } else {
            num = "0";
        }
        EnJrtNfOv enu = EnJrtNfOv.getEnumByName(fieldName);

        JSONObject cJobj = new JSONObject();
        cJobj.put(Key.ID, enu.getId());
        cJobj.put(Key.NAME, enu.getCnName());
        cJobj.put(Key.VALUE, num + enu.getUnit());

        return cJobj;
    }


    @Override
    public Response getTrendLineChart(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, EnJrtNfLine.class);
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            EnJrtNfLine enJrtNfLine = EnJrtNfLine.getByType(type);

            List<TimeTotalVo> list = enNetFlowDao.getTrendBy(
                    platform, startDate, endDate, accurateToHour, type);
            ChartResp chartResp = getLineChartFrom(list, enJrtNfLine.getName(), startDate, endDate, Key.Num0, false, accurateToHour);

            setLineChartUnit(chartResp, enJrtNfLine.getUnit());
            return Response.ok().entity(chartResp).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getOverviewsTable(String platform, String startDate, String endDate, String orderTarget, String orderType) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<EnBasicVisitPo> ebvPos = enNetFlowDao.getEnBasicVisitPos(platform, startDate, endDate, orderTarget, orderType);
            JSONArray resJarr = new JSONArray(gson.toJson(ebvPos));

            JSONObject resJObj = getRespResultJObj();
            resJObj.put(Key.DATA, resJarr);
            resJObj.put(Key.Now_Date, RegDateUtils.formatToNoHourF(new Date()));
            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getNetflowTop5BarChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = enNetFlowDao.getNetflowPvTopXBy(platform, startDate, endDate, Top_Ele5);
            return Response.ok().entity(getBarChartFrom(
                    list, getTypeNameMapFrom(list, false), CN.Uv_Top5, true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getNetflowBounceTop5BarChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = enNetFlowDao.getNetflowBounceTopXBy(platform, startDate, endDate, Top_Ele5);
            return Response.ok().entity(getBarChartFrom(
                    list, getTypeNameMapFrom(list, false), CN.Bounce_Uv_Top5, true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getGlobalAreaDistAnalysis(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = enNetFlowDao.getGlobalAreaDistPvBy(platform, startDate, endDate);
            Map<String, String> typeNameMap = getTypeNameMapFrom(list, false);
            //replaceSomeCodeForGlobal(typeNameMap);

            return Response.ok().entity(getPieChartFromGroupByVo(list, typeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getGlobalAreaDistTable(String platform, String startDate, String endDate,
                                           int limit, int offset, String orderType) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    "platform:", platform, ", startDate:", startDate, ", endDate:", endDate,
                    ", limit:", limit, ", offset:", offset, ", orderType:", orderType));

            TotalPvUvVo totalPvUvBy = enNetFlowDao.getTotalPvUvBy(platform, startDate, endDate);
            JsonArray tableDataJArr = enNetFlowDao.getGlobalAreaDistTableDataBy(platform, startDate, endDate,
                    Key.Pv, limit, offset, orderType, totalPvUvBy);

            ChartResp chartResp = RespUtils.getSuccessChartResp();
            chartResp.setTotalSize(totalPvUvBy.getTotalCounts());

            JsonObject resJObj = gson.toJsonTree(chartResp).getAsJsonObject();
            resJObj.add(Key.Table_Data, tableDataJArr);

            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getSourceMediumUvPieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = enNetFlowDao.getSourceMediumUvBy(platform, startDate, endDate);
            fillEmptyTypeForPieChart(list, sourceTypeNameMap);

            return Response.ok().entity(getPieChartFromGroupByVo(list, sourceTypeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     * @param type  not used now
     */
    @Override
    public Response getSourceTypeLineChart(String platform, String startDate, String endDate, String type) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            List<TimeTotalVo> list0 = enNetFlowDao.getSourceTypeBy(
                    platform, startDate, endDate, accurateToHour, EnJrtNfSm.ORGANIC.getType());
            ChartResp chartResp0 = getLineChartFrom(list0, CN.Visit_Source, startDate, endDate, Key.Num0, false, accurateToHour);

            List<TimeTotalVo> list1 = enNetFlowDao.getSourceTypeBy(
                    platform, startDate, endDate, accurateToHour, EnJrtNfSm.DIRECT.getType());
            ChartResp chartResp1 = getLineChartFrom(list1, CN.Visit_Source, startDate, endDate, Key.Num0, false, accurateToHour);

            List<TimeTotalVo> list2 = enNetFlowDao.getSourceTypeBy(
                    platform, startDate, endDate, accurateToHour, EnJrtNfSm.REFERRAL.getType());
            ChartResp chartResp2 = getLineChartFrom(list2, CN.Visit_Source, startDate, endDate, Key.Num0, false, accurateToHour);

            RegLineChart lineChart0 = chartResp0.getLineChart();
            List<String[]> seriesDataMulti = lineChart0.getSeriesDataMulti();
            seriesDataMulti.add(lineChart0.getSeriesData());
            seriesDataMulti.add(chartResp1.getLineChart().getSeriesData());
            seriesDataMulti.add(chartResp2.getLineChart().getSeriesData());

            lineChart0.setSeriesData(new String[0]);
            lineChart0.setLegendData(new String[]{
                    EnJrtNfSm.ORGANIC.getName(), EnJrtNfSm.DIRECT.getName(), EnJrtNfSm.REFERRAL.getName()});
            return Response.ok().entity(chartResp0).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getSourceTypeTable(String platform, String startDate, String endDate,
            String orderType) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    "platform:", platform, ", startDate:", startDate, ", endDate:", endDate,
                    ", orderType:", orderType));

            JsonArray tableDataJArr = enNetFlowDao.getSourceTypeTableDataBy(platform, startDate, endDate,
                    Key.Pv, orderType);

            ChartResp chartResp = RespUtils.getSuccessChartResp();
            chartResp.setTotalSize(3);

            JsonObject resJObj = gson.toJsonTree(chartResp).getAsJsonObject();
            resJObj.add(Key.Table_Data, tableDataJArr);

            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getEntrancesPageTop10Table(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> grpByVos = enNetFlowDao.getEntrancesPageTop10By(platform, startDate, endDate, Top_Ele10);
            return Response.ok().entity(StringUtils.getUtf8Bytes(getResJobjFrom(grpByVos).toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    private JSONObject getResJobjFrom(List<GroupByVo> grpByVos) throws Exception {
        double allTotalCounts = getAllTotalCountsFrom(grpByVos);
        JSONArray resJarr = new JSONArray();

        if (grpByVos.size() > 0) {
            for (GroupByVo ele : grpByVos) {
                JSONObject cjobj = new JSONObject();
                cjobj.put(Key.NAME, ele.getType());
                cjobj.put(Key.VALUE, ele.getTotal());
                cjobj.put(Key.PERCENT, getDoublePercent(ele.getTotal() / allTotalCounts * 100));

                resJarr.put(cjobj);
            }
        }

        JSONObject resJObj = getRespResultJObj();
        resJObj.put(Key.DATA, resJarr);
        resJObj.put(Key.Now_Date, RegDateUtils.formatToNoHourF(new Date()));
        return resJObj;
    }


    @Override
    public Response getVisitedPageTop10Table(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> grpByVos = enNetFlowDao.getVisitedPageTop10By(platform, startDate, endDate, Top_Ele10);
            return Response.ok().entity(StringUtils.getUtf8Bytes(getResJobjFrom(grpByVos).toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getMediumTop10Table(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> grpByVos = enNetFlowDao.getMediumTop10By(platform, startDate, endDate, Top_Ele10);
            return Response.ok().entity(StringUtils.getUtf8Bytes(getResJobjFrom(grpByVos).toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


}

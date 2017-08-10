package org.jumao.bi.service.impl.enjrt;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.enjrt.EnJrtFinancingDao;
import org.jumao.bi.entites.jrt.financing.EnFiCompanyPo;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.enjrt.EnJrtFinancingSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.EnJrtFTType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 */
public class EnJrtFinancingSvcImpl extends ChartBasicService implements EnJrtFinancingSvc {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EnJrtFinancingDao enJrtFinancingDao;


    @Override
    public Response getTrendLineChart(String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    ", type:", typeStr));
            int type = RespUtils.checkEnumType(typeStr, EnJrtFTType.class);
            EnJrtFTType enJrtFTType = EnJrtFTType.getByType(type);

            List<TimeTotalVo> list = enJrtFinancingDao.getTrendLineChartBy(
                    null, null, null, false, type);

            String startDate = changeCrtTmFromSqlTo(list, true);
            String endDate = changeCrtTmFromSqlTo(list, false);

            ChartResp chartResp = getLineChartFrom(list, enJrtFTType.getName(), startDate, endDate, Key.Num0, true, false);

            //setLineChartUnit(chartResp, enJrtFTType.getUnit());
            return Response.ok().entity(chartResp).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getTrendAnalysisLineChart(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, EnJrtFTType.class);
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            List<TimeTotalVo> list = enJrtFinancingDao.getTrendLineChartBy(
                    platform, startDate, endDate, accurateToHour, type);
            return Response.ok().entity(
                    getLineChartFrom(list, CN.Financing_Trend, startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getGlobalAreaDist(String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    ", type:", typeStr));
            int type = RespUtils.checkEnumType(typeStr, EnJrtFTType.class);

            List<GroupByVo> list = enJrtFinancingDao.getGlobalAreaDistBy(null, null, type);
            Map<String, String> typeNameMap = getTypeNameMapFrom(list, false);
            replaceSomeCodeForGlobal(typeNameMap);

            return Response.ok().entity(getPieChartFromGroupByVo(list, typeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getGlobalAreaDistAnalysis(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, EnJrtFTType.class);

            List<GroupByVo> list = enJrtFinancingDao.getGlobalAreaDistBy(startDate, endDate, type);
            Map<String, String> typeNameMap = getTypeNameMapFrom(list, false);
            replaceSomeCodeForGlobal(typeNameMap);

            return Response.ok().entity(getPieChartFromGroupByVo(list, typeNameMap)).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }

    @Override
    public Response getAreaDetailTable(long areaId, String platform, String startDate, String endDate,
                                       int type, int limit, int offset) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    "areaId:", areaId, ", startDate:", startDate, ", endDate:", endDate,
                    ", type:", type, ", limit:", limit, ", offset:", offset));
            JsonArray tableDataJArr = null;
            long totalCounts = 0L;

            if (type == EnJrtFTType.RZSQ.getType()) {
                tableDataJArr = enJrtFinancingDao.getApplyDetail(areaId, startDate, endDate, limit, offset);
                totalCounts = enJrtFinancingDao.getApplyTotalCounts(areaId, startDate, endDate);

            } else if (type == EnJrtFTType.RZQYe.getType()) {
                List<EnFiCompanyPo> list = enJrtFinancingDao.getCompanyDetail(areaId, startDate, endDate, limit, offset);
                tableDataJArr = gson.toJsonTree(subList(list, limit, offset)).getAsJsonArray();
                totalCounts = list.size();
            } else {
                tableDataJArr = new JsonArray();
            }
            reformatCreateTime(tableDataJArr);

            ChartResp chartResp = RespUtils.getSuccessChartResp();
            chartResp.setTotalSize((int) totalCounts);

            JsonObject resJObj = gson.toJsonTree(chartResp).getAsJsonObject();
            resJObj.add(Key.Table_Data, tableDataJArr);

            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }


}

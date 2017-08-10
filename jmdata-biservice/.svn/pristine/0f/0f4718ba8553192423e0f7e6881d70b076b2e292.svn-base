package org.jumao.bi.service.impl.jrt;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.jrt.JrtFinancingDao;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jrt.JrtFinancingSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.JrtFTType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 */
public class JrtFinancingSvcImpl extends ChartBasicService implements JrtFinancingSvc {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JrtFinancingDao jrtFinancingDao;


    @Override
    public Response getTrendLineChart(String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    ", type:", typeStr));
            int type = RespUtils.checkEnumType(typeStr, JrtFTType.class);

            List<TimeTotalVo> list = jrtFinancingDao.getTrendLineChartBy(
                    null, null, null, false, type);

            String startDate = changeCrtTmFromSqlTo(list, true);
            String endDate = changeCrtTmFromSqlTo(list, false);

            return Response.ok().entity(
                    getLineChartFrom(list, CN.Financing_Trend, startDate, endDate, Key.Num0, true, false)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getTrendAnalysisLineChart(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, JrtFTType.class);
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            List<TimeTotalVo> list = jrtFinancingDao.getTrendLineChartBy(
                    platform, startDate, endDate, accurateToHour, type);
            return Response.ok().entity(
                    getLineChartFrom(list, CN.Financing_Trend, startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    @Override
    public Response getTrendDetailTable(int type, int limit, int offset) throws Exception {
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                    ", type:", type, ", limit:", limit, ", offset:", offset));
            JsonArray tableDataJArr = null;
            long totalCounts = 0L;

            if (type == JrtFTType.RZSQ.getType()) {
                tableDataJArr = jrtFinancingDao.getApplyDetail(limit, offset);
                totalCounts = jrtFinancingDao.getApplyTotalCounts();

            } else if (type == JrtFTType.RSXQ.getType()) {
                tableDataJArr = jrtFinancingDao.getRequiredDetail(limit, offset);
                totalCounts = jrtFinancingDao.getRequiredTotalCounts();

            } else if (type == JrtFTType.RZQYue.getType()) {
                tableDataJArr = jrtFinancingDao.getSignedDetail(limit, offset);
                totalCounts = jrtFinancingDao.getSignedTotalCounts();
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
            throw GeneralUtils.getWrapEx(e);
        }
    }



}

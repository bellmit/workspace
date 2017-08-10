package org.jumao.bi.service.impl.trade.register;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.DataSrcIncrTable;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.trade.register.RegisterAnalysis;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.AUOType;
import org.jumao.bi.utis.enums.VUOType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterAnalysisImpl extends ChartBasicService implements RegisterAnalysis {

    private Logger logger = Logger.getLogger(this.getClass());
    private Gson gson = new Gson();

    @Autowired
    private TradeRegisterDao tradeRegisterDao;


    /**
     */
    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = tradeRegisterDao.getNewlyIncrBy(
                    Table.Uc_Jumore_User, platform, startDate, endDate, accurateToHour);
            return Response.ok().entity(
                    getLineChartFrom(list, CN.Reg_Newlyincr, startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getMsNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        return getNewRegLineChart(platform, startDate, endDate);
    }

    /**
     */
    public Response getNewRegPieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = tradeRegisterDao.getDataSrcIncrBy(Table.Uc_Jumore_User, platform, startDate, endDate);
            return Response.ok().entity(getPieChartFromGroupByVo(list, dataSrcNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getMsNewRegPieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = tradeRegisterDao.getMsUserIncrPieBy(platform, startDate, endDate);
            return Response.ok().entity(getPieChartFromGroupByVo(list, PlatFormUtil.platformNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getNewRegTable(String platform, String startDate, String endDate,
                                   int limit, int offset) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<DataSrcIncrTable> list = tradeRegisterDao.getDataSrcIncrTableBy(
                    platform, startDate, endDate, accurateToHour, limit, offset);
            long totalUser = tradeRegisterDao.getTotalUserCounts(platform, startDate, endDate);

            return Response.ok().entity(getTableFromNewRegs(list, totalUser, limit, offset, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthUOverview(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, AUOType.class);
            List<TimeTotalVo> list = null;
            boolean formatToNh = true;
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            if (AUOType.PV.getType() == type) {
                list = tradeRegisterDao.getPvOrUvBy(platform, startDate, endDate, true);
                formatToNh = false;

            } else if (AUOType.UV.getType() == type) {
                list = tradeRegisterDao.getPvOrUvBy(platform, startDate, endDate, false);
                formatToNh = false;

            } else if (AUOType.REGISTER.getType() == type) {
                list = tradeRegisterDao.getNewlyIncrBy(Table.Uc_Jumore_User, platform, startDate, endDate, accurateToHour);

            } else if (AUOType.THREE_CERT_AUTH.getType() == type) {
                list = tradeRegisterDao.getCert3AuthBy(startDate, endDate, accurateToHour);

            } else if (AUOType.AUTH_LETTER.getType() == type) {
                list = tradeRegisterDao.getAuthLetterBy(startDate, endDate, accurateToHour);
            }
            return Response.ok().entity(getLineChartFrom(
                    list, AUOType.getNameByType(type), startDate, endDate, Key.Num0, formatToNh, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthUCert3History(String platform) throws Exception {
        try {
            RespUtils.checkPlatform(platform);
            LogUtils.writeLogs(logger, StringUtils.joinStr(
                    GeneralUtils.getCurrMethod(), ", platform:", platform));

            List<GroupByVo> list = tradeRegisterDao.getAuthUCert3HistoryBy(platform);
            return Response.ok().entity(getPieChartFromGroupByVo(list, compCategoryMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthUPassedHistogram(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = tradeRegisterDao.getCert3AuthBy(startDate, endDate, accurateToHour);
            return Response.ok().entity(getLineChartFrom(
                    list, CN.Auth_User_Passed, startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthULicPercentPie(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = tradeRegisterDao.getLicPercentPieBy(platform, startDate, endDate);
            return Response.ok().entity(getPieChartFromGroupByVo(list, licTpyeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthUAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> cert3List = tradeRegisterDao.getCert3AndAuthLetterAreaPieBy(startDate, endDate, 0);
            return Response.ok().entity(getPieChartFromGroupByVo(cert3List, getProvinceCodeNameMap(cert3List))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getAuthUAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = tradeRegisterDao.getCert3AndAuthLetterAreaPieBy(startDate, endDate, Top_Ele10);
            return Response.ok().entity(getBarChartFrom(
                    list, getProvinceCodeNameMap(list), CN.Auth_User_Area_Dist_Top10, true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getVisaAndPayOverview(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, VUOType.class);
            List<TimeTotalVo> list = null;
            boolean formatToNh = true;
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            if (VUOType.PV.getType() == type) {
                list = tradeRegisterDao.getPvOrUvBy(platform, startDate, endDate, true);
                formatToNh = false;

            } else if (VUOType.UV.getType() == type) {
                list = tradeRegisterDao.getPvOrUvBy(platform, startDate, endDate, false);
                formatToNh = false;

            } else if (VUOType.VISA.getType() == type) {
                list = tradeRegisterDao.getVisaTTVsBy(platform, startDate, endDate, accurateToHour);

            } else if (VUOType.PAY.getType() == type) {
                list = tradeRegisterDao.getPayTTVsBy(platform, startDate, endDate, accurateToHour);

            }
            return Response.ok().entity(
                    getLineChartFrom(list, VUOType.getNameByType(type), startDate, endDate, Key.Num0, formatToNh, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getVisaAndPayHistoryPie(String platform) throws Exception {
        try {
            RespUtils.checkPlatform(platform);
            LogUtils.writeLogs(logger, StringUtils.joinStr(
                    GeneralUtils.getCurrMethod(), ", platform:", platform));

            long visaHistoryTotal = tradeRegisterDao.getVisaHisTotal(platform);
            long payHistoryTotal = tradeRegisterDao.getPayHisTotal(platform);
            List<GroupByVo> payList = tradeRegisterDao.getVisaAndPayHistoryPieBy(platform);

            ChartResp cr = getPieChartFromGroupByVo(payList, getTypeNameMapFrom(payList, false));
            JsonObject obj = gson.toJsonTree(cr).getAsJsonObject();
            obj.addProperty(Key.VISA_HISTORY_COUNTS, visaHistoryTotal);
            obj.addProperty(Key.PAY_HISTORY_COUNTS, payHistoryTotal);
            return Response.ok().entity(StringUtils.getUtf8Bytes(obj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getVisaAndPayAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> visaList = tradeRegisterDao.getVisaOrPayAreaDistBy(platform, startDate, endDate, 0, true);
            List<GroupByVo> payList = tradeRegisterDao.getVisaOrPayAreaDistBy(platform, startDate, endDate, 0, false);

            ChartResp visaCp = getPieChartFromGroupByVo(visaList, getProvinceCodeNameMap(visaList));
            ChartResp payCp = getPieChartFromGroupByVo(payList, getProvinceCodeNameMap(payList));

            Map<String, ChartResp> legendCpMap = new HashMap<String, ChartResp>();
            legendCpMap.put(CN.Visa, visaCp);
            legendCpMap.put(CN.Pay, payCp);
            return Response.ok().entity(getPieForMultiDim(legendCpMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getVisaAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> visaList = tradeRegisterDao.getVisaOrPayAreaDistBy(platform, startDate, endDate, Top_Ele10, true);
            return Response.ok().entity(
                    getBarChartFrom(visaList, getProvinceCodeNameMap(visaList), CN.Visa, true))
                    .build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getPayAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> payList = tradeRegisterDao.getVisaOrPayAreaDistBy(platform, startDate, endDate, Top_Ele10, false);
            return Response.ok().entity(
                    getBarChartFrom(payList, getProvinceCodeNameMap(payList), CN.Pay, true))
                    .build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


}

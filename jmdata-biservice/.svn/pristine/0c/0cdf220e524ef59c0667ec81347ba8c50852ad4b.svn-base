package org.jumao.bi.service.impl.jyt.register;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.jyt.JytRegIncrAndAuthDao;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jyt.register.JytRegIncrAndAuthSvc;
import org.jumao.bi.service.jyt.register.JytRegisterBasicSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.JytAUOType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author chen qian
 */
public class JytRegIncrAndAuthSvcImpl extends ChartBasicService implements JytRegIncrAndAuthSvc {

    private Logger logger = Logger.getLogger(this.getClass());
    private Gson gson = new Gson();

    @Autowired
    private TradeRegisterDao tradeRegisterDao;

    @Autowired
    private JytRegIncrAndAuthDao jytRegIncrAndAuthDao;

    @Autowired
    private JytRegisterBasicSvc jytRegisterBasicSvc;

    /**
     */
    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        return jytRegisterBasicSvc.getNewRegLineChart(platform, startDate, endDate, true);
    }

    /**
     */
    public Response getRegAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getRegAreaDist(startDate, endDate, Top_Ele0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, getProvinceCodeNameMap(list))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getRegAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getRegAreaDist(startDate, endDate, Top_Ele10);
            return Response.ok().entity(getBarChartFrom(
                    list, getProvinceCodeNameMap(list), "", true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    /**
     */
    public Response getNewRegTop5(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getNewRegPieChartBy(startDate, endDate, Top_Ele5);
            return Response.ok().entity(getBarChartFrom(
                    list, PlatFormUtil.platformNameMap, CN.Jyt_New_Reg_Top5, true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    /**
     */
    public Response getNewRegPieChart(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getNewRegPieChartBy(startDate, endDate, Top_Ele0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, PlatFormUtil.platformNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    //---------------------------------------------
    //--------------  以下认证分析  ---------------
    //---------------------------------------------

    /**
     */
    public Response getAuthULineChart(String platform, String startDate, String endDate, String typeStr) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            int type = RespUtils.checkEnumType(typeStr, JytAUOType.class);
            List<TimeTotalVo> list = null;
            boolean accurateToHour = checkAccurateToHour(startDate, endDate);

            if (JytAUOType.SMRZ.getType() == type) {
                list = jytRegIncrAndAuthDao.getSmrzBy(startDate, endDate, accurateToHour);

            } else if (JytAUOType.WTFSZRZ.getType() == type) {
                list = jytRegIncrAndAuthDao.getWtfszrzBy(startDate, endDate, accurateToHour);

            } else if (JytAUOType.GYSSZRZ.getType() == type) {
                list = jytRegIncrAndAuthDao.getGysszrzBy(startDate, endDate, accurateToHour);

            } else if (JytAUOType.WTSSQ.getType() == type) {
                list = jytRegIncrAndAuthDao.getWtssqBy(startDate, endDate, accurateToHour);

            } else if (JytAUOType.GYSSQ.getType() == type) {
                list = jytRegIncrAndAuthDao.getGyssqBy(startDate, endDate, accurateToHour);
            }
            return Response.ok().entity(getLineChartFrom(
                    list, JytAUOType.getNameByType(type), startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getAuthUCert3History(String platform) throws Exception {
        try {
            RespUtils.checkPlatform(platform);
            LogUtils.writeLogs(logger, StringUtils.joinStr(
                    GeneralUtils.getCurrMethod(), ", platform:", platform));

            long entrustHistoryTotal = jytRegIncrAndAuthDao.getEntrustHisTotal();
            long supplyHistoryTotal = jytRegIncrAndAuthDao.getSupplyHisTotal();

            List<GroupByVo> payList = jytRegIncrAndAuthDao.getEntrustAndSupplyHistoryPieBy();

            ChartResp cr = getPieChartFromGroupByVo(payList, getTypeNameMapFrom(payList, false));
            JsonObject obj = gson.toJsonTree(cr).getAsJsonObject();
            obj.addProperty(Key.Entrust_History_Counts, entrustHistoryTotal);
            obj.addProperty(Key.Supply_History_Counts, supplyHistoryTotal);
            return Response.ok().entity(StringUtils.getUtf8Bytes(obj.toString())).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getSupplyAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getSupplyAreaDistBy(startDate, endDate, Top_Ele0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, getProvinceCodeNameMap(list))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getSupplyAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getSupplyAreaDistBy(startDate, endDate, Top_Ele10);
            return Response.ok().entity(getBarChartFrom(
                    list, getProvinceCodeNameMap(list), "", true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getEntrustAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getEntrustAreaDistBy(startDate, endDate, Top_Ele0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, getProvinceCodeNameMap(list))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getEntrustAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getEntrustAreaDistBy(startDate, endDate, Top_Ele10);
            return Response.ok().entity(getBarChartFrom(
                    list, getProvinceCodeNameMap(list), "", true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getDriverAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getDriverAreaDistBy(startDate, endDate, Top_Ele0);
            return Response.ok().entity(getPieChartFromGroupByVo(list, getProvinceCodeNameMap(list))).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    public Response getDriverAreaDistTop10(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytRegIncrAndAuthDao.getDriverAreaDistBy(startDate, endDate, Top_Ele10);
            return Response.ok().entity(getBarChartFrom(
                    list, getProvinceCodeNameMap(list), "", true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


}


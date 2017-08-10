package org.jumao.bi.service.jyt.overview;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.dao.jyt.JytOverviewDao;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.JytEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 */
@Repository(JytOverviewBasicSvc.JYT_OVERVIEW_BASIC_SVC)
public class JytOverviewBasicSvc extends ChartBasicService {

    public static final String JYT_OVERVIEW_BASIC_SVC = "jytOverviewBasicSvc";


    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JytOverviewDao jytOverviewDao;


    /**
     * @param isCn  是否中文版
     */
    public Response getNumbersConfigable(String itemIds, String platform,
                                         String startDate, String endDate, boolean isCn) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));
            String[] idsArr = itemIds.split(Key.HYPHEN);
            JSONArray resJArr = new JSONArray();

            for (String ele : idsArr) {
                if (NumberUtils.isDigits(ele)) {
                    int itemId = Integer.parseInt(ele);
                    JytEnum jytEnum = JytEnum.getByType(itemId);

                    JSONObject retJObj = new JSONObject();
                    retJObj.put(Key.ID, itemId);
                    retJObj.put(Key.NAME, jytEnum.getCnName());
                    retJObj.put(Key.UNIT, jytEnum.getUnit());

                    long num = jytOverviewDao.getNumbersBy(itemId, jytEnum, isCn);
                    int divide = GeneralUtils.getDenominatorForUnit(jytEnum.getUnit());
                    retJObj.put(Key.VALUE, numDivideSth(num, divide));
                    resJArr.put(retJObj);
                }
            }

            JSONObject resJObj = getRespResultJObj();
            resJObj.put(Key.DATA, resJArr);
            resJObj.put(Key.Now_Date, RegDateUtils.formatToNoHourF(new Date()));
            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }


    public Response getLineChartByItemId(String itemId, String platform, String startDate, String endDate, boolean isCn) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = jytOverviewDao.getLineChartByItemId(itemId, startDate, endDate, accurateToHour, isCn);

            JytEnum jytEnum = JytEnum.getByType(Integer.parseInt(itemId));
            int divideNum = GeneralUtils.getDenominatorForUnit(jytEnum.getUnit());

            ChartResp chartResp = getLineChartFrom(list, jytEnum.getCnName(), startDate, endDate, divideNum, true, accurateToHour);
            chartResp.getLineChart().setUnit(jytEnum.getUnit());
            return Response.ok().entity(chartResp).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }


    public Response getTransportLinePieChart(String platform, String startDate, String endDate, boolean isCn) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytOverviewDao.getTransportLineBy(startDate, endDate, isCn);
            return Response.ok().entity(
                    getPieChartFromGroupByVo(
                    list,
                    isCn ? lineTypeNameMap : enLineTypeNameMap
                    )
            ).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


    public Response getRequireTop5BarChart(String platform, String startDate, String endDate, boolean isCn) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> list = jytOverviewDao.getRequireTop5BarChartBy(startDate, endDate, Top_Ele5, isCn);
            return Response.ok().entity(getBarChartFrom(
                    list, getTypeNameMapFrom(list, true), CN.Require_Top5, true)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }


}

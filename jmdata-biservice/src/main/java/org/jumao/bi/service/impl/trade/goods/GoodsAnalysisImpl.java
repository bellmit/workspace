package org.jumao.bi.service.impl.trade.goods;


import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.trade.goods.GoodsAnalysis;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RespUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsAnalysisImpl extends ChartBasicService implements GoodsAnalysis {

    private Logger logger = Logger.getLogger(this.getClass());


    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

        boolean accurateToHour = checkAccurateToHour(startDate, endDate);
        List<TimeTotalVo> list = goodsDao.getNewlyIncrBy(platform, startDate, endDate, accurateToHour);
        return Response.ok().entity(getLineChartFrom(list, CN.Goods_Newlyincr, startDate, endDate, Key.Num0, true, accurateToHour)).build();
    }


    public Response getIndustryPieChart(String platform, String startDate, String endDate) throws Exception {
        LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

        List<GroupByVo> list = goodsDao.getIndustryPieBy(platform, startDate, endDate);
        return Response.ok().entity(getPieChartFromGroupByVo(list, industryIdNameMap)).build();
    }


    public Response getCategoryPieChart(String industryId, String platform, String startDate, String endDate) throws Exception {
        RespUtils.checkIndustryId(industryId);
        LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

        List<GroupByVo> list = goodsDao.getCategoryPieBy(industryId, platform, startDate, endDate);
        List<GroupByVo> cateIdNameVos = goodsDao.getCateIdNameVosBy(industryId);
        return Response.ok().entity(getPieChartFromGroupByVo(list, getTypeNameMapFrom(cateIdNameVos, false))).build();
    }


    //这里的 industry 或 indus 全都是 行业中文名
    public Response getIndustryDiff(String platform, String startDate, String endDate) throws Exception {
        try {
            Map<String, Double> indusAvgMoneyMap = getIndusAvgMoneyMap(startDate, endDate);
            Map<String, Long> indusPvMap = new HashMap<String, Long>();
            Map<String, Set<String>> indusLoginCompIdsMap = getIndustryLoginCompIdsMap(startDate, endDate, indusPvMap);
            Map<String, Long> indusOrderCountsMap = getIndustryOrderCountsMap(indusLoginCompIdsMap, startDate, endDate);

            JSONArray resJArr = getResJArr(indusAvgMoneyMap, indusLoginCompIdsMap, indusOrderCountsMap, indusPvMap);
            JSONObject resJObj = getRespResultJObj();
            resJObj.put(Key.DATA, resJArr);

            return Response.ok().entity(StringUtils.getUtf8Bytes(resJObj.toString())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

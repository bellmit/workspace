package org.jumao.bi.service.impl.jyt.overview;


import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jumao.bi.dao.jyt.JytOverviewDao;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jyt.overview.JytOverviewAnalysis;
import org.jumao.bi.service.jyt.overview.JytOverviewBasicSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.JytEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;


/**
 * @author chen qian
 */
public class JytOverviewAnalysisImpl implements JytOverviewAnalysis {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JytOverviewBasicSvc jytOverviewBasicSvc;


    @Override
    public Response getNumbersConfigable(String itemIds, String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getNumbersConfigable(itemIds, platform, startDate, endDate, true);
    }

    @Override
    public Response getLineChartByItemId(String itemId, String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getLineChartByItemId(itemId, platform, startDate, endDate, true);
    }

    @Override
    public Response getTransportLinePieChart(String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getTransportLinePieChart(platform, startDate, endDate, true);
    }

    @Override
    public Response getRequireTop5BarChart(String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getRequireTop5BarChart(platform, startDate, endDate, true);
    }


}

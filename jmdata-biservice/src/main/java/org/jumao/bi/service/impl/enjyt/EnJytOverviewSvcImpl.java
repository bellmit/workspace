package org.jumao.bi.service.impl.enjyt;

import org.apache.log4j.Logger;
import org.jumao.bi.service.enjyt.EnJytOverviewSvc;
import org.jumao.bi.service.jyt.overview.JytOverviewBasicSvc;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.JytEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 */
public class EnJytOverviewSvcImpl implements EnJytOverviewSvc {


    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JytOverviewBasicSvc jytOverviewBasicSvc;


    @Override
    public Response getNumbersConfigable(String platform, String startDate, String endDate) throws Exception {
        String itemIds = StringUtils.joinStr(
                JytEnum.GYSQYS.getType(), Key.HYPHEN,
                JytEnum.WTFQYS.getType(), Key.HYPHEN,
                JytEnum.CKS.getType(), Key.HYPHEN,
                JytEnum.XLS.getType());
        return jytOverviewBasicSvc.getNumbersConfigable(itemIds, platform, startDate, endDate, false);
    }

    @Override
    public Response getLineChartByItemId(String itemId, String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getLineChartByItemId(itemId, platform, startDate, endDate, false);
    }

    @Override
    public Response getTransportLinePieChart(String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getTransportLinePieChart(platform, startDate, endDate, false);
    }

    @Override
    public Response getRequireTop5BarChart(String platform, String startDate, String endDate) throws Exception {
        return jytOverviewBasicSvc.getRequireTop5BarChart(platform, startDate, endDate, false);
    }

}

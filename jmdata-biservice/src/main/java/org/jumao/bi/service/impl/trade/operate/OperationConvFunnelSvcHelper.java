package org.jumao.bi.service.impl.trade.operate;

import java.util.ArrayList;
import java.util.List;

import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.FunnelChart;
import org.jumao.bi.entites.operate.ConvFunnelInfo;
import org.jumao.bi.entites.operate.ConvFunnelResponse;

public class OperationConvFunnelSvcHelper {
	/**
	 * 构造漏斗图
	 * @param convFunnelInfos
	 * @param response
	 */
    public static void buildFunnelChart(List<ConvFunnelInfo> convFunnelInfos, ConvFunnelResponse response) {
        FunnelChart funnelChart = new FunnelChart();
        List<CommonBean> seriesData = new ArrayList<CommonBean>();
        String[] legendData = new String[convFunnelInfos.size()];
        int i = 0;
        for (ConvFunnelInfo convFunnelInfo : convFunnelInfos) {

            String cateName = convFunnelInfo.getItemName();
            Integer cateCnt = convFunnelInfo.getItemValue();
            CommonBean commonBean = new CommonBean(cateName, cateCnt.toString());
            seriesData.add(commonBean);
            legendData[i] = cateName;
            i++;
        }
        

        funnelChart.setSeriesData(seriesData);
        funnelChart.setLegendData(legendData);
        response.setFunnelChart(funnelChart);
    }

}

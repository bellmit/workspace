package org.jumao.bi.service.impl.trade.operate;

import java.util.ArrayList;
import java.util.List;

import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.entites.charts.PieChart;
import org.jumao.bi.entites.operate.GoodsCateResponse;
import org.jumao.bi.entites.operate.GoodsInfo;

public class OperationGoodsSvcHelper {
	/**
	 * 构造商品饼图
	 * @param goodsInfos
	 * @param response
	 */
    public static void buildGoodsPieChart(List<GoodsInfo> goodsInfos, GoodsCateResponse response) {
        PieChart pieChart = new PieChart();
        List<CommonBean> seriesData = new ArrayList<CommonBean>();
        String[] legendData = new String[goodsInfos.size()];
        int i = 0;
        for (GoodsInfo goodsinfo : goodsInfos) {

            String cateName = goodsinfo.getGoodsCateName();
            Integer cateCnt = goodsinfo.getGoodsCateCnt();
            CommonBean commonBean = new CommonBean(cateName, cateCnt.toString());
            seriesData.add(commonBean);
            legendData[i] = cateName;
            i++;
        }
        

        pieChart.setSeriesFromCb(seriesData);
        pieChart.setLegendData(legendData);
        response.setPieChart(pieChart);
    }

}

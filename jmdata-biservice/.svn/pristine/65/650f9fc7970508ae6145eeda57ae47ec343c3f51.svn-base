package org.jumao.bi.component;

import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.charts.NewPieChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

@Component
public class PieChartBuilder extends BaseChartBuilder {

	@Override
	public  void buildData(List<BaseInfo> baseInfos, Map responseMap, ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		NewPieChart pieChart = SpringContextUtil.getBean(NewPieChart.class);
		Class seriesTypeClass = context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		pieChart.setSeriesData(getSeriesData(baseInfos,seriesTypeClass));
        pieChart.setLegendData(getLegendData(baseInfos));
        //response.setPieChart(pieChart);
		responseMap.put("pieChart",pieChart);
	}

}

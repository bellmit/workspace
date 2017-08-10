package org.jumao.bi.component.builder;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.NewPieChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class PieCBD extends AbstractChartBuilder {

	@Override
	public  void buildData(List<Map<String, Object>> baseInfos, Map responseMap, ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		/*NewPieChart pieChart = SpringContextUtil.getBean(NewPieChart.class);
		Class seriesTypeClass = context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		pieChart.setSeriesFromCb(getSeriesCb(baseInfos,seriesTypeClass));
        pieChart.setLegendData(getLegendData(baseInfos));*/
        //response.setPieChart(pieChart);
		Map<String,Object> pieChart = new LinkedHashMap();
		Class seriesTypeClass = context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		pieChart.put("seriesData",getSeriesData(baseInfos,seriesTypeClass));
		pieChart.put("legendData",getItemNameData(baseInfos));
		responseMap.put("pieChart",pieChart);
	}

}

package org.jumao.bi.component.builder;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.NewFunnelChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class FunnelCBD extends AbstractChartBuilder{

	@Override
	public void buildData(List<Map<String, Object>> baseInfos, Map responseMap,
			ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		/*NewFunnelChart funnelChart = SpringContextUtil.getBean(NewFunnelChart.class);
		Class seriesTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		funnelChart.setSeriesFromCb(getSeriesCb(baseInfos,seriesTypeClass));
		funnelChart.setLegendData(getLegendData(baseInfos));*/
        //response.setFunnelChart(funnelChart);
		Map<String,Object> funnelChart = new LinkedHashMap();
		Class seriesTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		funnelChart.put("seriesData",getSeriesData(baseInfos,seriesTypeClass));
		funnelChart.put("legendData",getItemNameData(baseInfos));
		responseMap.put("funnelChart",funnelChart);
	}
	
}

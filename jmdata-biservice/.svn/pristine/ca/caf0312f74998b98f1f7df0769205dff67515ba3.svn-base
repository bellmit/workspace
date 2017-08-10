package org.jumao.bi.component;

import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.charts.NewFunnelChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;
@Component
public class FunnelChartBuilder extends BaseChartBuilder{
	
	@Override
	public void buildData(List<BaseInfo> baseInfos, Map responseMap,
			ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		NewFunnelChart funnelChart = SpringContextUtil.getBean(NewFunnelChart.class);
		Class seriesTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Series_Type);
		funnelChart.setSeriesData(getSeriesData(baseInfos,seriesTypeClass));
		funnelChart.setLegendData(getLegendData(baseInfos));
        //response.setFunnelChart(funnelChart);
		responseMap.put("funnelChart",funnelChart);
	}
	
}

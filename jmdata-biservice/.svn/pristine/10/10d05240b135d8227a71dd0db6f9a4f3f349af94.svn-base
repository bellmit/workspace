package org.jumao.bi.component.builder;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.DoubleYAxisLineChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoubleYLineCBD extends AbstractChartBuilder {

	@Override
	public void buildData(List<Map<String, Object>> baseInfos, Map responseMap,
			ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		/*DoubleYAxisLineChart lineChart = SpringContextUtil.getBean(DoubleYAxisLineChart.class);
		//lineChart.setLegendData(new String[]{"数量","金额"});
		lineChart.setLegendData((String[])context.getUntilMap().get(ParamConst.Legend_Name));
		lineChart.setSeriesFromCb(getDoubleSeriesData(baseInfos));
        lineChart.setxAxisData(getLegendData(baseInfos));*/
        
        //response.setLineChart(lineChart);
		Map<String,Object> lineChart = new LinkedHashMap();
		lineChart.put("legendData",(String[])context.getUntilMap().get(ParamConst.Legend_Name));
		lineChart.put("seriesData",getDoubleSeriesData(baseInfos));
		lineChart.put("xAxisData",getItemNameData(baseInfos));
		responseMap.put("lineChart",lineChart);
	}
	
	/**
	 * 获取双Y轴数据
	 * @param baseInfos
	 * @return
	 * @throws Exception
	 */
	public Map<String,List> getDoubleSeriesData(List<Map<String, Object>> baseInfos) throws Exception{
		int size = baseInfos.size();
        List<Integer> seriesLeftData = new ArrayList<Integer>(Collections.nCopies(size, 0));
        List<BigDecimal> seriesRightData = new ArrayList<BigDecimal>(Collections.nCopies(size, new BigDecimal(0)));
        for (int i = 0; i < baseInfos.size(); i++) {
    		seriesLeftData.set(i, Integer.valueOf(baseInfos.get(i).get(ParamConst.Col_Name_Item_Lvalue).toString()));
    		seriesRightData.set(i, new BigDecimal(baseInfos.get(i).get(ParamConst.Col_Name_Item_Rvalue).toString()));
        }
        Map<String,List> data = new HashMap<String,List>();
        data.put("left", seriesLeftData);
        data.put("right", seriesRightData);
        return data;
	}

}

package org.jumao.bi.component.builder;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.LinkageBarChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BarCBD extends AbstractChartBuilder {

	@Override
	public  void buildData(List<Map<String, Object>> baseInfos, Map responseMap, ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		/*LinkageBarChart barChart = SpringContextUtil.getBean(LinkageBarChart.class);
		barChart.setSeriesName(getSeriesName(context));
		barChart.setSeriesFromCb(getSeriesDataformBigDecimal(baseInfos));
		barChart.setyAxisData(getyAxisData(baseInfos));
		Class itemIdTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Itemid_Type);
		barChart.setItemId(getItemId(baseInfos,itemIdTypeClass));*/
        //response.setBarChart(barChart);
		Map<String,Object> barChart = new LinkedHashMap();
		barChart.put("seriesData",getSeriesDataformBigDecimal(baseInfos));
		barChart.put("seriesName",getSeriesName(context));
		barChart.put("yAxisData",getyAxisData(baseInfos));
		Class itemIdTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Itemid_Type);
		barChart.put("itemId",(getItemId(baseInfos,itemIdTypeClass)));
		responseMap.put("barChart",barChart);
	}
	
	private String getSeriesName(ComponentContext context) throws Exception{
		String seriesName = context.getUntilMap().get(ParamConst.Series_Name).toString();
		return seriesName;
	}
	/**
	 * 获取itemId列表
	 * @param baseInfos
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  List<?> getItemId(List<Map<String,Object>> baseInfos,Class<?> clazz) throws Exception{
		if (clazz==String.class){
        	List<String> itemIdList = new ArrayList<String>();
			for (Map baseInfo : baseInfos) {
				//String itemId =  baseInfo.getItemId().toString();
				String itemId = baseInfo.get(ParamConst.Col_Name_Item_Id).toString();
	        	itemIdList.add(itemId);
	        }
			return itemIdList;
		}else{
			List<Integer> itemIdList = new ArrayList<Integer>();
			for (Map baseInfo : baseInfos) {
				//Integer itemId =  Integer.valueOf(baseInfo.getItemId().toString());
				Integer itemId =  Integer.valueOf(baseInfo.get(ParamConst.Col_Name_Item_Id).toString());
	        	itemIdList.add(itemId);
	        }
        	return itemIdList;
        }
	}
	/**
	 * 获取y轴数据
	 * @param baseInfos
	 * @return
	 * @throws Exception
	 */
	public  String[] getyAxisData(List<Map<String,Object>> baseInfos) throws Exception{
		int size = baseInfos.size();
		String[] axisData = new String[size];
		for (int i=0;i<size;i++) {
			Map<String,Object> baseInfo = baseInfos.get(i);
            //String itemName = baseInfo.getItemName();
			String itemName = baseInfo.get(ParamConst.Col_Name_Item_Name).toString();
            axisData[i] = itemName;
        }
		return axisData;
	}
	/**
	 * 获取SeriesData以bigDecimal类型返回
	 * @param baseInfos
	 * @return
	 * @throws Exception
	 */
	public  BigDecimal[] getSeriesDataformBigDecimal(List<Map<String,Object>> baseInfos) throws Exception {
		// TODO Auto-generated method stub
		int size = baseInfos.size();
		BigDecimal[] seriesData = new BigDecimal[size];
		for (int i=0;i<size;i++) {
			Map<String,Object> baseInfo = baseInfos.get(i);
            // seriesData[i] = new BigDecimal(baseInfo.getItemValue().toString());
			seriesData[i] = new BigDecimal(baseInfo.get(ParamConst.Col_Name_Item_Value).toString());
        }
		return seriesData;
	}
	
}

package org.jumao.bi.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.charts.LinkageBarChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

@Component
public class BarChartBuilder extends BaseChartBuilder {

	@Override
	public  void buildData(List<BaseInfo> baseInfos, Map responseMap, ComponentContext context) throws Exception {
		// TODO Auto-generated method stub
		LinkageBarChart barChart = SpringContextUtil.getBean(LinkageBarChart.class);
		barChart.setSeriesName(getSeriesName(context));
		barChart.setSeriesData(getSeriesDataformBigDecimal(baseInfos));
		barChart.setyAxisData(getyAxisData(baseInfos));
		Class itemIdTypeClass = (Class)context.getReturnTypeMap().get(ParamConst.Return_Itemid_Type);
		barChart.setItemId(getItemId(baseInfos,itemIdTypeClass));
        //response.setBarChart(barChart);
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
	public  List<?> getItemId(List<BaseInfo> baseInfos,Class<?> clazz) throws Exception{
		if (clazz==String.class){
        	List<String> itemIdList = new ArrayList<String>();
			for (BaseInfo baseInfo : baseInfos) {
				String itemId =  baseInfo.getItemId().toString();
	        	itemIdList.add(itemId);
	        }
			return itemIdList;
		}else{
			List<Integer> itemIdList = new ArrayList<Integer>();
			for (BaseInfo baseInfo : baseInfos) {
				Integer itemId =  Integer.valueOf(baseInfo.getItemId().toString());
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
	public  String[] getyAxisData(List<BaseInfo> baseInfos) throws Exception{
		int size = baseInfos.size();
		String[] axisData = new String[size];
		for (int i=0;i<size;i++) {
			BaseInfo baseInfo = baseInfos.get(i);
            String itemName = baseInfo.getItemName();
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
	public  BigDecimal[] getSeriesDataformBigDecimal(List<BaseInfo> baseInfos) throws Exception {
		// TODO Auto-generated method stub
		int size = baseInfos.size();
		BigDecimal[] seriesData = new BigDecimal[size];
		for (int i=0;i<size;i++) {
			BaseInfo baseInfo = baseInfos.get(i);
            seriesData[i] = new BigDecimal(baseInfo.getItemValue().toString());
        }
		return seriesData;
	}
	
}

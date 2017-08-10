package org.jumao.bi.component;

import org.jumao.bi.entites.charts.CommonTBean;
import org.jumao.bi.utis.GeneralUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseChartBuilder {
	
	public abstract void buildData(List<BaseInfo> baseInfos,Map responseMap,ComponentContext context) throws Exception;

	/**
	 * 获取seriesData数据
	 * @param baseInfos
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  List<CommonTBean<?>> getSeriesData(List<BaseInfo> baseInfos,Class<?> clazz) throws Exception{
		List<CommonTBean<?>> seriesData = new ArrayList<CommonTBean<?>>();
		for (BaseInfo baseInfo : baseInfos) {
            String itemName = baseInfo.getItemName();
            CommonTBean<?> commonTBean;
            if (clazz==BigDecimal.class){
            	BigDecimal itemValue =  new BigDecimal(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<BigDecimal>(itemName, itemValue);
            }else if (clazz==Long.class){
            	Integer itemValue =  Integer.valueOf(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<Integer>(itemName, itemValue);
            }else if (clazz==Double.class){
            	Double itemValue =  Double.valueOf(baseInfo.getItemValue().toString());
                commonTBean = new CommonTBean<Double>(itemName, itemValue);
            }else{
            	String itemValue =  baseInfo.getItemValue().toString();
                commonTBean = new CommonTBean<String>(itemName, itemValue);
            }
            
            seriesData.add(commonTBean);
        }
		return seriesData;
	}

	/**
	 * 组装表格形式的数据
	 * 需要传入返回的列名和默认列名的映射
	 * @param baseInfos
	 * @param colNameMap {"分组名":itemName,"值1":itemValue1,"值2":itemValue2,...}
	 * @return [{colName1:colValue1,colName2:colValue2,...},{},...]
	 * @throws Exception
	 */
	public List<Map<String,String>> getSeriesData(List<BaseInfo> baseInfos, Map<String,String> colNameMap) throws Exception{
		List data = new ArrayList<Map<String,String>>();
		for (BaseInfo baseInfo:baseInfos){
			Map<String,String> colMap = new LinkedHashMap();
			Class clazz = baseInfo.getClass();
			for (Map.Entry<String, String> entry : colNameMap.entrySet()){
				String property = entry.getKey();
				Method method = clazz.getDeclaredMethod(GeneralUtils.getGetterMethodName(property));
				colMap.put(entry.getValue(),(String)method.invoke(baseInfo));
			}
			data.add(colMap);
		}
		return data;
	}

	/**
	 * 获取图例legendData数据
	 * @param baseInfos
	 * @return
	 * @throws Exception
	 */
	public  String[] getLegendData(List<BaseInfo> baseInfos) throws Exception{
		String[] legendData = new String[baseInfos.size()];
		int i = 0;
        for (BaseInfo baseInfo : baseInfos) {
            String itemName = baseInfo.getItemName();
            legendData[i] = itemName;
            i++;
        }
		return legendData;
	}
}

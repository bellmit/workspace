package org.jumao.bi.component.builder;

import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础图形构建类，不同于BaseChartBuilder，不依赖于具体对象
 * Created by Administrator on 2017/7/7.
 */
public abstract class AbstractChartBuilder {
    public abstract void buildData(List<Map<String,Object>> baseInfos,Map responseMap,ComponentContext context) throws Exception;

    /**
     * 组装表格形式的数据
     * 需要传入返回的列名和默认列名的映射
     * @param baseInfos
     * @param colNameMap {"分组名":itemName,"值1":itemValue1,"值2":itemValue2,...}
     * @return [{colName1:colValue1,colName2:colValue2,...},{},...]
     * @throws Exception
     */
    public List<Map<String,Object>> getSeriesData(List<Map<String,Object>> baseInfos, Map<String,String> colNameMap) throws Exception{
        if (colNameMap.size()>0){
            List data = new ArrayList<Map<String,?>>();
            for (Map<String,?> baseInfo:baseInfos){
                Map<String,Object> colMap = new LinkedHashMap();
                for (Map.Entry<String, String> entryCol : colNameMap.entrySet()){
                    for (Map.Entry<String, ?> entryInfo : baseInfo.entrySet()){
                        if (entryInfo.getKey().equalsIgnoreCase(entryCol.getKey())){
                            colMap.put(entryCol.getValue(),entryInfo.getValue());
                        }
                    }
                }
                data.add(colMap);
            }
            return data;
        }else{
            return baseInfos;
        }

    }

    /**
     * 普通键值对形式的数据，可指定value的类型
     * @param baseInfos
     * @param clazz
     * @return
     * @throws Exception
     */
	public  List<Map<String,Object>> getSeriesData(List<Map<String,Object>> baseInfos,Class<?> clazz) throws Exception{
		List<Map<String,Object>> seriesData = new ArrayList();
		for (Map baseInfo : baseInfos) {
			String itemName = baseInfo.get(ParamConst.Col_Name_Item_Name).toString();
			Object itemValue = null;
			Map<String,Object> commonTBean = new LinkedHashMap<>();
			if (clazz==BigDecimal.class){
				itemValue =  new BigDecimal(baseInfo.get(ParamConst.Col_Name_Item_Value).toString());
			}else if (clazz==Long.class){
				itemValue =  Integer.valueOf(baseInfo.get(ParamConst.Col_Name_Item_Value).toString());
			}else if (clazz==Double.class){
				itemValue =  Double.valueOf(baseInfo.get(ParamConst.Col_Name_Item_Value).toString());
			}else{
				itemValue =  baseInfo.get(ParamConst.Col_Name_Item_Value).toString();
			}
			commonTBean.put("name",itemName);
			commonTBean.put("value",itemValue);

			seriesData.add(commonTBean);
		}
		return seriesData;
	}

    /**
     * 获取分组名
     * @param baseInfos
     * @return
     * @throws Exception
     */
    public  String[] getItemNameData(List<Map<String,Object>> baseInfos) throws Exception{
        String[] legendData = new String[baseInfos.size()];
        int i = 0;
        for (Map<String,Object> baseInfo : baseInfos) {
            String itemName = baseInfo.get(ParamConst.Col_Name_Item_Name).toString();
            legendData[i] = itemName;
            i++;
        }
        return legendData;
    }

    /**
     * 获取分组值，和上一个方法形成key,value
     * @param baseInfos
     * @return
     * @throws Exception
     */
    public Object[] getItemValueData(List<Map<String,Object>> baseInfos) throws Exception{
        Object[] itemValueData = new Object[baseInfos.size()];
        int i = 0;
        for (Map<String,Object> baseInfo : baseInfos) {
            Object itemValue = baseInfo.get(ParamConst.Col_Name_Item_Value);
            itemValueData[i] = itemValue;
            i++;
        }
        return itemValueData;
    }

}

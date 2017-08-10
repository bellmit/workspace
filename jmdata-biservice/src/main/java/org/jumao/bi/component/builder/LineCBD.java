package org.jumao.bi.component.builder;

import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
@Component
public class LineCBD extends AbstractChartBuilder {
    @Override
    public void buildData(List<Map<String, Object>> baseInfos, Map responseMap, ComponentContext context) throws Exception {
        Map<String,Object> lineChart = new LinkedHashMap();

        String legendData=context.getUntilMap().get(ParamConst.Legend_Name).toString();
        String unit = context.getUntilMap().get(ParamConst.Unit_Name).toString();
        lineChart.put("legendData",legendData);
        lineChart.put("title",legendData);
        lineChart.put("xAxisData",getItemNameData(baseInfos));
        lineChart.put("seriesData",getItemValueData(baseInfos));
        lineChart.put("unit",unit);
        responseMap.put("lineChart",lineChart);
    }
}

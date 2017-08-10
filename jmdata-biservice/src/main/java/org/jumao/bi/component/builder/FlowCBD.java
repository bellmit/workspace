package org.jumao.bi.component.builder;

import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseInfo;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.FlowChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class FlowCBD extends AbstractChartBuilder {
    @Override
    public void buildData(List<Map<String, Object>> baseInfos, Map responseMap, ComponentContext context) throws Exception {
        /*FlowChart flowChart = SpringContextUtil.getBean(FlowChart.class);
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        flowChart.setSeriesFromCb(getSeriesCb(baseInfos,colNameMap));*/
        //response.setFlowChart(flowChart);
        Map<String,Object> flowChart = new LinkedHashMap();
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        flowChart.put("seriesData",getSeriesData(baseInfos,colNameMap));
        responseMap.put("flowChart",flowChart);
    }
}

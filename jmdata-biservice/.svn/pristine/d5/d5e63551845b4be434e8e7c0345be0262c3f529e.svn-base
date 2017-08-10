package org.jumao.bi.component;

import org.jumao.bi.entites.charts.FlowChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class FlowChartBuilder extends BaseChartBuilder {
    @Override
    public void buildData(List<BaseInfo> baseInfos, Map responseMap, ComponentContext context) throws Exception {
        FlowChart flowChart = SpringContextUtil.getBean(FlowChart.class);
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        flowChart.setSeriesData(getSeriesData(baseInfos,colNameMap));
        //response.setFlowChart(flowChart);
        responseMap.put("flowChart",flowChart);
    }
}

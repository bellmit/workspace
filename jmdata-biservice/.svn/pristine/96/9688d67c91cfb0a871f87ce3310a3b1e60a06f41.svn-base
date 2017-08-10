package org.jumao.bi.component;

import org.jumao.bi.entites.charts.CommonTableChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class TableChartBuilder extends BaseChartBuilder {
    @Override
    public void buildData(List<BaseInfo> baseInfos, Map responseMap, ComponentContext context) throws Exception {
        CommonTableChart tableChart = SpringContextUtil.getBean(CommonTableChart.class);
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        tableChart.setSeriesData(getSeriesData(baseInfos,colNameMap));
        //response.setTableChart(tableChart);
        responseMap.put("tableChart",tableChart);
    }
}

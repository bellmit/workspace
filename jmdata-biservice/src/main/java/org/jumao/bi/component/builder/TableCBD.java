package org.jumao.bi.component.builder;

import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.entites.charts.NewTableChart;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */
@Component
public class TableCBD extends AbstractChartBuilder {
    @Override
    public void buildData(List<Map<String, Object>> baseInfos, Map responseMap, ComponentContext context) throws Exception {
        /*NewTableChart tableChart = SpringContextUtil.getBean(NewTableChart.class);
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        tableChart.setSeriesFromCb(getSeriesCb(baseInfos,colNameMap));*/
        Map<String,Object> tableChart = new LinkedHashMap();
        Map<String,String> colNameMap = (Map<String, String>) context.getUntilNestedMap().get(ParamConst.Col_Name_Map);
        tableChart.put("seriesData",getSeriesData(baseInfos,colNameMap));
        responseMap.put("tableChart",tableChart);
    }
}

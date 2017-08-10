package org.jumao.bi.entites.charts;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 流向图
 * Created by Administrator on 2017/6/19.
 */
@Component
@Scope("prototype")
public class FlowChart {
    private static final long serialVersionUID = 4335236181123447736L;
    private List<Map<String,String>> seriesData;

    public List<Map<String,String>> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Map<String,String>> seriesData) {
        this.seriesData = seriesData;
    }
}

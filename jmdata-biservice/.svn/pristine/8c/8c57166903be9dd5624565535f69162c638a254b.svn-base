package org.jumao.bi.entites.charts;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 通用表格组件，格式为{"col1":list1,"col2":list2}
 * Created by Administrator on 2017/6/15.
 */
@Component
@Scope("prototype")
public class NewTableChart {
    private static final long serialVersionUID = 4445236181123449936L;
    private List<Map<String,Object>> seriesData;

    public List<Map<String,Object>> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Map<String,Object>> seriesData) {
        this.seriesData = seriesData;
    }
}

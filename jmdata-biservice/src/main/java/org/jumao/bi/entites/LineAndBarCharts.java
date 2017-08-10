package org.jumao.bi.entites;

import java.io.Serializable;
import java.util.List;

/**
 * 拆线、柱状组合图
 * 
 * @author Administrator
 *
 */
public class LineAndBarCharts implements Serializable {

    private static final long serialVersionUID = -796538809666945055L;

    private List<Series>      series;
    private String[]          legendData;
    private List<String>          xAxisData;
    private String            title;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLegendData() {
        return legendData;
    }

    public void setLegendData(String[] legendData) {
        this.legendData = legendData;
    }

}

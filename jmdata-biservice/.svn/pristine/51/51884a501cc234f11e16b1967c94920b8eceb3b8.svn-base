package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

public class LineCharts implements Serializable {

    private static final long serialVersionUID = -5244773794123014069L;
    private String[]          legendData;
    private List<String>      xAxisData;
    private List<BigDecimal>  seriesData;
    private String            unit;

    public LineCharts() {
    }

    public LineCharts(String[] legendData, List<String> xAxisData, List<BigDecimal> seriesData) {
        this.legendData = legendData;
        this.xAxisData = xAxisData;
        this.seriesData = seriesData;
    }

    public LineCharts(List<String> xAxisData, List<BigDecimal> seriesData) {
        this.xAxisData = xAxisData;
        this.seriesData = seriesData;
    }

    public String[] getLegendData() {
        return legendData;
    }

    public void setLegendData(String[] legendData) {
        this.legendData = legendData;
    }

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<BigDecimal> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<BigDecimal> seriesData) {
        this.seriesData = seriesData;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static LineCharts setLineCharts(List<String> xAxisData, List<Map<String, Object>> rows, String xAxisDataKey,
            String seriesDataKey, Map<String, BigDecimal> map) {
        String operateDate = null;
        for (Map<String, Object> row : rows) {
            operateDate = row.get(xAxisDataKey).toString();
            map.put(operateDate, NumberUtils.createBigDecimal(row.get(seriesDataKey).toString()));
        }

        // 构造数据
        List<BigDecimal> seriesData = new ArrayList<BigDecimal>();
        List<String> keyList = new ArrayList<String>(map.keySet());
        Collections.sort(keyList);
        Collections.sort(xAxisData);
        for (String key1 : keyList) {
            seriesData.add(map.get(key1));
        }

        return new LineCharts(xAxisData, seriesData);
    }

}

package org.jumao.bi.entites.operate;

import java.io.Serializable;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.PieChart;

public class OrderTypeResponse extends CommonResponse implements Serializable {
	
    private static final long serialVersionUID = -6109175764169882688L;
    private PieChart pieChart;
    public PieChart getPieChart() {
        return pieChart;
    }
    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }
    
}

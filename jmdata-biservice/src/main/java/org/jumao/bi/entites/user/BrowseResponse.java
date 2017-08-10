package org.jumao.bi.entites.user;

import java.io.Serializable;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.PieChart;

public class BrowseResponse extends CommonResponse implements Serializable{
    

    private static final long serialVersionUID = -8044745529836098163L;
    private PieChart pieChart;
    public PieChart getPieChart() {
        return pieChart;
    }
    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }
    

}

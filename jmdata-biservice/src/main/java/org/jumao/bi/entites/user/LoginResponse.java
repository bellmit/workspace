package org.jumao.bi.entites.user;

import java.io.Serializable;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.LineChart;

public class LoginResponse extends CommonResponse implements Serializable{
    

    private static final long serialVersionUID = -6109175764169882642L;
    private LineChart lineChart;
    public LineChart getLineChart() {
        return lineChart;
    }
    public void setLineChart(LineChart lineChart) {
        this.lineChart = lineChart;
    }
   

}

package org.jumao.bi.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.BarChart;
import org.jumao.bi.entites.charts.CommonTableChart;
import org.jumao.bi.entites.charts.DoubleYAxisLineChart;
import org.jumao.bi.entites.charts.FlowChart;
import org.jumao.bi.entites.charts.NewFunnelChart;
import org.jumao.bi.entites.charts.NewPieChart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@Component
@Scope("prototype")
public class BaseResponse extends CommonResponse implements Serializable {
	
    private static final long serialVersionUID = -6109175756869882688L;

    private DoubleYAxisLineChart lineChart;
    
    private NewPieChart pieChart;
    
    private NewFunnelChart funnelChart;
    
    private BarChart barChart;

    private CommonTableChart tableChart;

    private FlowChart flowChart;

	public DoubleYAxisLineChart getLineChart() {
		return lineChart;
	}
	public void setLineChart(DoubleYAxisLineChart doubleYAxisLineChart) {
		this.lineChart = doubleYAxisLineChart;
	}
	public NewPieChart getPieChart() {
		return pieChart;
	}
	public void setPieChart(NewPieChart pieChart) {
		this.pieChart = pieChart;
	}
	
	public NewFunnelChart getFunnelChart() {
		return funnelChart;
	}
	public void setFunnelChart(NewFunnelChart funnelChart) {
		this.funnelChart = funnelChart;
	}
	public BarChart getBarChart() {
		return barChart;
	}
	public void setBarChart(BarChart barChart) {
		this.barChart = barChart;
	}

	public CommonTableChart getTableChart() {
		return tableChart;
	}

	public void setTableChart(CommonTableChart tableChart) {
		this.tableChart = tableChart;
	}

	public FlowChart getFlowChart() {
		return flowChart;
	}

	public void setFlowChart(FlowChart flowChart) {
		this.flowChart = flowChart;
	}
}

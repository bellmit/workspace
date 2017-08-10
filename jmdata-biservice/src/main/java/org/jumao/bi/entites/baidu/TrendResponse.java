package org.jumao.bi.entites.baidu;

import java.io.Serializable;

import org.jumao.bi.entites.CommonResponse;

public class TrendResponse extends CommonResponse implements Serializable {

	private static final long serialVersionUID = -7996500003074644848L;
	private String[] xAxisData;
	private TrendChart pvChart;
	private TrendChart uvChart;
	private TrendChart ipChart;
	private TrendChart averageTimeChart;
	private TrendChart avgVisitPagesChart;
	private TrendChart exitRateChart;

	public String[] getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(String[] xAxisData) {
		this.xAxisData = xAxisData;
	}

	public TrendChart getPvChart() {
		return pvChart;
	}

	public void setPvChart(TrendChart pvChart) {
		this.pvChart = pvChart;
	}

	public TrendChart getUvChart() {
		return uvChart;
	}

	public void setUvChart(TrendChart uvChart) {
		this.uvChart = uvChart;
	}

	public TrendChart getIpChart() {
		return ipChart;
	}

	public void setIpChart(TrendChart ipChart) {
		this.ipChart = ipChart;
	}

	public TrendChart getAverageTimeChart() {
		return averageTimeChart;
	}

	public void setAverageTimeChart(TrendChart averageTimeChart) {
		this.averageTimeChart = averageTimeChart;
	}

	public TrendChart getAvgVisitPagesChart() {
		return avgVisitPagesChart;
	}

	public void setAvgVisitPagesChart(TrendChart avgVisitPagesChart) {
		this.avgVisitPagesChart = avgVisitPagesChart;
	}

	public TrendChart getExitRateChart() {
		return exitRateChart;
	}

	public void setExitRateChart(TrendChart exitRateChart) {
		this.exitRateChart = exitRateChart;
	}

}

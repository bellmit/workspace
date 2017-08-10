package org.jumao.bi.entites.baidu;

import java.io.Serializable;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.BarChart;

public class TopPageResponse extends CommonResponse implements Serializable {


	private static final long serialVersionUID = -1984059963630817013L;
	private BarChart pvChart;
	private BarChart existCountChart;
	public BarChart getPvChart() {
		return pvChart;
	}
	public void setPvChart(BarChart pvChart) {
		this.pvChart = pvChart;
	}
	public BarChart getExistCountChart() {
		return existCountChart;
	}
	public void setExistCountChart(BarChart existCountChart) {
		this.existCountChart = existCountChart;
	}



}

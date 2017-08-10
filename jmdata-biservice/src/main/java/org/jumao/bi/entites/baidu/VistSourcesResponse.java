package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.util.List;

import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.charts.LineChart;
import org.jumao.bi.entites.charts.PieChart;

public class VistSourcesResponse extends CommonResponse implements Serializable {

	private static final long serialVersionUID = 1089202485897772664L;
	private LineChart lineChart;
	private PieChart pieChart;
	private List<BaiduTabBean> table;

	public LineChart getLineChart() {
		return lineChart;
	}

	public void setLineChart(LineChart lineChart) {
		this.lineChart = lineChart;
	}

	public PieChart getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChart pieChart) {
		this.pieChart = pieChart;
	}

    public List<BaiduTabBean> getTable() {
        return table;
    }

    public void setTable(List<BaiduTabBean> table) {
        this.table = table;
    }


	
}

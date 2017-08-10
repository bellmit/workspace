package org.jumao.bi.entites;

import java.io.Serializable;
import java.util.List;

/**
 * 柱状图图
 * 
 * @author Administrator
 *
 */
public class BarChartsResult implements Serializable {

	private static final long serialVersionUID = -796538809666945055L;

	private String seriesName;
	private List<String> legendData;
	private List<String> xAxisData;
	private List<BarSeries> series;
	private String title;

	// 返回状态
	private ResponseResult status;

	public ResponseResult getStatus() {
		return status;
	}

	public void setStatus(ResponseResult status) {
		this.status = status;
	}

	public List<String> getLegendData() {
		return legendData;
	}

	public void setLegendData(List<String> legendData) {
		this.legendData = legendData;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<BarSeries> getSeries() {
		return series;
	}

	public void setSeries(List<BarSeries> series) {
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

}

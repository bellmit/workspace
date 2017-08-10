package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 条形图：区块条形图
 * 
 * @author Administrator
 *
 */
public class AreaBarCharts implements Serializable {

	private static final long serialVersionUID = -796538809666945055L;

	private String seriesName;
	private List<BigDecimal> seriesData;// 简单条形图：没有多个legendData
	private List<String> yAxisData;
	private String title;

	// 返回状态
	private ResponseResult status;

	public ResponseResult getStatus() {
		return status;
	}

	public void setStatus(ResponseResult status) {
		this.status = status;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<BigDecimal> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<BigDecimal> seriesData) {
		this.seriesData = seriesData;
	}

	public List<String> getyAxisData() {
		return yAxisData;
	}

	public void setyAxisData(List<String> yAxisData) {
		this.yAxisData = yAxisData;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

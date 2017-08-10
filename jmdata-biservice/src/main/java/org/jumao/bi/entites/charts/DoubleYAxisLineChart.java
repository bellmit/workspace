package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class DoubleYAxisLineChart implements Serializable {

	private static final long serialVersionUID = -5244773794679014069L;
	private String[] legendData;
	private String[] xAxisData;
	private Map<String,List> seriesData;

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public String[] getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(String[] xAxisData) {
		this.xAxisData = xAxisData;
	}

	public Map<String, List> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(Map<String, List> seriesData) {
		this.seriesData = seriesData;
	}


}

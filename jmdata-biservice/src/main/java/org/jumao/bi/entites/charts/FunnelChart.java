package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 漏斗图
 */
public class FunnelChart implements Serializable {

	private static final long serialVersionUID = 4445236191954347736L;
	private String[] legendData = new String[0];
	private List<CommonBean> seriesData = new ArrayList<CommonBean>();

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public List<CommonBean> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<CommonBean> seriesData) {
		this.seriesData = seriesData;
	}

}

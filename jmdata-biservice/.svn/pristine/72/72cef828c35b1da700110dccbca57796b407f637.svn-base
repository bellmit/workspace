package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 饼图的通用版，返回的SeriesData中的value可以是任何类型
 */
@Component
@Scope("prototype")
public class NewPieChart implements Serializable {

	private static final long serialVersionUID = 4445236181854347736L;
	private String[] legendData = new String[0];
	private List<CommonTBean<?>> seriesData = new ArrayList<CommonTBean<?>>();

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public List<CommonTBean<?>> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<CommonTBean<?>> seriesData) {
		this.seriesData = seriesData;
	}

}

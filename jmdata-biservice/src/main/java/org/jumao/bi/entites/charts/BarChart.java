package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class BarChart implements Serializable {

	private static final long serialVersionUID = 2969983904713300449L;
	private String seriesName;
	private String[] yAxisData;
	private BigDecimal[] seriesData;

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String[] getyAxisData() {
		return yAxisData;
	}

	public void setyAxisData(String[] yAxisData) {
		this.yAxisData = yAxisData;
	}

	public BigDecimal[] getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(BigDecimal[] seriesData) {
		this.seriesData = seriesData;
	}

}

package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 带有数据的id，可用作柱状图和其他图的联动
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class LinkageBarChart extends BarChart implements Serializable {
	private static final long serialVersionUID = 2960083904713300449L;
	private String seriesName;
	private String[] yAxisData;
	private BigDecimal[] seriesData;
	private List<?> itemId;

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

	public List<?> getItemId() {
		return itemId;
	}

	public void setItemId(List<?> itemId) {
		this.itemId = itemId;
	}	
}

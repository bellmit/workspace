package org.jumao.bi.entites;

import java.io.Serializable;

public class TableCharts implements Serializable {

	private static final long serialVersionUID = -7275311248341173164L;
//	private String[] legendData;
//	private String[] xAxisData;
	//private String seriesName;
	private TypeCompanyBean[] seriesData;
	private String title;
	public TypeCompanyBean[] getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(TypeCompanyBean[] seriesData) {
		this.seriesData = seriesData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
}

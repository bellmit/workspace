package org.jumao.bi.entites;

import java.io.Serializable;

public class BillTableCharts implements Serializable {

	private static final long serialVersionUID = -7275311248341173164L;
//	private String[] legendData;
//	private String[] xAxisData;
	//private String seriesName;
	private CompanyBill[] seriesData;
	private String title;
	
	public CompanyBill[] getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(CompanyBill[] seriesData) {
		this.seriesData = seriesData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
}

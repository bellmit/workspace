package org.jumao.bi.entites.baidu;

import java.io.Serializable;

public class DailyMetricBean implements Serializable {

	private static final long serialVersionUID = -8515902978536706129L;
	private String platform;
	private String date;
	private String pv;
	private String uv;
	private String ips;
	private String stayTime;
	private String exits;
	private String avgVisitPages;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv;
	}

	public String getUv() {
		return uv;
	}

	public void setUv(String uv) {
		this.uv = uv;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getStayTime() {
		return stayTime;
	}

	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
	}

	public String getExits() {
		return exits;
	}

	public void setExits(String exits) {
		this.exits = exits;
	}

	public String getAvgVisitPages() {
		return avgVisitPages;
	}

	public void setAvgVisitPages(String avgVisitPages) {
		this.avgVisitPages = avgVisitPages;
	}
	
	

}

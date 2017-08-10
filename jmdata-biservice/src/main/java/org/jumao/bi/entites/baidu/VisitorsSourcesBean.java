package org.jumao.bi.entites.baidu;

import java.io.Serializable;

public class VisitorsSourcesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3549762859591683324L;
	private String date;
	private String name;
	private String metrics;
	private Integer value;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

}

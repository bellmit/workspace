package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VisitorsSourcesChart implements Serializable {

	private static final long serialVersionUID = 6471016978912623890L;
	
	
	private List<String> legend=new ArrayList<String>();//类型字符串
	
	/**
	 * 访客来源
	 */
	private List<VisitorsSourcesBean> data;


	public List<VisitorsSourcesBean> getData() {
		return data;
	}


	public void setData(List<VisitorsSourcesBean> data) {
		this.data = data;
	}


	public List<String> getLegend() {
		for (VisitorsSourcesBean visitorsSourcesBean : data) {
			legend.add(visitorsSourcesBean.getName());
		}
		
		
		return legend;
	}


	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	

}

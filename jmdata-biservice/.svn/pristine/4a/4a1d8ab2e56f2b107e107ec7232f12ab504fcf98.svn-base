package org.jumao.bi.entites.charts;

import org.jumao.bi.utis.constants.Key;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可表示饼图、中国地图
 */
public class PieChart implements Serializable {

	private static final long serialVersionUID = 4445236181854347736L;
	private String[] legendData = new String[0];
	private List<Map<String, String>> seriesData = new ArrayList<Map<String, String>>();

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public List<Map<String, String>> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<Map<String, String>> seriesData) {
		this.seriesData = seriesData;
	}

	public static List<CommonBean> getSeriesCb(List<Map<String, String>> seriesData) {
		List<CommonBean> list = new ArrayList<CommonBean>();

		for (Map<String, String> map : seriesData) {
			CommonBean commonBean = new CommonBean();

			commonBean.setTypeOrId(map.getOrDefault(Key.Type_Or_Id, "-1"));
			commonBean.setName(map.getOrDefault(Key.NAME, ""));
			commonBean.setValue(map.getOrDefault(Key.VALUE, ""));

			list.add(commonBean);
		}

		return list;
	}

	public void setSeriesFromCb(List<CommonBean> seriesData) {
		List<Map<String, String>> seriesList = new ArrayList<Map<String, String>>();

		for (CommonBean ele : seriesData) {
			Map<String, String> map = new HashMap<String, String>();

			map.put(Key.Type_Or_Id, ele.getTypeOrId());
			map.put(Key.NAME, ele.getName());
			map.put(Key.VALUE, ele.getValue());

			seriesList.add(map);
		}

		this.seriesData = seriesList;
	}

}

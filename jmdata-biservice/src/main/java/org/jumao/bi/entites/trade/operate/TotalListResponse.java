package org.jumao.bi.entites.trade.operate;

import org.jumao.bi.entites.CommonResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 累计值、单表map多元素返回主对象
 */
public class TotalListResponse extends CommonResponse implements Serializable{

	private static final long serialVersionUID = -5244773794123014069L;
	private List<Map<String, String>> data;

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
}

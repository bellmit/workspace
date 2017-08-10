package org.jumao.bi.entites.trade.operate;

import org.jumao.bi.entites.CommonResponse;

import java.io.Serializable;

public class MasBasicResponse extends CommonResponse implements Serializable{

	private static final long serialVersionUID = -5244773794123014069L;
	private String[] xAxisData;
	private LineChart openStoreTotal;
	private LineChart itemTotal;
	private LineChart orderTotal;
	private LineChart payTotal;
	private LineChart tranTotal;

	public String[] getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(String[] xAxisData) {
		this.xAxisData = xAxisData;
	}

	public LineChart getOpenStoreTotal() {
		return openStoreTotal;
	}

	public void setOpenStoreTotal(LineChart openStoreTotal) {
		this.openStoreTotal = openStoreTotal;
	}

	public LineChart getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(LineChart itemTotal) {
		this.itemTotal = itemTotal;
	}

	public LineChart getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(LineChart orderTotal) {
		this.orderTotal = orderTotal;
	}

	public LineChart getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(LineChart payTotal) {
		this.payTotal = payTotal;
	}

	public LineChart getTranTotal() {
		return tranTotal;
	}

	public void setTranTotal(LineChart tranTotal) {
		this.tranTotal = tranTotal;
	}
}

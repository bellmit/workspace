package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 进出口分类统计：进出口贸易国别top
 * 
 * @author liquan
 *
 */
public class InOutCustomsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String rowkey;//
	// private String date;// 日期字符串
	// private String countryCode;// 国家编码
	private String customsName;// 关区/海关：名称
	private String inoutType;// 进出口类型：1:进口；2:出口
	private String customsCode;// 关区/海关：编码
	private BigDecimal totalAmount;
	private int index;// top序号：pc端用到

	public InOutCustomsBean() {
		super();
	}

	public InOutCustomsBean(String rowkey, String customsName,
			String inoutType, String customsCode, BigDecimal totalAmount,
			int index) {
		super();
		this.rowkey = rowkey;
		this.customsName = customsName;
		this.inoutType = inoutType;
		this.customsCode = customsCode;
		this.totalAmount = totalAmount;
		this.index = index;
	}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getCustomsName() {
		return customsName;
	}

	public void setCustomsName(String customsName) {
		this.customsName = customsName;
	}

	public String getInoutType() {
		return inoutType;
	}

	public void setInoutType(String inoutType) {
		this.inoutType = inoutType;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

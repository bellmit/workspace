package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 进出口分类统计：进出口贸易国别top
 * 
 * @author liquan
 *
 */
public class InOutCountryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String rowkey;//
	// private String date;// 日期字符串
	private String countryCode;// 国家编码
	private String countryName;// 国家名称
	private String inoutType;// 进出口类型：1:进口；2:出口
	private String customsCode;// 关区/海关：编码
	private BigDecimal totalAmount;
	private int index;// top序号：pc端用到

	public InOutCountryBean() {
		super();
	}
 

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getInoutType() {
		return inoutType;
	}

	public void setInoutType(String inoutType) {
		this.inoutType = inoutType;
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

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

}

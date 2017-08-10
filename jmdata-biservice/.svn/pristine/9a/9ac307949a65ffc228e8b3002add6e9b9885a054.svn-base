package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业金额统计对象：
 * 
 * 企业分类统计：拍档/客户企业订单数top||拍档企业企业收入top/客户企业企业支出top||拍档/客户企业订单货值top
 * 
 * @author liquan
 *
 */
public class CompanyAmountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String rowkey;//
	// private String date;// 日期字符串
	private String companyCode;// 企业编码
	private String companyName;// 企业名称
	// private String inoutType;// 进出口类型：1:进口；2:出口
	private String customsCode;// 关区/海关：编码
	private BigDecimal totalAmount;
	private int index;// top序号：pc端用到

	public CompanyAmountBean() {
		super();
	}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

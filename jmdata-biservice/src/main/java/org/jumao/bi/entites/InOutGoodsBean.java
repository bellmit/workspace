package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 进出口分类统计：进出口贸易国别top
 * 
 * @author liquan
 *
 */
public class InOutGoodsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String rowkey;//
	// private String date;// 日期字符串
	private String goodsCode;// 商品编码
	private String goodsName;// 商品名称
	private String inoutType;// 进出口类型：1:进口；2:出口
	private String customsCode;// 关区/海关：编码
	private BigDecimal totalAmount;
	private int index;// top序号：pc端用到

	public InOutGoodsBean() {
		super();
	}

	public InOutGoodsBean(String rowkey, String goodsCode, String goodsName,
			String inoutType, String customsCode, BigDecimal totalAmount,
			int index) {
		super();
		this.rowkey = rowkey;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

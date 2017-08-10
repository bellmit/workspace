package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 买家、卖家企业成交额top10的企业：top3的商品
 * @author liquan
 *
 */
public class CompanyBillGoods implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String platformId;// 平台id：
	private String companyId;
	private String date;// 日期字符串
	private String goodsId;// 日期字符串
	// rowkey
	private String name;
	// private long value;
	private BigDecimal amount;
	private int count;// 下单数
	private int index;// 序号：pc端用到

	public CompanyBillGoods() {
		super();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}

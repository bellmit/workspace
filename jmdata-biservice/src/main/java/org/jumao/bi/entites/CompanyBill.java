package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 买家、卖家企业成交额top10的企业
 * @author liquan
 *
 */
public class CompanyBill implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String platformId;// 平台id：
	private String date;// 日期字符串
	private String companyId;
	// rowkey
	private String name;
	private String type;// 公司性质
	private String industry;
	// private long value;
	private BigDecimal amount;
	private int count;// 下单数
	private String topGoods;// 前三位的商品名称：用逗号隔开
	private int index;// 序号：pc端用到

	public CompanyBill() {
		super();
	}

	public CompanyBill(String name, String type, String industry,
			BigDecimal amount, int count, String topGoods, int index) {
		super();
		this.name = name;
		this.type = type;
		this.industry = industry;
		this.amount = amount;
		this.count = count;
		this.topGoods = topGoods;
		this.index = index;
	}

	public CompanyBill(String name, String type, String industry,
			BigDecimal amount) {
		super();
		this.name = name;
		this.type = type;
		this.industry = industry;
		this.amount = amount;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	// public long getValue() {
	// return value;
	// }
	// public void setValue(long value) {
	// this.value = value;
	// }

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

	public String getTopGoods() {
		return topGoods;
	}

	public void setTopGoods(String topGoods) {
		this.topGoods = topGoods;
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

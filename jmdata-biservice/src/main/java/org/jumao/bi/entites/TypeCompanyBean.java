package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

public class TypeCompanyBean implements Serializable {

	private static final long serialVersionUID = 8052622983525623021L;

	// key:组成字段
	private String platformId;// 平台id：
	private String date;// 日期字符串
	private String type;// type:企业性质：
	// key:组成字段
	//
	private BigDecimal dayAdd; // 每天新增{企业}总数
	private BigDecimal licenceDayAdd;// 每天新增{上传三证：企业}总数
	private BigDecimal loginDayAdd;// 每天新增{登录：企业}总数
	private BigDecimal goodsDayAdd; // 每天新增{商品}总数
	private BigDecimal importDayAdd; // 每天导入{企业}总数

	// -----统计字段：数据库无此字段-------
	private BigDecimal totalCount; //  {企业}总数
	private String loginRatio;// 统计全部{登陆：企业}占比
	private String licenceRatio;// {上传三证：企业}占比
	private String goodsRatio;// 发布商品占比

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getDayAdd() {
		return dayAdd;
	}

	public void setDayAdd(BigDecimal dayAdd) {
		this.dayAdd = dayAdd;
	}

	public BigDecimal getLicenceDayAdd() {
		return licenceDayAdd;
	}

	public void setLicenceDayAdd(BigDecimal licenceDayAdd) {
		this.licenceDayAdd = licenceDayAdd;
	}

	public BigDecimal getLoginDayAdd() {
		return loginDayAdd;
	}

	public void setLoginDayAdd(BigDecimal loginDayAdd) {
		this.loginDayAdd = loginDayAdd;
	}

	public BigDecimal getGoodsDayAdd() {
		return goodsDayAdd;
	}

	public void setGoodsDayAdd(BigDecimal goodsDayAdd) {
		this.goodsDayAdd = goodsDayAdd;
	}

	public String getLoginRatio() {
		return loginRatio;
	}

	public void setLoginRatio(String loginRatio) {
		this.loginRatio = loginRatio;
	}

	public String getLicenceRatio() {
		return licenceRatio;
	}

	public void setLicenceRatio(String licenceRatio) {
		this.licenceRatio = licenceRatio;
	}

	public String getGoodsRatio() {
		return goodsRatio;
	}

	public void setGoodsRatio(String goodsRatio) {
		this.goodsRatio = goodsRatio;
	}

	public BigDecimal getImportDayAdd() {
		return importDayAdd;
	}

	public void setImportDayAdd(BigDecimal importDayAdd) {
		this.importDayAdd = importDayAdd;
	}

	// -----统计字段：数据库无此字段-------

}

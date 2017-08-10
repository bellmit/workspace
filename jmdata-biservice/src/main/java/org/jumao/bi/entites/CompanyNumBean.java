package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 企业分类统计：企业新增数||企业分类统计：企业活跃数
 * 
 * @author liquan
 *
 */
public class CompanyNumBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// rowkey
	private String rowkey;//
	private String date;// 日期字符串
	private String dataLable;// 数据坐标：界面横坐标显示的文字
	// private String companyName;// 企业名称
	// private String inoutType;// 进出口类型：1:进口；2:出口
	// private String customsCode;// 关区/海关：编码
	private BigDecimal totalNum;// 总数量
	private int index;// top序号：pc端用到

	public CompanyNumBean() {
		super();
	}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDataLable() {
		return dataLable;
	}

	public void setDataLable(String dataLable) {
		this.dataLable = dataLable;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

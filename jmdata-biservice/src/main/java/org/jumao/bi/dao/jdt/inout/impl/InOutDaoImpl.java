package org.jumao.bi.dao.jdt.inout.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.jdt.inout.IInOutDao;
import org.jumao.bi.entites.InOutCountryBean;
import org.jumao.bi.entites.InOutCustomsBean;
import org.jumao.bi.entites.InOutGoodsBean;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("inOutDao")
public class InOutDaoImpl implements IInOutDao {

	@Autowired
	IHBaseDao hbasedao;

	/**
	 * 进出口分类统计：进出口贸易国别top
	 */
	private static final String In_Out_Country_Table = "jmbi:inOutCountryAmount";

	/**
	 * 进出口分类统计：进出口口岸top
	 */
	private static final String In_Out_Customs_Table = "jmbi:inOutCustomsAmount";

	/**
	 * 进出口分类统计：进出口商品top
	 */
	private static final String In_Out_Goods_Table = "jmbi:inOutGoodsAmount";

	// =====================================

	private static final byte[] Table_Cf = "info".getBytes();

	private static final BigDecimal Zero = new BigDecimal(0);
	
	private static final String Info_Str = "info";
	
	private static final String Comma_Str = ",";
	
	private static final String Customs_Code_Str = "customsCode";
	
	private static final String Underline_Str = "_";
	
	private static final int Index_Three = 3;
	
	private static final String Total_Amount_Qulifier = "totalAmount";

	// ===============进出口分类统计：进出口贸易国别top======================
	/**
	 * 进出口分类统计：进出口贸易国别top
	 * @see org.jumao.bi.dao.jdt.inout.IInOutDao#retrieveInOutCountryAmountByPeriod(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<InOutCountryBean> retrieveInOutCountryAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add(Info_Str + Comma_Str + Customs_Code_Str + Comma_Str + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(In_Out_Country_Table,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(In_Out_Country_Table, fromKey, endKey);
		}

		// 在这里累加合并：减少数据量
		Map<String, InOutCountryBean> map = new HashMap<String, InOutCountryBean>();
		for (Result result : results) {
			InOutCountryBean company = convertInOutCountryBy(result);
			// companyList.add(company);
			// 国家编码不为空
			if (null != company && isNotNull(company.getCountryCode())) {
				if (map.containsKey(company.getCountryCode())) {// 已经存在的国家：累计其：金额
					InOutCountryBean value = map.get(company.getCountryCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));// 保留4位小数
//							.setScale(4, BigDecimal.ROUND_HALF_UP)
					map.put(company.getCountryCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//					.setScale(4,BigDecimal.ROUND_HALF_UP));// 保留4位小数
					map.put(company.getCountryCode(), company);
				}
			}
		}
		List<InOutCountryBean> companyList = new ArrayList<InOutCountryBean>();
		for (Entry<String, InOutCountryBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口贸易国别top
	 * 
	 * @param result
	 * @return
	 */
	private InOutCountryBean convertInOutCountryBy(Result result) {
		InOutCountryBean bean = new InOutCountryBean();
		String rewKey = Bytes.toString(result.getRow());
		String countryName = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("countryName")));
		String totalAmount = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes(Total_Amount_Qulifier)));
		//
		String[] strs = rewKey.split(Underline_Str, -1);

		String customsCode = strs[2];
		String tradingCountry = strs[Index_Three];
		bean.setCustomsCode(customsCode);
		bean.setCountryCode(tradingCountry);
		bean.setCountryName(countryName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(Zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		//(customs_code+"||"+trading_country+"||"+countryName+"||"+bean.getTotalAmount());
		return bean;
	}

	// ===============进出口分类统计：进出口贸易国别top======================

	// ===============进出口分类统计：进出口口岸top======================
	/**
	 * 进出口分类统计：进出口口岸top
	 * @see org.jumao.bi.dao.jdt.inout.IInOutDao#retrieveInOutCustomsAmountByPeriod(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<InOutCustomsBean> retrieveInOutCustomsAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add(Info_Str + Comma_Str + Customs_Code_Str + Comma_Str + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(In_Out_Customs_Table,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(In_Out_Customs_Table, fromKey, endKey);
		}
//		List<Result> results = hbasedao.getRows(IN_OUT_CUSTOMS_TABLE, fromKey,
//				endKey);
		// 在这里累加合并：减少数据量
		Map<String, InOutCustomsBean> map = new HashMap<String, InOutCustomsBean>();
		for (Result result : results) {
			InOutCustomsBean company = convertInOutCustomsBy(result);
			// companyList.add(company);
			// 海关/关区编码不为空
			if (null != company && isNotNull(company.getCustomsCode())) {
				if (map.containsKey(company.getCustomsCode())) {// 已经存在的海关/关区：累计其：金额
					InOutCustomsBean value = map.get(company.getCustomsCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));
//							.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCustomsCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//							.setScale(4,BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCustomsCode(), company);
				}
			}
		}
		List<InOutCustomsBean> companyList = new ArrayList<InOutCustomsBean>();
		for (Entry<String, InOutCustomsBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口海关/关区top
	 * 
	 * @param result
	 * @return
	 */
	private InOutCustomsBean convertInOutCustomsBy(Result result) {
		InOutCustomsBean bean = new InOutCustomsBean();
		String rewKey = Bytes.toString(result.getRow());
		String customsName = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("customsName")));
		String totalAmount = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes(Total_Amount_Qulifier)));
		//
		String[] strs = rewKey.split(Underline_Str, -1);

		String customsCode = strs[2];
		// String trading_country = strs[3];
		bean.setCustomsCode(customsCode);
		// bean.setCountryCode(trading_country);
		bean.setCustomsName(customsName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(Zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		return bean;
	}

	// ===============进出口分类统计：进出口口岸top======================

	// ===============进出口分类统计：进出口商品top======================
	/**
	 * 进出口分类统计：进出口商品top
	 * @see org.jumao.bi.dao.jdt.inout.IInOutDao#retrieveInOutGoodsAmountByPeriod(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<InOutGoodsBean> retrieveInOutGoodsAmountByPeriod(
			String fromKey, String endKey, String customsCode)
			throws IOException {

		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr = new ArrayList<String>();
			arr.add(Info_Str + Comma_Str + Customs_Code_Str + Comma_Str + customsCode);
			results = hbasedao.getRowsByKeyAndFilterLike(In_Out_Goods_Table,
					fromKey, endKey, arr);
		} else {
			results = hbasedao.getRows(In_Out_Goods_Table, fromKey, endKey);
		}
//		List<Result> results = hbasedao.getRows(IN_OUT_GOODS_TABLE, fromKey,
//				endKey);
		// 在这里累加合并：减少数据量
		Map<String, InOutGoodsBean> map = new HashMap<String, InOutGoodsBean>();
		for (Result result : results) {
			InOutGoodsBean company = convertInOutGoodsBy(result);
			// companyList.add(company);
			// 商品码不为空
			if (null != company && isNotNull(company.getGoodsCode())) {
				if (map.containsKey(company.getGoodsCode())) {// 已经存在的商品：累计其：金额
					InOutGoodsBean value = map.get(company.getGoodsCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));
//							.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getGoodsCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//					.setScale(4, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getGoodsCode(), company);
				}
			}
		}
		List<InOutGoodsBean> companyList = new ArrayList<InOutGoodsBean>();
		for (Entry<String, InOutGoodsBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：进出口商品top
	 * 
	 * @param result
	 * @return
	 */
	private InOutGoodsBean convertInOutGoodsBy(Result result) {
		InOutGoodsBean bean = new InOutGoodsBean();
		String rewKey = Bytes.toString(result.getRow());
		String goodsName = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("goodsName")));
		String totalAmount = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes(Total_Amount_Qulifier)));
		//
		String[] strs = rewKey.split(Underline_Str, -1);

		String customsCode = strs[2];
		String hsNo = strs[Index_Three];//商品hs编码
		bean.setCustomsCode(customsCode);
		bean.setGoodsCode(hsNo);
		bean.setGoodsName(goodsName);
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(Zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		return bean;
	}

	// ===============进出口分类统计：进出口商品top======================

	/**
	 * 字符串：不为空：返回true
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNotNull(String str) {
		if (null != str && str.trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
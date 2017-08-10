package org.jumao.bi.dao.jdt.company.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.jdt.company.ICompanyDao;
import org.jumao.bi.entites.CompanyAmountBean;
import org.jumao.bi.entites.CompanyNumBean;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDaoImpl implements ICompanyDao {

	@Autowired
	IHBaseDao hbasedao;

	// =====================================

	private static final byte[] Table_Cf = "info".getBytes();

	private static final BigDecimal Zero = new BigDecimal(0);

	// ===============企业分类统计：由于几个top统计======================
	/**
	 * 用于几个top统计
	 * @see org.jumao.bi.dao.jdt.company.ICompanyDao#retrieveCompanyTopByPeriod(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public List<CompanyAmountBean> retrieveCompanyTopByPeriod(String fromKey,
			String endKey, String tableName, int point, String customsCode)
			throws IOException {
		List<Result> results = new ArrayList<Result>();
		if (isNotNull(customsCode)) {
			List<String> arr= new ArrayList<String>();
			arr.add("info"+","+"customsCode"+","+customsCode);
			  results = hbasedao.getRowsByKeyAndFilterLike(tableName, fromKey, endKey, arr);
		} else {
			  results = hbasedao.getRows(tableName, fromKey, endKey);
		}

		// 在这里累加合并：减少数据量
		Map<String, CompanyAmountBean> map = new HashMap<String, CompanyAmountBean>();
		for (Result result : results) {
			CompanyAmountBean company = convertCompanyAmountBeanBy(result);
			// companyList.add(company);
			// 企业编码不为空
			if (null != company && isNotNull(company.getCompanyCode())) {
				if (map.containsKey(company.getCompanyCode())) {// 已经存在的企业：累计其：金额
					CompanyAmountBean value = map.get(company.getCompanyCode());
					value.setTotalAmount(value.getTotalAmount()
							.add(company.getTotalAmount()));
//							.setScale(point, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCompanyCode(), value);
				} else {
					company.setTotalAmount(company.getTotalAmount());
//							.setScale(point, BigDecimal.ROUND_HALF_UP)// 保留4位小数
					map.put(company.getCompanyCode(), company);
				}
			}
		}
		List<CompanyAmountBean> companyList = new ArrayList<CompanyAmountBean>();
		for (Entry<String, CompanyAmountBean> entry : map.entrySet()) {
			companyList.add(entry.getValue());
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：由于几个top统计
	 * 
	 * @param result
	 * @return
	 */
	private CompanyAmountBean convertCompanyAmountBeanBy(Result result) {
		CompanyAmountBean bean = new CompanyAmountBean();
		String rewKey = Bytes.toString(result.getRow());
		String companyName = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("companyName")));
		String totalAmount = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("totalAmount")));
		//
		String[] strs = rewKey.split("_", -1);

		String customsCode = strs[1];
		String companyCode = strs[2];
		bean.setCustomsCode(customsCode);
		bean.setCompanyCode(companyCode);
		bean.setCompanyName(DesensitizationUtils.getDesStr(companyName));
		// 订单金额
		if (null == totalAmount || totalAmount.trim().length() == 0) {
			bean.setTotalAmount(Zero);
		} else {
			bean.setTotalAmount(new BigDecimal(totalAmount));
		}
		return bean;
	}

	// ===============企业分类统计：由于几个top统计======================

	//

	//

	// ===============企业分类统计：由于2个企业数统计==新增企业数、活跃企业数====================

	/**
	 * 新增企业数、活跃企业数
	 * @see org.jumao.bi.dao.jdt.company.ICompanyDao#retrieveCompanyNumByPeriod(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public List<CompanyNumBean> retrieveCompanyNumByPeriod(String fromKey,
			String endKey, String tableName, int point, String timeType)
			throws IOException {

		List<Result> results = hbasedao.getRows(tableName, fromKey, endKey);
		List<CompanyNumBean> companyList = new ArrayList<CompanyNumBean>();

		for (Result result : results) {
			CompanyNumBean company = convertCompanyNumBeanBy(result);
			companyList.add(company);
		}
		return companyList;
	}

	/**
	 * 进出口分类统计：由于2个企业数统计
	 * 
	 * @param result
	 * @return
	 */
	private CompanyNumBean convertCompanyNumBeanBy(Result result) {
		CompanyNumBean bean = new CompanyNumBean();
		String rewKey = Bytes.toString(result.getRow());
		String totalCount = Bytes.toString(result.getValue(Table_Cf,
				Bytes.toBytes("totalCount")));
		//
		String[] strs = rewKey.split("_", -1);

		String identity = strs[0];// '身份：0客户，1拍档，2金融机构'
		String dateStr = strs[1];
		bean.setDate(dateStr);
		// 订单金额
		if (null == totalCount || totalCount.trim().length() == 0) {
			bean.setTotalNum(Zero);
		} else {
			bean.setTotalNum(new BigDecimal(totalCount));
		}
		return bean;
	}

	// ===============企业分类统计：由于2个企业数统计======================

	//

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
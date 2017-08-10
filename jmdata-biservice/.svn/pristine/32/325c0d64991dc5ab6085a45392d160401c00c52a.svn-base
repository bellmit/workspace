package org.jumao.bi.dao.jdt.company;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.CompanyAmountBean;
import org.jumao.bi.entites.CompanyNumBean;

public interface ICompanyDao {

	/**
	 * 企业分类统计：由于几个top统计hbase的表结构一致；这里用一个方法。把表名传过来，进行查询
	 * 
	 * @param fromKey
	 * @param endKey
	 * @param tableName
	 *            要查询的hbase表名
	 * @param point
	 *            保留几位小数
	 * @param customsCode
	 *            海关/关区：编码
	 * @return
	 * @throws IOException
	 */
	List<CompanyAmountBean> retrieveCompanyTopByPeriod(String fromKey,
			String endKey, String tableName, int point, String customsCode) throws IOException;

	/**
	 * 企业分类统计：由于2个企业数统计的4个hbase的表结构一致；
	 * 企业新增数||企业活跃数
	 * 
	 * @param fromKey
	 * @param endKey
	 * @param tableName
	 *            要查询的hbase表名
	 * @param point
	 *            保留几位小数
	 * @param timeType
	 *            时间类型：week、month、quarter、year {2个表，一个是天统计，一个是月统计}
	 * @return
	 * @throws IOException
	 */
	List<CompanyNumBean> retrieveCompanyNumByPeriod(String fromKey,
			String endKey, String tableName, int point, String timeType)
			throws IOException;

	// /**
	// * 进出口分类统计：进出口口岸top
	// *
	// * @param fromDate
	// * @param endDate
	// * @return
	// * @throws IOException
	// */
	// public List<InOutCustomsBean> retrieveInOutCustomsAmountByPeriod(
	// String fromKey, String endKey) throws IOException;
	//
	// /**
	// * 进出口分类统计：进出口商品top
	// *
	// * @param fromDate
	// * @param endDate
	// * @return
	// * @throws IOException
	// */
	// List<InOutGoodsBean> retrieveInOutGoodsAmountByPeriod(String fromKey,
	// String endKey) throws IOException;

}

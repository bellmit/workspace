package org.jumao.bi.dao.jdt.inout;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.InOutCountryBean;
import org.jumao.bi.entites.InOutCustomsBean;
import org.jumao.bi.entites.InOutGoodsBean;

public interface IInOutDao {

	/**
	 * 进出口分类统计：进出口贸易国别top
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param customsCode
	 *            海关/关区：编码
	 * @return
	 * @throws IOException
	 */
	List<InOutCountryBean> retrieveInOutCountryAmountByPeriod(String fromKey,
			String endKey, String customsCode) throws IOException;

	/**
	 * 进出口分类统计：进出口口岸top
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param customsCode
	 *            海关/关区：编码
	 * @return
	 * @throws IOException
	 */
	public List<InOutCustomsBean> retrieveInOutCustomsAmountByPeriod(
			String fromKey, String endKey, String customsCode) throws IOException;

	/**
	 * 进出口分类统计：进出口商品top
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param customsCode
	 *            海关/关区：编码
	 * @return
	 * @throws IOException
	 */
	List<InOutGoodsBean> retrieveInOutGoodsAmountByPeriod(String fromKey,
			String endKey, String customsCode) throws IOException;

}

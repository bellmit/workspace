package org.jumao.bi.dao.baidu;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.baidu.DailyMetricBean;
import org.jumao.bi.entites.baidu.NOVisitorsBean;
import org.jumao.bi.entites.baidu.TopPageBean;
import org.jumao.bi.entites.baidu.VisitorsSourcesBean;
import org.jumao.bi.entites.baidu.WorldVisitBean;
import org.jumao.bi.entites.charts.CommonBean;

public interface IBaiduDao {

	/**
	 * 百度指标趋势统计
	 * @param startKey
	 * @param endKey
	 * @return
	 * @throws IOException
	 */
	List<DailyMetricBean> retrieveMetricDataByKeyRange(String startKey,
			String endKey) throws IOException;

	/**
	 * 百度新老访客统计
	 * @param startKey
	 * @param endKey
	 * @return
	 * @throws IOException
	 */
	List<NOVisitorsBean> getONVisitorsByKeyRange(String startKey, String endKey)
			throws IOException;

	/**
	 * 百度访客来源统计
	 * @param startKey
	 * @param endKey
	 * @return
	 * @throws IOException
	 */
	List<VisitorsSourcesBean> getVisitorsSourcesByKeyRange(String startKey,
			String endKey) throws IOException;
	
	/**
	 * 百度访客地域统计
	 * @param startKey
	 * @param endKey
	 * @return
	 * @throws IOException
	 */
	List<CommonBean> getVisitorAreasByDate(String startKey, String endKey) throws IOException;
	
	
	/**
     * 百度访客地域统计
     * @param startKey
     * @param endKey
     * @return
     * @throws IOException
     */
    List<WorldVisitBean> getVisitorCountryByDate(String startKey, String endKey) throws IOException;
	
	
	/**
	 * 百度页面流量，退出次数统计
	 * @param startKey
	 * @param endKey
	 * @return
	 * @throws IOException
	 */
	List<TopPageBean> getTopPageCountByKeyRange(String startKey, String endKey) throws IOException;
}

package org.jumao.bi.dao.baidu.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.baidu.IBaiduDao;
import org.jumao.bi.entites.baidu.DailyMetricBean;
import org.jumao.bi.entites.baidu.NOVisitorsBean;
import org.jumao.bi.entites.baidu.TopPageBean;
import org.jumao.bi.entites.baidu.VisitorsSourcesBean;
import org.jumao.bi.entites.baidu.WorldVisitBean;
import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.commons.frameworks.jmframework.commutil.MRConstants;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Repository("baiduDao")
public class BaiduDaoImpl implements IBaiduDao {

	@Autowired
	IHBaseDao hbasedao;

	private static final byte[] Column_Family_Name = Bytes.toBytes(MRConstants.TAB_CF);
	private static final String Invalid_Data = "--";

	/**
	 * 获取一段时间内百度指标数据
	 * @see org.jumao.bi.dao.baidu.IBaiduDao#retrieveMetricDataByKeyRange(java.lang.String, java.lang.String)
	 */
	public List<DailyMetricBean> retrieveMetricDataByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_DAILY_STAT_TAB, startKey, endKey);

		byte[] platformIdField = Bytes.toBytes("platformId");
		byte[] dateField = Bytes.toBytes("date");
		byte[] pvField = Bytes.toBytes("pv");
		byte[] uvField = Bytes.toBytes("uv");
		byte[] ipsField = Bytes.toBytes("ips");
		// byte[] entrancesField = Bytes.toBytes("entrances");
		byte[] exitsField = Bytes.toBytes("exits");
		byte[] stayTimeField = Bytes.toBytes("stayTime");
		byte[] avgVisitPagesField = Bytes.toBytes("avgVisitPages");
		List<DailyMetricBean> dataResult = new ArrayList<DailyMetricBean>();

		for (Result result : results) {
			DailyMetricBean overview = new DailyMetricBean();
			if(Invalid_Data.equals(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, pvField))))) {//无效数据，跳过
				continue;
			}
			overview.setDate(Bytes.toString(result.getValue(Column_Family_Name, dateField)));
			overview.setPlatform(Bytes.toString(result.getValue(Column_Family_Name,
					platformIdField)));
			overview.setPv(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, pvField))));
			overview.setUv(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, uvField))));
			overview.setIps(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, ipsField))));
			// overview.setEntrances(convertString(Bytes.toString(result.getValue(CF_NAME,
			// entrancesField))));
			overview.setExits(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, exitsField))));
			overview.setStayTime(convertString(Bytes.toString(result.getValue(
					Column_Family_Name, stayTimeField))));
			overview.setAvgVisitPages(convertString(Bytes.toString(result
					.getValue(Column_Family_Name, avgVisitPagesField))));
			dataResult.add(overview);
		}

		return dataResult;

	}

	private String convertString(String sourceStr) {

		return StringUtils.isNotBlank(sourceStr) ? sourceStr : "0"; // 赋默认值为0

	}

	/**
	 * 查询一段时间内新老访客数据
	 * @see org.jumao.bi.dao.baidu.IBaiduDao#getONVisitorsByKeyRange(java.lang.String, java.lang.String)
	 */
	public List<NOVisitorsBean> getONVisitorsByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_VISITOR_TAB, startKey, endKey);

		byte[] pvCountB = Bytes.toBytes("pvCount");
		byte[] visitorCountB = Bytes.toBytes("visitorCount");
		byte[] newVisitorCountB = Bytes.toBytes("newVisitorCount");
		byte[] avgVisitTimeB = Bytes.toBytes("avgVisitTime");
		byte[] avgVisitPageB = Bytes.toBytes("avgVisitPage");
		List<NOVisitorsBean> visitors = new ArrayList<NOVisitorsBean>();
		for (Result result : results) {
			NOVisitorsBean visitor = new NOVisitorsBean();
			
			String avgVisitTime = Bytes.toString(result.getValue(Column_Family_Name,
					avgVisitTimeB));
			if (Invalid_Data.equals(avgVisitTime)) {//无效数据
				continue;
			}
			String newVisitorCount = Bytes.toString(result.getValue(Column_Family_Name,
					newVisitorCountB));
			String pvCount = Bytes.toString(result.getValue(Column_Family_Name, pvCountB));
			String visitorCount = Bytes.toString(result.getValue(Column_Family_Name,
					visitorCountB));
			String avgVisitPage = Bytes.toString(result.getValue(Column_Family_Name,
					avgVisitPageB));
			visitor.setAvgVisitPage(Double.parseDouble(avgVisitPage));
			visitor.setAvgVisitTime(Double.parseDouble(avgVisitTime));
			visitor.setNewVisitorCount(new Integer(newVisitorCount));
			visitor.setPvCount(new Integer(pvCount));
			visitor.setVisitorCount(new Integer(visitorCount));

			visitors.add(visitor);
		}

		return visitors;
	}

	/**
	 * 查询一段时间内访客来源数据
	 * @see org.jumao.bi.dao.baidu.IBaiduDao#getVisitorsSourcesByKeyRange(java.lang.String, java.lang.String)
	 */
	public List<VisitorsSourcesBean> getVisitorsSourcesByKeyRange(
			String startKey, String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
            		MRConstants.JMBI_BAIDU_SOURCES_TAB, startKey, endKey);
       
		List<VisitorsSourcesBean> visitorSources = new ArrayList<VisitorsSourcesBean>();
		byte[] dateB = Bytes.toBytes("date");
		byte[] valuesB = Bytes.toBytes("value");

		for (Result result : results) {
			
			String date = Bytes.toString(result.getValue(Column_Family_Name, dateB));
			String json = Bytes.toString(result.getValue(Column_Family_Name, valuesB));
			if("{}".equals(json) || "{}{}".equals(json)) {//过滤无效数据
			    continue;
			}
			JSONObject job = JSON.parseObject(json);
			Set<Entry<String, Object>> set = job.entrySet();
			for (Entry<String, Object> entry : set) {
				VisitorsSourcesBean bean = new VisitorsSourcesBean();
				bean.setDate(date);
				bean.setName(entry.getKey());
				String values = entry.getValue().toString();
				if(Invalid_Data.equals(values)) {//无效数据
					continue;
				}
				bean.setMetrics(values);
				//bean.setValue(Integer.parseInt(entry.getValue().toString()));
				visitorSources.add(bean);
			}
		}


		return visitorSources;
	}

	/**
	 * 查询一段时间内访客地域数据
	 * @see org.jumao.bi.dao.baidu.IBaiduDao#getVisitorAreasByDate(java.lang.String, java.lang.String)
	 */
	public List<CommonBean> getVisitorAreasByDate(String startKey, String endKey)
			throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_AREAS_TAB, startKey, endKey);

		byte[] nameB = Bytes.toBytes("name");
		byte[] valueB = Bytes.toBytes("value");
		List<CommonBean> vistorAreas = new ArrayList<CommonBean>();
		for (Result result : results) {
			String name = Bytes.toString(result.getValue(Column_Family_Name, nameB));
			String value = Bytes.toString(result.getValue(Column_Family_Name, valueB));
			if(Invalid_Data.equals(value)) {//无效数据
				continue;
			}
			CommonBean areaBean = new CommonBean(name, value);
			vistorAreas.add(areaBean);
		}

		return vistorAreas;
	}

	/**
	 * 获取页面排行时间范围内数量
	 * @see org.jumao.bi.dao.baidu.IBaiduDao#getTopPageCountByKeyRange(java.lang.String, java.lang.String)
	 */
	public List<TopPageBean> getTopPageCountByKeyRange(String startKey,
			String endKey) throws IOException {

		List<Result> results = hbasedao.getRows(
				MRConstants.JMBI_BAIDU_TOP_PAGE_TAB, startKey, endKey);

		byte[] nameB = Bytes.toBytes("name");
		byte[] pvCountB = Bytes.toBytes("pvCount");
		byte[] existCountB = Bytes.toBytes("existCount");

		List<TopPageBean> topPages = new ArrayList<TopPageBean>();
		for (Result result : results) {
			String name = Bytes.toString(result.getValue(Column_Family_Name, nameB));
			boolean isFilter = name.contains(".pre") || name.contains(".test") 
			        || name.contains("192.") || name.contains("172.") 
			        || name.contains("localhost") || name.contains("zsdazong");
			if(isFilter) {//过滤掉测试，和预发环境的url统计
			    continue;
			}
			String pvCount = Bytes.toString(result.getValue(Column_Family_Name, pvCountB));
			if(Invalid_Data.equals(pvCount)) {//无效数据
				continue;
			}
			String existCount = Bytes.toString(result.getValue(Column_Family_Name,
					existCountB));
			TopPageBean topBean = new TopPageBean(name, new BigDecimal(pvCount), new BigDecimal(existCount));
			topPages.add(topBean);
		}

		return topPages;
	}

    @Override
    public List<WorldVisitBean> getVisitorCountryByDate(String startKey, String endKey) throws IOException {
       
        List<Result> results = hbasedao.getRows(
                "jmbi:baidu_visit_world", startKey, endKey);

        byte[] countryB = Bytes.toBytes("country");
        byte[] pvB = Bytes.toBytes("pv");
        byte[] uvB = Bytes.toBytes("uv");
        byte[] ipB = Bytes.toBytes("ip");
        
        List<WorldVisitBean> vistorWorld = new ArrayList<WorldVisitBean>();
        for (Result result : results) {
            String country = Bytes.toString(result.getValue(Column_Family_Name, countryB));
            String pv = Bytes.toString(result.getValue(Column_Family_Name, pvB));
            String uv = Bytes.toString(result.getValue(Column_Family_Name, uvB));
            String ip = Bytes.toString(result.getValue(Column_Family_Name, ipB));

            WorldVisitBean areaBean = new WorldVisitBean(country, new BigDecimal(pv),
                    new BigDecimal(uv), new BigDecimal(ip));
            vistorWorld.add(areaBean);
        }

        return vistorWorld;

    }

}

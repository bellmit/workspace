package org.jumao.bi.service.impl.baidu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.baidu.IBaiduDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.baidu.AreasDistrictResponse;
import org.jumao.bi.entites.baidu.DailyMetricBean;
import org.jumao.bi.entites.baidu.MetricDescBean;
import org.jumao.bi.entites.baidu.NOVisitorsBean;
import org.jumao.bi.entites.baidu.OverviewResponse;
import org.jumao.bi.entites.baidu.TopPageBean;
import org.jumao.bi.entites.baidu.TopPageResponse;
import org.jumao.bi.entites.baidu.TrendResponse;
import org.jumao.bi.entites.baidu.VisitorsSourcesBean;
import org.jumao.bi.entites.baidu.VistSourcesResponse;
import org.jumao.bi.entites.baidu.VistorsTypeResponse;
import org.jumao.bi.entites.baidu.WorldDistrictResponse;
import org.jumao.bi.entites.baidu.WorldVisitBean;
import org.jumao.bi.entites.charts.CommonBean;
import org.jumao.bi.service.baidu.IBaiduSvc;
import org.jumao.bi.utis.ValidUtils;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BaiduSvcImpl implements IBaiduSvc {
	@Autowired
	private IBaiduDao baiduDao;

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 百度指标趋势接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getTimetrend(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getTimetrend(String platform, String startDate,
			String endDate) {

		writeLogs(String.format("start getTimetrend>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

		TrendResponse response = new TrendResponse();
		String validMsg = validatRequest(platform, startDate, endDate);
		if (null != validMsg) {//输入参数问题，错误的请求
			response.setStatus(new ResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE, validMsg));
			return Response.serverError().entity(response).build();
		}

		try {
			// 校验输入日期格式，并转为连续日期集合，出现异常则直接返回错误的日期格式请求问题
			List<String> dates = CalendarUtils.getAllDatesByPeriod(startDate,
					endDate);

			// 查询数据
			String startKey = platform + startDate;
			String endKey = platform + CalendarUtils.nextDateStr(endDate);
			List<DailyMetricBean> data = baiduDao.retrieveMetricDataByKeyRange(
					startKey, endKey);

			// 构建趋势统计图数据格式
			Map<String, Object> resultMap = new HashMap<String, Object>();
			BaiduSvcHelper.buildTrendCharts(data, dates, resultMap);

			// 构造服务响应结果
			BaiduSvcHelper.buildTrendResponse(response, resultMap);
			response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));


		} catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
			response.setStatus(new ResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
							+ e.getMessage()));
			writeLogs("Baidu trend statistics error: " + e.getMessage());

			return Response.serverError().entity(response).build();
		}

		writeLogs("end getTimetrend.");

		return Response.ok().entity(response).build();
	}

	/**
	 * 百度访问页面排名接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getVistTop(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getVistTop(String platform, String startDate, String endDate) {
	    
	    writeLogs(String.format("start getVistTop>>platform:: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

		TopPageResponse response = new TopPageResponse();
		
		String validMsg = validatRequest(platform, startDate, endDate);
		if (null != validMsg) {//输入参数问题，错误的请求
			response.setStatus(new ResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE, validMsg));
			return Response.serverError().entity(response).build();
		}


		try {
			// 查询数据
			String startKey = platform + startDate;
			String endKey = platform + CalendarUtils.nextDateStr(endDate);
			List<TopPageBean> data = baiduDao.getTopPageCountByKeyRange(
					startKey, endKey);

			// 构建流量排名统计图数据格式
			Map<String, Object> resultMap = new HashMap<String, Object>();
			BaiduSvcHelper.buildBarCharts(data, resultMap);

			// 构造服务响应结果
			BaiduSvcHelper.buildVistTopResponse(response, resultMap);
			response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));

		} catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
			response.setStatus(new ResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
							+ e.getMessage()));
			writeLogs("Baidu vist top page statistics error: " + e.getMessage());

			return Response.serverError().entity(response).build();
		}

		writeLogs("end getVistTop.");

		return Response.ok().entity(response).build();

	}

	/**
	 * 百度访问来源接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getVistSources(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getVistSources(String platform, String startDate,
			String endDate) {
	    
	    writeLogs(String.format("start getVistSources>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

		VistSourcesResponse response = new VistSourcesResponse();
		
		String validMsg = validatRequest(platform, startDate, endDate);
		if (null != validMsg) {//输入参数问题，错误的请求
			response.setStatus(new ResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE, validMsg));
			return Response.serverError().entity(response).build();
		}


		try {
			// 查询数据
			String startKey = platform + startDate;
			String endKey = platform + CalendarUtils.nextDateStr(endDate);
			List<VisitorsSourcesBean> data = baiduDao
					.getVisitorsSourcesByKeyRange(startKey, endKey);

			// 构建访问来源统计图数据格式
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<String> dates = CalendarUtils.getAllDatesByPeriod(startDate,
					endDate);
			BaiduSvcHelper.buildVistSourcesCharts(data, dates, resultMap);

			// 构造服务响应结果
			BaiduSvcHelper.buildVistSourceResponse(response, resultMap);
			response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));

		} catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
			response.setStatus(new ResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
							+ e.getMessage()));
			writeLogs("Baidu vist sources statistics error: " + e.getMessage());

			return Response.serverError().entity(response).build();
		}

		writeLogs("end getVistSources.");

		return Response.ok().entity(response).build();

	}

	/**
	 * 百度新老访客接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getVistorsType(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getVistorsType(String platform, String startDate,
			String endDate) {
	    
	    writeLogs(String.format("start getVistorsType>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

		VistorsTypeResponse response = new VistorsTypeResponse();
		
		String validMsg = validatRequest(platform, startDate, endDate);
		if (null != validMsg) {//输入参数问题，错误的请求
			response.setStatus(new ResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE, validMsg));
			return Response.serverError().entity(response).build();
		}


		try {
			// 查询数据
			String startKey = platform + startDate;
			String endKey = platform + CalendarUtils.nextDateStr(endDate);
			List<NOVisitorsBean> data = baiduDao.getONVisitorsByKeyRange(
					startKey, endKey);

			// 构建用户访问类别统计图数据格式
			Map<String, Object> resultMap = new HashMap<String, Object>();
			BaiduSvcHelper.buildTableCharts(data, resultMap);

			// 构造服务响应结果
			BaiduSvcHelper.buildVistorsTypeResponse(response, resultMap);
			response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));

		} catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
			response.setStatus(new ResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
							+ e.getMessage()));
			writeLogs("Baidu vistors type page statistics error: " + e.getMessage());

			return Response.serverError().entity(response).build();
		}

		writeLogs("end getVistorsType.");

		return Response.ok().entity(response).build();
	}

	/**
	 *百度访问地域接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getAreasDistrict(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getAreasDistrict(String platform, String startDate,
			String endDate) {
	    
	    writeLogs(String.format("start getAreasDistrict>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

		AreasDistrictResponse response = new AreasDistrictResponse();
		
		String validMsg = validatRequest(platform, startDate, endDate);
		if (null != validMsg) {//输入参数问题，错误的请求
			response.setStatus(new ResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE, validMsg));
			return Response.serverError().entity(response).build();
		}


		try {
			// 查询数据
			String startKey = platform + startDate;
			String endKey = platform + CalendarUtils.nextDateStr(endDate);
			List<CommonBean> data = baiduDao.getVisitorAreasByDate(startKey,
					endKey);

			// 构建地域分布统计图数据格式
			Map<String, Object> resultMap = new HashMap<String, Object>();
			BaiduSvcHelper.buildGrapCharts(data, resultMap);

			// 构造服务响应结果
			BaiduSvcHelper.buildAreasDistrictResponse(response, resultMap);
			response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));

		} catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
			response.setStatus(new ResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
							+ e.getMessage()));
			writeLogs("Baidu areas district statistics error: " + e.getMessage());

			return Response.serverError().entity(response).build();
		}

		writeLogs("end getAreasDistrict.");

		return Response.ok().entity(response).build();

	}

	private void writeLogs(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}


	/**
	 * 百度数据概览接口
	 * @see org.jumao.bi.service.baidu.IBaiduSvc#getBaiduOverview(java.lang.String, java.lang.String, java.lang.String)
	 */
    public Response getBaiduOverview(String platform, String startDate, String endDate) {

        writeLogs(String.format("start baidu overview>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

        OverviewResponse response = new OverviewResponse();
        String validMsg = validatRequest(platform, startDate, endDate);
        if (null != validMsg) {//输入参数问题，错误的请求
            response.setStatus(new ResponseResult(
                    ServiceConst.INVADLID_REQUEST_CODE, validMsg));
            return Response.serverError().entity(response).build();
        }

        try {

            // 查询数据
            String startKey = platform + startDate;
            String endKey = platform + CalendarUtils.nextDateStr(endDate);
            // 总览其实和每天的趋势到这来功能都类似，也可以把这两个接口合并，为了接口隔离和前端对接方便分成两个接口，
            List<DailyMetricBean> data = baiduDao.retrieveMetricDataByKeyRange(
                    startKey, endKey);

            // 构建趋势统计图数据格式
            List<MetricDescBean> overview = new ArrayList<MetricDescBean>();
            BaiduSvcHelper.buildBaiduOverview(data, overview);

            // 构造服务响应结果
            response.setOverview(overview);
            response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE,
                    ServiceConst.SUCCESS_MSG));


        } catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
            response.setStatus(new ResponseResult(
                    ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
                            + e.getMessage()));
            writeLogs("Baidu overview statistics error: " + e.getMessage());

            return Response.serverError().entity(response).build();
        }

        writeLogs("end baidu overview.");

        return Response.ok().entity(response).build();

    }

    @Override
    public Response getWorldDistrict(String platform, String startDate, String endDate) {
        writeLogs(String.format("start getWorldDistrict>>platform: %s, startDate: %s, endDate: %s.", platform, startDate, endDate));

        WorldDistrictResponse response = new WorldDistrictResponse();
        
        String validMsg = validatRequest(platform, startDate, endDate);
        if (null != validMsg) {//输入参数问题，错误的请求
            response.setStatus(new ResponseResult(
                    ServiceConst.INVADLID_REQUEST_CODE, validMsg));
            return Response.serverError().entity(response).build();
        }


        try {
            // 查询数据
            String startKey = platform + startDate;
            String endKey = platform + CalendarUtils.nextDateStr(endDate);
            List<WorldVisitBean> data = baiduDao.getVisitorCountryByDate(startKey,
                    endKey);

            // 构建地域分布统计图数据格式
            Map<String, Object> resultMap = new HashMap<String, Object>();
            BaiduSvcHelper.buildWorldCharts(data, response);

            // 构造服务响应结果
            response.success();
            

        } catch (Exception e) {// 服务端异常HBASE问题或其它不可预知的异常
            response.setStatus(new ResponseResult(
                    ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG
                            + e.getMessage()));
            writeLogs("Baidu World district statistics error: " + e.getMessage());

            return Response.serverError().entity(response).build();
        }

        writeLogs("end getWorldDistrict.");

        return Response.ok().entity(response).build();

    }
    
    private String validatRequest(String platform, String startDate,
            String endDate) {

        if (null == platform || null == startDate || null == endDate) {
            return "传入了空值的参数!";
        } else if (!ValidUtils.isNumeric(platform)) {
            return "非法的平台参数!";
        } else if (!ValidUtils.isCorrectDateRange(startDate, endDate)) {
            return "非法的日期格式或日期范围!";
        }
        return null;
    }



}

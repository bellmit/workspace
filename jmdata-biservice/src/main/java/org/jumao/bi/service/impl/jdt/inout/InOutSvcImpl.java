package org.jumao.bi.service.impl.jdt.inout;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jdt.inout.IInOutDao;
import org.jumao.bi.entites.AreaBarCharts;
import org.jumao.bi.entites.InOutCountryBean;
import org.jumao.bi.entites.InOutCustomsBean;
import org.jumao.bi.entites.InOutGoodsBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.ResultList;
import org.jumao.bi.service.jdt.inout.IInOutSvc;
import org.jumao.bi.utis.LogUtils;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class InOutSvcImpl implements IInOutSvc {

	@Autowired
	IInOutDao inOutDao;

	private Logger logger = Logger.getLogger(this.getClass());
	private static final String Customs_Code_All = "all";
	private static final String Excel_Export = "export"; //Excel导出功能
	private static final String Non_Excel_Export = "not"; //Excel导出功能
	private static final String Import_Type = "1";//进口
	private static final String Export_Type = "2";//出口
	private static final String Parameter_In = "in";//参数类型为进口in
	private static final String Parameter_Out = "out";//参数类型为出口out
	private static final String Underline_Str = "_";
	private static final int Top_Five = 5;
	private static final int Round_Four = 4;
	private static final int Ten_Thousand_Num = 10000;


	/**
	 * 进出口分类统计：进出口贸易国别top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutCountryAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutCountryAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {
        LogUtils.writeLogs(logger, 
                String.format("start getInOutCountryAmount>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode || Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		AreaBarCharts areaBarCharts = new AreaBarCharts();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String title = "";
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
			title = "进口贸易国别TOP5";
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
			title = "出口贸易国别TOP5";
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutCountryBean> list;
		try {
			list = inOutDao
					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCountry(list, Non_Excel_Export);
			// }
			// 条形图：展示top5
			
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCountry(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			LogUtils.writeLogs(logger, "getInOutCountryAmount error: " + e.getMessage());
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}
	
	/**
	 * 进出口分类统计：出口贸易国别top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutCountryAmountExport(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutCountryAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {
        LogUtils.writeLogs(logger, 
                String.format("start getInOutCountryAmountExport>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode|| Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutCountryBean> result = new ResultList<InOutCountryBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
//		String title = "进口贸易国别TOP5";
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
//			title = "进口贸易国别TOP5";
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
//			title = "出口贸易国别TOP5";
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutCountryBean> list;
		try {
			list = inOutDao
					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();

			toplist = buildPeriodCountry(list, Excel_Export);
 
			return Response.ok().entity(toplist).build();// 导出返回List,

		} catch (IOException e) {
			LogUtils.writeLogs(logger, "getInOutCountryAmountExport error: " + e.getMessage());
			result.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(result).build();
		}

	}

//	public Response getInOutCountryAmountExport(String timeType,
//			String startDate, String endDate, String customsCode, String inout) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("start getInCountryAmount>>timeType: " + timeType
//					+ " startDate:" + startDate + " endDate:" + endDate
//					+ " customsCode:" + customsCode + " inout:" + inout);
//		}
//		if(null==customsCode||"all".equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
//			customsCode="";
//		}
//		ResultList<InOutCountryBean> result = new ResultList<InOutCountryBean>();
//		boolean validRequest = (null != timeType && null != startDate && null != endDate);
//		if (!validRequest) {
//			result.setStatus(buildResponseResult(
//					ServiceConst.INVADLID_REQUEST_CODE,
//					ServiceConst.REQUEST_MSG));
//			return Response.serverError().entity(result).build();
//		}
//
//		String toDate = endDate;// hbase查询：结束时间：后退一天
//		toDate = CalendarUtils.nextDateStr(endDate);
//		String inout_type = "1";// 进出口类型：1:进口；2:出口
//		if ("in".equals(inout)) {
//			inout_type = "1";
//		} else if ("out".equals(inout)) {
//			inout_type = "2";
//		}
//		String startKey = inout_type + "_" + startDate + "_" + customsCode;
//		String endKey = inout_type + "_" + toDate + "_" + customsCode;
//
//		List<InOutCountryBean> list;
//		try {
//			list = inOutDao
//					.retrieveInOutCountryAmountByPeriod(startKey, endKey,customsCode);
//			List<InOutCountryBean> toplist = new ArrayList<InOutCountryBean>();
//			// if ("week".equals(type)) {
//			toplist = buildPeriodCountry(list, "export");
//			// }
//
//			// 导出全部top
//		
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
//			return Response.ok().entity(result).build();// 导出返回List,
//			// List<String[]> result = new ArrayList<String[]>();
//			// for (int i = 0; i < toplist.size(); i++) {
//			// wordData.get(i).setIndex(i + 1);// 排序
//			// String[] array = { wordData.get(i).getIndex() + "",
//			// wordData.get(i).getName(), wordData.get(i).getType(),
//			// wordData.get(i).getIndustry(),
//			// wordData.get(i).getCount() + "",
//			// wordData.get(i).getAmount().toPlainString(),
//			// wordData.get(i).getTopGoods() };
//			// result.add(array);
//			// }
//			//
//			// ExportExcel eu = new ExportExcel();
//			// HSSFWorkbook workbook = eu.exportExcel(
//			// "排名,公司名称,公司性质,所属行业,下单数量,下单金额(万),商品名称", "buy", result);
//			// ByteArrayOutputStream output = new ByteArrayOutputStream();
//			// workbook.write(output);
//			//
//			// byte[] ba = output.toByteArray();
//			// excelFile = new ByteArrayInputStream(ba);
//			// output.flush();
//			// output.close();
//
//		} catch (IOException e) {
//			if (logger.isDebugEnabled()) {
//				logger.debug("index error: " + e.getMessage());
//			}
//			result.setStatus(buildResponseResult(
//					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
//			return Response.serverError().entity(result).build();
//		}
//
//	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private AreaBarCharts buildAreaBarChartsCountry(
			List<InOutCountryBean> toplist, String title) {
		AreaBarCharts charts = new AreaBarCharts();

		List<String> yaxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yaxisData.add(toplist.get(i).getCountryName());
				seriesData.add(toplist.get(i).getTotalAmount());
			}
		}

		charts.setTitle(title);
		charts.setSeriesName("");
		charts.setyAxisData(yaxisData);
		charts.setSeriesData(seriesData);
		return charts;
	}

	/**
	 * 
	 * @param list
	 * @param isExport
	 *            :: export导出数据，不光取前10
	 * @return
	 * @throws IOException
	 */
	private List<InOutCountryBean> buildPeriodCountry(
			List<InOutCountryBean> list, String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutCountryBean>() {
			public int compare(InOutCountryBean arg0, InOutCountryBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutCountryBean> topList = new ArrayList<InOutCountryBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(Ten_Thousand_Num);// 转化成：万美元
		for (InOutCountryBean deal : list) {
			if (!Excel_Export.equals(isExport)) {// 导出数据，不光取前10
				if (i == Top_Five) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(Round_Four, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口贸易国别top======================

	//

	//

	// ===============进出口分类统计：进出口口岸top======================
	/**
	 * 进出口分类统计：进出口口岸top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutCustomsAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutCustomsAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {

        LogUtils.writeLogs(logger, 
                String.format("start getInOutCustomsAmount>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode||Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		AreaBarCharts areaBarCharts = new AreaBarCharts();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String title = "";
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
			title = "进口口岸TOP5";
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
			title = "出口口岸TOP5";
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutCustomsBean> list;
		try {
			list = inOutDao
					.retrieveInOutCustomsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCustomsBean> toplist = new ArrayList<InOutCustomsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCustoms(list, Non_Excel_Export);
			// }
			// 条形图：展示top5
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsCustoms(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {

			LogUtils.writeLogs(logger, "getInOutCustomsAmount error: " + e.getMessage());
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	/**
	 * 进出口分类统计：出口口岸top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutCustomsAmountExport(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutCustomsAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {

        LogUtils.writeLogs(logger, 
                String.format("start getInOutCustomsAmountExport>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode|| Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutCustomsBean> result = new ResultList<InOutCustomsBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutCustomsBean> list;
		try {
			list = inOutDao
					.retrieveInOutCustomsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutCustomsBean> toplist = new ArrayList<InOutCustomsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodCustoms(list, Excel_Export);
			// }
			// 导出全部top
			 
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
			return Response.ok().entity(toplist).build();// 导出返回List,

		} catch (IOException e) {
			LogUtils.writeLogs(logger, "getInOutCustomsAmountExport error: " + e.getMessage());		
			result.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(result).build();
		}

	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private AreaBarCharts buildAreaBarChartsCustoms(
			List<InOutCustomsBean> toplist, String title) {
		AreaBarCharts charts = new AreaBarCharts();

		List<String> yaxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yaxisData.add(toplist.get(i).getCustomsName());
				seriesData.add(toplist.get(i).getTotalAmount());
			}
		}

		charts.setTitle(title);
		charts.setSeriesName("");
		charts.setyAxisData(yaxisData);
		charts.setSeriesData(seriesData);
		return charts;
	}

	/**
	 * 
	 * @param list
	 * @param isExport
	 *            :: export导出数据，不光取前10
	 * @return
	 * @throws IOException
	 */
	private List<InOutCustomsBean> buildPeriodCustoms(
			List<InOutCustomsBean> list, String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutCustomsBean>() {
			public int compare(InOutCustomsBean arg0, InOutCustomsBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutCustomsBean> topList = new ArrayList<InOutCustomsBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(Ten_Thousand_Num);// 转化成：万美元
		for (InOutCustomsBean deal : list) {
			if (!Excel_Export.equals(isExport)) {// 导出数据，不光取前10
				if (i == Top_Five) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(Round_Four, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口口岸top======================

	//

	//

	// ===============进出口分类统计：进出口商品top======================
	/**
	 * 进出口分类统计：进出口商品top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutGoodsAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutGoodsAmount(String timeType, String startDate,
			String endDate, String customsCode, String inout) {
        LogUtils.writeLogs(logger, 
                String.format("start getInOutGoodsAmount>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode || Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		AreaBarCharts areaBarCharts = new AreaBarCharts();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String title = "";
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
			title = "进口商品TOP5";
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
			title = "出口商品TOP5";
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutGoodsBean> list;
		try {
			list = inOutDao.retrieveInOutGoodsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutGoodsBean> toplist = new ArrayList<InOutGoodsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodGoods(list, Non_Excel_Export);
			// }
			// 条形图：展示top5
			
			// charts = buildCompanyAreaCharts(weekMap, title);
			areaBarCharts = buildAreaBarChartsGoods(toplist, title);
			areaBarCharts.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
					ServiceConst.SUCCESS_MSG));
			return Response.ok().entity(areaBarCharts).build();

		} catch (IOException e) {
			LogUtils.writeLogs(logger, "getInOutGoodsAmount error: " + e.getMessage());			
			areaBarCharts.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(areaBarCharts).build();
		}

	}

	/**
	 * 进出口分类统计：出口商品top
	 * @see org.jumao.bi.service.jdt.inout.IInOutSvc#getInOutGoodsAmountExport(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response getInOutGoodsAmountExport(String timeType,
			String startDate, String endDate, String customsCode, String inout) {

	    LogUtils.writeLogs(logger, 
                String.format("start getInOutGoodsAmountExport>>timeType: %s, startDate: %s, endDate: %s, customsCode: %s, inout:%s", timeType, startDate, endDate, customsCode, inout));
		
		if(null==customsCode|| Customs_Code_All.equals(customsCode)){//当前端选择关区选全国时，这里设置为空字符，后面查询
			customsCode="";
		}
		ResultList<InOutGoodsBean> result = new ResultList<InOutGoodsBean>();
		boolean validRequest = (null != timeType && null != startDate && null != endDate);
		if (!validRequest) {
			result.setStatus(buildResponseResult(
					ServiceConst.INVADLID_REQUEST_CODE,
					ServiceConst.REQUEST_MSG));
			return Response.serverError().entity(result).build();
		}

		String toDate = endDate;// hbase查询：结束时间：后退一天
		toDate = CalendarUtils.nextDateStr(endDate);
		String inoutType = Import_Type;// 进出口类型：1:进口；2:出口
		if (Parameter_In.equals(inout)) {
			inoutType = Import_Type;
		} else if (Parameter_Out.equals(inout)) {
			inoutType = Export_Type;
		}
		String startKey = inoutType + Underline_Str + startDate + Underline_Str + customsCode;
		String endKey = inoutType + Underline_Str + toDate + Underline_Str + customsCode;

		List<InOutGoodsBean> list;
		try {
			list = inOutDao.retrieveInOutGoodsAmountByPeriod(startKey, endKey,customsCode);
			List<InOutGoodsBean> toplist = new ArrayList<InOutGoodsBean>();
			// if ("week".equals(type)) {
			toplist = buildPeriodGoods(list, Excel_Export);
			// }
			// 导出全部top
			 
//			result.setStatus(buildResponseResult(ServiceConst.SUCCESS_CODE,
//					ServiceConst.SUCCESS_MSG));
//			result.setList(toplist);
			return Response.ok().entity(toplist).build();// 导出返回List,
	 
		} catch (IOException e) {
			LogUtils.writeLogs(logger, "getInOutGoodsAmountExport error: " + e.getMessage());
			result.setStatus(buildResponseResult(
					ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG));
			return Response.serverError().entity(result).build();
		}

	}

	/**
	 * 构建 条形图
	 * 
	 * @param toplist
	 * @param title
	 * @return
	 */
	private AreaBarCharts buildAreaBarChartsGoods(List<InOutGoodsBean> toplist,
			String title) {
		AreaBarCharts charts = new AreaBarCharts();
		
		List<String> yaxisData = new ArrayList<String>();
		//
		List<BigDecimal> seriesData = new ArrayList<BigDecimal>();

		if (toplist.size() > 0) {//
			for (int i = 0; i < toplist.size(); i++) {
				yaxisData.add(toplist.get(i).getGoodsName());
				seriesData.add(toplist.get(i).getTotalAmount());
			}
		}
 
		charts.setTitle(title);
		charts.setSeriesName("");
		charts.setyAxisData(yaxisData);
		charts.setSeriesData(seriesData);
		return charts;
	}

	/**
	 * 
	 * @param list
	 * @param isExport
	 *            :: export导出数据，不光取前10
	 * @return
	 * @throws IOException
	 */
	private List<InOutGoodsBean> buildPeriodGoods(List<InOutGoodsBean> list,
			String isExport) throws IOException {
		// 排序：从hbase里面查询出来的是乱序
		Collections.sort(list, new Comparator<InOutGoodsBean>() {
			public int compare(InOutGoodsBean arg0, InOutGoodsBean arg1) {
				return arg1.getTotalAmount().compareTo(arg0.getTotalAmount());
			}
		});

		List<InOutGoodsBean> topList = new ArrayList<InOutGoodsBean>();
		int i = 0;
		BigDecimal div = new BigDecimal(Ten_Thousand_Num);// 转化成：万美元
		for (InOutGoodsBean deal : list) {
			if (!Excel_Export.equals(isExport)) {// 导出数据，不光取前10
				if (i == Top_Five) {
					break;// 只取top5
				}
			}
			i = i + 1;
			deal.setTotalAmount((deal.getTotalAmount().divide(div)).setScale(Round_Four, BigDecimal.ROUND_HALF_UP));//保留几位小数
			deal.setIndex(i);
			topList.add(deal);
		}
		return topList;
	}

	// ===============进出口分类统计：进出口商品top======================

	private ResponseResult buildResponseResult(String code, String message) {
		ResponseResult result = new ResponseResult();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

}
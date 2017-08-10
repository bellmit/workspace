package org.jumao.bi.service.trade.register;

import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.RegUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 注册分析相关接口
 * @author chen qian
 */
@Path("/v1/register/")
@Produces(MediaType.APPLICATION_JSON)
public interface RegisterAnalysis {

	/**
	 * 新增注册数 折线图
	 */
	@GET
	@Path(RegUrl.Newlyincr_Linechart)
	public Response getNewRegLineChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


	/**
	 * 平台 or 总站 新增注册数 折线图
	 */
	@GET
	@Path(RegUrl.Ms_Newlyincr_Linechart)
	public Response getMsNewRegLineChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


	/**
	 * 新增注册数 饼图
	 */
	@GET
	@Path(RegUrl.Newlyincr_Piechart)
	public Response getNewRegPieChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 平台 or 总站 新增注册数 饼图
	 */
	@GET
	@Path(RegUrl.Ms_Newlyincr_Piechart)
	public Response getMsNewRegPieChart(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 新增注册数 表格
	 */
	@GET
	@Path(RegUrl.Newlyincr_Table)
	public Response getNewRegTable(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate,
			@PathParam(Key.Limit) int limit,
			@PathParam(Key.Offset) int offset) throws Exception;

	/**
	 * 认证用户 概览折线图
	 */
	@GET
	@Path(RegUrl.Auth_User_Overview_Linechart)
	public Response getAuthUOverview(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate,
			@PathParam(Key.Type) String type) throws Exception;

	/**
	 * 认证用户 三证审核历史累积
	 */
	@GET
	@Path(RegUrl.Auth_User_Cert3_History)
	public Response getAuthUCert3History(
			@PathParam(Key.Platform) String platform) throws Exception;

	/**
	 * 认证用户 认证审核通过占比
	 */
	@GET
	@Path(RegUrl.Auth_User_Passed_Histogram)
	public Response getAuthUPassedHistogram(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 认证用户 三证合一比例饼图
	 */
	@GET
	@Path(RegUrl.Auth_User_Lic_Pie)
	public Response getAuthULicPercentPie(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 认证用户  三证审核、授权书地域分布 中国地图
	 */
	@GET
	@Path(RegUrl.Auth_User_Area_Dist)
	public Response getAuthUAreaDist(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 认证用户 三证审核、授权书地域分布TOP10
	 */
	@GET
	@Path(RegUrl.Auth_User_Area_Dist_Top10)
	public Response getAuthUAreaDistTop10(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 签章支付 概览折线图
	 */
	@GET
	@Path(RegUrl.Visa_And_Pay_Overview_Linechart)
	public Response getVisaAndPayOverview(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate,
			@PathParam(Key.Type) String type) throws Exception;

	/**
	 * 签章支付 历史累计 饼图
	 */
	@GET
	@Path(RegUrl.Visa_And_Pay_History_Pie)
	public Response getVisaAndPayHistoryPie(
			@PathParam(Key.Platform) String platform) throws Exception;


	/**
	 * 签章和支付 开通地域分布 中国地图
	 */
	@GET
	@Path(RegUrl.Visa_And_Pay_Area_Dist)
	public Response getVisaAndPayAreaDist(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;

	/**
	 * 签章 地域分布TOP10
	 */
	@GET
	@Path(RegUrl.Visa_Area_Dist_Top10)
	public Response getVisaAreaDistTop10(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


	/**
	 * 支付 地域分布TOP10
	 */
	@GET
	@Path(RegUrl.Pay_Area_Dist_Top10)
	public Response getPayAreaDistTop10(
			@PathParam(Key.Platform) String platform,
			@PathParam(Key.Start_Date) String startDate,
			@PathParam(Key.End_Date) String endDate) throws Exception;


}

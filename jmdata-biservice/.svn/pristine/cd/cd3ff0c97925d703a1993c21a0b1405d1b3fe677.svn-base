package org.jumao.bi.service.jdt.company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 进出口分类统计
 * 
 * @author Administrator
 *
 */
@Path("/v1/company")
@Produces(MediaType.APPLICATION_JSON)
public interface ICompanySvc {

	// =============企业分类统计：几个top统计=================

	/**
	 * 企业分类统计：由于几个top统计hbase的表结构一致；这里用一个方法。把表名传过来，进行查询
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param chart
	 *            查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；； 3、客户企业支出top
	 *            ；；4、拍档企业收入top；；5、客户企业:订单货值top；；6、拍档企业:订单货值top
	 * @return
	 */
	@GET
	@Path("/getCompanyAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/chart/{chart}")
	public Response getCompanyAmount(@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("chart") String chart);

	/**
	 * 企业分类统计：由于几个top统计hbase的表结构一致；这里用一个方法。把表名传过来，进行查询
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param chart
	 *            查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；； 3、客户企业支出top
	 *            ；；4、拍档企业收入top；；5、客户企业:订单货值top；；6、拍档企业:订单货值top
	 * @return
	 */
	@GET
	@Path("/getCompanyAmountExport/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/chart/{chart}")
	public Response getCompanyAmountExport(
			@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("chart") String chart);

	// =============企业分类统计：几个top统计=================
	
	
	//
	
	//
	
	
	// =============企业分类统计：新增企业、活动企业数量=================
	
	/**
	 * 企业分类统计：由于2个企业数统计hbase的表结构一致；
	 * 企业新增数||企业活跃数
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year {2个表，一个是天统计，一个是月统计}
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 *            海关/关区：编码
	 * @param chart
	 *            查询的图表：{企业分类统计的}1、企业新增数；；2、企业活跃数
	 * @return
	 */
	@GET
	@Path("/getCompanyNum/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/chart/{chart}")
	public Response getCompanyNum(
			@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("chart") String chart);
	
	// =============企业分类统计：新增企业、活动企业数量=================
}

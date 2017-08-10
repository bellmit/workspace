package org.jumao.bi.service.jdt.inout;

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
@Path("/v1/inout")
@Produces(MediaType.APPLICATION_JSON)
public interface IInOutSvc {

	// =============国别=================

	/**
	 * 进出口分类统计：进口贸易国别top
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param inout
	 *            in进口、out出口
	 * @return
	 */
	@GET
	@Path("/getInOutCountryAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
	public Response getInOutCountryAmount(
			@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("inout") String inout);

	/**
	 * 进出口分类统计：出口贸易国别top
	 * 
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param inout
	 *            in进口、out出口
	 * @return
	 */
	@GET
	@Path("/getInOutCountryAmountExport/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
	public Response getInOutCountryAmountExport(
			@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("inout") String inout);

	// =============国别=================
	
	//
	
	// =============海关/关区=================

		/**
		 * 进出口分类统计：进口口岸top
		 *
		 * @param timeType
		 *            时间类型：week、month、quarter、year
		 * @param startDate
		 *            开始时间
		 * @param endDate
		 *            结束时间
		 * @param customsCode
		 *            海关/关区：编码
		 * @param inout
		 *            in进口、out出口
		 * @return
		 */
		@GET
		@Path("/getInOutCustomsAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
		public Response getInOutCustomsAmount(
				@PathParam("timeType") String timeType,
				@PathParam("startDate") String startDate,
				@PathParam("endDate") String endDate,
				@PathParam("customsCode") String customsCode,
				@PathParam("inout") String inout);

		/**
		 * 进出口分类统计：出口口岸top
		 *
		 * @param timeType
		 *            时间类型：week、month、quarter、year
		 * @param startDate
		 *            开始时间
		 * @param endDate
		 *            结束时间
		 * @param customsCode
		 *            海关/关区：编码
		 * @param inout
		 *            in进口、out出口
		 * @return
		 */
		@GET
		@Path("/getInOutCustomsAmountExport/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
		public Response getInOutCustomsAmountExport(
				@PathParam("timeType") String timeType,
				@PathParam("startDate") String startDate,
				@PathParam("endDate") String endDate,
				@PathParam("customsCode") String customsCode,
				@PathParam("inout") String inout);

		// =============海关/关区=================

	
	//

	// =============商品=================

	/**
	 * 进出口分类统计：进口商品top
	 *
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param inout
	 *            in进口、out出口
	 * @return
	 */
	@GET
	@Path("/getInOutGoodsAmount/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
	public Response getInOutGoodsAmount(@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("inout") String inout);

	/**
	 * 进出口分类统计：出口商品top
	 *
	 * @param timeType
	 *            时间类型：week、month、quarter、year
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param customsCode
	 *            海关/关区：编码
	 * @param inout
	 *            in进口、out出口
	 * @return
	 */
	@GET
	@Path("/getInOutGoodsAmountExport/timeType/{timeType}/startDate/{startDate}/endDate/{endDate}/customsCode/{customsCode}/inout/{inout}")
	public Response getInOutGoodsAmountExport(
			@PathParam("timeType") String timeType,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("customsCode") String customsCode,
			@PathParam("inout") String inout);

	// =============商品=================

	
}

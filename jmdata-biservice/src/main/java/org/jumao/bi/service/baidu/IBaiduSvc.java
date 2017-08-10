package org.jumao.bi.service.baidu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 查询百度用户行为各种统计数据
 * 
 * @author 1
 *
 */
@Path("/v1/baidu/")
@Produces(MediaType.APPLICATION_JSON)
public interface IBaiduSvc {

    /**
     * 获取百度指标概览(pv总数,uv总数,ip总数,exists跳出率)
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("/overivew/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getBaiduOverview(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
    
    
    
    /**
	 * 获取百度指标趋势(pv,uv,stayTime,ips,exists,avgVisitPages)
	 * @param platform
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/trend/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
	public Response getTimetrend(@PathParam("platform") String platform,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
	
	/**
	 * 获取页面最大访问量和退出次数
	 * @param platform
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/vist/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
	public Response getVistTop(@PathParam("platform") String platform,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
	

	/**
	 * 获取访问来源
	 * @param platform
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/sources/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
	public Response getVistSources(@PathParam("platform") String platform,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
	
	/**
	 * 获取新老访客类型
	 * @param platform
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/vistors/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
	public Response getVistorsType(@PathParam("platform") String platform,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
	
	/**
	 * 获取访问地域分布
	 * @param platform
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/areas/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
	public Response getAreasDistrict(@PathParam("platform") String platform,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
	
	
	
    /**
     * 获取按国家
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("/world/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getWorldDistrict(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);



}

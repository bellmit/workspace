package org.jumao.bi.service.jyt.register;

import org.jumao.bi.utis.constants.JytCnEnUrl;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.RegUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 注册分析 日新增用户 和 认证分析 接口
 * @author chen qian
 */
@Path(JytCnEnUrl.V1_Jyt_Register)
@Produces(MediaType.APPLICATION_JSON)
public interface JytRegIncrAndAuthSvc {

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
     * 注册用户地域分布 中国地图，无数据，未提供sql
     */
//    @GET
//    @Path(JytCnEnUrl.Reg_Area_Dist)
//    public Response getRegAreaDist(
//            @PathParam(Key.Platform) String platform,
//            @PathParam(Key.Start_Date) String startDate,
//            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 注册用户地域分布 top10 条形图，无数据，未提供sql
     */
//    @GET
//    @Path(JytCnEnUrl.Reg_Area_Dist_Top10)
//    public Response getRegAreaDistTop10(
//            @PathParam(Key.Platform) String platform,
//            @PathParam(Key.Start_Date) String startDate,
//            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 新增用户来源 top5
     */
    @GET
    @Path(JytCnEnUrl.Newlyincr_Top5)
    public Response getNewRegTop5(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 新增用户来源 饼图
     */
    @GET
    @Path(RegUrl.Newlyincr_Piechart)
    public Response getNewRegPieChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    //---------------------------------------------
    //--------------  以下认证分析  ---------------
    //---------------------------------------------

    /**
     * 认证用户 概览折线图
     */
    @GET
    @Path(JytCnEnUrl.Auth_User_Linechart)
    public Response getAuthULineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;



    /**
     * 委托、供应 历史累积 饼图
     */
    @GET
    @Path(JytCnEnUrl.Auth_User_History)
    public Response getAuthUCert3History(
            @PathParam(Key.Platform) String platform) throws Exception;


    /**
     * 供应商地域分布 中国地图
     */
    @GET
    @Path(JytCnEnUrl.Supply_Area_Dist)
    public Response getSupplyAreaDist(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 供应商地域分布 top10
     */
    @GET
    @Path(JytCnEnUrl.Supply_Area_Dist_Top10)
    public Response getSupplyAreaDistTop10(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 委托商地域分布 中国地图
     */
    @GET
    @Path(JytCnEnUrl.Entrust_Area_Dist)
    public Response getEntrustAreaDist(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 委托商地域分布 top10
     */
    @GET
    @Path(JytCnEnUrl.Entrust_Area_Dist_Top10)
    public Response getEntrustAreaDistTop10(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 司机地域分布 中国地图，没数据，未提供sql
     */
    @GET
    @Path(JytCnEnUrl.Driver_Area_Dist)
    public Response getDriverAreaDist(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 司机域分布 top10，没数据，未提供sql
     */
    @GET
    @Path(JytCnEnUrl.Driver_Area_Dist_Top10)
    public Response getDriverAreaDistTop10(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


}

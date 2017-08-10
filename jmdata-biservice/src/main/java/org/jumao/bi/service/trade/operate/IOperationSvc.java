package org.jumao.bi.service.trade.operate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/4/26.
 */
@Path("/v1/operate/")
@Produces(MediaType.APPLICATION_JSON)
public interface IOperationSvc {

    /**
     * 运营总览顶部6个统计图
     */
    @GET
    @Path("/total/{platform}")
    public Response getTotal(@PathParam("platform") String startDate);


    /**
     * 查询pv、uv等基本指标
     */
    @GET
    @Path("/getBasic/{platform}/{startDate}/{endDate}")
    public Response getBasic(@PathParam("platform")String platform,@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 查询买家转化率等指标
     */
    @GET
    @Path("/getBuyer/{platform}/{startDate}/{endDate}")
    public Response getBuyer(@PathParam("platform")String platform,@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 查询卖家转化率等指标
     */
    @GET
    @Path("/getSeller/{platform}/{startDate}/{endDate}")
    public Response getSeller(@PathParam("platform") String platform,@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 查询关联公司top5、子账户开通top5等指标
     */
    @GET
    @Path("/getTop/{startDate}/{endDate}")
    public Response getTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);





}

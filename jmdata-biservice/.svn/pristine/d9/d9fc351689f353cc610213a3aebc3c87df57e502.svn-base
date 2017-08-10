package org.jumao.bi.service.jzx;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/14.
 */
@Path("/v1/jzx/overview/")
@Produces(MediaType.APPLICATION_JSON)
public interface JzxOverviewSvc {

    /**
     * 运营概览
     */
    @GET
    @Path("/OperationOverview")
    public Response OperationOverview();

    /**
     * 新增项目订单
     */
    @GET
    @Path("/newProjectOrder/{startDate}/{endDate}")
    public Response newProjectOrder(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 市场营销订单  各行业占比
     */
    @GET
    @Path("/industryShare/{startDate}/{endDate}")
    public Response IndustryShare(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 市场营销订单  各细分服务类型占比
     */
    @GET
    @Path("/TypeRatio/{startDate}/{endDate}")
    public Response TypeRatio(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 新增注册用户
     */
    @GET
    @Path("/newRegisteredUser/{startDate}/{endDate}")
    public Response newRegisteredUser(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);


}

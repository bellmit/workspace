package org.jumao.bi.service.jrt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/14.
 */
@Path("/v1/jrt/overview/")
@Produces(MediaType.APPLICATION_JSON)
public interface JrtOverviewSvc {

    /**
     * 运营概览
     */
    @GET
    @Path("/OperationOverview")
    public Response OperationOverview();

    /**
     * 业务数据概览
     */
    @GET
    @Path("/ServiceDataOverview/{startDate}/{endDate}")
    public Response ServiceDataOverview(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 注册转化
     */
    @GET
    @Path("/RegistrationConversion/{startDate}/{endDate}")
    public Response RegistrationConversion(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);


}

package org.jumao.bi.service.enjrt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/14.
 */
@Path("/v1/enjrt/overview/")
@Produces(MediaType.APPLICATION_JSON)
public interface EnJrtOverviewSvc {

    /**
     * 运营概览
     */
    @GET
    @Path("/operationOverview")
    public Response operationOverview();

    /**
     * 业务数据概览
     */
    @GET
    @Path("/serviceDataOverview/{startDate}/{endDate}")
    public Response serviceDataOverview(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 注册转化
     */
    @GET
    @Path("/registrationConversion/{startDate}/{endDate}")
    public Response registrationConversion(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);


}

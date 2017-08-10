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
@Path("/v1/masoperate/")
@Produces(MediaType.APPLICATION_JSON)
public interface IMasOperationSvc {

    /**
     * 运营总览顶部6个统计图
     */
    @GET
    @Path("/total/{platform}")
    public Response getTotal(@PathParam("platform") String platform);


    /**
     * 查询开通店铺数等5个基本指标
     */
    @GET
    @Path("/getBasic/{startDate}/{endDate}")
    public Response getBasic( @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 查询发布商品数 行业 占比
     */
    @GET
    @Path("/getItemProportion/{startDate}/{endDate}")
    public Response getItemProportion( @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 订单笔数 行业 占比
     */
    @GET
    @Path("/getOrderProportion/{startDate}/{endDate}")
    public Response getOrderProportion( @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);


    /**
     * 订单金额 行业 占比
     */
    @GET
    @Path("/getOrderMoneyProportion/{startDate}/{endDate}")
    public Response getOrderMoneyProportion( @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);






}

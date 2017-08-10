package org.jumao.bi.service.trade.operate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 运营分析订单信息
 */
@Path("/v1/operateOrder/")
@Produces(MediaType.APPLICATION_JSON)
public interface IOperationOrderSvc {

    /**
     * 获取订单类型
     */
    @GET
    @Path("/orderType/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getOrderType(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;
    
    /**
     * 获取订单金额
     */
    @GET
    @Path("/orderPay/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getOrderPay(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;

}

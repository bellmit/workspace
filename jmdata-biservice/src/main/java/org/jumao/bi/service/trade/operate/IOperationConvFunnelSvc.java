package org.jumao.bi.service.trade.operate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 运营分析转化漏斗
 */
@Path("/v1/operateConv/")
@Produces(MediaType.APPLICATION_JSON)
public interface IOperationConvFunnelSvc {

    /**
     * 获取转化漏斗
     * @throws Exception 
     */
    @GET
    @Path("/convFunnel/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getConvFunnel(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;
    

}

package org.jumao.bi.service.trade.operate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 运营分析商品信息
 */
@Path("/v1/operateGoods/")
@Produces(MediaType.APPLICATION_JSON)
public interface IOperationGoodsSvc {

    /**
     * 获取商品类目数量
     * @throws Exception 
     */
    @GET
    @Path("/goodsCate/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getGoodsCate(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;
    

}

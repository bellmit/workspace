package org.jumao.bi.service.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/7/11.
 */
@Path("/v1/test")
@Produces(MediaType.APPLICATION_JSON)
public interface TestSvc {
    /**
     * 物流方式Top-人民币
     * @param platform
     * @param startDate
     * @param endDate
     * @param wareHouseType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getTopWarehousingByRMB/platform/{platform}/startDate/{startDate}/endDate/{endDate}/wareHouseType/{wareHouseType}")
    public Response getTopWarehousingByRMB(@PathParam("platform") String platform,
                                             @PathParam("startDate") String startDate,
                                             @PathParam("endDate") String endDate,
                                             @PathParam("wareHouseType") String wareHouseType) throws Exception;
}
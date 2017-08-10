package org.jumao.bi.service.enjyt.deal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/15.
 * 物流商分析
 */
@Path("/v1/enjyt/lgProvidersAnalysis")
@Produces(MediaType.APPLICATION_JSON)
public interface EnLogisticsProvidersAnalysis {
    /**
     * 物流方式Top-人民币
     * @param platform
     * @param startDate
     * @param endDate
     * @param lineType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/lgTypeByRMBTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/lineType/{lineType}")
    public Response getTopLogisticsTypeByRMB(@PathParam("platform") String platform,
                                             @PathParam("startDate") String startDate,
                                             @PathParam("endDate") String endDate,
                                             @PathParam("lineType") String lineType) throws Exception;

    /**
     * 物流方式Top-美元
     * @param platform
     * @param startDate
     * @param endDate
     * @param lineType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/lgTypeByDollarTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/lineType{lineType}")
    public Response getTopLogisticsTypeByDollar(@PathParam("platform") String platform,
                                                @PathParam("startDate") String startDate,
                                                @PathParam("endDate") String endDate,
                                                @PathParam("lineType") String lineType) throws Exception;
    /**
     * 仓储Top-人民币
     * @param platform
     * @param startDate
     * @param endDate
     * @param wareHouseType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/warehousingByRMBTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/wareHouseType/{wareHouseType}")
    public Response getTopWarehousingByRMB(@PathParam("platform") String platform,
                                           @PathParam("startDate") String startDate,
                                           @PathParam("endDate") String endDate,
                                           @PathParam("wareHouseType") String wareHouseType) throws Exception;

    /**
     * 仓储Top-美元
     * @param platform
     * @param startDate
     * @param endDate
     * @param wareHouseType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/warehousingByDollarTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/wareHouseType/{wareHouseType}")
    public Response getTopWarehousingByDollar(@PathParam("platform") String platform,
                                              @PathParam("startDate") String startDate,
                                              @PathParam("endDate") String endDate,
                                              @PathParam("wareHouseType") String wareHouseType) throws Exception;
}

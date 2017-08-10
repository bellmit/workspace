package org.jumao.bi.service.jyt.deal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 物流方式分析
 * Created by Administrator on 2017/6/16.
 */
@Path("/v1/jyt/lgType")
@Produces(MediaType.APPLICATION_JSON)
public interface ILogisticsType {
    /**
     * 货物流向
     * @param platform
     * @param startDate
     * @param endDate
     * @param lineType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/freightFlow/platform/{platform}/startDate/{startDate}/endDate/{endDate}/lineType/{lineType}")
    public Response getFreightFlow(@PathParam("platform") String platform,
                                   @PathParam("startDate") String startDate,
                                   @PathParam("endDate") String endDate,
                                   @PathParam("lineType") String lineType) throws Exception;

    /**
     * 物流方式top10-人民币
     * @param platform
     * @param startDate
     * @param endDate
     * @param lineType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/lgTypeByRMBTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/lineType/{lineType}")
    public Response getTopLgTypeByRMB(@PathParam("platform") String platform,
                                      @PathParam("startDate") String startDate,
                                      @PathParam("endDate") String endDate,
                                      @PathParam("lineType") String lineType) throws Exception;

    /**
     * 物流方式top10-美元
     * @param platform
     * @param startDate
     * @param endDate
     * @param lineType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/lgTypeByDollarTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}/lineType/{lineType}")
    public Response getTopLgTypeByDollar(@PathParam("platform") String platform,
                                         @PathParam("startDate") String startDate,
                                         @PathParam("endDate") String endDate,
                                         @PathParam("lineType") String lineType) throws Exception;
}

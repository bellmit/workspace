package org.jumao.bi.service.enjzx;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 业务趋势分析
 * Created by Administrator on 2017/7/13.
 */
@Path("/v1/enjzx/businessTrend")
@Produces(MediaType.APPLICATION_JSON)
public interface EnJzxBusinessTrendSvc {
    /**
     * 意向单
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/intentionOrder/platform/{platform}/startDate/{startDate}/endDate/{endDate}/dateType/{dateType}")
    public Response getIntentionOrder(@PathParam("platform") String platform,
                                      @PathParam("startDate") String startDate,
                                      @PathParam("endDate") String endDate,
                                      @PathParam("dateType") String dateType) throws Exception;

    /**
     * 项目单
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/projectOrder/platform/{platform}/startDate/{startDate}/endDate/{endDate}/dateType/{dateType}")
    public Response getProjectOrder(@PathParam("platform") String platform,
                                    @PathParam("startDate") String startDate,
                                    @PathParam("endDate") String endDate,
                                    @PathParam("dateType") String dateType) throws Exception;

    /**
     * 合同金额
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/contractAmount/platform/{platform}/startDate/{startDate}/endDate/{endDate}/dateType/{dateType}")
    public Response getContractAmount(@PathParam("platform") String platform,
                                      @PathParam("startDate") String startDate,
                                      @PathParam("endDate") String endDate,
                                      @PathParam("dateType") String dateType) throws Exception;

    /**
     * 新增用户
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/newUsers/platform/{platform}/startDate/{startDate}/endDate/{endDate}/dateType/{dateType}")
    public Response getNewUsers(@PathParam("platform") String platform,
                                @PathParam("startDate") String startDate,
                                @PathParam("endDate") String endDate,
                                @PathParam("dateType") String dateType) throws Exception;
}

package org.jumao.bi.service.jyt.payment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Path("/v1/jyt/pay/")
@Produces(MediaType.APPLICATION_JSON)
public interface JytPaymentSvc {

    /**
     * 支付分析支付开通历史累计图1
     */
    @GET
    @Path("/payOne/{platformId}/{startDate}/{endDate}")
    public Response payOne(@PathParam("platformId")String platformId, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 支付分析支付开通历史累计数
     */
    @GET
    @Path("/payTotal")
    public Response payTotal();

    /**
     * 支付分析支付渠道占比
     */
    @GET
    @Path("/channelProportion")
    public Response channelProportion();

    /**
     * 支付分析支付开通地域分布
     */
    @GET
    @Path("/payOpenClime/{startDate}/{endDate}")
    public Response payOpenClime(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 支付分析支付开通地域分布top
     */
    @GET
    @Path("/payOpenClimeTop/{startDate}/{endDate}")
    public Response payOpenClimeTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图缓存
     */
    @GET
    @Path("/cache")
    public Map<String, Map<String, String>> cache();

    /**
     * 全部
     */
    @GET
    @Path("/trafficAll/{startDate}/{endDate}")
    public Response trafficAll(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图公路
     */
    @GET
    @Path("/trafficHighway/{startDate}/{endDate}")
    public Response trafficHighway(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图铁路
     */
    @GET
    @Path("/trafficRailway/{startDate}/{endDate}")
    public Response trafficRailway(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图内河航运
     */
    @GET
    @Path("/trafficInlandNavigation/{startDate}/{endDate}")
    public Response trafficInlandNavigation(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图海运
     */
    @GET
    @Path("/trafficOceanShipping/{startDate}/{endDate}")
    public Response trafficOceanShipping(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图空运
     */
    @GET
    @Path("/trafficAirTransport/{startDate}/{endDate}")
    public Response trafficAirTransport(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力图仓库
     */
    @GET
    @Path("/trafficStorage/{startDate}/{endDate}")
    public Response trafficStorage(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 运力排行基础属性缓存
     */
    @GET
    @Path("/TransportTopCache")
    public Map<String, String> TransportTopCache();

    /**
     * 运力排行
     */
    @GET
    @Path("/TransportTop/{startDate}/{endDate}")
    public Response TransportTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 车辆分析
     */
    @GET
    @Path("/CarAnalysis/{startDate}/{endDate}")
    public Response CarAnalysis(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     *  车辆分析base_data_option基础数据缓存
     */
    @GET
    @Path("/CarAnalysisCache")
    public Map<String, String> CarAnalysisCache();

    /**
     * 仓储分析热力图
     */
    @GET
    @Path("/StorageHeat/{startDate}/{endDate}")
    public Response StorageHeat(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 仓储分析仓库总数、可用面积、总面积
     */
    @GET
    @Path("/StorageArea/{startDate}/{endDate}")
    public Response StorageArea(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 仓库总面积top5
     */
    @GET
    @Path("/StorageAreaTop/{startDate}/{endDate}")
    public Response StorageAreaTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 仓库类型（面积）饼图
     */
    @GET
    @Path("/StorageTypeTop/{startDate}/{endDate}")
    public Response StorageTypeTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * 仓库面积企业排名top5
     */
    @GET
    @Path("/StorageCompanyTop/{startDate}/{endDate}")
    public Response StorageCompanyTop(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);



}

package org.jumao.bi.service.enjyt;

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
@Path("/v1/enjyt/pay/")
@Produces(MediaType.APPLICATION_JSON)
public interface EnJytCapaSvc {

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
     * AIR FREIGHT
     */
    @GET
    @Path("/airFreight/{startDate}/{endDate}")
    public Response airFreight(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * RAIL TRANSPORT
     */
    @GET
    @Path("/railTransport/{startDate}/{endDate}")
    public Response railTransport(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * LAND TRANSPORT
     */
    @GET
    @Path("/landTransport/{startDate}/{endDate}")
    public Response landTransport(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

    /**
     * WAREHOUSE TRANSPORT
     */
    @GET
    @Path("/warehouseTransport/{startDate}/{endDate}")
    public Response warehouseTransport(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

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

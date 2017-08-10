package org.jumao.bi.service.enjrt;



import org.jumao.bi.utis.constants.EnJrtUrl;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 聚融通（en） 融资相关
 */
@Path(EnJrtUrl.V1_Enjrt_Financing)
@Produces(MediaType.APPLICATION_JSON)
public interface EnJrtFinancingSvc {


    /**
     * 总览的 融资趋势 折线图
     */
    @GET
    @Path(EnJrtUrl.Financing_Trend_LineChart)
    public Response getTrendLineChart(
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 业务分析的 融资趋势分析 折线图
     */
    @GET
    @Path(EnJrtUrl.Trend_Analysis_LineChart)
    public Response getTrendAnalysisLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;


     /**
     * 总览的 区域分布 世界地图
     */
    @GET
    @Path(EnJrtUrl.Global_Area_Dist)
    public Response getGlobalAreaDist(
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 业务分析的 区域分布 世界地图
     */
    @GET
    @Path(EnJrtUrl.Global_Area_Dist_Date_Range)
    public Response getGlobalAreaDistAnalysis(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 业务分析的 融资明细 表格
     */
    @GET
    @Path(EnJrtUrl.Area_Detail_Table)
    public Response getAreaDetailTable(
            @PathParam(Key.Area) long areaId,
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) int type,
            @PathParam(Key.Limit) int limit,
            @PathParam(Key.Offset) int offset) throws Exception;


}

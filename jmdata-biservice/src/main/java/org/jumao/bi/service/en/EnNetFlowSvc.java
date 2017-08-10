package org.jumao.bi.service.en;

import org.jumao.bi.utis.constants.EnNetFlowUrl;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 英文版 流量分析
 */
@Path(EnNetFlowUrl.V1_En_Netflow)
@Produces(MediaType.APPLICATION_JSON)
public interface EnNetFlowSvc {


    /**
     * 概览  几个数字
     */
    @GET
    @Path(EnNetFlowUrl.Overviews)
    public Response getOverviews(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 趋势分析 折线图
     */
    @GET
    @Path(EnNetFlowUrl.Netflow_Trend_LineChart)
    public Response getTrendLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 各平台流量指标情况  表格
     */
    @GET
    @Path(EnNetFlowUrl.Overviews_Table)
    public Response getOverviewsTable(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Order_Target) String orderTarget,
            @PathParam(Key.Order_Type) String orderType) throws Exception;


    /**
     * 页面的uv top5
     */
    @GET
    @Path(EnNetFlowUrl.Netflow_PvTop5_Barchart)
    public Response getNetflowTop5BarChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 跳出的uv top5
     */
    @GET
    @Path(EnNetFlowUrl.Netflow_BounceTop5_Barchart)
    public Response getNetflowBounceTop5BarChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 区域分布 世界地图
     */
    @GET
    @Path(EnNetFlowUrl.Global_Area_Dist_Pv)
    public Response getGlobalAreaDistAnalysis(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 国家区域分布 表格
     */
    @GET
    @Path(EnNetFlowUrl.Global_Area_Dist_Table)
    public Response getGlobalAreaDistTable(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Limit) int limit,
            @PathParam(Key.Offset) int offset,
            @PathParam(Key.Order_Type) String orderType) throws Exception;


    /**
     * 访问来源 饼图
     */
    @GET
    @Path(EnNetFlowUrl.Source_Medium_Uv_Piechart)
    public Response getSourceMediumUvPieChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 访问来源 折线图
     */
    @GET
    @Path(EnNetFlowUrl.Source_Medium_LineChart)
    public Response getSourceTypeLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 访问来源 表格
     */
    @GET
    @Path(EnNetFlowUrl.Source_Medium_Table)
    public Response getSourceTypeTable(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Order_Type) String orderType) throws Exception;



    /**
     * 入口页 pv top10
     */
    @GET
    @Path(EnNetFlowUrl.Entrances_Page_Top10_Table)
    public Response getEntrancesPageTop10Table(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 受访页 pv top10
     */
    @GET
    @Path(EnNetFlowUrl.Visited_Page_Top10_Table)
    public Response getVisitedPageTop10Table(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 来源站（不包括直接访问） pv top10
     */
    @GET
    @Path(EnNetFlowUrl.Medium_Top10_Table)
    public Response getMediumTop10Table(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;



}

package org.jumao.bi.service.jrt;



import org.jumao.bi.utis.constants.JrtUrl;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 融资趋势、分析、明细
 */
@Path(JrtUrl.V1_Jrt_Financing)
@Produces(MediaType.APPLICATION_JSON)
public interface JrtFinancingSvc {


    /**
     * 融资趋势 折线图
     */
    @GET
    @Path(JrtUrl.Trend_LineChart)
    public Response getTrendLineChart(
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 融资趋势分析 折线图
     */
    @GET
    @Path(JrtUrl.Trend_Analysis_LineChart)
    public Response getTrendAnalysisLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate,
            @PathParam(Key.Type) String type) throws Exception;


    /**
     * 融资明细 表格
     */
    @GET
    @Path(JrtUrl.Trend_Detail_Table)
    public Response getTrendDetailTable(
            @PathParam(Key.Type) int type,
            @PathParam(Key.Limit) int limit,
            @PathParam(Key.Offset) int offset) throws Exception;


}

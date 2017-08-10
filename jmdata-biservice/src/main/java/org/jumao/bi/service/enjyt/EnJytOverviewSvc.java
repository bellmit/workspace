package org.jumao.bi.service.enjyt;

import org.jumao.bi.utis.constants.JytCnEnUrl;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 总览相关接口
 * @author chen qian
 */
@Path(JytCnEnUrl.V1_Enjyt_Overview)
@Produces(MediaType.APPLICATION_JSON)
public interface EnJytOverviewSvc {

    /**
     * 可配置的几个总览数字
     */
    @GET
    @Path(JytCnEnUrl.En_Numbers_Configable)
    public Response getNumbersConfigable(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 总览折线图
     */
    @GET
    @Path(JytCnEnUrl.Both_Linechart_By_Itemid)
    public Response getLineChartByItemId(
            @PathParam(Key.Item_Id) String itemId,
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 总览物流线路
     */
    @GET
    @Path(JytCnEnUrl.Both_Transport_Line_Piechart)
    public Response getTransportLinePieChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 需求发布数 top5
     */
    @GET
    @Path(JytCnEnUrl.Both_Require_Top5_Barchart)
    public Response getRequireTop5BarChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


}

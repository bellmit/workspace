package org.jumao.bi.service.trade.goods;


import org.jumao.bi.utis.constants.GoodsUrl;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 商品分析相关接口
 * @author chen qian
 */
@Path("/v1/goods/")
@Produces(MediaType.APPLICATION_JSON)
public interface GoodsAnalysis {

    /**
     * 发布商品数 折线图
     */
    @GET
    @Path(GoodsUrl.Newlyincr_Linechart)
    public Response getNewRegLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;

    /**
     * 行业 占比 饼图
     */
    @GET
    @Path(GoodsUrl.Industry_Piechart)
    public Response getIndustryPieChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 某行业的类目 占比 饼图
     */
    @GET
    @Path(GoodsUrl.Category_Piechart)
    public Response getCategoryPieChart(
            @PathParam(Key.Industry_Id) String industryId,
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 行业差异 综合分析
     */
    @GET
    @Path(GoodsUrl.Industry_Diff_Bubble_Gradient)
    public Response getIndustryDiff(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;



}

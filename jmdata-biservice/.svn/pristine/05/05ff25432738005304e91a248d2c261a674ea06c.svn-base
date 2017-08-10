package org.jumao.bi.service.trade.shop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/shop")
@Produces(MediaType.APPLICATION_JSON)
public interface IShopSvc {
    /**
     * 
     * openTrend:开通店铺趋势图.
     * 
     * @author Administrator
     * @date 2017年5月2日 下午2:51:21
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/openTrend/{startDate}/{endDate}/")
    public Response openTrend(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;

    /**
     * 
     * perfect:基础资料完善统计及占比.
     * 
     * @author Administrator
     * @date 2017年5月3日 上午9:37:33
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/perfect/{startDate}/{endDate}/")
    public Response perfect(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;

    /**
     * 
     * releaseRank:商品发布排行.
     * 
     * @author Administrator
     * @date 2017年5月3日 上午10:58:57
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/releaseRank/{startDate}/{endDate}/{platform}/")
    public Response releaseRank(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * 
     * categoryRank:店铺分类排行.
     * 
     * @author Administrator
     * @date 2017年5月3日 上午11:14:09
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/categoryRank/{startDate}/{endDate}/")
    public Response categoryRank(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;

    /**
     * 
     * dealRank:店铺成交排行
     * 
     * @author Administrator
     * @date 2017年5月3日 下午2:49:54
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/dealRank/{startDate}/{endDate}/{platform}/")
    public Response dealRank(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * 
     * releaseRank:商品发布列表.
     * 
     * @author Administrator
     * @date 2017年5月3日 上午10:58:57
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/releaseList/{startDate}/{endDate}/{platform}/")
    public Response releaseList(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * 
     * categoryList:店铺分类列表.
     * 
     * @author Administrator
     * @date 2017年5月4日 下午4:20:29
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/categoryList/{startDate}/{endDate}/")
    public Response categoryList(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;

    /**
     * 
     * dealList:成交分析列表.
     * 
     * @author Administrator
     * @date 2017年5月5日 上午9:25:19
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/dealList/{startDate}/{endDate}/{platform}/")
    public Response dealList(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;
}

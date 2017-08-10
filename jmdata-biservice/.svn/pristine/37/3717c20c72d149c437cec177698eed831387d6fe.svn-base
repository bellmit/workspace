package org.jumao.bi.service.trade.shop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/shopAnalysis")
@Produces(MediaType.APPLICATION_JSON)
public interface IShopAnalysisSvc {
    /*
     * 商品分类数TOP5店铺
     */
    @GET
    @Path("/goodsCategoriesTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getTopGoodsCategories(@PathParam("platform") String platform,
    		@PathParam("startDate") String startDate, 
    		@PathParam("endDate") String endDate) throws Exception;
    
    /*
     * 商品发布数行业占比
     */
    @GET
    @Path("/goodsIndustryRatio/platform/{platform}/startDate/{startDate}/endDate/{endDate}/itemId/{itemId}")
    public Response getGoodsIndustryRatio(@PathParam("platform") String platform,
    		@PathParam("startDate") String startDate, 
    		@PathParam("endDate") String endDate,
    		@PathParam("itemId") String storeId) throws Exception;
    /*
     * 成交金额TOP5
     */
    @GET
    @Path("/moneyTransactionMoneyTop/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getTopTransactionMoney(@PathParam("platform") String platform,
    		@PathParam("startDate") String startDate, 
    		@PathParam("endDate") String endDate) throws Exception;
    
    /*
     * 成交金额行业占比
     */
    @GET
    @Path("/moneyIndustryRatio/platform/{platform}/startDate/{startDate}/endDate/{endDate}/itemId/{itemId}")
    public Response getMoneyIndustryRatio(@PathParam("platform") String platform,
    		@PathParam("startDate") String startDate, 
    		@PathParam("endDate") String endDate,
    		@PathParam("itemId") String itemId) throws Exception;

}

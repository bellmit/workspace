package org.jumao.bi.service.trade.deal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/deal")
@Produces(MediaType.APPLICATION_JSON)
public interface IDealSvc {
    /**
     * operate:平台运营情况.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午1:51:46
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/operate/{startDate}/{endDate}/{platform}/")
    public Response operate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * translate:交易转化跟踪
     * 
     * @author Administrator
     * @date 2017年5月5日 下午3:57:31
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/translate/{startDate}/{endDate}/{platform}/")
    public Response translate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * pickup:提货方式用户占比.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午4:14:09
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/pickup/{startDate}/{endDate}/{platform}/")
    public Response pickup(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * delivery:交货时间占比.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午5:26:37
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/delivery/{startDate}/{endDate}/{platform}/")
    public Response delivery(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * receipt:确认收货时间.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午5:33:52
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/receipt/{startDate}/{endDate}/{platform}/")
    public Response receipt(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * settle:结算方式.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午5:47:46
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/settle/{startDate}/{endDate}/{platform}/")
    public Response settle(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * pay:支付方式.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午6:11:02
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/pay/{startDate}/{endDate}/{platform}/")
    public Response pay(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * contract:合同方式.
     * 
     * @author Administrator
     * @date 2017年5月5日 下午6:13:37
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/contract/{startDate}/{endDate}/{platform}/")
    public Response contract(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * areaAmount:地域金额.
     * 
     * @author Administrator
     * @date 2017年5月10日 上午11:28:40
     * @param role 角色：seller 卖家、buyer 买家
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param platform 平台ID
     * @param sortField 排序字段：ordernum 笔数、paytotal 金额
     * @return
     * @throws Exception
     */
    @GET
    @Path("/areaAmount/{role}/{startDate}/{endDate}/{platform}/{sortField}/")
    public Response areaAmount(@PathParam("role") String role, @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("platform") String platform, @PathParam("sortField") String sortField)
            throws Exception;

    /**
     * payChannel:支付渠道金额占比.
     * 
     * @author Administrator
     * @date 2017年5月8日 上午11:15:36
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/payChannel/{startDate}/{endDate}/{platform}/")
    public Response payChannel(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * paymentDays:付款天数用户占比.
     * 
     * @author Administrator
     * @date 2017年5月24日 上午11:01:31
     * @param startDate
     * @param endDate
     * @param platform
     * @return
     * @throws Exception
     */
    @GET
    @Path("/paymentDays/{startDate}/{endDate}/{platform}/")
    public Response paymentDays(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("platform") String platform) throws Exception;

    /**
     * industryAmount:行业订单分析.
     * 
     * @author Administrator
     * @date 2017年5月31日 下午2:15:39
     * @param startDate
     * @param endDate
     * @param sortField 排序字段：ordernum 笔数、paytotal 金额
     * @return
     * @throws Exception
     */
    @GET
    @Path("/industryAmount/{startDate}/{endDate}/{sortField}/")
    public Response industryAmount(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("sortField") String sortField) throws Exception;

    /**
     * industryPieCharts:合同方式、结算方式和支付方式按行业分组的饼图.
     * 
     * @author Administrator
     * @date 2017年6月7日 下午3:48:23
     * @param startDate
     * @param endDate
     * @param code
     * @param key 合同方式:contract_type;结算方式:clearing_type;支付方式:payment_type;
     * @return
     * @throws Exception
     */
    @GET
    @Path("/industryPieCharts/{startDate}/{endDate}/{code}/{key}/")
    public Response industryPieCharts(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("code") String code, @PathParam("key") String key) throws Exception;

    /**
     * categoryAmount:指定行业订单笔数各类目占比.
     * 
     * @author Administrator
     * @date 2017年6月8日 下午2:47:36
     * @param startDate
     * @param endDate
     * @param industryId
     * @param sortField
     * @return
     * @throws Exception
     */
    @GET
    @Path("/categoryAmount/{startDate}/{endDate}/{industryId}/{sortField}/")
    public Response categoryAmount(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("industryId") String industryId, @PathParam("sortField") String sortField) throws Exception;

}

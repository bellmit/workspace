package org.jumao.bi.service.jdt.areaorder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/areaorder")
@Produces(MediaType.APPLICATION_JSON)
public interface IAreaOrderSvc {
    /**
     * 
     * trend:订单数量或金额趋势图.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:19:17
     * @param flag 1:订单数;2:金额
     * @param timeType 时间类型：week、month、quarter、year
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param customsCode 海关/关区：编码
     * @param interval 间隔(默认为0)
     * @return
     * @throws Exception
     */
    @GET
    @Path("/trend/{flag}/{timeType}/{startDate}/{endDate}/{customsCode}/{interval}/")
    public Response trend(@PathParam("flag") String flag, @PathParam("timeType") String timeType, @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("customsCode") String customsCode, @PathParam("interval") int interval)
            throws Exception;

    /**
     * 
     * map:散点图.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:19:17
     * @param timeType 时间类型：week、month、quarter、year
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws Exception
     */
    @GET
    @Path("/map/{timeType}/{startDate}/{endDate}/")
    public Response map(@PathParam("timeType") String timeType, @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate) throws Exception;

    /**
     * 
     * list:列表.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:19:17
     * @param timeType 时间类型：week、month、quarter、year
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param customsCode 海关/关区：编码
     * @param interval 间隔(默认为0)
     * @return
     * @throws Exception
     */
    @GET
    @Path("/list/{startDate}/{endDate}/{customsCode}/")
    public Response list(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("customsCode") String customsCode) throws Exception;
}

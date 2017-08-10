package org.jumao.bi.service.jrt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 融资趋势、分析、明细
 */
@Path("/v1/jrt/company/")
@Produces(MediaType.APPLICATION_JSON)
public interface ICompanySvc {
    /**
     * type:入驻企业类别饼图.
     * 
     * @author Administrator
     * @date 2017年7月5日 上午10:23:41
     * @return
     * @throws Exception
     */
    @GET
    @Path("/type/")
    public Response type() throws Exception;

    /**
     * productType:上架产品类型饼图.
     * 
     * @author Administrator
     * @date 2017年7月5日 上午10:27:00
     * @return
     * @throws Exception
     */
    @GET
    @Path("/productType/")
    public Response productType() throws Exception;

    /**
     * area:企业用户区域分布.
     * 
     * @author Administrator
     * @date 2017年7月5日 上午10:28:45
     * @return
     * @throws Exception
     */
    @GET
    @Path("/area/")
    public Response area() throws Exception;

    /**
     * enterTrend:入驻企业区域图.
     * 
     * @author Administrator
     * @date 2017年7月5日 上午10:43:50
     * @param startDate
     * @param endDate
     * @param enterpriseType 0:其它;51:工厂;52:贸易商;53:工贸一体
     * @return
     * @throws Exception
     */
    @GET
    @Path("/enterTrend/{startDate}/{endDate}/{enterpriseType}/")
    public Response enterTrend(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("enterpriseType") String enterpriseType) throws Exception;

    /**
     * shelfTrend:上架产品区域图.
     * 
     * @author Administrator
     * @date 2017年7月5日 上午10:49:30
     * @param startDate
     * @param endDate
     * @param productType
     * @return
     * @throws Exception
     */
    @GET
    @Path("/shelfTrend/{startDate}/{endDate}/{productType}/")
    public Response shelfTrend(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("productType") String productType) throws Exception;
    
    /**
     * shelfProductType:上架产品类型列表.
     * 
     * @author Administrator
     * @date 2017年7月11日 上午9:42:28
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @GET
    @Path("/shelfProductType/{startDate}/{endDate}/")
    public Response shelfProductType(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws Exception;
    
    
}

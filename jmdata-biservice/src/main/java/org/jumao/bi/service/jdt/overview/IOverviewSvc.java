package org.jumao.bi.service.jdt.overview;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/overview")
@Produces(MediaType.APPLICATION_JSON)
public interface IOverviewSvc {
    /**
     * 
     * getOverview:总览.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:09:45
     * @return
     * @throws Exception
     */
    @GET
    @Path("/getOverview")
    public Response getOverview() throws Exception;

    /**
     * 
     * starLevel:企业星级分布情况.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:07:07
     * @param area 地区编码
     * @return
     * @throws Exception
     */
    @GET
    @Path("/starLevel/{area}/")
    public Response starLevel(@PathParam("area") String area) throws Exception;

    /**
     * 
     * partner:拍档区域分布.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:08:00
     * @param area 地区编码
     * @return
     * @throws Exception
     */
    @GET
    @Path("/partner/{area}/")
    public Response partner(@PathParam("area") String area) throws Exception;

    /**
     * 
     * customer:客户区域分布.
     * 
     * @author Administrator
     * @date 2017年3月7日 下午3:09:05
     * @param area 地区编码
     * @return
     * @throws Exception
     */
    @GET
    @Path("/customer/{area}/")
    public Response customer(@PathParam("area") String area) throws Exception;

}

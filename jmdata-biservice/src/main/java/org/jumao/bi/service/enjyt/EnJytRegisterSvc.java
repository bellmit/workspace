package org.jumao.bi.service.enjyt;

import org.jumao.bi.utis.constants.JytCnEnUrl;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.RegUrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 日新增用户
 * @author chen qian
 */
@Path(JytCnEnUrl.V1_Enjyt_Register)
@Produces(MediaType.APPLICATION_JSON)
public interface EnJytRegisterSvc {

	/**
	 * 新增注册数 折线图
	 */
	@GET
	@Path(RegUrl.Newlyincr_Linechart)
	public Response getNewRegLineChart(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


    /**
     * 注册用户地域分布 世界地图
     */
    @GET
    @Path(JytCnEnUrl.Reg_Area_Dist)
    public Response getRegAreaDist(
            @PathParam(Key.Platform) String platform,
            @PathParam(Key.Start_Date) String startDate,
            @PathParam(Key.End_Date) String endDate) throws Exception;


}

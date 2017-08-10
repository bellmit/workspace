package org.jumao.bi.service.user;

import java.text.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jumao.bi.entites.user.UserLogout;
import org.jumao.bi.entites.user.UserTraces;

/**
 * 查询用户行为各种统计数据
 * 
 * @author 1
 *
 */
@Path("/v1/usertrace/")
@Produces(MediaType.APPLICATION_JSON)
public interface IUserTraceSvc {

	/**
	 * 添加用户平台轨迹信息
	 * @return
	 */
	@POST
	@Path("/login")
	public Response addUserTraceInfo(UserTraces userTraces);
	
	
	/**
     * 添加用户平台轨迹信息
     * @return
     */
    @POST
    @Path("/logout")
    public Response addUserLogoutInfo(UserLogout userLogout);
	

    /**
     * 获取用户登入情况
     * @return
     * @throws ParseException 
     */
    @GET
    @Path("/login/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getUserLoginInfo(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
    
    
    
    /**
     * 获取用户浏览器模型
     * @return
     */
    @GET
    @Path("/browser/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getUserBrowserInfo(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

}

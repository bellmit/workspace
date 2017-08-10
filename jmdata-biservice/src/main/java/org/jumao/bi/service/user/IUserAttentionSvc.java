/** 
 * Project Name:jmdata-biservice 
 * File Name:IUserAttentionSvc.java 
 * Package Name:org.jumao.bi.service.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月21日 下午2:31:03
 */
package org.jumao.bi.service.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jumao.bi.entites.user.UserSearches;

/**
 * Function: 用户关注接口服务
 * 
 * @author 1
 * @date 2017年6月21日 下午2:31:03
 * @version 
 * @see
 */

@Path("/v1/userattention/")
@Produces(MediaType.APPLICATION_JSON)
public interface IUserAttentionSvc {
    
    /**
     * 添加用户搜索关键字信息
     * @return
     */
    @POST
    @Path("/keyword")
    public Response addUserSearchInfo(UserSearches userSearches);
    
    
    /**
     * 获取平台搜索关键字top10
     * @return
     *
     */
    @GET
    @Path("/wordrank/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getWordRankInfo(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
    
    
    /**
     * 获取平台词云信息
     * @return
     * 
     */
    @GET
    @Path("/wordcloud/platform/{platform}/startDate/{startDate}/endDate/{endDate}")
    public Response getWordCloudInfo(@PathParam("platform") String platform,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate);

}

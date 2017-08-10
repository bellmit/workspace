/** 
 * Project Name:jmdata-biservice 
 * File Name:UserAttetionSvcImpl.java 
 * Package Name:org.jumao.bi.service.impl.user
 * Copyright (c) 2017, JUMORE Co.,Ltd. All Rights Reserved. 
 *
 * @author 1
 * @date 2017年6月22日 上午9:28:44
 */
package org.jumao.bi.service.impl.user;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.user.IUserAttentionDao;
import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.user.SqlResultBean;
import org.jumao.bi.entites.user.UserSearchResponse;
import org.jumao.bi.entites.user.UserSearches;
import org.jumao.bi.entites.user.WordCloudResponse;
import org.jumao.bi.service.user.IUserAttentionSvc;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Function: 用户关注接口实现
 * 
 * @author 1
 * @date 2017年6月22日 上午9:28:44
 * @version 
 * @see
 */
public class UserAttentionSvcImpl implements IUserAttentionSvc {

    private Logger  logger = Logger.getLogger(this.getClass());
    
    @Autowired
    IUserAttentionDao userAttentionDao;
    
    @Override
    public Response addUserSearchInfo(UserSearches userSearches) {

        LogUtils.writeLogs(logger, "add user search keyword infomation from ES start.");
        CommonResponse response = new CommonResponse();
        try {
            userAttentionDao.addUserSearchInfo(userSearches.getList());
            response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        } catch (IOException e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG + e.getMessage()));
            LogUtils.writeLogs(logger, "add user search keyword infomation error: " + e.getMessage());

            return Response.serverError().entity(response).build();
        }

        LogUtils.writeLogs(logger, "end user search keyword infomation.");
        return Response.ok().entity(response).build();
    }

    @Override
    public Response getWordRankInfo(String platform, String startDate, String endDate) {
  
        LogUtils.writeLogs(logger, "get user search keyword rank for platform " + platform + " from " + startDate + " to " + endDate);
        UserSearchResponse response = new UserSearchResponse();
        //top 10 search keyword
        ParamBean param = new ParamBean(platform, startDate, endDate, "10");   
        
        List<SqlResultBean> keywords = userAttentionDao.getTopNSearchWords(param);  
        UserAttentionSvcHelper.buildWordRankBarChart(keywords, response);
        response.success();
        
        LogUtils.writeLogs(logger, "end user search keyword rank infomation.");
        return Response.ok().entity(response).build();
    }

    @Override
    public Response getWordCloudInfo(String platform, String startDate, String endDate) {
        
        LogUtils.writeLogs(logger, "get search word cloud for platform " + platform + " from " + startDate + " to " + endDate);
        WordCloudResponse response = new WordCloudResponse();
        //search word cloud i.e top 50
        String topFifty = "50";
        ParamBean param = new ParamBean(platform, startDate, endDate, topFifty);   
        List<SqlResultBean> wordCloud = userAttentionDao.getTopNSearchWords(param);
        
        response.setWordCloud(wordCloud);
        response.success();
   
        LogUtils.writeLogs(logger, "end search word cloud infomation.");
        return Response.ok().entity(response).build();
    }

}

package org.jumao.bi.service.impl.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.user.IUserTraceDao;
import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.user.BrowseBean;
import org.jumao.bi.entites.user.BrowseResponse;
import org.jumao.bi.entites.user.LoginBean;
import org.jumao.bi.entites.user.LoginResponse;
import org.jumao.bi.entites.user.UserLogout;
import org.jumao.bi.entites.user.UserTraces;
import org.jumao.bi.service.user.IUserTraceSvc;
import org.jumao.bi.utis.LogUtils;
import org.jumao.commons.frameworks.jmframework.commutil.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTraceSvcImpl implements IUserTraceSvc {

    @Autowired
    private IUserTraceDao userTraceDao;

    private Logger        logger = Logger.getLogger(this.getClass());

    /**
     * 增加用户登入信息接口
     * @see org.jumao.bi.service.user.IUserTraceSvc#addUserTraceInfo(org.jumao.bi.entites.user.UserTraces)
     */
    public Response addUserTraceInfo(UserTraces userTraces) {
        LogUtils.writeLogs(logger, "add user trace infomation from ES start.");
        CommonResponse response = new CommonResponse();
        try {
            userTraceDao.addUserTraceInfo(userTraces.getList());
            response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        } catch (IOException e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG + e.getMessage()));
            LogUtils.writeLogs(logger, "add user trace infomation error: " + e.getMessage());

            return Response.serverError().entity(response).build();
        }

        LogUtils.writeLogs(logger, "end user trace infomation.");
        return Response.ok().entity(response).build();

    }

    /**
     * 增加用户登出信息接口
     * @see org.jumao.bi.service.user.IUserTraceSvc#addUserLogoutInfo(org.jumao.bi.entites.user.UserLogout)
     */
    public Response addUserLogoutInfo(UserLogout userLogout) {
        LogUtils.writeLogs(logger, "add user logout infomation from ES start.");
        CommonResponse response = new CommonResponse();
        try {
            userTraceDao.addUserLogoutInfo(userLogout.getList());
            response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        } catch (IOException e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG + e.getMessage()));
            LogUtils.writeLogs(logger, "add user logout infomation error: " + e.getMessage());
            return Response.serverError().entity(response).build();
        }

        LogUtils.writeLogs(logger, "end user logout infomation.");
        return Response.ok().entity(response).build();
    }
    
    /**
     * 查询用户登录信息接口
     * @see org.jumao.bi.service.user.IUserTraceSvc#getUserLoginInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response getUserLoginInfo(String platform, String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get userlogin statistics infomation for platform " + platform + " from " + startDate + " to " + endDate);
        LoginResponse response = new LoginResponse();
        boolean isToday = startDate.equals(endDate);
        List<String> xaxisData;
        
        if(isToday) {
            xaxisData = UserTraceSvcHelper.buildDayHours();
        } else {
            try {
                xaxisData = CalendarUtils.getAllDatesByPeriod(startDate, endDate);
            } catch (ParseException e) {// 请求日期格式错误
                response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
                return Response.serverError().entity(response).build();
            }
        }
        
        List<LoginBean> logins;
        ParamBean param = new ParamBean(platform, startDate, endDate);
        if(isToday) {
            logins = userTraceDao.getTodayLoginInfo(param);
        } else {
            logins = userTraceDao.getUserLoginInfo(param);
        }
          

        UserTraceSvcHelper.buildLoginLineChart(xaxisData, logins, response);

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        LogUtils.writeLogs(logger, "end userlogin statistics infomation.");
        return Response.ok().entity(response).build();
    }

    /**
     * 查询用户浏览器信息接口.
     * @see org.jumao.bi.service.user.IUserTraceSvc#getUserBrowserInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response getUserBrowserInfo(String platform, String startDate, String endDate) {

        LogUtils.writeLogs(logger, "get browse statistics infomation for platform " + platform + " from " + startDate + " to " + endDate);
        BrowseResponse response = new BrowseResponse();
        boolean isToday = startDate.equals(endDate);
        List<BrowseBean> browses;
        ParamBean param = new ParamBean(platform, startDate, endDate);
        if (isToday) {
            browses = userTraceDao.getTodayBrowserInfo(param);
        } else {
            browses = userTraceDao.getUserBrowserInfo(param);
        }

        UserTraceSvcHelper.buildBrowserPieChart(browses, response);
        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        LogUtils.writeLogs(logger, "end browse statistics infomation.");
        return Response.ok().entity(response).build();

    }




}

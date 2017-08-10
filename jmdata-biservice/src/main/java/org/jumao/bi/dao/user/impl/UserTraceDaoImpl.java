package org.jumao.bi.dao.user.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.CommonDaoImpl;
import org.jumao.bi.dao.user.IUserTraceDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.user.BrowseBean;
import org.jumao.bi.entites.user.LoginBean;
import org.jumao.bi.entites.user.UserLogoutInfo;
import org.jumao.bi.entites.user.UserTraceInfo;
import org.springframework.stereotype.Repository;


@Repository("userTraceDao")
public class UserTraceDaoImpl extends CommonDaoImpl implements IUserTraceDao {

    public static final String User_Traces_Tab = "jmbi:userTraces";
    /**
     * 增加用户登入信息
     * @see org.jumao.bi.dao.user.IUserTraceDao#addUserTraceInfo(java.util.List)
     */
    public void addUserTraceInfo(List<UserTraceInfo> userTraces) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (UserTraceInfo userTrace : userTraces) {
            putUserTraceInfo(puts, userTrace);
        }
        
        if (!puts.isEmpty()) {
            super.hbasedao.save(puts, User_Traces_Tab);
        }
    }
    
    /**
     * 增加用户登出信息
     * @see org.jumao.bi.dao.user.IUserTraceDao#addUserLogoutInfo(java.util.List)
     */
    public void addUserLogoutInfo(List<UserLogoutInfo> userLogouts) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (UserLogoutInfo logout : userLogouts) {
             String logoutTime = logout.getLogoutTime();
             Put put = new Put(Bytes.toBytes(logout.getKey()));         
             put.addColumn(Column_Family_Name, "logoutTime".getBytes(), logoutTime.getBytes());
             puts.add(put);
        }
        
        if (!puts.isEmpty()) {
            super.hbasedao.save(puts, User_Traces_Tab);
        }
        
    }

    private void putUserTraceInfo(List<Put> puts, UserTraceInfo userTrace) {
        String userId = userTrace.getUserId();
        String companyId = userTrace.getCompanyId();
        String isOverseas = userTrace.getIsOverseas();
        String bizCode = userTrace.getBizCode();
        String loginTime = userTrace.getLoginTime();
        String deviceType = userTrace.getDeviceType();
        String browseType = userTrace.getBrowseType();
        Put put = new Put(Bytes.toBytes(userTrace.getKey()));         
        put.addColumn(Column_Family_Name, "userId".getBytes(), userId.getBytes());
        if (null == companyId) {//有些用户没有绑定公司，则默认公司ID为0
            companyId = "0";
        }
        put.addColumn(Column_Family_Name, "companyId".getBytes(), companyId.getBytes());
        put.addColumn(Column_Family_Name, "isOverseas".getBytes(), isOverseas.getBytes());
        put.addColumn(Column_Family_Name, "bizCode".getBytes(), bizCode.getBytes());
        put.addColumn(Column_Family_Name, "loginTime".getBytes(), loginTime.getBytes());
        if (null == deviceType) {
            deviceType = "未知";
        }
        put.addColumn(Column_Family_Name, "deviceType".getBytes(), deviceType.getBytes());
        if (null == browseType) {
            browseType = "未知";
        }
        put.addColumn(Column_Family_Name, "browseType".getBytes(), browseType.getBytes());
        puts.add(put);
    }

    
    /**
     * 获取一段时间内登入用户浏览器信息
     * @see org.jumao.bi.dao.user.IUserTraceDao#getUserBrowserInfo(org.jumao.bi.entites.ParamBean)
     */
    public List<BrowseBean> getUserBrowserInfo(ParamBean param) {
        String sql = "select browse_type, count(browse_type) as nums from jmbi_user_traces where "
                + " biz_code='" + param.getPlatform()
                + "' and login_time between '" + param.getStartDate()
                + "' and '" + param.getEndDate()
                + "' group by browse_type";

        
        return super.getSqlResult(sql, BrowseBean.class);
        
    }

    /**
     * 获取一段时间平台用户登入情况信息
     * @see org.jumao.bi.dao.user.IUserTraceDao#getUserLoginInfo(org.jumao.bi.entites.ParamBean)
     */
    public List<LoginBean> getUserLoginInfo(ParamBean param) {
        String sql = "select STRLEFT(login_time, 8) as login , count(STRLEFT(login_time, 8)) as nums from jmbi_user_traces where"
                + " biz_code='" + param.getPlatform()
                + "' and login_time between '" + param.getStartDate()
                + "' and '" + param.getEndDate()
                + "' group by  login order by login";
        
        return super.getSqlResult(sql, LoginBean.class);
    }

    /**
     * 获取当天用户浏览器信息
     * @see org.jumao.bi.dao.user.IUserTraceDao#getTodayBrowserInfo(org.jumao.bi.entites.ParamBean)
     */
    public List<BrowseBean> getTodayBrowserInfo(ParamBean param) {
        String sql = "select browse_type, count(browse_type) as nums from jmbi_user_traces where "
                + "biz_code='" + param.getPlatform()
                + "' and STRLEFT(login_time, 8)='" + param.getStartDate()
                + "' group by browse_type";
   
        
        return super.getSqlResult(sql, BrowseBean.class);
    }

    /**
     * 获取当天用户登入情况
     * @see org.jumao.bi.dao.user.IUserTraceDao#getTodayLoginInfo(org.jumao.bi.entites.ParamBean)
     */
    public List<LoginBean> getTodayLoginInfo(ParamBean param) {
        //按指定日期的小时来统计
        String sql = "select SUBSTR(login_time, 9, 2) as login , count(SUBSTR(login_time, 9, 2)) as nums from jmbi_user_traces where "
                + "biz_code='" + param.getPlatform()
                + "' and STRLEFT(login_time, 8)='" + param.getStartDate()
                + "' group by  login order by login";


        return super.getSqlResult(sql, LoginBean.class);

    }






}

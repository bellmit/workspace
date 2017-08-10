package org.jumao.bi.dao.user;

import java.io.IOException;
import java.util.List;

import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.user.BrowseBean;
import org.jumao.bi.entites.user.LoginBean;
import org.jumao.bi.entites.user.UserLogoutInfo;
import org.jumao.bi.entites.user.UserTraceInfo;

public interface IUserTraceDao {
	
	void addUserTraceInfo(List<UserTraceInfo> userTraces) throws IOException;
	
	void addUserLogoutInfo(List<UserLogoutInfo> userLogouts) throws IOException;
	
	List<BrowseBean> getUserBrowserInfo(ParamBean param) ;
	
	List<LoginBean> getUserLoginInfo(ParamBean param);
	
	List<BrowseBean> getTodayBrowserInfo(ParamBean param) ;
	    
	List<LoginBean> getTodayLoginInfo(ParamBean param);

}

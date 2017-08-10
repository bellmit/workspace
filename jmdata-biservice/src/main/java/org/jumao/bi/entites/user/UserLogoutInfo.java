package org.jumao.bi.entites.user;

import java.io.Serializable;

public class UserLogoutInfo implements Serializable{
    
    private static final long serialVersionUID = -2247637463576223821L;
    private String key;//session ID, 登录登出唯一性
    private String logoutTime;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getLogoutTime() {
        return logoutTime;
    }
    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }
    

}


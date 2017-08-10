package org.jumao.bi.entites.user;

import java.io.Serializable;

public class UserTraceInfo implements Serializable{
	
	private static final long serialVersionUID = 8822965560390351660L;
	private String key;
	private String userId;
	private String companyId;
	private String isOverseas; // 值false表示中文站用户，true表示英文站用户
	private String bizCode;//业务平台编码即platform
	private String loginTime;
	private String deviceType;
	private String browseType;
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getBrowseType() {
        return browseType;
    }
    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getIsOverseas() {
        return isOverseas;
    }
    public void setIsOverseas(String isOverseas) {
        this.isOverseas = isOverseas;
    }


}

package org.jumao.bi.entites.user;

import java.io.Serializable;

public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = -3767156526107545874L;
    private String login;
    private Integer nums;
   
    public Integer getNums() {
        return nums;
    }
    public void setNums(Integer nums) {
        this.nums = nums;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    
    

}

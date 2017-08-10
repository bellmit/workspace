/**
 * 
 */
package com.jumore.jmbi.model.param.permission;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jumore.jmbi.model.param.PageQueryParam;

/** 
* 账号参数
* @author: luochao
* @since: 2016年7月29日  上午10:07:52
* @history:
*/
public class AccountParam extends PageQueryParam {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2385913448977725572L;

    /**主键*/
    @NotNull(message = "用户id不能为空")
    private Long              id;
    /**用户名*/
    @NotEmpty(message = "用户名不能为空")
    private String            name;
    /**登陆账户*/
    private String            account;

    /**手机*/
    @NotEmpty(message = "手机号不能为空")
    private String            mobileNo;

    /**昵称*/
    @NotEmpty(message = "昵称不能为空")
    public String             nickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}

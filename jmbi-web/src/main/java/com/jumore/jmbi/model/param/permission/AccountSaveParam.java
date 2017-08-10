/**
 * 
 */
package com.jumore.jmbi.model.param.permission;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.jumore.jmbi.common.util.validation.Groups;
import com.jumore.jmbi.model.param.BaseParam;

/** 
* 账号参数
* @author: luochao
* @since: 2016年7月29日  上午10:07:52
* @history:
*/
public class AccountSaveParam extends BaseParam {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2385913448977725572L;

    /**主键*/
    @NotNull(message = "用户id不能为空", groups = { Groups.update1.class, Groups.update.class })
    private Long              id;
    /**用户名*/
    @NotEmpty(message = "用户名不能为空", groups = { Groups.insert.class, Groups.update.class })
    private String            name;

    /**登陆账户*/
    @NotEmpty(message = "帐户不能为空", groups = { Groups.insert.class })
    private String            account;

    /**手机*/
    @Pattern(message = "请输入正确的手机号", regexp = "^[1][3,4,5,7,8][0-9]{9}$", groups = { Groups.insert.class, Groups.update.class })
    private String            mobileNo;
    /**邮箱*/
    @Email(message = "邮箱格式不正确", groups = { Groups.insert.class, Groups.update.class })
    private String            mail;
    /**qq*/
    private String            qq;
    /**地址*/
    private String            address;

    /**性别*/
    @Min(value = 0, message = "性别不能为空", groups = { Groups.insert.class, Groups.update.class })
    private Integer           sex;
    /**角色id*/
    @Min(value = 1, message = "角色不能为空", groups = { Groups.insert.class, Groups.update.class })
    private Long              roleId;

    /**昵称*/
    @NotEmpty(message = "昵称不能为空", groups = { Groups.insert.class, Groups.update.class })
    public String             nickName;

    /**新密码*/
    @NotEmpty(message = "新密码不能为空", groups = { Groups.insert.class })
    private String            newPassword;
    /**校验新密码一致性*/
    @NotEmpty(message = "二次校验密码不能为空", groups = { Groups.insert.class })
    private String            checkPasswod;

    /**状态*/
    @Min(value = 1, message = "状态不能为空", groups = { Groups.update1.class })
    private Integer           status;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPasswod() {
        return checkPasswod;
    }

    public void setCheckPasswod(String checkPasswod) {
        this.checkPasswod = checkPasswod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

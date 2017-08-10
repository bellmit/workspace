/**
 * 
 */
package com.jumore.jmbi.model.param.permission;

import org.hibernate.validator.constraints.NotEmpty;

import com.jumore.jmbi.common.util.validation.Groups;
import com.jumore.jmbi.model.param.BaseParam;

/** 
*密码修改参数类
* @author: luochao
* @since: 2016年7月29日  下午1:08:57
* @history:
*/
public class PasswordParam extends BaseParam {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3008447956510480870L;
    /**主键*/
    private Integer              id;
    /**原密码*/
    @NotEmpty(message = "原密码不能为空", groups = { Groups.update.class })
    private String            oldPassword;
    /**新密码*/
    @NotEmpty(message = "新密码不能为空", groups = { Groups.update.class, Groups.update1.class })
    private String            newPassword;
    /**校验新密码一致性*/
    @NotEmpty(message = "二次校验密码不能为空", groups = { Groups.update.class, Groups.update1.class })
    private String            checkPasswod;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

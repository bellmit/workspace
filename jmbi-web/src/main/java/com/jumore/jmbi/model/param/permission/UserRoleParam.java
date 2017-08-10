/**
 * 
 */
package com.jumore.jmbi.model.param.permission;

import com.jumore.jmbi.model.param.BaseParam;

/** 
*
* @author: luochao
* @since: 2016年7月29日  下午5:17:31
* @history:
*/
public class UserRoleParam extends BaseParam {

    /**
     * 
     */
    private static final long serialVersionUID = -3309119072112569726L;

    /**主键*/
    private Long              id;

    /**用户id*/
    private Long              userId;

    /**角色id*/
    private Long              roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

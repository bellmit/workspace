package com.jumore.jmbi.dao.entity.permission;

import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Sequence;
import com.jumore.dove.aop.Table;
import com.jumore.jmbi.dao.entity.base.BaseEntity;

/**
 * 角色菜单关联表
 * @author yexinzhou
 */
@Entity
@Table(name = "perm_role_menu")
public class RoleMenu extends BaseEntity {

    @Id
    @Sequence
    public Long id;

    @Column(name = "role_id")
    public Long roleId;

    @Column(name = "menu_id")
    public Long menuId;
}

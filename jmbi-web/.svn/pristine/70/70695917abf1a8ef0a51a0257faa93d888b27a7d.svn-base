package com.jumore.jmbi.dao.entity.permission;

import java.util.Date;

import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Sequence;
import com.jumore.dove.aop.Table;
import com.jumore.jmbi.dao.entity.base.BaseEntity;

/**
 * 用户角色关联表
 * @author yexinzhou
 */
@Entity
@Table(name = "perm_user_role")
public class AccountRole extends BaseEntity {

    @Id
    @Sequence
    public Long id;

    @Column(name = "role_id")
    public Long roleId;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "create_user_id")
    public Long createUserId;

    @Column(name = "create_time")
    public Date createTime;
}

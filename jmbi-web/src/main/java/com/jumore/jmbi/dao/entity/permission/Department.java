package com.jumore.jmbi.dao.entity.permission;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jmbi_department")
public class Department {
    /** 部门ID */
    @Column(name = "id")
    @Id
    @AutoIncrease
    private Integer                   id;

    /** 部门名称 */
    @Column(name = "name")
    private String                    name;

    /** 创建人ID */
    @Column(name = "user_id")
    private Integer                   userId;

    /** 创建人姓名 */
    private String                    userName;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date                      createTime;

    /** 部门名称 */
    @Column(name = "remark")
    private String                    remark;

    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
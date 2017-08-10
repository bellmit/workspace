package com.jumore.jmbi.dao.entity.permission;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "jmbi_role")
public class Role {
    /** 角色ID */
    @Column(name = "id")
    @Id
    @AutoIncrease
    private Integer id;

    /** 角色名称 */
    @Column(name = "name")
    private String  name;

    /** 部门ID */
    @Column(name = "dept_id")
    private Integer deptId;

    /** 平台ID */
    @Column(name = "platform_id")
    private Integer platformId;

    /** 功能项：用逗号分隔 */
    @Column(name = "functions")
    private String  functions;

    /** 0-启用，1-禁用 */
    @Column(name = "status")
    private Integer status;

    private String  deptName;

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions == null ? null : functions.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
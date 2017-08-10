package com.jumore.jmbi.dao.entity.permission;

import java.io.Serializable;
import java.util.Date;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;
import com.jumore.jmbi.dao.entity.base.BaseEntity;

@Entity
@Table(name = "jmbi_admin")
public class Account extends BaseEntity implements Serializable {

    private static final  transient   long serialVersionUID = 1L;

    /** ����ID�������� */
    @Column(name = "id")
    @Id
    @AutoIncrease
    private Integer                     id;

    /** ����Ա�˺� */
    @Column(name = "account")
    private String                      account;

    /** ����Ա���� */
    @Column(name = "password")
    private String                      password;

    /** ���� */
    @Column(name = "name")
    private String                      name;

    /** ��ϵ�绰 */
    @Column(name = "phone")
    private String                      phone;

    /** ����Ա��ɫ���ͣ�0��������Ա�� 1��ͨ����Ա */
    @Column(name = "role")
    private Integer                     role;

    /** �Ƿ�Ϊɾ����¼��1���ǣ�0������ */
    @Column(name = "is_del")
    private Integer                     isDel;

    /** 0-���ã�1-���� */
    @Column(name = "status")
    private Integer                     status;

    /** ����ʱ�� */
    @Column(name = "create_time")
    private Date                        createTime;

    /** ���� */
    @Column(name = "mail")
    private String                      mail;

    /** ���� */
    @Column(name = "department")
    private String                      department;

    /** ��ע */
    @Column(name = "remark")
    private String                      remark;

    /** 所属角色 */
    @Column(name = "roles")
    private String                      roles;

    private String                      functions;
    /** 所属角色名称 */
    private String                      roleNames;
    /** 所属部门 */
    private transient String            deptName;
    /** 所属平台 */
    private String                      platformIds;
    private String                      platformNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPlatformNames() {
        return platformNames;
    }

    public void setPlatformNames(String platformNames) {
        this.platformNames = platformNames;
    }

    public String getPlatformIds() {
        return platformIds;
    }

    public void setPlatformIds(String platformIds) {
        this.platformIds = platformIds;
    }
}
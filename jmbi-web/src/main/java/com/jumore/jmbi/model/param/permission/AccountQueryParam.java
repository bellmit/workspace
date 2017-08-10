package com.jumore.jmbi.model.param.permission;

import java.util.Date;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Id;
import com.jumore.jmbi.model.param.PageQueryParam;


 
public class AccountQueryParam extends PageQueryParam {

    private static final long serialVersionUID = 6289148855138278597L;

    /** ����ID�������� */
	@Column(name = "id")
	@Id
	@AutoIncrease
	private Integer id;

	/** ����Ա�˺� */
	@Column(name = "account")
	private String account;

	/** ����Ա���� */
	@Column(name = "password")
	private String password;

	/** ���� */
	@Column(name = "name")
	private String name;

	/** ��ϵ�绰 */
	@Column(name = "phone")
	private String phone;

	/** ����Ա��ɫ���ͣ�0��������Ա�� 1��ͨ����Ա */
	@Column(name = "role")
	private Integer role;

	/** �Ƿ�Ϊɾ����¼��1���ǣ�0������ */
	@Column(name = "is_del")
	private Integer isDel;

	/** 0-���ã�1-���� */
	@Column(name = "status")
	private Integer status;

	/** ����ʱ�� */
	@Column(name = "create_time")
	private Date createTime;

	/** ���� */
	@Column(name = "mail")
	private String mail;

	/** ���� */
	@Column(name = "department")
	private String department;

	/** ��ע */
	@Column(name = "remark")
	private String remark;
	
	
	/**
	 * 开始时间
	 */
	private String bdate;

	/**
	 * 结束时间
	 */
	private String edate;
 

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
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		this.mail = mail;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
 

	 
}
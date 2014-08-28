package com.jessrun.web.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @className CertifyAccount
 * @depiction 帐号表
 * @createTime 2013-5-27
 * @author huanko
 */
public class CertifyAccount implements Serializable{

	private static final long serialVersionUID = -1292913203933967267L;
	
	private Integer				id;				//编号
	private Integer				userId;			//用户编号
	private String				account;		//帐号
	private String 				password;		//密码
	private Integer				deptId;			//所在单位
	private String				loginId;		//登录编号
	private Date				loginTime;		//登录时间
	private Date				createdTime;	//创建时间
	private Integer				createdId;		//创建人编号
	private String 				createdName;	//创建人姓名
	private Date				modifyTime;		//修改时间
	private Integer				modifyId;		//修改人编号
	private String 				modifyName;		//修改人姓名
	private String				loginIp;		//登录IP		
	private Integer 			status;			//状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyId() {
		return modifyId;
	}
	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedId() {
		return createdId;
	}
	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}		
}

package com.jessrun.certify.vo;

import java.util.Date;

/**
 * @author kehuan
 * @createdTime 2013-04-22
 */
public class RoleAccount {
	private String accountNum;
	private Integer roleID;
	private Integer isValID;
	private Integer sortNum;
	private Date createdTime;
	private Integer createdUserID;
	private String createdUserName;
	private Date modifyTime;
	private Integer modifyUserID;
	private String modifyUserName;
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public Integer getIsValID() {
		return isValID;
	}
	public void setIsValID(Integer isValID) {
		this.isValID = isValID;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedUserID() {
		return createdUserID;
	}
	public void setCreatedUserID(Integer createdUserID) {
		this.createdUserID = createdUserID;
	}
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyUserID() {
		return modifyUserID;
	}
	public void setModifyUserID(Integer modifyUserID) {
		this.modifyUserID = modifyUserID;
	}
	public String getModifyUserName() {
		return modifyUserName;
	}
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	
	
	
}

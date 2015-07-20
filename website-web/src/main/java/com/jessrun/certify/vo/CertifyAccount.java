package com.jessrun.certify.vo;
/**
 * @author kehuan
 * @createdTime 2013-04-22
 */
import java.util.Date;
public class CertifyAccount {
	private String accountNum;
	private String password;
	private Date loginTime;
	private String loginIP;
	private String loginID;
	private Integer isOnline;
	private Integer sort;
	private Date createdTime;
	private Integer createdUserID;
	private String createdUserName;
	private Integer orgaType;
	private Integer orgaID;
	private Integer accountState;
	private Integer defaultCompanyId;
	private Integer defaultStateId;
	private String defaultCompanyName;
	private String defaultCompanyNameSim;
	private String defaultStateName;
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public Integer getOrgaType() {
		return orgaType;
	}
	public void setOrgaType(Integer orgaType) {
		this.orgaType = orgaType;
	}
	public Integer getOrgaID() {
		return orgaID;
	}
	public void setOrgaID(Integer orgaID) {
		this.orgaID = orgaID;
	}
	public Integer getAccountState() {
		return accountState;
	}
	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}
	public Integer getDefaultCompanyId() {
		return defaultCompanyId;
	}
	public void setDefaultCompanyId(Integer defaultCompanyId) {
		this.defaultCompanyId = defaultCompanyId;
	}
	public Integer getDefaultStateId() {
		return defaultStateId;
	}
	public void setDefaultStateId(Integer defaultStateId) {
		this.defaultStateId = defaultStateId;
	}
	public String getDefaultCompanyName() {
		return defaultCompanyName;
	}
	public void setDefaultCompanyName(String defaultCompanyName) {
		this.defaultCompanyName = defaultCompanyName;
	}
	public String getDefaultCompanyNameSim() {
		return defaultCompanyNameSim;
	}
	public void setDefaultCompanyNameSim(String defaultCompanyNameSim) {
		this.defaultCompanyNameSim = defaultCompanyNameSim;
	}
	public String getDefaultStateName() {
		return defaultStateName;
	}
	public void setDefaultStateName(String defaultStateName) {
		this.defaultStateName = defaultStateName;
	}
	
}

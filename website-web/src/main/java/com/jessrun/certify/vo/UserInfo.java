package com.jessrun.certify.vo;

import java.util.Date;

public class UserInfo {
	private int id;
	
	private int userId;
	
	private String userName;
	
	private String accountNum;
	
	private Date loginTime;
	
	private String loginIP;
	
	private String loginId;
	
	private Integer orgaType;
	
	private Integer orgaId;
	
	private Integer deptId;
	
	private Integer defaultCompanyId;
	
	private String defaultCompanyName;
	
	private String defaultCompanyNameSIM;
	
	private String defaultCompanyNameSIMCN;
	
	private Integer defaultStateId;
	
	private String defaultStateName;
	
	private Integer defaultDeptId;
	
	private String defaultDeptName;
	
	private Integer maxOrgaType;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public Integer getOrgaType() {
		return orgaType;
	}

	public void setOrgaType(Integer orgaType) {
		this.orgaType = orgaType;
	}

	public Integer getOrgaId() {
		return orgaId;
	}

	public void setOrgaId(Integer orgaId) {
		this.orgaId = orgaId;
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

	public Integer getMaxOrgaType() {
		return maxOrgaType;
	}

	public void setMaxOrgaType(Integer maxOrgaType) {
		this.maxOrgaType = maxOrgaType;
	}

	public String getDefaultCompanyNameSIM() {
		return defaultCompanyNameSIM;
	}

	public void setDefaultCompanyNameSIM(String defaultCompanyNameSIM) {
		this.defaultCompanyNameSIM = defaultCompanyNameSIM;
	}
	
	

	public String getDefaultCompanyName() {
		return defaultCompanyName;
	}

	public void setDefaultCompanyName(String defaultCompanyName) {
		this.defaultCompanyName = defaultCompanyName;
	}

	public String getDefaultCompanyNameSIMCN() {
		return defaultCompanyNameSIMCN;
	}

	public void setDefaultCompanyNameSIMCN(String defaultCompanyNameSIMCN) {
		this.defaultCompanyNameSIMCN = defaultCompanyNameSIMCN;
	}

	public String getDefaultStateName() {
		return defaultStateName;
	}

	public void setDefaultStateName(String defaultStateName) {
		this.defaultStateName = defaultStateName;
	}

	public Integer getDefaultDeptId() {
		return defaultDeptId;
	}

	public void setDefaultDeptId(Integer defaultDeptId) {
		this.defaultDeptId = defaultDeptId;
	}

	public String getDefaultDeptName() {
		return defaultDeptName;
	}

	public void setDefaultDeptName(String defaultDeptName) {
		this.defaultDeptName = defaultDeptName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}



	public static final String USERINFO_KEY = "___UserInfo";
}

package com.jessrun.web.system.domain;

public class ConfCompany {
	private Integer companyId;
	private String confName;
	private String confKey;
	private String confVal;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getConfVal() {
		return confVal;
	}
	public void setConfVal(String confVal) {
		this.confVal = confVal;
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	
}

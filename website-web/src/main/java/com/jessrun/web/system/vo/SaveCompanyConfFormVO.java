package com.jessrun.web.system.vo;

import java.util.List;

import com.jessrun.web.system.domain.ConfCompany;

public class SaveCompanyConfFormVO {
	private Integer companyId;
	private List<ConfCompany> confList;
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public List<ConfCompany> getConfList() {
		return confList;
	}
	public void setConfList(List<ConfCompany> confList) {
		this.confList = confList;
	}
	
}

package com.jessrun.web.system.service;

import java.util.List;

import com.jessrun.web.system.domain.ConfCostCompany;

public interface ConfCostCompanyService {
	public List<ConfCostCompany> getCompanyConfByCompanyId(Integer companyId);
	
	public void deleteByCompanyId(Integer companyId);
	
	public void add(ConfCostCompany confCostCompany);
	
}

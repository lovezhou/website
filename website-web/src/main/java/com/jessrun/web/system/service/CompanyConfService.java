package com.jessrun.web.system.service;

import java.util.List;

import com.jessrun.web.system.domain.ConfCompany;

public interface CompanyConfService {
	public List<ConfCompany> getCompanyConfByCompanyId(Integer companyId);
	
	public void delete(ConfCompany confCompany);
	
	public void delete(Integer companyId,String confKey);
	
	public void update(ConfCompany confCompany);
	
	public void add(ConfCompany confCompany);
	
	public void saveCompanyConf(Integer companyId,List<ConfCompany> confList);
}

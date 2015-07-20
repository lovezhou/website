package com.jessrun.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CompanyConfMapper;
import com.jessrun.web.system.domain.ConfCompany;
import com.jessrun.web.system.service.CompanyConfService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class CompanyConfServiceImpl implements CompanyConfService {
	@Autowired
	private CompanyConfMapper companyConfMapper;
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void add(ConfCompany confCompany){
		companyConfMapper.insert(confCompany.getCompanyId(),confCompany.getConfName(), confCompany.getConfKey(), confCompany.getConfVal());
	}
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void update(ConfCompany confCompany){
		companyConfMapper.update(confCompany.getCompanyId(),confCompany.getConfKey(), confCompany.getConfVal());
	}
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void delete(Integer companyId,String confKey){
		companyConfMapper.delete(companyId, confKey);
	}
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void delete(ConfCompany confCompany){
		companyConfMapper.delete(confCompany.getCompanyId(), confCompany.getConfKey());
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
	public List<ConfCompany> getCompanyConfByCompanyId(Integer companyId){
		return companyConfMapper.selectByCompanyId(companyId);
	}
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void saveCompanyConf(Integer companyId,List<ConfCompany> confList){
		if(confList == null){
			return;
		}
		
		for(ConfCompany confCompany : confList){
			companyConfMapper.update(companyId,  confCompany.getConfKey(), confCompany.getConfVal());
		}
	}

	public CompanyConfMapper getCompanyConfMapper() {
		return companyConfMapper;
	}

	public void setCompanyConfMapper(CompanyConfMapper companyConfMapper) {
		this.companyConfMapper = companyConfMapper;
	}
	
	
}

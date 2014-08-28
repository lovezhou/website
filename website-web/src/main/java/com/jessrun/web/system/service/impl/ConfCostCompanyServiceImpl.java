package com.jessrun.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CompanyConfMapper;
import com.jessrun.web.system.dao.ConfCostCompanyMapper;
import com.jessrun.web.system.domain.ConfCompany;
import com.jessrun.web.system.domain.ConfCostCompany;
import com.jessrun.web.system.service.CompanyConfService;
import com.jessrun.web.system.service.ConfCostCompanyService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class ConfCostCompanyServiceImpl implements ConfCostCompanyService {
	@Autowired
	private ConfCostCompanyMapper confCostCompanyMapper;
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void add(ConfCostCompany confCostCompany){
	    confCostCompanyMapper.insert(confCostCompany);
	}
	
	@Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS) 
	public void deleteByCompanyId(Integer companyId){
	    confCostCompanyMapper.deleteByCompanyId(companyId);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
	public List<ConfCostCompany> getCompanyConfByCompanyId(Integer companyId){
		return confCostCompanyMapper.getCompanyConfByCompanyId(companyId);
	}
	
}

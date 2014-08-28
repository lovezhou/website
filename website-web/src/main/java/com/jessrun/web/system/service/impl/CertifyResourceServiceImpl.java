package com.jessrun.web.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CertifyResourceMapper;
import com.jessrun.web.system.domain.CertifyResource;
import com.jessrun.web.system.service.CertifyResourceService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CertifyResourceServiceImpl implements CertifyResourceService{
	@Autowired
	private CertifyResourceMapper resourceMapper;

	
	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyResource> getCertifyResourceList(Integer accountId) {
		return resourceMapper.getCertifyResourceList(accountId);
	}
	
	public CertifyResourceMapper getResourceMapper() {
		return resourceMapper;
	}

	public void setResourceMapper(CertifyResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;
	}
}

package com.jessrun.web.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CertifyUserMapper;
import com.jessrun.web.system.domain.CertifyUser;
import com.jessrun.web.system.service.CertifyUserService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class CertifyUserServiceImpl implements CertifyUserService{

	@Autowired
	private CertifyUserMapper certifyUserMapper;
	
	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int addCertifyUser(CertifyUser certifyUser) {
		return certifyUserMapper.addCertifyUser(certifyUser);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int deleteByPrimaryKey(Integer Id) {
		return certifyUserMapper.deleteByPrimaryKey(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public CertifyUser getCertifyUserById(Integer Id) {
		return certifyUserMapper.getCertifyUserById(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyUser> getCertifyUserListByPage(Map<String, Object> map) {
		return certifyUserMapper.getCertifyUserListByPage(map);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int updateCertifyUser(CertifyUser certifyUser) {
		return certifyUserMapper.updateCertifyUser(certifyUser);
	}

	public CertifyUserMapper getCertifyUserMapper() {
		return certifyUserMapper;
	}

	public void setCertifyUserMapper(CertifyUserMapper certifyUserMapper) {
		this.certifyUserMapper = certifyUserMapper;
	}
	
	

}

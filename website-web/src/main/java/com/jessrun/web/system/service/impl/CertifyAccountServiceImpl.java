package com.jessrun.web.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.certify.vo.UserInfo;
import com.jessrun.web.system.dao.CertifyAccountMapper;
import com.jessrun.web.system.domain.CertifyAccount;
import com.jessrun.web.system.service.CertifyAccountService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class CertifyAccountServiceImpl implements CertifyAccountService{
	
	@Autowired
	private CertifyAccountMapper accountMapper;

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int addCertifyAccount(CertifyAccount certifyAccount) {
		return accountMapper.addCertifyAccount(certifyAccount);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int deleteCertifyAccount(Integer Id) {
		return accountMapper.deleteCertifyAccount(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public CertifyAccount getCertifyAccountById(Integer Id) {
		return accountMapper.getCertifyAccountById(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserInfo> getCertifyAccountListByPage(
			Map<String, Object> map) {
		return accountMapper.getCertifyAccountListByPage(map);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int updateCertifyAccount(CertifyAccount certifyAccount) {
		return accountMapper.updateCertifyAccount(certifyAccount);
	}

	public CertifyAccountMapper getAccountMapper() {
		return accountMapper;
	}

	public void setAccountMapper(CertifyAccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	
}

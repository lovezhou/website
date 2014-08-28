package com.jessrun.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CertifyDeptMapper;
import com.jessrun.web.system.domain.CertifyDept;
import com.jessrun.web.system.service.CertifyDeptService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class CertifyDeptServiceImpl implements CertifyDeptService{
	
	@Autowired
	private CertifyDeptMapper certifyDeptMapper;
	
	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int addCertifyDept(CertifyDept certifyDept) {
		return certifyDeptMapper.addCertifyDept(certifyDept);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int deleteCertifyDept(Integer Id) {
		return certifyDeptMapper.deleteCertifyDept(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public CertifyDept getCertifyDeptById(Integer Id) {
		return certifyDeptMapper.getCertifyDeptById(Id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyDept> getCertifyDeptList(Integer deptId) {
		return certifyDeptMapper.getCertifyDeptList(deptId);
	}
	
	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyDept> getDeptList(Integer id){
		return certifyDeptMapper.getDeptList(id);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int updateCertifyDept(CertifyDept certifyDept) {
		return certifyDeptMapper.updateCertifyDept(certifyDept);
	}

	public CertifyDeptMapper getCertifyDeptMapper() {
		return certifyDeptMapper;
	}

	public void setCertifyDeptMapper(CertifyDeptMapper certifyDeptMapper) {
		this.certifyDeptMapper = certifyDeptMapper;
	}
	
	

}

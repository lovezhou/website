package com.jessrun.web.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.CertifyResoAccoMapper;
import com.jessrun.web.system.domain.CertifyResoAcco;
import com.jessrun.web.system.service.CertifyResoAccoService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class CertifyResoAccoServiceImpl implements CertifyResoAccoService{

	@Autowired
	private CertifyResoAccoMapper resoAccoMapper;
	
	
	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int addCertifyResoAcco(CertifyResoAcco resoAcco, String resoId,Integer accountId) {
		resoAccoMapper.deleteCertifyResoAccoByAccountId(accountId);
		resoId = resoId + "-1";  	
    	String [] ids = resoId.split(",");
    	resoAcco = new CertifyResoAcco();
    	Integer count = 0;
    	for(int i=0;i<ids.length-1;i++){
    		resoAcco.setResourceId(Integer.parseInt(ids[i]));
    		resoAcco.setAccountId(accountId);
    		count =  resoAccoMapper.addCertifyResoAcco(resoAcco);
    	}
    	return count;
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int deleteCertifyResoAcco(Integer ResourceId) {
		return resoAccoMapper.deleteCertifyResoAcco(ResourceId);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyResoAcco> getCertifyResoAccoById(Integer ResourceId) {
		return resoAccoMapper.getCertifyResoAccoById(ResourceId);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CertifyResoAcco> getCertifyResoAccoListByPage(
			Map<String, Object> map) {
		return resoAccoMapper.getCertifyResoAccoListByPage(map);
	}

	@Override
	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
	public int updateCertifyResoAcco(CertifyResoAcco resoAcco) {
		return resoAccoMapper.updateCertifyResoAcco(resoAcco);
	}

	public CertifyResoAccoMapper getResoAccoMapper() {
		return resoAccoMapper;
	}

	public void setResoAccoMapper(CertifyResoAccoMapper resoAccoMapper) {
		this.resoAccoMapper = resoAccoMapper;
	}
}

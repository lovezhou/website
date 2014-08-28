package com.jessrun.web.system.service;

import java.util.List;
import java.util.Map;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.web.system.domain.CertifyUser;

public interface CertifyUserService extends JessrunMonitor{
	public List<CertifyUser> getCertifyUserListByPage(Map<String,Object> map);
	
	public CertifyUser getCertifyUserById(Integer Id);
	
	public int addCertifyUser(CertifyUser certifyUser);
	
	public int updateCertifyUser(CertifyUser certifyUser);
	
	public int deleteByPrimaryKey(Integer Id);
}

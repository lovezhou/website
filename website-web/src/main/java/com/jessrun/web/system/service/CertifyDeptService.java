package com.jessrun.web.system.service;

import java.util.List;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.web.system.domain.CertifyDept;

public interface CertifyDeptService extends JessrunMonitor{
	
	public List<CertifyDept> getCertifyDeptList(Integer deptId);
	
	public CertifyDept getCertifyDeptById(Integer Id);
	
	public int addCertifyDept(CertifyDept certifyDept);
	
	public int updateCertifyDept(CertifyDept certifyDept);
	
	public int deleteCertifyDept(Integer Id);
	
	public List<CertifyDept> getDeptList(Integer id);
}

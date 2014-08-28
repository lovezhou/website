package com.jessrun.web.system.service;

import java.util.List;
import java.util.Map;

import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.web.system.domain.CertifyAccount;

public interface CertifyAccountService extends JessrunMonitor{
	public List<UserInfo> getCertifyAccountListByPage(
			Map<String, Object> map);

	public CertifyAccount getCertifyAccountById(Integer Id);

	public int addCertifyAccount(CertifyAccount certifyAccount);

	public int updateCertifyAccount(CertifyAccount certifyAccount);

	public int deleteCertifyAccount(Integer Id);
}

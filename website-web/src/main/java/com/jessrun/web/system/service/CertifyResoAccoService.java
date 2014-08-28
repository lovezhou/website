package com.jessrun.web.system.service;

import java.util.List;
import java.util.Map;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.web.system.domain.CertifyResoAcco;

public interface CertifyResoAccoService extends JessrunMonitor{
	public List<CertifyResoAcco> getCertifyResoAccoListByPage(
			Map<String, Object> map);

	public int addCertifyResoAcco(CertifyResoAcco resoAcco,String resoId,Integer accountId);

	public int updateCertifyResoAcco(CertifyResoAcco resoAcco);

	public int deleteCertifyResoAcco(Integer ResourceId);

	public List<CertifyResoAcco> getCertifyResoAccoById(Integer ResourceId);
}

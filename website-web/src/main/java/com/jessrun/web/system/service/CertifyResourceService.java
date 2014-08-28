package com.jessrun.web.system.service;

import java.util.List;
import java.util.Map;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.web.system.domain.CertifyResource;

public interface CertifyResourceService extends JessrunMonitor{
	List<CertifyResource> getCertifyResourceList(Integer accountId);
}

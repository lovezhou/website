package com.jessrun.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.CertifyResource;

public interface CertifyResourceMapper extends OracleMapper  {
	List<CertifyResource> getCertifyResourceList(@Param("accountId")Integer accountId);
}

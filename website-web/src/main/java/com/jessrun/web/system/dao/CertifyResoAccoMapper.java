package com.jessrun.web.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.CertifyResoAcco;

@Repository
public interface CertifyResoAccoMapper extends OracleMapper{
	 List<CertifyResoAcco> getCertifyResoAccoListByPage(
			Map<String, Object> map);

	 int addCertifyResoAcco(CertifyResoAcco resoAcco);

	 int updateCertifyResoAcco(CertifyResoAcco resoAcco);

	 int deleteCertifyResoAcco(Integer ResourceId);

	 List<CertifyResoAcco> getCertifyResoAccoById(Integer ResourceId);
	 
	 int deleteCertifyResoAccoByAccountId(@Param("accountId")Integer accountId);
	 
}

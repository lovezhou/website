package com.jessrun.web.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.CertifyUser;

@Repository
public interface CertifyUserMapper extends OracleMapper{
	 List<CertifyUser> getCertifyUserListByPage(Map<String,Object> map);
	
	 CertifyUser getCertifyUserById(Integer Id);
	
	 int addCertifyUser(CertifyUser certifyUser);
	
	 int updateCertifyUser(CertifyUser certifyUser);
	
	 int deleteByPrimaryKey(Integer Id);
}

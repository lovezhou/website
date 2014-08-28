package com.jessrun.web.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.CertifyAccount;
@Repository
public interface CertifyAccountMapper  extends OracleMapper {
	 List<UserInfo> getCertifyAccountListByPage(
			Map<String, Object> map);

	 CertifyAccount getCertifyAccountById(Integer Id);

	 int addCertifyAccount(CertifyAccount certifyAccount);

	 int updateCertifyAccount(CertifyAccount certifyAccount);

	 int deleteCertifyAccount(Integer Id);
}

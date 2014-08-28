package com.jessrun.web.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.CertifyDept;

@Repository
public interface CertifyDeptMapper extends OracleMapper{
	 List<CertifyDept> getCertifyDeptList(Integer deptId);
	
	 CertifyDept getCertifyDeptById(Integer Id);
	
	 int addCertifyDept(CertifyDept certifyDept);
	
	 int updateCertifyDept(CertifyDept certifyDept);
	
	 int deleteCertifyDept(Integer Id);
	 
	 List<CertifyDept> getDeptList(@Param(value="id")Integer id);
}

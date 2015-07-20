package com.jessrun.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.ConfCostCompany;

@Repository
public interface ConfCostCompanyMapper extends OracleMapper  {
    
	public List<ConfCostCompany> getCompanyConfByCompanyId(@Param(value="companyId")Integer companyId);
	
	public int deleteByCompanyId(@Param(value="companyId")Integer companyId);
	
	public int insert(ConfCostCompany confCostCompany);



}

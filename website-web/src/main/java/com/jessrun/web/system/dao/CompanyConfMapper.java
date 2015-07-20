package com.jessrun.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.ConfCompany;

public interface CompanyConfMapper extends OracleMapper  {
	public List<ConfCompany> selectByCompanyId(@Param(value="companyId")Integer companyId);
	
	public int delete(@Param(value="companyId")Integer companyId,@Param(value="confKey")String confKey);
	
	public int update(@Param(value="companyId")Integer companyId,@Param(value="confKey")String confKey,@Param(value="confVal")String confVal);
	
	public int insert(@Param(value="companyId")Integer companyId,@Param(value="confName")String confName,@Param(value="confKey")String confKey,@Param(value="confVal")String confVal);
}

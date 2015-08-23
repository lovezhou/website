package com.jessrun.rbac.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.rbac.domain.SysRoleVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysRoleMapper extends OracleMapper {

		 int saveObject(SysRoleVO  obj); 

		 int updateObject(SysRoleVO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 SysRoleVO selectById(String id);

		 List<SysRoleVO> selectListByPage(Map<String,Object> model);

 		 List<SysRoleVO> isUniqueExist(ValueObject vo);
}
package com.jessrun.system.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.system.domain.SysUserVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysUserMapper extends OracleMapper {

		 int saveObject(SysUserVO  obj); 

		 int updateObject(SysUserVO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 SysUserVO selectById(String id);

		 List<SysUserVO> selectListByPage(Map<String,Object> model);

 		 List<SysUserVO> isUniqueExist(ValueObject vo);
}
package com.jessrun.system.dao;

import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.system.domain.SysDict;
import java.util.List;
import java.util.Map;
@Repository
public interface SysDictMapper extends OracleMapper {
		 int saveObject(SysDict  obj); 

		 int updateObject(SysDict  obj); 

		 int deleteById(String  id);

		 SysDict selectById(String id);

		 List<SysDict> selectListByPage(Map<String,Object> model);

}
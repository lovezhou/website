package com.jessrun.rbac.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.rbac.domain.SysFunctionsVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysFunctionsMapper extends OracleMapper {

		 int saveObject(SysFunctionsVO  obj); 

		 int updateObject(SysFunctionsVO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 SysFunctionsVO selectById(String id);

		 List<SysFunctionsVO> selectListByPage(Map<String,Object> model);

 		 List<SysFunctionsVO> isUniqueExist(ValueObject vo);
}
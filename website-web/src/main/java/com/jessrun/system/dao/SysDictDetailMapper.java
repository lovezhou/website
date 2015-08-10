package com.jessrun.system.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.system.domain.SysDictDetailVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysDictDetailMapper extends OracleMapper {

		 int saveObject(SysDictDetailVO  obj); 

		 int updateObject(SysDictDetailVO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 SysDictDetailVO selectById(String id);

		 List<SysDictDetailVO> selectListByPage(Map<String,Object> model);

 		 List<SysDictDetailVO> isUniqueExist(ValueObject vo);
}
package com.jessrun.system.dao;

import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.system.domain.SysDictVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysDictMapper extends OracleMapper {

		 int saveObject(SysDictVO  obj); 

		 int updateObject(SysDictVO  obj); 

		 int deleteById(String  id);
 
		 int deleteByIds(List<String> ids);
		 
		 SysDictVO selectById(String id);

		 List<SysDictVO> selectListByPage(Map<String,Object> model);

		 List<SysDictVO> isUniqueExist(ValueObject vo);

         List<SysDictVO> queryDict();

}
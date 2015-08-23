package com.jessrun.rbac.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import com.jessrun.rbac.domain.SysResourcesVO;
import java.util.List;
import java.util.Map;

@Repository
public interface SysResourcesMapper extends OracleMapper {

		 int saveObject(SysResourcesVO  obj); 

		 int updateObject(SysResourcesVO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 SysResourcesVO selectById(String id);

		 List<SysResourcesVO> selectListByPage(Map<String,Object> model);

 		 List<SysResourcesVO> isUniqueExist(ValueObject vo);

         List<SysResourcesVO> selectListTree();
}
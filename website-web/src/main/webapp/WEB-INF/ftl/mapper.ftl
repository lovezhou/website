package ${packageName}.dao;


import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.common.web.ValueObject;
import ${packageName}.domain.${className}VO;
import java.util.List;
import java.util.Map;

@Repository
public interface ${className}Mapper extends OracleMapper {

		 int saveObject(${className}VO  obj); 

		 int updateObject(${className}VO  obj); 

		 int deleteById(String  id);
		 
		 int deleteByIds(List<String> ids);

		 ${className}VO selectById(String id);

		 List<${className}VO> selectListByPage(Map<String,Object> model);

 		 List<${className}VO> isUniqueExist(ValueObject vo);
}
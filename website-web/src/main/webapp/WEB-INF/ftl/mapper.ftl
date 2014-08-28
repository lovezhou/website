package ${basePath}.dao;

import org.springframework.stereotype.Repository;
import com.jessrun.common.dao.mybatis.OracleMapper;
import ${basePath}.domain.${className};
import java.util.List;
import java.util.Map;

@Repository
public interface ${className}Mapper extends OracleMapper {

		 int saveObject(${className}  obj); 

		 int updateObject(${className}  obj); 

		 int deleteById(String  id);

		 ${className} selectById(String id);

		 List<${className}> selectListByPage(Map<String,Object> model);

}
package ${basePath}.service;

import ${basePath}.domain.${className};
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface ${className}Service extends JessrunMonitor {
         
		 int saveObject(${className}  obj); 

		 int updateObject(${className}  obj); 

		 int deleteById(String  id);

		 ${className} selectById(String id);

		 List<${className}> selectListByPage(Map<String,Object> model);

}
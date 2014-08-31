package ${basePath}.service;

import ${basePath}.domain.${className};
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface ${className}Service extends JessrunMonitor {
         
		 public int saveObject(${className}  obj); 

		 public int updateObject(${className}  obj); 

		 public int deleteById(String  id);

		 public ${className} selectById(String id);

		 public List<${className}> selectListByPage(Map<String,Object> model);

}
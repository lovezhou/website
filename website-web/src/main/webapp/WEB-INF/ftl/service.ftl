package ${packageName}.service;

import ${packageName}.domain.${className}VO;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface ${className}Service extends JessrunMonitor {
         
		 public int saveObject(${className}VO  obj); 

		 public int updateObject(${className}VO  obj); 

		 public int deleteById(String  id);

		 public ${className}VO selectById(String id);

		 public List<${className}VO> selectListByPage(Map<String,Object> model);

}
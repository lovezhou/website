package ${basePath}.service.impl;

import ${basePath}.domain.${className};
import ${basePath}.service.${className}Service;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class ${className}ServiceImpl implements ${className}Service {

		 @Autowired
         private  ${className}Mapper   ${classNameVar}Mapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 int saveObject(${className}  obj){
		 	 return ${classNameVar}Mapper.saveObject(obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 int updateObject(${className}  obj){
		 	return ${classNameVar}Mapper.updateObject(obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 int deleteById(String  id){
		 	return ${classNameVar}Mapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 ${className} selectById(String id){
		 	return ${classNameVar}Mapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 List<${className}> selectListByPage(Map<String,Object> model){
		 	return ${classNameVar}Mapper.selectListByPage(model);
		 }

}
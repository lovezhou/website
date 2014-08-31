package ${basePath}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${basePath}.domain.${className};
import ${basePath}.service.${className}Service;
import  ${basePath}.dao.${className}Mapper;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class ${className}ServiceImpl implements ${className}Service {

		<#assign classNameVar="${className[0]?lower_case+className[1..]}">
		 @Autowired
         private  ${className}Mapper   ${classNameVar}Mapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(${className}  obj){
		 	 return ${classNameVar}Mapper.saveObject(obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(${className}  obj){
		 	return ${classNameVar}Mapper.updateObject(obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return ${classNameVar}Mapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public ${className} selectById(String id){
		 	return ${classNameVar}Mapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<${className}> selectListByPage(Map<String,Object> model){
		 	return ${classNameVar}Mapper.selectListByPage(model);
		 }

}
package ${packageName}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.domain.${className}VO;
import ${packageName}.service.${className}Service;
import  ${packageName}.dao.${className}Mapper;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class ${className}ServiceImpl implements ${className}Service {

		<#assign classNameVar="${className[0]?lower_case+className[1..]}">
		 @Autowired
         private  ${className}Mapper   ${classNameVar}Mapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(${className}VO  obj){
		 	 return ${classNameVar}Mapper.saveObject(obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(${className}VO  obj){
		 	return ${classNameVar}Mapper.updateObject(obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return ${classNameVar}Mapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public ${className}VO selectById(String id){
		 	return ${classNameVar}Mapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<${className}VO> selectListByPage(Map<String,Object> model){
		 	return ${classNameVar}Mapper.selectListByPage(model);
		 }

}
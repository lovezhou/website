package ${packageName}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.common.web.ValueObject;
import com.jessrun.constant.Constant;
import com.jessrun.platform.util.StringUtils;

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
		 public int saveObject(ValueObject  obj){
		 	 return ${classNameVar}Mapper.saveObject((${className}VO)obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		 	return ${classNameVar}Mapper.updateObject((${className}VO)obj);
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
		 
		     @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public int deleteByIds(List<String> ids) {
            return  ${classNameVar}Mapper.deleteByIds(ids);
        }

        @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public boolean isUniqueExist(ValueObject vo) {
             List<${className}VO> list = ${classNameVar}Mapper.isUniqueExist(vo);
             if(StringUtils.isNullOrEmpty(vo.getId())){
                 if(list==null || list.size()==0){
                     return true;
                 }else{
                     return false;
                 }
             }else{
                 if(list==null || list.size()==0){
                     return true;
                 }else if(list.size()==1){
                     if(vo.getId().equals(list.get(0).getId())){
                         return true;
                     }else{
                         return  false;
                     }
                 }else{
                     return false;
                 }
             }
        }

}
package com.jessrun.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.common.web.ValueObject;
import com.jessrun.constant.Constant;
import com.jessrun.platform.util.StringUtils;

import com.jessrun.rbac.domain.SysRoleVO;
import com.jessrun.rbac.service.SysRoleService;
import  com.jessrun.rbac.dao.SysRoleMapper;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysRoleServiceImpl implements SysRoleService {

		 @Autowired
         private  SysRoleMapper   sysRoleMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(ValueObject  obj){
		 	 return sysRoleMapper.saveObject((SysRoleVO)obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		 	return sysRoleMapper.updateObject((SysRoleVO)obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return sysRoleMapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public SysRoleVO selectById(String id){
		 	return sysRoleMapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<SysRoleVO> selectListByPage(Map<String,Object> model){
		 	return sysRoleMapper.selectListByPage(model);
		 }
		 
		     @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public int deleteByIds(List<String> ids) {
            return  sysRoleMapper.deleteByIds(ids);
        }

        @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public boolean isUniqueExist(ValueObject vo) {
             List<SysRoleVO> list = sysRoleMapper.isUniqueExist(vo);
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
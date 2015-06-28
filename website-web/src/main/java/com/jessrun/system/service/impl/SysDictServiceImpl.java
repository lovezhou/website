package com.jessrun.system.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.system.dao.SysDictMapper;
import com.jessrun.system.domain.SysDict;
import com.jessrun.system.service.SysDictService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysDictServiceImpl implements SysDictService {

	
	     
		 
         @Autowired
         private  SysDictMapper   sysDictMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
         public int saveObject(SysDict  obj){
        	 int num = sysDictMapper.saveObject(obj);
        	 Cache dictCache =  EhCacheUtil.newInstance().getCache("DictCache");
        	 
        	 
             return num  ;
         }

         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
         public int updateObject(SysDict  obj){
            return sysDictMapper.updateObject(obj);
         } 
            
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
         public int deleteById(String  id){
            return sysDictMapper.deleteById(id);
         }

         @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
         public SysDict selectById(String id){
            return sysDictMapper.selectById(id);
         }

        @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
        public List<SysDict> selectListByPage(Map<String,Object> model){
            return sysDictMapper.selectListByPage(model);
        }

}


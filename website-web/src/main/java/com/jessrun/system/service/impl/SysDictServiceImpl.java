package com.jessrun.system.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.common.web.ValueObject;
import com.jessrun.constant.Constant;
import com.jessrun.system.dao.SysDictMapper;
import com.jessrun.system.domain.SysDictVO;
import com.jessrun.system.service.SysDictService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysDictServiceImpl implements SysDictService {
    
         
         private EhCacheUtil ehCacheUtil;
         
         public SysDictServiceImpl(){
             InputStream  is = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
             ehCacheUtil  = EhCacheUtil.newInstance(is);
         }

		 @Autowired
         private  SysDictMapper   sysDictMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(ValueObject  obj){
		 	 return sysDictMapper.saveObject((SysDictVO)obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		    int num =  sysDictMapper.updateObject((SysDictVO) obj);
		    //将新的数据缓存起来
		    ehCacheUtil.put(Constant.DICT_CACHE, obj.getId(), obj);
		 	return num;
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		    int num =  sysDictMapper.deleteById(id);
		    ehCacheUtil.removeObject(Constant.DICT_CACHE, id);
		 	return num ;
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public SysDictVO selectById(String id){
	        SysDictVO vo =(SysDictVO) ehCacheUtil.getObject(Constant.DICT_CACHE, id);
	        if(vo==null){
	            vo= sysDictMapper.selectById(id);
	            ehCacheUtil.put(Constant.DICT_CACHE, vo.getId(), vo);
	        }
		 	return vo;
		 }

	  	 @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<SysDictVO> selectListByPage(Map<String,Object> model){
		 	return sysDictMapper.selectListByPage(model);
		 }

}
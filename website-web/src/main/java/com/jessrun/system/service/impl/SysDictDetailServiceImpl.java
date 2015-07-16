package com.jessrun.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.system.domain.SysDictDetailVO;
import com.jessrun.system.service.SysDictDetailService;
import  com.jessrun.system.dao.SysDictDetailMapper;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysDictDetailServiceImpl implements SysDictDetailService {

		 @Autowired
         private  SysDictDetailMapper   sysDictDetailMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(SysDictDetailVO  obj){
		 	 return sysDictDetailMapper.saveObject(obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(SysDictDetailVO  obj){
		 	return sysDictDetailMapper.updateObject(obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return sysDictDetailMapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public SysDictDetailVO selectById(String id){
		 	return sysDictDetailMapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<SysDictDetailVO> selectListByPage(Map<String,Object> model){
		 	return sysDictDetailMapper.selectListByPage(model);
		 }

}
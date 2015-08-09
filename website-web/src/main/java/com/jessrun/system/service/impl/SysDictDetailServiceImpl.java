package com.jessrun.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.common.web.ValueObject;
import com.jessrun.constant.Constant;
import com.jessrun.platform.util.StringUtils;

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
		 public int saveObject(ValueObject  obj){
		 	 return sysDictDetailMapper.saveObject((SysDictDetailVO)obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		 	return sysDictDetailMapper.updateObject((SysDictDetailVO)obj);
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
		 
		     @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public int deleteByIds(List<String> ids) {
            return  sysDictDetailMapper.deleteByIds(ids);
        }

        @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public boolean isUniqueExist(ValueObject vo) {
             List<SysDictDetailVO> list = sysDictDetailMapper.isUniqueExist(vo);
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
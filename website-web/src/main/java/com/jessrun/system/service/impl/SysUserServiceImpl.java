package com.jessrun.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.cache.EhCacheUtil;
import com.jessrun.common.web.ValueObject;
import com.jessrun.constant.Constant;
import com.jessrun.platform.util.MD5Util;
import com.jessrun.platform.util.StringUtils;

import com.jessrun.system.domain.SysUserVO;
import com.jessrun.system.service.SysUserService;
import  com.jessrun.system.dao.SysUserMapper;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysUserServiceImpl implements SysUserService {

		 @Autowired
         private  SysUserMapper   sysUserMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(ValueObject  obj){
             SysUserVO vo = (SysUserVO)obj;
             vo.setLoginPasswd(MD5Util.getMD5String(vo.getLoginPasswd()));
		 	 return sysUserMapper.saveObject(vo);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		     SysUserVO vo = (SysUserVO)obj;
		     SysUserVO tmp = selectById(vo.getId());
		     if(!vo.getLoginPasswd().equals(tmp.getLoginPasswd())){
		         vo.setLoginPasswd(MD5Util.getMD5String(vo.getLoginPasswd()));
		     }
		 	return sysUserMapper.updateObject(vo);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return sysUserMapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public SysUserVO selectById(String id){
		 	return sysUserMapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<SysUserVO> selectListByPage(Map<String,Object> model){
		 	return sysUserMapper.selectListByPage(model);
		 }
		 
		     @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public int deleteByIds(List<String> ids) {
            return  sysUserMapper.deleteByIds(ids);
        }

        @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public boolean isUniqueExist(ValueObject vo) {
             List<SysUserVO> list = sysUserMapper.isUniqueExist(vo);
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
package com.jessrun.rbac.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.common.web.ValueObject;
import com.jessrun.platform.util.StringUtils;
import com.jessrun.platform.util.TreeUtil;
import com.jessrun.rbac.dao.SysResourcesMapper;
import com.jessrun.rbac.domain.SysResourcesVO;
import com.jessrun.rbac.service.SysResourcesService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class SysResourcesServiceImpl implements SysResourcesService {

		 @Autowired
         private  SysResourcesMapper   sysResourcesMapper;
         
         @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int saveObject(ValueObject  obj){
		 	 return sysResourcesMapper.saveObject((SysResourcesVO)obj);
		 }

		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int updateObject(ValueObject  obj){
		 	return sysResourcesMapper.updateObject((SysResourcesVO)obj);
		 } 
			
		 @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
		 public int deleteById(String  id){
		 	return sysResourcesMapper.deleteById(id);
		 }

	     @Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public SysResourcesVO selectById(String id){
		 	return sysResourcesMapper.selectById(id);
		 }

	  	@Transactional(value="OracletransactionManager",readOnly = true, propagation = Propagation.SUPPORTS)
		 public List<SysResourcesVO> selectListByPage(Map<String,Object> model){
		 	return sysResourcesMapper.selectListByPage(model);
		 }
		 
		     @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public int deleteByIds(List<String> ids) {
            return  sysResourcesMapper.deleteByIds(ids);
        }

        @Override
        @Transactional(value="OracletransactionManager",readOnly = false, propagation = Propagation.SUPPORTS)
        public boolean isUniqueExist(ValueObject vo) {
             List<SysResourcesVO> list = sysResourcesMapper.isUniqueExist(vo);
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

        @Override
        public List<SysResourcesVO> selectListTree() throws Exception {
             List<SysResourcesVO> list = sysResourcesMapper.selectListTree();
             return TreeUtil.convertToTree(list, "id", "pid", "children", "0");
        }
        
        

}
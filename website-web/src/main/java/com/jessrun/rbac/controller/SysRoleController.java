package com.jessrun.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.ValueObject;

import com.jessrun.rbac.domain.SysRoleVO;
import com.jessrun.rbac.service.SysRoleService;

@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController  extends  BaseController{

    @Autowired
    private SysRoleService sysRoleService;
    
    public void init() {
    	this.setListView("/rbac/sysRole_list");
    	this.setClazz(SysRoleVO.class);
    }
    
    @Override
    public Service getService() {
        return sysRoleService;
    }
    
    
   @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  sysRoleService.isUniqueExist(vo);
       return new Object[]{flag,"【角色名称】必须唯一！"};
    }
    
 
 

}

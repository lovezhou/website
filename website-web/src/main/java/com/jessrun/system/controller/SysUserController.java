package com.jessrun.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.ValueObject;

import com.jessrun.system.domain.SysUserVO;
import com.jessrun.system.service.SysUserService;

@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController  extends  BaseController{

    @Autowired
    private SysUserService sysUserService;
    
    public void init() {
    	this.setListView("/system/sysUser_list");
    	this.setClazz(SysUserVO.class);
    }
    
    @Override
    public Service getService() {
        return sysUserService;
    }
    
    
   @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  sysUserService.isUniqueExist(vo);
       return new Object[]{flag,"【登录名】，【身份证号】必须唯一！"};
    }
    
 
 

}

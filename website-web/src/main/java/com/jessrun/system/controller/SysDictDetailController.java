package com.jessrun.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.ValueObject;

import com.jessrun.system.domain.SysDictDetailVO;
import com.jessrun.system.service.SysDictDetailService;

@Controller
@RequestMapping(value = "/sysDictDetail")
public class SysDictDetailController  extends  BaseController{

    @Autowired
    private SysDictDetailService sysDictDetailService;
    
    public void init() {
    	this.setListView("/system/sysDictDetail_list");
    	this.setClazz(SysDictDetailVO.class);
    }
    
    @Override
    public Service getService() {
        return sysDictDetailService;
    }
    
    
   @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  sysDictDetailService.isUniqueExist(vo);
       return new Object[]{flag,"【字典代码】必须唯一！"};
    }
    
 
 

}

package com.jessrun.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.web.BaseController;
import com.jessrun.platform.util.StringUtils;
import com.jessrun.system.service.SysDictService;

@Controller
@RequestMapping(value = "/sysDict")
public class SysDictController extends  BaseController{
    
    @Autowired
    private SysDictService sysDictService;

    public SysDictController(){
        super("/system/sysDict_list");
    }
    
    

    
    @RequestMapping(value = "/toConfig.do", method = RequestMethod.GET)
    public ModelAndView toConfigView(HttpServletRequest req)  throws Exception {
        
        return new ModelAndView("/system/sysDict_config");
    }
 

}

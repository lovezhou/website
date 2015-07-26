
package com.jessrun.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.common.web.BaseController;
import com.jessrun.system.domain.SysDictVO;
import com.jessrun.system.service.SysDictService;

@Controller
@RequestMapping(value = "/sysDict")
public class SysDictController extends  BaseController{
    
	private SysDictVO vo  ;
	
    @Autowired
    private SysDictService sysDictService;

    
    public void init() {
    	this.setListView("/system/sysDict_list");
    	this.setClazz(SysDictVO.class);
    }

    
    @Override
    public JessrunMonitor getService() {
        return sysDictService;
    }


    @RequestMapping(value = "/toConfig.do", method = RequestMethod.GET)
    public ModelAndView toConfigView(HttpServletRequest req)  throws Exception {
        
        return new ModelAndView("/system/sysDict_config");
    }
 

}


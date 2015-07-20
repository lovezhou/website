package com.jessrun.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.web.BaseController;
import com.jessrun.system.service.SysDictDetailService;

@Controller
@RequestMapping(value = "/sysDictDetail")
public class SysDictDetailController extends BaseController {

    @Autowired
    private SysDictDetailService sysDictDetailService;
    
    public SysDictDetailController(){
        super("/system/sysDictDetail_list");
    }

}

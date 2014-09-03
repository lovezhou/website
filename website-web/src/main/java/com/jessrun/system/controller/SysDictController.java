package com.jessrun.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.system.domain.SysDict;
import com.jessrun.system.service.SysDictService;

@Controller
@RequestMapping(value = "/sysDict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView toSysDictPage(HttpServletRequest req)  throws Exception {
        ModelAndView mav = new ModelAndView("/system/sysDict_list");
        return mav;
    }
 

}
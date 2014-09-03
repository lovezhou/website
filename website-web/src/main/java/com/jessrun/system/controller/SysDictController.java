package com.jessrun.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.platform.util.SerializeUtils;
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
 
    @ResponseBody
    @RequestMapping(value = "/query.json", method = RequestMethod.POST)
    public String query(HttpServletRequest req)  throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        List<SysDict> sysDictList = sysDictService.selectListByPage(model);
        String data = SerializeUtils.toJson(sysDictList);
        return  data;
    }

}
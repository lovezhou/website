package com.jessrun.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.common.web.AjaxUtil;
import com.jessrun.common.web.RequestPageParameter;
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
 
    @SuppressWarnings("all")
    @RequestMapping(value = "/query.json", method = RequestMethod.POST)
    public void query(HttpServletRequest req ,HttpServletResponse response)  throws Exception {
        Map  map = RequestPageParameter.convertPageMap(null,req);
        List<SysDict> sysDictList = sysDictService.selectListByPage(map);
        AjaxUtil.success(response, sysDictList,((Pagination)map.get("pagination")).getCount());
    }

}
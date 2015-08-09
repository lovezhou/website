
package com.jessrun.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.AjaxUtil;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.ValueObject;
import com.jessrun.platform.util.StringUtils;
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
    public Service getService() {
        return sysDictService;
    }


    @RequestMapping(value = "/toConfig.do", method = RequestMethod.GET)
    public ModelAndView toConfigView(HttpServletRequest req)  throws Exception {
        
        return new ModelAndView("/system/sysDict_config");
    }

    @RequestMapping(value = "/queryDict.json", method = RequestMethod.POST)
    public void queryDict(HttpServletRequest req,String filter,HttpServletResponse response)  throws Exception {
        if(StringUtils.isNullOrEmpty(filter)){
          List<SysDictVO> list = sysDictService.queryDict();
          AjaxUtil.success(response, list);
        }
        
        
    }

    

    @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  sysDictService.isUniqueExist(vo);
       return new Object[]{flag,"【字典代码】必须唯一！"};
    }
    
}


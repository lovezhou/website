package com.jessrun.codegenerator.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.codegenerator.domain.ColumnAndType;
import com.jessrun.codegenerator.service.CodeGeneratorService;

/**
 * 
 * 代码生成器
 * @author zmy 2014-8-6 下午10:31:38
 */
@Controller
@RequestMapping(value="/codegenerator")
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @RequestMapping(value="/codegenerator.do", method = RequestMethod.GET)
    public ModelAndView menuXtgn(HttpServletRequest arg0) throws Exception {

        ModelAndView mav = new ModelAndView("/codegenerator/codegenerator");

        return mav;
    }
    
    @RequestMapping(value="/toListView.do", method = RequestMethod.POST)
    public ModelAndView toListView(String tableName ,HttpServletRequest req) throws Exception {
        List<ColumnAndType> list = codeGeneratorService.getListColumnAndType(tableName.toUpperCase());
        ModelAndView mav = new ModelAndView("/codegenerator/codegenerator");
        mav.addObject("voList", list);
        mav.addObject("tableName",tableName);
        mav.addObject("className",codeGeneratorService.convertTableNameToClassName(tableName));
        return mav;
    }
    
    
    // @ResponseBody
    @RequestMapping(value="/codeGenerator.do", method = RequestMethod.POST)
    public ModelAndView codeGenerator(HttpServletRequest req) throws Exception {
        @SuppressWarnings("all")
        Map<String,String[]> map =( Map<String,String[]>)  req.getParameterMap();
      
        Map<String,Object> data = codeGeneratorService.codeGenerator(map);
        ModelAndView mav = new ModelAndView("integration.ftl");
        //视图名称
        mav.addAllObjects(data);
        return mav;
    }

    

}

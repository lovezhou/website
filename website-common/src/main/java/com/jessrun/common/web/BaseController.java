package com.jessrun.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.platform.util.StringUtils;


public class BaseController {
    
    private String   defaultView ="defaultView" ;
    
    protected String  listView  ;
    
    public BaseController(){
        
    }
    
    public BaseController(String listView){
        this.listView=listView;
    }
    
    
    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView toListView(HttpServletRequest req)  throws Exception {
        if(StringUtils.isNullOrEmpty(listView)){
            listView = defaultView ;
        }
        return new ModelAndView(listView);
    }
 
    

}

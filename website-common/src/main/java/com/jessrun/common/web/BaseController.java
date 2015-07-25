package com.jessrun.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.platform.util.StringUtils;


public abstract class BaseController {
    
	private final String  formData = "_jsonData" ;
	
	protected Class<? extends ValueObject> clazz; //vo对于的class
	
	private   JessrunMonitor service ;
	
    private  String   defaultView ="index" ;
    
    protected String  listView  ;
    
    public BaseController(){
    	init();
    	
    }
    
    /**
     * controller 初始化方法
     * 用来初始化BaseController中的成员属性
     */
    public abstract void init();
   
    
    
    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView toListView(HttpServletRequest req)  throws Exception {
        if(StringUtils.isNullOrEmpty(listView)){
            listView = defaultView ;
        }
        return new ModelAndView(listView);
    }
 
    
    
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest req,HttpServletResponse response)  throws Exception {
    	String json = req.getParameter(formData);
    	ValueObject vo = (ValueObject)JSONObject.toBean(JSONObject.fromObject(json),clazz);
        service.saveObject(vo);
    	return  null;
    }

	
    
    
    
	public Class<? extends ValueObject> getClazz() {
	
		return clazz;
	}

	
	public void setClazz(Class<? extends ValueObject> clazz) {
		this.clazz = clazz;
	}

	
	public String getListView() {
		return listView;
	}

	
	public void setListView(String listView) {
		this.listView = listView;
	}

    
    
    
}

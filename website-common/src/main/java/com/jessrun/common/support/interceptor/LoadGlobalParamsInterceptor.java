package com.jessrun.common.support.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;

@Component
public class LoadGlobalParamsInterceptor implements HandlerInterceptor {
	
	public static Map<String,Object> globalParamsMap;
	
	private static final Logger log         = LoggerFactory.getLogger(LoadGlobalParamsInterceptor.class);
	
	public void putGlobalParamsMap(String key,Object obj){
		
		if(globalParamsMap == null){
			globalParamsMap = new HashMap<String,Object>();
		}
		
		log.info("input '" + key + "' into globalParamsMap ");
		
		globalParamsMap.put(key, obj);
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		if(globalParamsMap != null){
			
			for(String key : globalParamsMap.keySet()){
				arg0.setAttribute(key, globalParamsMap.get(key));
			}
		}
		
		return true;
	}

}

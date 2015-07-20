package com.jessrun.common.support.spring.view;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 多视图解析器用来解决spring多视图配置麻烦的问题
 * 
 * @author zmy
 * @version 1.0, 2014-8-27
 * @since 1.0, 2014-8-27
 */
public class MultiViewResolver implements ViewResolver {

	private Map<String,ViewResolver>  resolvers;
	
	/**
	 * 分配解析视图到各个模块
	 * {@inheritDoc}
	 * @see org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang.String, java.util.Locale)
	 */
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		View  view = null;
		if(StringUtils.isNotEmpty(viewName)){
	      int index =  viewName.indexOf(".");
	      String suffix="";
		  if(-1!=index){
			  suffix=viewName.substring(index+1, viewName.length());
			  viewName= viewName.substring(0, index);
		  }
		  if(!suffix.equals("")){
			  ViewResolver resolver = resolvers.get(suffix);
			  view =  resolver.resolveViewName(viewName, locale);
		  }else{
			  view =  resolvers.get("jsp").resolveViewName(viewName, locale);
		  }
		}
		return view;
	}

	
	public Map<String, ViewResolver> getResolvers() {
	
		return resolvers;
	}

	
	public void setResolvers(Map<String, ViewResolver> resolvers) {
	
		this.resolvers = resolvers;
	}

	
	
}

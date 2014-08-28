package com.jessrun.common.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jessrun.platform.util.ConfigUtils;

public class NewCookieUtils {
	public static Cookie getCookieByName(HttpServletRequest request,String cookieName){
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return null;
		}
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(cookieName)){
				return cookie;
			}
		}
		
		return null;
	}
	
	public static Cookie createCookie(String name,String val){
		//替换会出错的字符为不常用的字符
		//val = val.replaceAll("~", "\"");
		
		Cookie cookie = new Cookie(name, val);
		
		cookie.setPath("/");
		return cookie;
	}
	
	public static void writeCookie(HttpServletRequest request,HttpServletResponse response,Cookie cookie){
		if (request != null) {
            String host = request.getHeader("Host");
            if (ConfigUtils.WEBSITE.equals(host)) cookie.setDomain("." + ConfigUtils.DOMAIN);
        }
	    response.addCookie(cookie);
	}
	
	public static void writeCookie(HttpServletRequest request,HttpServletResponse response,Cookie cookie,int maxAge){
		if (request != null) {
            String host = request.getHeader("Host");
            if (ConfigUtils.WEBSITE.equals(host)) cookie.setDomain("." + ConfigUtils.DOMAIN);
        }
	    if(maxAge>0)  cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	public static void deleteCookie(HttpServletResponse response,String cookieName){
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie); 
	}
}

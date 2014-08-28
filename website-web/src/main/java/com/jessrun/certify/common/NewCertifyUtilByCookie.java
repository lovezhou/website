package com.jessrun.certify.common;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jessrun.certify.constant.CookieNames;
import com.jessrun.certify.constant.ParamKeys;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.web.NewCookieUtils;
import com.jessrun.platform.util.SerializeUtils;

public class NewCertifyUtilByCookie {
	/**
     * 产生会话cookie,将用户信息放入COOKIE
     * @return 创建的cookie
	 * @throws Exception 
     */
    public static void createCookie(HttpServletRequest request,HttpServletResponse response,UserInfo userInfo) throws Exception {
    	//System.out.println(SerializeUtils.toJson(userInfo));
    	String temp = SerializeUtils.toJson(userInfo);
    	temp = temp.replaceAll("\"","~");
    	//System.out.println(temp);
    	temp=URLEncoder.encode(temp,"UTF-8");
    	Cookie cookie = new Cookie(CookieNames.CERTIFY_COOKIE_NAME, temp);
    	//System.out.println(cookie.getValue());
        cookie.setPath("/");
        
        NewCookieUtils.writeCookie(request,response, cookie);
    }
    
    public static UserInfo getUserInfo(HttpServletRequest request) throws Exception {
    	Cookie cookie = NewCookieUtils.getCookieByName(request, CookieNames.CERTIFY_COOKIE_NAME);
    	
    	if(cookie == null){
    		return null;
    	}

    	String temp = URLDecoder.decode(cookie.getValue(),"UTF-8");
    	
    	UserInfo userInfo = SerializeUtils.jsonToObj(temp.replaceAll("~", "\""),UserInfo.class);
    	
        return userInfo;
    }
    
    public static String getCompanySIMFromCookie(HttpServletRequest arg0){
		Cookie cookie = NewCookieUtils.getCookieByName(arg0, CookieNames.COMPANYSIM_COOKIE_NAME);
		if(cookie != null){
			return cookie.getValue();
		}
		return null;
	}
    
    public static void setLoginErrorMessage(HttpServletRequest request,HttpServletResponse response,String message){
    	NewCookieUtils.writeCookie(request, response, NewCookieUtils.createCookie(CookieNames.LOGIN_ERROR_COOKIE_NAME, message));
    }
    
    public static String getLoginErrorMessage(HttpServletRequest arg0,HttpServletResponse response){
    	Cookie cookie = NewCookieUtils.getCookieByName(arg0, CookieNames.LOGIN_ERROR_COOKIE_NAME);
		if(cookie != null){
			String message = cookie.getValue();
			cookie = new Cookie(CookieNames.LOGIN_ERROR_COOKIE_NAME,null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			return message;
		}
		return null;
    }
	
    public static String getCompanySIMFromRequest(HttpServletRequest arg0){
		Object obj = arg0.getAttribute(ParamKeys.COMPANYSIM_KEY);
		if(obj == null){
			obj = arg0.getParameter(ParamKeys.COMPANYSIM_KEY);
		}
		if(obj != null){
			return (String)obj;
		}
		return null;
	}
	
    public static boolean getIsSelfRewriteMode(HttpServletRequest arg0){
		Object obj = arg0.getAttribute(ParamKeys.SELF_REWRITE_MODE);
		if(obj != null){
			if(((String)obj).toLowerCase().equals("true")){
				return true;
			}
		}
		return false;
	}
    
    //private static final int CERTIFY_MAX_AGE = 60*20;
}

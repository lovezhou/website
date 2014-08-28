package com.jessrun.certify.common;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.web.CookieUtils;
import com.jessrun.platform.util.SerializeUtils;

/**
 * COOKIE的操作类
 * @author Awen
 *
 */
public class CertifyUtilByCookie {
	/**
	 * cookie名
	 */
	private String cookiename = DEFAULT_COOKIE_NAME;

	/**
	 * COOKIE的最大生命周期
	 */
	private int cookieMaxAge = DEFAULT_PRINCIPALCOOKIE_MAX_AGE;
	
	/**
	 * COOKIE的默认字符集
	 */
	private String cookieEncode = DEFAULT_ENCODE;
	
	/**
     * 产生会话cookie,将用户信息放入COOKIE
     * @return 创建的cookie
	 * @throws Exception 
     */
    private Cookie createCookie(UserInfo userInfo) throws Exception {
    	//System.out.println(SerializeUtils.toJson(userInfo));
    	String temp = SerializeUtils.toJson(userInfo);
    	temp = temp.replaceAll("\"","~");
    	//System.out.println(temp);
    	temp=URLEncoder.encode(temp,"UTF-8");  
    	Cookie cookie = new Cookie(cookiename, temp);
    	//System.out.println(cookie.getValue());
        cookie.setPath("/");
        return cookie;
    }
    
    /**
     * 从COOKIE取出用户信息，没有就会返回空
     * @return
     * @throws Exception 
     */
    public UserInfo getUserInfoFromCookie() throws Exception{
    	Cookie cookie = CookieUtils.getCookie(cookiename);
    	if(cookie == null){
    		return null;
    	}

    	String temp = URLDecoder.decode(cookie.getValue(),"UTF-8");
    	
    	UserInfo userInfo = SerializeUtils.jsonToObj(temp.replaceAll("~", "\""),UserInfo.class);
    	
        return userInfo;
    }
    
    
    /**
     * 移除cookie
     */
    public void removeCookie() {
        CookieUtils.removeCookie(cookiename, "/");
    }

    /**
     * 写入cookie
     * @param userInfo
     * @throws Exception 
     */
    public void writeCookie(UserInfo userInfo) throws Exception {
        CookieUtils.writeCookie(createCookie(userInfo));
    }

	public String getCookieEncode() {
		return cookieEncode;
	}
	public void setCookieEncode(String cookieEncode) {
		this.cookieEncode = cookieEncode;
	}
	public int getCookieMaxAge() {
		return cookieMaxAge;
	}
	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}
	
	/**
     * 默认会话cookie 超时时间(以秒为单位)
     */
    private static final int    DEFAULT_PRINCIPALCOOKIE_MAX_AGE = 3600 * 24 * 30;
    
    /**
     * 默认的cookie名
     */
    private static final String DEFAULT_COOKIE_NAME             = "CertifyCookie";
    
    /**
     * 默认的COOKIE字符集
     */
    private static final String DEFAULT_ENCODE                  = "utf-8";
}

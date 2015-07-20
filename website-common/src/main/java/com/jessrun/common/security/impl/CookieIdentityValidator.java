/**
 * @(#)CookieIdentityValidatorImpl.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.security.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jessrun.common.security.AuthenticationException;
import com.jessrun.common.security.AuthenticationProvider;
import com.jessrun.common.security.IdentityValidator;
import com.jessrun.common.security.KeepLoginStatusVerifier;
import com.jessrun.common.security.Principal;
import com.jessrun.common.security.Verifier;
import com.jessrun.common.web.CookieUtils;
import com.jessrun.common.web.WebContext;
import com.jessrun.platform.util.DateUtils;
import com.jessrun.platform.util.DateUtils.TimeUnit;
import com.jessrun.platform.util.security.Base64Utils;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2011-2-17
 */
public abstract class CookieIdentityValidator implements IdentityValidator {

    private static final Log LOG = LogFactory.getLog(CookieIdentityValidator.class);

    public abstract void setAuthenticationProvider(AuthenticationProvider authenticationProvider);

    public abstract AuthenticationProvider getAuthenticationProvider();

    protected abstract String getPrincipalcookieName();

    protected abstract String getvisitorCookieName();

    /**
     * 是否是单一客户端登录，即一个账户同时只能有一个客户端登录<br/>
     * 如果是单一客户端登录那么采用帐号的最后登录时间戳作为sha摘要<br/>
     * 否则时间戳会保留在每个客户端保证每个客户端都可登录<br/>
     * 
     * @return
     * @author xu.jianguo
     */
    protected abstract boolean singleClientLogin();

    /**
     * 多账户登录的最后时间戳cookie name
     * 
     * @return
     * @author xu.jianguo
     */
    protected abstract String getLastLoginTimeCookieName();

    /**
     * 自动登录cookie name
     */
    public static final String  AUTO_LOGIN_COOKIE_NAME  = "auto_login";

    /**
     * 多账户登录的最后时间戳cookie name
     */
    // public static final String LAST_LOGIN_TIME="last_login_time";

    /**
     * 会话cookie 超时时间(以秒为单位)
     */
    private static final int    PRINCIPALCOOKIE_MAX_AGE = 3600 * 12;
    /**
     * 持久cookie 超时时间(以秒为单位)
     */
    private static final int    VISITORCOOKIE_MAX_AGE   = 3600 * 24 * 30;
    private static final String SPLIT                   = "&";
    private static final String ENCODE                  = "utf-8";

    private String getSHAParam(Serializable id, Long lastLoginTime) {
        return id.toString() + lastLoginTime.toString();
    }

    /**
     * 通过调用CookieUtils和MD5Utils产生会话cookie
     * 
     * @author luoyifan
     * @param principal 用户对象
     * @return 创建的cookie
     */
    private Cookie createPrincipalCookie(Principal principal) {
        StringBuffer cookValue = new StringBuffer();
        Long lastLoginTime = principal.getLastLoginTime();
        String shaParam = getSHAParam(principal.getIdentity(), lastLoginTime);
        cookValue.append(principal.getIdentity().toString());
        cookValue.append(SPLIT + Base64Utils.urlEncoding(DigestUtils.shaHex(shaParam)));
        Cookie cookie = new Cookie(getPrincipalcookieName(), cookValue.toString());
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 通过调用CookieUtils产生自动登录的持久cookie
     * 
     * @author luoyifan
     * @param principal 用户对象
     * @return 创建的cookie
     */
    private Cookie createAutoLoginCookie(Verifier verifier, Principal principal) {
        CookieUtils.removeCookie(getvisitorCookieName(), "/");
        if (!(verifier instanceof KeepLoginStatusVerifier)) return null;
        KeepLoginStatusVerifier vf = (KeepLoginStatusVerifier) verifier;
        if (!vf.isKeepLoginStatus() || vf.getKeepLoginMaxTime() == 0) return null;
        String value = principal.getIdentity().toString() + SPLIT + principal.getLoginName();
        Cookie cookie = new Cookie(AUTO_LOGIN_COOKIE_NAME, Base64Utils.urlEncoding(value));
        cookie.setMaxAge(vf.getKeepLoginMaxTime());
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 通过调用CookieUtils产生持久cookie
     * 
     * @param registerName 用户名
     * @return 创建的cookie
     */
    private Cookie createVisitorCookie(String registerName) {
        try {
            registerName = URLEncoder.encode(registerName, ENCODE);
            Cookie cookie = new Cookie(getvisitorCookieName(), registerName);
            cookie.setMaxAge(VISITORCOOKIE_MAX_AGE);
            cookie.setPath("/");
            return cookie;
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
            return null;
        }
    }

    /**
     * 通过调用CookieUtils和MD5Utils产生最后登录的会话cookie 如果是单一客户端登录的设置将不会被创建
     * 
     * @author luoyifan
     * @param principal 用户对象
     * @return 创建的cookie
     */
    private Cookie createLastLoginTime(Principal principal) {
        if (singleClientLogin()) return null;
        Cookie cookie = new Cookie(getLastLoginTimeCookieName(), principal.getLastLoginTime().toString());
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 通过调用CookieUtils查找持久cookie，
     * 
     * @return 找到返回true，否则返回false
     */
    public boolean isVisited() {
        return currentVisitor() != null;
    }

    /**
     * 参照currentPrincipal方法的逻辑，如果用户已登录返回 true ,否则返回null
     * 
     * @return
     */
    public boolean isLogined() {
        return currentPrincipal() != null;
    }

    /**
     * 通过调用CookieUtils查找会话cookie，<br>
     * 如果找到，取得cookie中id，调用验证提供者的get方法，得到用户，<br>
     * 再根据cookie摘要与生成摘要对比，如果一致再判断时间戳距离当前时间的间隔是否在合适范围，<br>
     * 如果是返回认证用户，否则返回null
     * 
     * @return true 表示是已登录 false 表示没有登录
     */
    public Principal currentPrincipal() {
        Principal principal = getRequestPrincipal();
        if (principal != null) return principal;
        principal = getByPrincipalCookie();
        if (principal != null) return principal;
        principal = getByAutoLogin();
        if (principal != null) {
            CookieUtils.writeCookie(createPrincipalCookie(principal));
            WebContext.currentRequest().setAttribute(getPrincipalcookieName(), principal);
        }
        return principal;
    }

    /**
     * 通过调用CookieUtils查找会话cookie，<br>
     * 如果找到，取得cookie中id，调用验证提供者的get方法，得到用户，<br>
     * 再根据cookie摘要与生成摘要对比，如果一致再判断时间戳距离当前时间的间隔是否在合适范围，<br>
     * 如果是返回认证用户，否则返回null
     * 
     * @return true 表示是已登录 false 表示没有登录
     */
    private Principal getByPrincipalCookie() {
        Cookie cookie = CookieUtils.getCookie(getPrincipalcookieName());
        if (cookie == null) return null;
        String cookieValue = cookie.getValue();
        String[] values = cookieValue.split(SPLIT);
        Serializable id = values[0];
        String cookieSHAValue = values[1];
        Principal principal = getAuthenticationProvider().get(id);
        if (principal == null) return null;
        Long lastLoginTime = getPrincipalLastLoginTime(principal);
        if (lastLoginTime == null) return null;
        String shaParam = getSHAParam(principal.getIdentity(), lastLoginTime);
        String shaValue = Base64Utils.urlEncoding(DigestUtils.shaHex(shaParam));
        if (!shaValue.equals(cookieSHAValue)) return null;
        Date nowDate = new Date();
        Date maxAccessDate = DateUtils.add(new Date(lastLoginTime.longValue()), TimeUnit.SECONDS,
                                           PRINCIPALCOOKIE_MAX_AGE);
        return maxAccessDate.compareTo(nowDate) > 0 ? principal : null;
    }

    private Long getPrincipalLastLoginTime(Principal principal) {
        if (singleClientLogin()) {
            return principal.getLastLoginTime();
        }
        Cookie cookie = CookieUtils.getCookie(getLastLoginTimeCookieName());
        if (cookie == null) return null;
        try {
            return Long.valueOf(cookie.getValue());
        } catch (NumberFormatException e) {
            LOG.error("last_login_time cookie format number error.", e);
        }
        return null;
    }

    /**
     * 根据保持登录状态的持久cookie得到认证用户
     * 
     * @author luoyifan
     * @return
     */
    private Principal getByAutoLogin() {
        Cookie cookie = CookieUtils.getCookie(AUTO_LOGIN_COOKIE_NAME);
        if (cookie == null) return null;
        String encodingValue = cookie.getValue();
        if (encodingValue == null) return null;
        String decodeValue = Base64Utils.decodeing(encodingValue);
        try {
            Long id = new Long(decodeValue.split(SPLIT)[0]);
            Principal pricipal = getAuthenticationProvider().get(id);
            if (pricipal == null) return null;
            if (!pricipal.getLoginName().equals(decodeValue.split(SPLIT)[1])) return null;
            return pricipal;
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }

    }

    private Principal getRequestPrincipal() {
        HttpServletRequest request = WebContext.currentRequest();
        if (request == null) return null;
        return (Principal) request.getAttribute(getPrincipalcookieName());
    }

    /**
     * 通过调用CookieUtils查找持久cookie,
     * 
     * @return 找到返回cookie中用户名，否则返回null
     */
    public String currentVisitor() {
        Object visitorObj = WebContext.currentRequest().getAttribute(getvisitorCookieName());
        if (visitorObj != null) return (String) visitorObj;
        try {
            Cookie cookie = CookieUtils.getCookie(getvisitorCookieName());
            if (cookie == null) return null;
            return URLDecoder.decode(cookie.getValue(), ENCODE);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public void logout() {
        CookieUtils.removeCookie(getPrincipalcookieName(), "/");
        CookieUtils.removeCookie(getLastLoginTimeCookieName(), "/");
        CookieUtils.removeCookie(AUTO_LOGIN_COOKIE_NAME, "/");
        // CookieUtils.removeCookie(getvisitorCookieName(), "/");
        WebContext.currentRequest().setAttribute(getPrincipalcookieName(), null);
        WebContext.currentRequest().setAttribute(getvisitorCookieName(), null);
    }

    /**
     * 通过调用验证提供者进行登录，如果登录成功，将会话cookie和持久cookie写入客户端
     * 
     * @param verifier 用户名
     * @return 返回登录后的认证主体
     * @throws AuthenticationException
     */
    public Principal login(Verifier verifier) throws AuthenticationException {
        Principal principal = getAuthenticationProvider().authenticate(verifier);
        if (principal == null) {
            throw new AuthenticationException("登录失败");
        }
        writeLoginCookie(principal, verifier);
        return principal;
    }

    private void writeLoginCookie(Principal principal, Verifier verifier) {
        CookieUtils.writeCookie(createPrincipalCookie(principal));
        CookieUtils.writeCookie(createLastLoginTime(principal));
        CookieUtils.writeCookie(createVisitorCookie(principal.getLoginName()));
        CookieUtils.writeCookie(createAutoLoginCookie(verifier, principal));
        WebContext.currentRequest().setAttribute(getPrincipalcookieName(), principal);
        WebContext.currentRequest().setAttribute(getvisitorCookieName(), principal.getLoginName());

    }

}

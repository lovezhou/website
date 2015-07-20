/**
 * @(#)PageCacheFilter.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import com.jessrun.common.cache.anno.PageCache;
import com.jessrun.common.web.WebApplicationContextUtils;
import com.jessrun.platform.util.StringUtils;

/**
 * spring mvc controller method 缓存
 * 
 * @author luoyifan
 * @version 1.0,2011-7-19
 */

public abstract class ControllerMethodFilter implements Filter {

    private ServletContext            ctx;
    private AbstractUrlHandlerMapping requestMapping;
    private String[]                  interceptSuffix;
    private Map<String, PageCache>    cacheMapping = new HashMap<String, PageCache>();

    private static final Log          LOG          = LogFactory.getLog(ControllerMethodFilter.class);

    private PathMatcher               pathMatcher  = new AntPathMatcher();

    @Override
    public void init(FilterConfig config) throws ServletException {
        ctx = config.getServletContext();
        interceptSuffix = config.getInitParameter("interceptSuffix").trim().split(",");
        if (interceptSuffix == null || interceptSuffix.length == 0) throw new RuntimeException(
                                                                                               "interceptSuffix is null");
        init(ctx);
        requestMapping = (AbstractUrlHandlerMapping) WebApplicationContextUtils.getService("requestMapping", ctx);
        createCacheMapping();
        printLog();
    }

    /**
     * 子类初始数据的模板方法
     * 
     * @author luoyifan
     * @param ctx
     * @throws ServletException
     */
    protected abstract void init(ServletContext ctx) throws ServletException;

    private void printLog() {
        for (String url : cacheMapping.keySet()) {
            LOG.info("use controller cache : " + url);
        }
    }

    /**
     * 得到spring mvc controller对象列表
     * 
     * @author luoyifan
     * @return
     */
    private Set<Object> getControllers() {
        Set<Object> controllers = new HashSet<Object>();
        for (String url : requestMapping.getHandlerMap().keySet()) {
            Object value = requestMapping.getHandlerMap().get(url);
            if (value instanceof String) continue;
            controllers.add(value);
        }
        return controllers;
    }

    /**
     * 初始化 cache mapping 映射列表
     * 
     * @author luoyifan
     */
    private void createCacheMapping() {
        Set<Object> controllers = getControllers();
        for (Object clazz : controllers) {
            Method[] methods = clazz.getClass().getDeclaredMethods();
            for (Method method : methods) {
                PageCache pageCache = method.getAnnotation(PageCache.class);
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                if (pageCache == null || methodMapping == null) continue;
                RequestMapping classMapping = clazz.getClass().getAnnotation(RequestMapping.class);
                String url = fromRequestMappingInUrlMapping(classMapping, methodMapping);
                if (url != null) cacheMapping.put(url, pageCache);
            }
        }
    }

    /**
     * 根据controller 类上的RequestMapping 和方法上的 RequestMapping匹配出和requestMapping.getHandlerMap一致的url地址
     * 
     * @author luoyifan
     * @param classMapping
     * @param methodMapping
     * @return
     */
    private String fromRequestMappingInUrlMapping(RequestMapping classMapping, RequestMapping methodMapping) {
        Set<String> mappingUrls = combineMapping(classMapping, methodMapping);
        for (String url : requestMapping.getHandlerMap().keySet()) {
            for (String value : mappingUrls) {
                if (url.equals(value)) return url;
            }
        }
        return null;
    }

    private Set<String> combineMapping(RequestMapping classMapping, RequestMapping methodMapping) {
        Set<String> urls = new HashSet<String>();
        if (classMapping != null) {
            String[] typeLevelPatterns = classMapping.value();
            String[] methodPatterns = methodMapping.value();
            if (typeLevelPatterns.length > 0 && methodPatterns.length > 0) {
                for (String tPaggern : typeLevelPatterns) {
                    if (!tPaggern.startsWith("/")) {
                        tPaggern = "/" + tPaggern;
                    }
                    for (String mPaggern : methodPatterns) {
                        urls.add(pathMatcher.combine(tPaggern, mPaggern));
                    }
                }
            }
        } else {
            String[] methodPatterns = methodMapping.value();
            for (String mPaggern : methodPatterns) {
                if (!mPaggern.startsWith("/")) {
                    mPaggern = "/" + mPaggern;
                }
                urls.add(mPaggern);
            }
        }
        return urls;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                             ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String thisUrl = httpRequest.getRequestURI();
        if (!includInterceptSuffix(thisUrl)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        PageCache pageCache = getCache(httpRequest);
        if (pageCache == null) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        doFilter(httpRequest, httpResponse, pageCache, chain);
    }

    private boolean includInterceptSuffix(String url) {
        for (String suffix : interceptSuffix)
            if (url.endsWith(suffix)) return true;
        return false;
    }

    /**
     * 子类实现具体的缓存逻辑
     * 
     * @author luoyifan
     * @param httpRequest
     * @param httpResponse
     * @param pageCache
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected abstract void doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                     PageCache pageCache, FilterChain chain) throws IOException, ServletException;

    /**
     * 根据请求地址得到 cache
     * 
     * @author luoyifan
     * @param request
     * @return
     */
    private PageCache getCache(HttpServletRequest request) {
        String urlPath = request.getRequestURI();
        PageCache pageCache = cacheMapping.get(urlPath);
        if (pageCache == null) {
            // Pattern match?
            List<String> matchingPatterns = new ArrayList<String>();
            for (String registeredPattern : cacheMapping.keySet()) {
                if (pathMatcher.match(registeredPattern, urlPath)) {
                    matchingPatterns.add(registeredPattern);
                }
            }
            String bestPatternMatch = null;
            Comparator<String> patternComparator = pathMatcher.getPatternComparator(urlPath);
            if (!matchingPatterns.isEmpty()) {
                Collections.sort(matchingPatterns, patternComparator);
                bestPatternMatch = matchingPatterns.get(0);
            }
            if (bestPatternMatch == null) return null;
            pageCache = cacheMapping.get(bestPatternMatch);
            if (pageCache == null) return null;
        }
        if (StringUtils.isNullOrEmpty(pageCache.except())) return pageCache;
        @SuppressWarnings("unchecked")
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                if (name.equals(pageCache.except())) return null;
            }
        }
        return pageCache;

    }

    @Override
    public void destroy() {

    }

}

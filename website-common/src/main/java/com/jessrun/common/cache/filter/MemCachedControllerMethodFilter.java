/**
 * @(#)PageCacheFilter.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.filter;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jessrun.common.cache.CacheManager;
import com.jessrun.common.cache.CacheUtils;
import com.jessrun.common.cache.anno.PageCache;
import com.jessrun.common.cache.pagecache.HttpServletResponseWrapper;
import com.jessrun.common.cache.pagecache.ResponsePage;
import com.jessrun.common.cache.pagecache.SerializableCookie;
import com.jessrun.common.web.WebApplicationContextUtils;
import com.jessrun.platform.util.StringUtils;
import com.jessrun.platform.util.security.Base64Utils;

/**
 * 基于 memcached 缓存实现
 * 
 * @author luoyifan
 * @version 1.0,2011-7-19
 */

public class MemCachedControllerMethodFilter extends ControllerMethodFilter {

    private CacheManager        memcachedManager;
    private static final Random RANDOM = new Random();

    @Override
    protected void init(ServletContext ctx) throws ServletException {
        memcachedManager = (CacheManager) WebApplicationContextUtils.getService("memcachedManager", ctx);
    }

    @Override
    protected void doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, PageCache pageCache,
                            FilterChain chain) throws IOException, ServletException {
        ResponsePage responsePage = fromResponsePage(pageCache, httpRequest, httpResponse, chain);
        if (responsePage == null) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        writeResponse(httpRequest, httpResponse, responsePage);
    }

    /**
     * @author luoyifan
     * @param cache
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private ResponsePage fromResponsePage(PageCache pageCache, HttpServletRequest httpRequest,
                                          HttpServletResponse httpResponse, FilterChain filterChain)
                                                                                                    throws IOException,
                                                                                                    ServletException {
        String key = calculateKey(httpRequest, pageCache);
        ResponsePage responsePage = (ResponsePage) (memcachedManager.get(key));
        if (responsePage != null) return responsePage;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpResponse, out);
        filterChain.doFilter(httpRequest, responseWrapper);
        responseWrapper.flush();
        responsePage = new ResponsePage(responseWrapper.getStatus(), responseWrapper.getContentType(),
                                        responseWrapper.getHeaders(), responseWrapper.getCookies(), out.toByteArray());
        if (responsePage.getStatusCode() == 200) {
            memcachedManager.put(key, responsePage,
                                 CacheUtils.calculateExpireDate(pageCache.idleTime(), pageCache.timeToIdleSeconds()));
            return responsePage;
        }
        return null;

    }

    /**
     * 计算cache 的key
     * 
     * @author luoyifan
     * @param request
     * @param cache
     * @return
     */
    private String calculateKey(HttpServletRequest request, PageCache pageCache) {
        StringBuffer stringBuffer = new StringBuffer(request.getRequestURI());
        String queryString = request.getQueryString();
        if (!pageCache.ignoreParams() && !StringUtils.isNullOrEmpty(queryString)) stringBuffer.append("?").append(queryString.trim());
        if (pageCache.random() != 0) stringBuffer.append(";random=").append(RANDOM.nextInt(pageCache.random()));
        String key = stringBuffer.toString();
        return Base64Utils.urlEncoding(key);
    }

    private void writeResponse(HttpServletRequest request, HttpServletResponse response, ResponsePage responsePage)
                                                                                                                   throws IOException {
        response.setStatus(responsePage.getStatusCode());
        response.setContentType(responsePage.getContentType());
        Collection<String[]> headers = responsePage.getHeaders();
        if (headers != null) {
            Iterator<String[]> it = headers.iterator();
            while (it.hasNext()) {
                String[] header = it.next();
                response.setHeader(header[0], header[1]);
            }
        }
        Collection<SerializableCookie> cookies = responsePage.getCookies();
        if (cookies != null) {
            Iterator<SerializableCookie> it = cookies.iterator();
            while (it.hasNext()) {
                SerializableCookie cookie = it.next();
                response.addCookie(cookie.toCookie());
            }
        }
        byte body[] = responsePage.getBody();
        response.setContentLength(body.length);
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(body);
        out.flush();
    }

}

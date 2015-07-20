/**
 * @(#)MethodCacheInterceptor.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.methodcache;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import com.jessrun.common.cache.CacheManager;
import com.jessrun.common.cache.CacheUtils;
import com.jessrun.common.cache.anno.MethodCache;
import com.jessrun.platform.util.security.Base64Utils;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-10
 */
public class MethodCacheInterceptor implements MethodInterceptor {

    @Resource(name = "memcachedManager")
    private CacheManager memcachedManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String realClassName = StringUtils.substringBefore(invocation.getThis().toString(), "@");
        Class<?> clazz = Class.forName(realClassName);
        Method invocationMethod = invocation.getMethod();
        Method method = clazz.getMethod(invocationMethod.getName(), invocationMethod.getParameterTypes());
        if (method.getReturnType().getName().equals("void")) return invocation.proceed();
        MethodCache methodCache = method.getAnnotation(MethodCache.class);
        if (methodCache == null) return invocation.proceed();
        String targetName = clazz.getName();
        String methodName = method.getName();
        String cacheKey = getCacheKey(targetName, methodName, invocation.getArguments(), methodCache.ignoreParams());

        Serializable cacheResult = memcachedManager.get(cacheKey);
        if (cacheResult == null) {
            cacheResult = (Serializable) invocation.proceed();
            memcachedManager.put(cacheKey,
                                 cacheResult,
                                 CacheUtils.calculateExpireDate(methodCache.idleTime(), methodCache.timeToIdleSeconds()));
        }
        return cacheResult;
    }

    /**
     * key生成机制：className.methodName.arguments[0].arguments[1]. ... 再进行转码 若argument为领域对象，请重写其hashCode方法
     * 
     * @param targetName
     * @param methodName
     * @param arguments
     * @param ignoreParams
     * @return
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments, boolean ignoreParams) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if (!ignoreParams) {
            if ((arguments != null) && (arguments.length != 0)) {
                for (int i = 0; i < arguments.length; i++) {
                    sb.append(".").append(arguments[i]);
                }
            }
        }

        return Base64Utils.urlEncoding(sb.toString());
    }

}

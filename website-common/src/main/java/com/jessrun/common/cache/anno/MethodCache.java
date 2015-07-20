/**
 * @(#)MethodCache.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法缓存注解
 * 
 * @author luoyifan
 * @version 1.0,2012-1-10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodCache {

    /**
     * 是否忽略清楚参数<br/>
     * 默认false表示不忽略清楚参数
     * 
     * @author luoyifan
     * @return
     */
    boolean ignoreParams() default false;

    /**
     * 几秒后失效<br/>
     * 默认1个小时
     * 
     * @author luoyifan
     * @return
     */
    int timeToIdleSeconds() default 60 * 60;

    /**
     * 失效时间(格式为hh:mm)<br/>
     * 例:08:00 表示每天8点钟失效<br/>
     * 如果idleTime和timeToIdleSeconds同时存在以idleTime为准
     * 
     * @author luoyifan
     * @return
     */
    String idleTime() default "";
}

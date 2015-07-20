/**
 * @(#)Cache.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面缓存 注解
 * 
 * @author luoyifan
 * @version 1.0,2011-7-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageCache {

    /**
     * 忽略缓存的Request请求参数 默认为src 如果设置为null表示不忽略缓存
     * 
     * @author luoyifan
     * @return
     */
    String except() default "src";

    /**
     * 是否忽略请求参数
     * 
     * @author luoyifan
     * @return
     */
    boolean ignoreParams() default false;

    /**
     * 随机数<br/>
     * 如果随机数大于0则产生多个缓存key
     * 
     * @author luoyifan
     * @return
     */
    int random() default 0;

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

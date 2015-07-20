/**
 * @(#)Customer.java
 *
 * Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.vaildator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *验证注解
 * @author  luoyifan
 * @version 1.0,2011-4-27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidator {
	
	/**
	 * 请求参数
	 * @author luoyifan
	 * @return
	 */
	String param();
	/**
	 * 请求参数对应的注释名字
	 * @author luoyifan
	 * @return
	 */
	String paramName();
	/**
	 * 验证类型
	 * @author luoyifan
	 * @return
	 */
	RegexValidatorType[] vaildatorTypes()default{};
	 
	/**
	 * 是否是必填，默认为false
	 * @author luoyifan
	 * @return
	 */
	boolean required() default false;
	
	/**
	 * 验证长度
	 * 如果只有一个整数则表示参数的长度和该整数要一样长
	 * @author luoyifan
	 * @return
	 */
	int[] length()default {};
	/**
	 * 必须是一个数字，其值必须大于等于指定的最小值 
	 * @return
	 */
	long min() default Long.MIN_VALUE;
	/**
	 * 必须是一个数字，其值必须小于等于指定的最大值
	 * @return
	 */
	long max() default Long.MAX_VALUE;
	/**
	 * 
	 *  必须是一个数字(任意精度的有符号十进制数)，其值必须大于等于指定的最小值 
	 * @return
	 */
	String DecimalMin() default "";
	/**
	 * 
	 *  必须是一个数字(任意精度的有符号十进制数)，其值必须小于等于指定的最大值
	 * @return
	 */
	String DecimalMax() default "";
	/**
	 * 自定义的正则验证表达式
	 * @return
	 * @author  xu.jianguo
	 */
	String customRegex() default "";
	/**
	 * 自定义的正则验证表达式校验错误返回的消息内容哦
	 * @return
	 * @author  xu.jianguo
	 */
	String customErrorMsg() default "校验失败";
}
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
 * 验证注解
 * @author  luoyifan
 * @version 1.0,2011-4-27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidators {
	ResultType resultType() default ResultType.MODE;
	ParamValidator[] value();
}






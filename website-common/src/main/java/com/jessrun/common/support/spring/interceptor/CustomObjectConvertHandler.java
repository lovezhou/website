/**
 * @(#)CustomObjectConvertHandler.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author luoyifan
 * @date 2012-4-27 description
 */
public interface CustomObjectConvertHandler {

    public Class<?> getObjectType();

    public Object execute(MethodParameter methodParameter, NativeWebRequest webRequest);

}

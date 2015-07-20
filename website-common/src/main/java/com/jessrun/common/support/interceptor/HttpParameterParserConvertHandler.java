/**
 * @(#)HttpParamterConvertHandler.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import com.jessrun.common.support.spring.interceptor.CustomObjectConvertHandler;
import com.jessrun.common.web.HttpParameterParser;

/**
 * @author tiger
 * @date 2012-11-16 description
 */
@Component
public class HttpParameterParserConvertHandler implements CustomObjectConvertHandler {

    @Override
    public Object execute(MethodParameter methodParameter, NativeWebRequest webRequest) {
        return HttpParameterParser.newInstance((HttpServletRequest) webRequest.getNativeRequest());
    }

    @Override
    public Class<?> getObjectType() {
        return HttpParameterParser.class;
    }
}

/**
 * @(#)JSONValidatorException.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.vaildator.exception;

/**
 * @author xu.jianguo
 * @date 2012-11-28 description
 */
public class XmlValidatorException extends ValidatorException {

    private static final long serialVersionUID = 4852863690115250348L;

    public XmlValidatorException(){
        super();
    }

    public XmlValidatorException(String name, String errorMessage){
        super(name, errorMessage);
    }

    public XmlValidatorException(String message, Throwable cause){
        super(message, cause);
    }

    public XmlValidatorException(String errorMessage){
        super(errorMessage);
    }

    public XmlValidatorException(Throwable cause){
        super(cause);
    }

}

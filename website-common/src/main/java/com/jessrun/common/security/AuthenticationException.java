/**
 * @(#)AuthenticationException.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.security;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2011-2-17
 */
public class AuthenticationException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public AuthenticationException(){
        super();
    }

    public AuthenticationException(String message, Throwable cause){
        super(message, cause);
    }

    public AuthenticationException(String message){
        super(message);
    }

    public AuthenticationException(Throwable cause){
        super(cause);
    }

}

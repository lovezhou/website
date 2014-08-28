/**
 * @(#)ResponsePage.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.pagecache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.Cookie;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public class ResponsePage implements Serializable {

    /**
	 * 
	 */
    private static final long              serialVersionUID = 1L;

    private int                            statusCode;
    private String                         contentType;
    private Collection<String[]>           headers;
    private Collection<SerializableCookie> cookies;
    private byte[]                         body;

    public ResponsePage(int statusCode, String contentType, Collection<String[]> headers, Collection<Cookie> cookies,
                        byte[] body){
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.headers = headers;
        if (cookies != null && cookies.size() > 0) {
            this.cookies = new ArrayList<SerializableCookie>();
            Iterator<Cookie> it = cookies.iterator();
            while (it.hasNext()) {
                Cookie cookie = it.next();
                this.cookies.add(new SerializableCookie(cookie));
            }
        }
        this.body = body.clone();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Collection<String[]> getHeaders() {
        return headers;
    }

    public void setHeaders(Collection<String[]> headers) {
        this.headers = headers;
    }

    public Collection<SerializableCookie> getCookies() {
        return cookies;
    }

    public void setCookies(Collection<SerializableCookie> cookies) {
        this.cookies = cookies;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body.clone();
    }
}

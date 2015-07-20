/**
 * @(#)SerializableCookie.java
 *
 * Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.pagecache;

import java.io.Serializable;

import javax.servlet.http.Cookie;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public class SerializableCookie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SerializableCookie(Cookie cookie) {
		name = cookie.getName();
		value = cookie.getValue();
		comment = cookie.getComment();
		domain = cookie.getDomain();
		maxAge = cookie.getMaxAge();
		path = cookie.getPath();
		secure = cookie.getSecure();
		version = cookie.getVersion();
	}

	public Cookie toCookie() {
		Cookie cookie = new Cookie(name, value);
		cookie.setComment(comment);
		if (domain != null)
			cookie.setDomain(domain);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		cookie.setSecure(secure);
		cookie.setVersion(version);
		return cookie;
	}

	private String name;
	private String value;
	private String comment;
	private String domain;
	private int maxAge;
	private String path;
	private boolean secure;
	private int version;
}
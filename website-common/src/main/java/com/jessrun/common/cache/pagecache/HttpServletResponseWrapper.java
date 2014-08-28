/**
 * @(#)HttpServletResponseWrapper.java
 *
 * Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.pagecache;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public class HttpServletResponseWrapper extends
		javax.servlet.http.HttpServletResponseWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;
	private int contentLength;
	private String contentType;
	private final List<String[]> headers = new ArrayList<String[]>();
	private final List<Cookie> cookies = new ArrayList<Cookie>();
	private ServletOutputStream servletOutputStream;
	private PrintWriter writer;

	public HttpServletResponseWrapper(HttpServletResponse response,
			OutputStream outputStream) {
		super(response);
		statusCode = 200;
		this.servletOutputStream = new ServletOutputStreamWrapper(outputStream);
	}

	public ServletOutputStream getOutputStream() {
		return servletOutputStream;
	}

	public void setStatus(int code) {
		statusCode = code;
		super.setStatus(code);
	}

	public void sendError(int i, String string) throws IOException {
		statusCode = i;
		super.sendError(i, string);
	}

	public void sendError(int i) throws IOException {
		statusCode = i;
		super.sendError(i);
	}

	public void sendRedirect(String string) throws IOException {
		statusCode = 302;
		super.sendRedirect(string);
	}

	public void setStatus(int code, String msg) {
		statusCode = code;
		super.setStatus(code);
	}

	public int getStatus() {
		return statusCode;
	}

	public void setContentLength(int length) {
		contentLength = length;
		super.setContentLength(length);
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentType(String type) {
		contentType = type;
		super.setContentType(type);
	}

	public String getContentType() {
		return contentType;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer == null)
			writer = new PrintWriter(new OutputStreamWriter(
					servletOutputStream, getCharacterEncoding()), true);
		return writer;
	}

	public void addHeader(String name, String value) {
		String header[] = { name, value };
		headers.add(header);
		super.addHeader(name, value);
	}

	public void setHeader(String name, String value) {
		addHeader(name, value);
	}

	public Collection<String[]> getHeaders() {
		return headers;
	}

	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
		super.addCookie(cookie);
	}

	public Collection<Cookie> getCookies() {
		return cookies;
	}

	public void flushBuffer() throws IOException {
		flush();
		super.flushBuffer();
	}

	public void reset() {
		super.reset();
		cookies.clear();
		headers.clear();
		statusCode = 200;
		contentType = null;
		contentLength = 0;
	}

	public void resetBuffer() {
		super.resetBuffer();
	}

	public void flush() throws IOException {
		if (writer != null)
			writer.flush();
		servletOutputStream.flush();
	}

	public String encodeRedirectUrl(String s) {
		return super.encodeRedirectURL(s);
	}

	public String encodeUrl(String s) {
		return super.encodeURL(s);
	}

}
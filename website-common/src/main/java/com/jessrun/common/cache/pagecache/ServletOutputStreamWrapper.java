/**
 * @(#)ServletOutputStreamWrapper.java
 *
 * Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache.pagecache;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public class ServletOutputStreamWrapper extends ServletOutputStream {

	public ServletOutputStreamWrapper(OutputStream stream) {
		this.stream = stream;
	}

	public void write(int b) throws IOException {
		stream.write(b);
	}

	public void write(byte b[]) throws IOException {
		stream.write(b);
	}

	public void write(byte b[], int off, int len) throws IOException {
		stream.write(b, off, len);
	}

	private OutputStream stream;
}

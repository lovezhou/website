/**
 * @(#)Codec.java Copyright 2010 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2010-11-10
 */
public class Base64Utils {

    public static String encoding(String value) {
        try {
            byte[] bs = Base64.encodeBase64(value.getBytes("UTF-8"));
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error.", e);
        }

    }

    public static String decodeing(String encodingValue) {
        try {
            byte[] bs = Base64.decodeBase64(encodingValue);
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("decode error.", e);
        }
    }

    public static String encodeingSafe(String value) {
        try {
            byte[] bs = Base64.encodeBase64(value.getBytes("UTF-8"), true);
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error.", e);
        }
    }

    public static String urlEncoding(String value) {
        try {
            byte[] bs = Base64.encodeBase64URLSafe(value.getBytes("UTF-8"));
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error.", e);
        }
    }

    public static String encodingString(byte[] byteArray) {
        return Base64.encodeBase64String(byteArray);
    }

    public static byte[] decodingString(String base64String) {
        return Base64.decodeBase64(base64String);
    }
}

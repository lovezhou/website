/**
 * @(#)PrivateKeyUtils.java
 * Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 私钥工具类
 * @author  luoyifan
 * @version 1.0,2012-2-21
 */
public class PrivateKeyUtils {
	/**
	 * IO读写缓冲区大小
	 */
	private static final int BUFFER_SIZE=1024;
	/**
	 * 将私钥对象写入文件中
	 * @author luoyifan
	 * @param privateKey	私钥对象
	 * @param fileName		要保存私钥的文件
	 */
	public static void write(PrivateKey privateKey,String fileName){
		BufferedOutputStream bof = null;
		try {
			bof = new BufferedOutputStream(new FileOutputStream(fileName) );
			bof.write(privateKey.getEncoded());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally{
			if(bof!=null) {
				try {
					bof.flush();
					bof.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				
			}
		}
	}
	/**
	 * 读取二进制私钥文件并转换成私钥对象
	 * @author luoyifan
	 * @param fileName
	 * @return
	 */
	public static PrivateKey read(String fileName) {
		BufferedInputStream ins = null;
		try {
			ins = new BufferedInputStream(new FileInputStream(fileName) );
			ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
			byte[] temp = new byte[BUFFER_SIZE];
			int size = 0;
			 while ((size = ins.read(temp)) != -1) {  
			       out.write(temp, 0, size);  
	         } 
			 byte[] bytes = out.toByteArray();
			
			 KeySpec spec = new PKCS8EncodedKeySpec(bytes);
			return KeyFactory.getInstance("RSA").generatePrivate(spec);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		finally{
			if(ins !=null) {
				try {
					ins.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	/**
	 * 把经过base64编码的私钥字符串转换成私钥对象
	 * @author luoyifan
	 * @param base64String
	 * @return
	 */
	public static PrivateKey readByBase64String(String base64String) {
		byte[] bytes = Base64.decodeBase64(base64String);
		KeySpec spec = new PKCS8EncodedKeySpec(bytes);
		try {
			return KeyFactory.getInstance("RSA").generatePrivate(spec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 对文件加密
	 * @author luoyifan
	 * @param privateKey	密钥
	 * @param srcFile		加密源文件
	 * @param destFile		加密后保存的目标文件
	 */
	public static void encryptFile(PrivateKey privateKey,String srcFile,String destFile) {
		InputStream ins = null;
		FileOutputStream ous = null;
		try {
			Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			ins = new FileInputStream(srcFile);
			int len = ins.available();
			byte[] bs = new byte[len];
			ins.read(bs);
			byte[] outBytes = cipher.doFinal(bs);
			ous = new FileOutputStream(destFile);
			ous.write(outBytes);
			ous.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			if(ins !=null) {
				try {
					ins.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			if(ous !=null) {
				try {
					ous.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	 
}


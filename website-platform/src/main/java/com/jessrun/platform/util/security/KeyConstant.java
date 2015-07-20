/**
 * @(#)KeyConstant.java
 * Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * @author  luoyifan
 * @date  2012-11-11
 * description
 */
public final class KeyConstant {
	/**
	 * 根密钥和证书的存储设施
	 */
	public static final KeyStore ROOT_KEYSTORE=KeyStoreUtils.loadKeyStore(SecurityPropertiesUtils.ROOT_KEYSTORE_FILE,SecurityPropertiesUtils.ROOT_KEYSTORE_TYPE,SecurityPropertiesUtils.ROOT_KEYSTORE_PASSWORD,true);
	/**
	 * 根证书
	 */
	public static final Certificate ROOT_CERTIFICATE=KeyStoreUtils.getCertificate(ROOT_KEYSTORE,SecurityPropertiesUtils.ROOT_KEYSTORE_ALIAS);
	/**
	 * 根密钥
	 */
	public static final PrivateKey ROOT_PRIVATE_KEY=KeyStoreUtils.getPrivateKey(ROOT_KEYSTORE,SecurityPropertiesUtils.ROOT_KEYSTORE_ALIAS,SecurityPropertiesUtils.ROOT_KEYSTORE_PASSWORD);
	/**
	 * 根公钥
	 */
	public static final PublicKey ROOT_PUBLIC_KEY=KeyStoreUtils.getPublicKey(ROOT_KEYSTORE,SecurityPropertiesUtils.ROOT_KEYSTORE_ALIAS);
	
}

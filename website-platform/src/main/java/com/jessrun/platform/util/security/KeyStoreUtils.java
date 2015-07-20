/**
 * @(#)KeyStoreUtils.java Copyright 2010 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import com.jessrun.platform.util.ConfigUtils;

/**
 * 密钥工具类
 * 
 * @author luoyifan
 * @version 1.0,2010-12-6
 */
public class KeyStoreUtils {

    /**
     * 生产keystore
     * 
     * @author luoyifan
     * @param alias 证书别名
     * @param cn 名字与姓氏
     * @param storepass 密码
     * @param fileName keystore文件名(完整路径)
     */
    public static void createKeyStore(String alias, String cn, String storepass, String fileName) {
        KeyStoreParam keyStoreParam = KeyStoreParam.getInsTanceByGenkey(alias, cn, storepass, fileName);
        executeCmd(keyStoreParam.toString());
    }

    /**
     * 根据keystore和别名得到证书
     * 
     * @author luoyifan
     * @param keyStore
     * @param alias
     * @return
     */
    public static Certificate getCertificate(KeyStore keyStore, String alias) {
        try {
            return keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            throw new RuntimeException("get certificate error.", e);
        }
    }

    /**
     * 根据keyStore获得公钥
     * 
     * @author luoyifan
     * @param keyStore
     * @param alias
     * @return
     */
    public static PublicKey getPublicKey(KeyStore keyStore, String alias) {
        return getCertificate(keyStore, alias).getPublicKey();
    }

    /**
     * 根据keyStore获得私钥
     * 
     * @author luoyifan
     * @param keyStore
     * @param alias
     * @param password
     * @return
     */
    public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String password) {
        try {
            return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("get privatekey error.", e);
        }
    }

    /**
     * 根据keyStore导出证书
     * 
     * @author luoyifan
     * @param alias
     * @param storepass
     * @param keyStoreName
     * @param cerFileName
     */
    public static void exportCert(String certAlias, String certPass, String keyStoreName, String certFileName) {
        KeyStoreParam keyStoreParam = KeyStoreParam.getInsTanceByExportcert(certAlias, certPass, keyStoreName,
                                                                            certFileName);
        executeCmd(keyStoreParam.toString());
    }

    private static int executeCmd(String cmdString) {
        CmdProcess cmdProcess = new CmdProcess(SecurityPropertiesUtils.KEY_TOOL_CMD + " " + cmdString, 10000);
        return cmdProcess.execute();
    }

    /**
     * 使用默认keystore类型加载keystore
     * 
     * @author luoyifan
     * @param keyStoreFile keystore文件
     * @param password 密码
     * @return
     */
    public static KeyStore loadKeyStore(String keyStoreFile, String password, boolean inClassPath) {
        return loadKeyStore(keyStoreFile, KeyStore.getDefaultType(), password, inClassPath);
    }

    /**
     * 指定keystore类型加载keystore<br/>
     * JKS的Provider是SUN，在每个版本的JDK中都有<br/>
     * JCEKS的Provider是SUNJCE，1.4后我们都能够直接使用它。<br/>
     * JCEKS在安全级别上要比JKS强，使用的Provider是JCEKS(推荐)，尤其在保护KeyStore中的私钥上（使用TripleDes）。<br/>
     * PKCS#12是公钥加密标准，它规定了可包含所有私钥、公钥和证书。 其以二进制格式存储，也称为 PFX 文件，在windows中可以直接导入到密钥区，注意，PKCS#12的密钥库保护密码同时也用于保护Key。<br/>
     * 
     * @author luoyifan
     * @param keyStoreFile keystore文件
     * @param type keystore类型
     * @param password 密码
     * @return
     */
    public static KeyStore loadKeyStore(String keyStoreFile, String type, String password, boolean inClassPath) {
        InputStream ins = null;
        try {
            KeyStore ks = KeyStore.getInstance(type);
            ins = inClassPath ? ConfigUtils.class.getResourceAsStream(keyStoreFile) : new FileInputStream(keyStoreFile);
            ks.load(ins, password.toCharArray());
            return ks;
        } catch (Exception e) {
            throw new RuntimeException("load keystore error.", e);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    throw new RuntimeException("close keystore file error.", e);
                }
            }
        }
    }
    /*
     * public static void main(String[] args) throws KeyStoreException { KeyStore keyStore =
     * KeyStoreUtils.loadKeyStore("D:/home/www/security/test001.pfx", "test001");
     * System.out.println(keyStore.getType()); Enumeration<String> aliases = keyStore.aliases();
     * for(;aliases.hasMoreElements();) { System.out.println(aliases.nextElement()); } Certificate certificate =
     * ROOT_CERTIFICATE; System.out.println(certificate.getType()); PublicKey publicKey = certificate.getPublicKey();
     * System.out.println(publicKey.getAlgorithm()); System.out.println( publicKey.getFormat());
     * System.out.println("============"); PrivateKey privateKey = ROOT_PRIVATE_KEY;
     * System.out.println(privateKey.getAlgorithm()); System.out.println( privateKey.getFormat());
     * //createKeyStore(SecurityPropertiesUtils.ROOT_KEYSTORE_ALIAS, SecurityPropertiesUtils.ROOT_KEYSTORE_CN,
     * SecurityPropertiesUtils.ROOT_KEYSTORE_PASSWORD, SecurityPropertiesUtils.ROOT_KEYSTORE_FILE);
     * exportCert(SecurityPropertiesUtils.ROOT_KEYSTORE_ALIAS, SecurityPropertiesUtils.ROOT_KEYSTORE_PASSWORD,
     * SecurityPropertiesUtils.ROOT_KEYSTORE_FILE,SecurityPropertiesUtils.CERT_TEMP_DIRECTORY+"TEST.cer"); }
     */
}

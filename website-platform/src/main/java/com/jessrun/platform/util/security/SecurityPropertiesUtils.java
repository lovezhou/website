/**
 * @(#)SecurityPropertiesUtils.java Copyright 2010 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2010-12-6
 */
public final class SecurityPropertiesUtils {

    private static String              propertiesFile = "security.properties";
    private static final Logger        log            = LoggerFactory.getLogger(SecurityPropertiesUtils.class);
    private static Map<String, String> propertiesMap;
    static {
        initProperty();
    }

    private static void initProperty() {
        InputStream ins = null;
        Properties properties = new Properties();
        try {
            ins = SecurityPropertiesUtils.class.getResourceAsStream(propertiesFile);
            properties.load(ins);
            propertiesMap = new HashMap<String, String>();
            Set<Entry<Object, Object>> entrySet = properties.entrySet();

            for (Entry<Object, Object> entry : entrySet) {
                propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (FileNotFoundException e) {
            log.error("file not found." + propertiesFile, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("com.jzt.platform.util.security.SecurityPropertiesUtils.initProperty()", e);
            throw new RuntimeException(e);
        }
    }

    private static final String getString(String key) {
        return propertiesMap.get(key);
    }

    private static final String getPathByClassRoot(String pathKey, boolean create) {
        String key = propertiesMap.get(pathKey);
        key = key.endsWith("/") ? key : key + "/";
        URL url = SecurityPropertiesUtils.class.getResource("/");
        String path = (url.getPath() + key).replace("//", "/");
        if (!create) {
            return path;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    private static final int getInt(String key) {
        return Integer.parseInt(propertiesMap.get(key));
    }

    /**
     * keytool命令
     */
    public static final String KEY_TOOL_CMD                    = getString("keytoolCommand");
    /**
     * 服务器端密钥库别名
     */
    public static final String ROOT_KEYSTORE_ALIAS             = getString("rootKye_Alias");
    /**
     * 服务器端密钥库-密码
     */
    public static final String ROOT_KEYSTORE_CN                = getString("rootKye_CN");

    /**
     * 服务器端密钥库文件名 rootKeyStoreFileName
     */
    public static final String ROOT_KEYSTORE_FILE              = getString("rootKeyStoreFileName");
    /**
     * 服务器端密钥库密码 rootKeyStorePassword
     */
    public static final String ROOT_KEYSTORE_PASSWORD          = getString("rootKeyStorePassword");

    /**
     * keystore 类型 rootKeyStoreType
     */
    public static final String ROOT_KEYSTORE_TYPE              = getString("rootKeyStoreType");

    /**
     * 数字签名算法 digital_sign_algorithm
     */
    public static final String DIGITAL_SIGN_ALGORITHM          = getString("digital_sign_algorithm");
    /**
     * 和安全相关的字符串编码格式 encode
     */
    public static final String SECURITY_ENCODE                 = getString("encode");
    /**
     * 得到证书申请最大有效期
     * 
     * @author luoyifan
     * @return
     */
    public static final int    CERT_APPLY_MAX_VALIDITY         = getInt("certificate_Apply_max_validity");

    /**
     * keystore_temp_directory 得到keystore保存的临时目录
     * 
     * @author luoyifan
     * @return
     */
    public static final String CERT_TEMP_DIRECTORY             = getPathByClassRoot("certificate_temp_directory", true);

    /**
     * 得到证书最大有效期
     * 
     * @author luoyifan
     * @return
     */
    public static final int    CERT_MAX_VALIDITY               = getInt("certificate_max_validity");
    /**
     * 得到非对称加密算法
     * 
     * @author luoyifan
     * @return
     */
    public static final String ASYMMETRIC_ENCRYPTION_ALGORITHM = getString("asymmetric_encryption_algorithm");
    /**
     * 得到对称加密算法
     * 
     * @author luoyifan
     * @return
     */
    public static final String SYMMETRIC_ENCRYPTION_ALGORITHM  = getString("symmetric_encryption_algorithm");

    /**
     * 组织单位名称
     * 
     * @author luoyifan
     * @return
     */
    public static final String ORGANIZATIONALUNIT              = getString("OU");

    /**
     * 组织名称
     * 
     * @author luoyifan
     * @return
     */
    public static final String ORGANIZATION                    = getString("O");

    /**
     * 城市或区域
     * 
     * @author luoyifan
     * @return
     */
    public static final String LOCATION                        = getString("L");

    /**
     * 州或省份
     * 
     * @author luoyifan
     * @return
     */
    public static final String STATE                           = getString("ST");
    /**
     * 两个字母的国家代码
     * 
     * @author luoyifan
     * @return
     */
    public static final String COUNTRY                         = getString("C");
}

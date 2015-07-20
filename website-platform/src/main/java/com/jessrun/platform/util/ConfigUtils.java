package com.jessrun.platform.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 取系统配置属性             
 * 
 * @author luoyifan 2012-12-14 下午11:28:13
 */
public final class ConfigUtils {

    private static String              DEFAULT_CONFIG_FILE = "/config.properties";

    private static final Logger        log                 = LoggerFactory.getLogger(ConfigUtils.class);

    private static Map<String, String> propertiesMap;

    static {
        try {
            initProperty();
        } catch (IOException e) {
            log.error("error at init properties", e);
        }
    }

    /**
     * 初始化配置文件(config.properties)
     * 
     * @throws IOException
     */
    private static void initProperty() throws IOException {
        if (propertiesMap != null) {
            return;
        }
        loadDefaultProperty();
    }

    private static void loadDefaultProperty() throws IOException {
        InputStream ins = null;
        Properties properties = new Properties();
        try {
            ins = ConfigUtils.class.getResourceAsStream(DEFAULT_CONFIG_FILE);
            if (ins == null) {
                return;
            }
            properties.load(ins);
        } finally {
            IOUtils.closeQuietly(ins);
        }
        propertiesMap = new HashMap<String, String>();
        Set<Entry<Object, Object>> entrySet = properties.entrySet();
        for (Entry<Object, Object> entry : entrySet) {
            propertiesMap.put((String) entry.getKey(), ((String) entry.getValue()).trim());
        }
    }

    private static final String getWebRoot() {

        URL url = ConfigUtils.class.getResource("/");
        String path = url.getPath();
        if (path.endsWith("/WEB-INF/classes/")) {
            int beginIndex = path.length() - "WEB-INF/classes/".length();
            return path.substring(0, beginIndex);
        }
        return path;
    }

    public static String getString(String proKey) {
        return propertiesMap.get(proKey);
    }

    public static String getString(String proKey, String defaultValue) {
        String value = propertiesMap.get(proKey);
        return StringUtils.isNullOrEmpty(value) ? defaultValue : value;
    }

    public static final int getInt(String key) {
        return Integer.parseInt(propertiesMap.get(key));
    }

    public static final long getLong(String key) {
        return Long.parseLong(propertiesMap.get(key));
    }

    private static final boolean getBoolean(String key, boolean defaultValue) {
        String str = getString(key, new Boolean(defaultValue).toString());
        if (str.equalsIgnoreCase("true") || str.equals("1") || str.equals("是") || str.equalsIgnoreCase("yes")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * 得到网站地址
     */
    public static final String       WEBSITE                    = getString("web.site", "");

    /**
     * 得到网站域名
     */
    public static final String       DOMAIN                     = getString("web.domain");

    /**
     * 网站的绝对路径
     */
    public static final String       WEB_ROOT                   = getWebRoot();

    /**
     * 网站上传图片的路径
     */
    public static final String       UPLOAD_IMAGE_PATH          = getString("upload.image.path", "/upload_file/image");

    /**
     * 定时任务启动运行开关
     */
    public static final boolean      QUARTZ_RUN_OPEN            = getBoolean("quartz.run.open", true);
    /**
     * 项目的基础包目录
     */
    public static final String       BASE_PACKAGE               = getString("project.base.package", "com");
    /**
     * JSON VIEW 是否是开发模式
     */
    public static final boolean      JSON_VIEW_DEV_MODE         = getBoolean("json.view.dev.mode", false);

    /**
     * xls模板的路径
     */
    public static final String       XLS_TEMPLATE_PATH          = getString("xls.template.path", "/xls_template/");

    /**
     * 文件上传的服务器路径
     */
    public static final String       UPLOAD_IMG_PATH            = getString("upload.img.path", "/");

    /**
     * 文件上传后访问的相对路径
     */
    public static final String       UPLOAD_IMG_URL_RELATIVE    = getString("upload.img.url_relative", "/");

    /**
     * 文件上传的扩展名
     */
    public static final List<String>  UPLOAD_IMG_FILE_EXTENSTION  = Arrays.asList(getString("upload.img.file_extension",
                                                                                          "").split(" "));

    /**
     * 上传文件的最大限制
     */
    public static final int          UPLOAD_IMG_MAXSIZE         = getInt("upload.img.maxsize");
}
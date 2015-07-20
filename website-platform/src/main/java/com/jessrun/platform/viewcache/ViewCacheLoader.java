package com.jessrun.platform.viewcache;

import java.io.File;
import java.util.Date;

public interface ViewCacheLoader {

    /**
     * 存放的文件后缀，XML文件
     */
    public static final String FILE_SUFFIX_XML = ".xml";

    /**
     * 获取缓存文件
     * 
     * @return 缓存文件
     */
    public File getCacheFile(Class<? extends ViewCache> viewCacheClass);

    /**
     * 具体的缓存加载策略(可能从文件，类路径，或者数据库)
     * 
     * @return 具体的视图缓存
     */
    public void loadViewCache(ViewCache viewCache);

    /**
     * 获取缓存最后修改时间(可能是缓存文件的最后修改时间，可能是数据库的最后更新时间)
     * 
     * @param filePath
     * @return
     */
    public Date getLastModifiedDate();

    /**
     * 缓存来源类型
     */
    public interface CacheSourceType {

        /** 文件 */
        int FILE      = 1;
        /** 数据库 */
        int DB        = 2;
        /** 从类路径加载 */
        int CLASSPATH = 3;
    }

    /**
     * 返回该load是从哪个地方加载数据的
     * 
     * @return {@link CacheSourceType}
     */
    public int getCacheSourceType();

}

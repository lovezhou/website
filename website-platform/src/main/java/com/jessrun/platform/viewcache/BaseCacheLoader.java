package com.jessrun.platform.viewcache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.jessrun.platform.util.FileUtil;
import com.jessrun.platform.util.StringUtils;

public abstract class BaseCacheLoader implements ViewCacheLoader {

    // 默认的存放缓存的位置
    protected static final String DEFAULT_CACHE_PATH = "/com/jzt/b2b/viewcache/file/";

    // 缓存文件存放的绝对路径
    protected String              cachePath;

    protected File                file;

    // 缓存加载方式，从文件系统，类路径，或者DB。 默认从类路径加载
    protected int                 cacheSourceType    = CacheSourceType.CLASSPATH;

    protected ViewCacheLoader     viewCacheLoader;

    /**
     * 获取filePath文件的最后一次修改时间
     * 
     * @param filePath 文件路径
     * @return 返回文件的最后一次修改时间
     */
    @Override
    public Date getLastModifiedDate() {
        if (cacheSourceType == CacheSourceType.FILE) {
            if (StringUtils.isNullOrEmpty(cachePath)) {
                return null;
            }
            if (FileUtil.isFile(file)) {
                return new Date(file.lastModified());
            }
        }
        return null;
    }

    public File getCacheFile(Class<? extends ViewCache> viewCacheClass) {
        if (StringUtils.isNullOrEmpty(cachePath)) {
            return null;
        }
        // 从文件系统加载
        FileSystemResource fileresource = new FileSystemResource(cachePath);
        file = getCacheFile(viewCacheClass, fileresource.getFile());
        return file;
    }

    private File getCacheFile(Class<? extends ViewCache> viewCacheClass, File file) {
        if (file.exists() && file.isFile()) {
            return file;
        }
        if (file.exists() && file.isDirectory()) {
            // 如果配置的是个目录，那么从该目录下面寻找同ViewCache名称一样的文件
            if (!cachePath.endsWith("/") && !cachePath.endsWith("\\\\")) {
                cachePath = cachePath + "/";
            }
            String path = cachePath + viewCacheClass.getSimpleName() + ViewCacheLoader.FILE_SUFFIX_XML;
            FileSystemResource resource = new FileSystemResource(path);
            File cacheFile = resource.getFile();
            if (cacheFile.exists() && cacheFile.isFile()) {
                return cacheFile;
            }
        }
        return null;
    }

    public InputStream getCacheInputStream(Class<? extends ViewCache> viewCacheClass) {
        String fileName = null;
        if (StringUtils.isNullOrEmpty(cachePath)) {
            fileName = viewCacheClass.getSimpleName();
        } else {
            fileName = cachePath;
        }
        ClassPathResource resource = new ClassPathResource(DEFAULT_CACHE_PATH + fileName
                                                           + ViewCacheLoader.FILE_SUFFIX_XML);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("默认的类库里加载不到 缓存文件:" + cachePath, e);
        }
    }

    @Override
    public int getCacheSourceType() {
        return cacheSourceType;
    }

    public void setCachePath(String cachePath) {
        if (StringUtils.isNullOrEmpty(cachePath)) {
            return;
        }
        this.cachePath = cachePath;
    }

    public String getCachePath() {
        return cachePath;
    }

    public ViewCacheLoader getViewCacheLoader() {
        return viewCacheLoader;
    }

    public void setViewCacheLoader(ViewCacheLoader viewCacheLoader) {
        this.viewCacheLoader = viewCacheLoader;
    }

}

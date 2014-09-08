package com.jessrun.platform.viewcache;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;

import com.jessrun.platform.viewcache.ViewCacheLoader.CacheSourceType;

/**
 * ViewCache基类
 * 
 * @author luoyifan 2012-12-3 上午10:26:01
 */
public class BaseViewCache implements ViewCache, InitializingBean {

    private static final long serialVersionUID = 8269457496563035543L;

    // 多久监控一次，是否缓存文件有修改
    protected long            watchSeconds     = 5000;

    // 当期文件的最后修改时间
    protected long            currentModifiedTime;

    // 缓存加载器
    protected ViewCacheLoader viewCacheLoader;

    public void setWatchSeconds(long watchSeconds) {
        this.watchSeconds = watchSeconds;
    }

    public void setViewCacheLoader(ViewCacheLoader viewCacheLoader) {
        this.viewCacheLoader = viewCacheLoader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        viewCacheLoader.loadViewCache(this);
        Date lastModified = viewCacheLoader.getLastModifiedDate();
        if (null != lastModified) {
            currentModifiedTime = lastModified.getTime();
        }
        if (CacheSourceType.CLASSPATH == viewCacheLoader.getCacheSourceType()) {
            // 从类路径加载的，必须重启应用的，不需要启动监听脚本，监听文件是否更新过
            return;
        }
        // 启动缓存文件的监听脚本
        Timer timer = new Timer(true);
        CacheWatcher watcher = new CacheWatcher();
        timer.schedule(watcher, 5, watchSeconds * 1000);
    }

    /**
     * 缓存监听器，实时监听缓存，如果缓存有更新的话，就重新加载缓存
     */
    protected class CacheWatcher extends TimerTask {

        @Override
        public void run() {
            int type = viewCacheLoader.getCacheSourceType();
            if (CacheSourceType.CLASSPATH == type) {
                return;
            }
            Date lastModified = viewCacheLoader.getLastModifiedDate();
            if (null == lastModified) {
                return;
            }
            long watchedTime = lastModified.getTime();
            if (watchedTime - currentModifiedTime > 0) {
                viewCacheLoader.loadViewCache(BaseViewCache.this);
                currentModifiedTime = watchedTime;
            }
        }
    }
}

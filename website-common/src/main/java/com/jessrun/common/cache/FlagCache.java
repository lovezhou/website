package com.jessrun.common.cache;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

/**
 * 标记缓存
 * 
 * @author luoyifan 2012-12-8 上午10:23:35
 */
public abstract class FlagCache implements InitializingBean {

    @Resource(name = "memcachedManager")
    public CacheManager     memcachedManager;

    /**
     * 监控频率(毫秒)
     */
    protected long          watchSeconds     = 5000;

    /**
     * 最后缓存版本
     */
    private long            lastCacheVersion = -1L;
    protected ReadWriteLock readWriteLock    = new ReentrantReadWriteLock();

    Timer                   timer            = new Timer(true);
    CacheWatcher            watcher          = new CacheWatcher();

    /**
     * 初始化缓存
     */
    public abstract void loadFlagCache();

    /**
     * 获取分布式key
     */
    protected abstract String getFlagKey();

    private long getRemoutVersion() {
        Long cacheVersion = (Long) memcachedManager.get(getFlagKey());
        if (cacheVersion == null) {
            return memcachedManager.incr(getFlagKey());
        }
        return cacheVersion;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        readWriteLock.writeLock().lock();
        try {
            loadFlagCache();
        } finally {
            readWriteLock.writeLock().unlock();
        }

        // 启动缓存文件的监听脚本
        // timer.schedule(watcher, 5, watchSeconds);
    }

    /**
     * 缓存监听器，实时监听缓存，如果缓存有更新的话，就重新加载缓存
     */
    protected class CacheWatcher extends TimerTask {

        @Override
        public void run() {
            long changeVersion = getRemoutVersion();
            if (changeVersion != lastCacheVersion) {
                readWriteLock.writeLock().lock();
                try {
                    loadFlagCache();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                lastCacheVersion = getRemoutVersion();
            }
        }
    }
}

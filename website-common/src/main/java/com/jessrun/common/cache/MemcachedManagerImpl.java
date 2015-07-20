/**
 * @(#)MemcachedManagerImpl.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.jessrun.platform.util.DateUtils;
import com.jessrun.platform.util.DateUtils.TimeUnit;

/**
 * memcached 缓存实现
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public class MemcachedManagerImpl implements CacheManager {

    private static final Logger log = LoggerFactory.getLogger(MemcachedManagerImpl.class);

    private Properties          properties;
    private MemCachedClient     memCachedClient;
    private SockIOPool          pool;
    private boolean             enable;

    public void initialize() {

        enable = Boolean.valueOf(properties.getProperty("enable", "true"));
        if (!enable) {
            return;
        }
        String[] weightStr = properties.getProperty("weights", "").split(" ");
        Integer[] weights = new Integer[weightStr.length];
        for (int i = 0; i < weightStr.length; i++) {
            weights[i] = Integer.valueOf(weightStr[i]);
        }

        pool = SockIOPool.getInstance("neeaMemcachedPool");
        pool.setInitConn(Integer.valueOf(properties.getProperty("initConn", "20")));
        pool.setMinConn(Integer.valueOf(properties.getProperty("minConn", "20")));
        pool.setMaxConn(Integer.valueOf(properties.getProperty("maxConn", "20")));
        pool.setMaintSleep(Integer.valueOf(properties.getProperty("maintSleep", "20")));
        pool.setNagle(Boolean.valueOf(properties.getProperty("nagle", "false")));
        pool.setSocketTO(Integer.valueOf(properties.getProperty("socketTO", "3000")));
        pool.setServers(properties.getProperty("servers", "").split(" "));
        pool.setWeights(weights);
        pool.initialize();

        memCachedClient = new MemCachedClient("neeaMemcachedPool");
        memCachedClient.setCompressEnable(Boolean.valueOf(properties.getProperty("compressEnable", "false")));
        memCachedClient.setCompressThreshold(Integer.valueOf(properties.getProperty("compressThreshold", "4096")));
    }

    public void shutDown() {
        if (!enable) {
            return;
        }
        pool.shutDown();
    }

    @Override
    public void clear() {
        if (!enable) {
            return;
        }
        memCachedClient.flushAll();
    }

    @Override
    public long incr(String key) {
        if (!enable) {
            return 0;
        }
        return memCachedClient.incr(key);
    }

    @Override
    public long decr(String key) {
        if (!enable) {
            return 0;
        }
        return memCachedClient.decr(key);
    }

    @Override
    public Serializable get(String key) {
        if (!enable) {
            return null;
        }
        try {
            return (Serializable) memCachedClient.get(key);
        } catch (Exception e) {
            log.error("get memcached error", e);
            return null;
        }

    }

    @Override
    public int getSize() {
        if (!enable) {
            return 0;
        }
        throw new RuntimeException("unSupport get size");
    }

    @Override
    public boolean put(String key, Serializable cacheResult, int timeToIdleSeconds) {
        if (!enable) {
            return false;
        }
        Date now = new Date();
        Date idleDate = DateUtils.add(now, TimeUnit.SECONDS, timeToIdleSeconds);

        return put(key, cacheResult, idleDate);
    }

    @Override
    public Object remove(String key) {
        if (!enable) {
            return null;
        }
        Object obj = get(key);
        memCachedClient.delete(key);
        return obj;
    }

    @Override
    public boolean put(String key, Serializable cacheResult, Date idleDate) {
        if (!enable) {
            return false;
        }
        try {
            memCachedClient.set(key, cacheResult, idleDate);
            return true;
        } catch (Exception e) {
            log.error("set memcached error", e);
            return false;
        }

    }

    public void setProperties(Properties hibernateProperties) {
        this.properties = hibernateProperties;
    }

    public Properties getProperties() {
        if (this.properties == null) {
            this.properties = new Properties();
        }
        return this.properties;
    }
}

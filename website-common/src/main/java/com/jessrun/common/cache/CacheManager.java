/**
 * @(#)CacheManager.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache;

import java.io.Serializable;
import java.util.Date;

/**
 * 缓存管理接口
 * 
 * @author luoyifan
 * @version 1.0,2012-1-5
 */
public interface CacheManager {

    /**
     * 得到缓存记录
     * 
     * @author luoyifan
     * @param key
     * @return
     */
    public Serializable get(String key);

    /**
     * 写入一条记录到缓存中
     * 
     * @author luoyifan
     * @param key 缓存key
     * @param result 缓存内容
     * @param timeToIdleSeconds 过期时间(单位秒)
     */
    public boolean put(String key, Serializable result, int timeToIdleSeconds);

    /**
     * 写入一条记录到缓存中
     * 
     * @author luoyifan
     * @param key 缓存key
     * @param result 缓存内容
     * @param idleDate 过期日期
     */
    public boolean put(String key, Serializable result, Date idleDate);

    /**
     * 删除一条缓存
     * 
     * @author luoyifan
     * @param key
     */
    public Object remove(String key);

    /**
     * 得到缓存数量
     * 
     * @author luoyifan
     * @return
     */
    public int getSize();

    /**
     * 清空缓存
     * 
     * @author luoyifan
     */
    public void clear();

    public long incr(String key);

    public long decr(String key);
}

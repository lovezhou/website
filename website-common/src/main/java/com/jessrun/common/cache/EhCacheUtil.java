package com.jessrun.common.cache;
import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * 类EhCacheUtil.java的实现描述 缓存实现
 * @author ZMy 2015-7-15 下午11:35:44
 */
public class EhCacheUtil {
	
	private  CacheManager cacheManager ;
	
	private static EhCacheUtil ehCacheUtil ;
	
	private  EhCacheUtil(String cacheConfig){
		cacheManager = new CacheManager(cacheConfig);
	}
	
	private  EhCacheUtil(InputStream inputstream){
        cacheManager = new CacheManager(inputstream);
    }
    
	
    public static EhCacheUtil  newInstance(String cacheConfig){
    	if(ehCacheUtil==null){
    		ehCacheUtil = new EhCacheUtil(cacheConfig);
    	}
    	return  ehCacheUtil;
    }
    
    public static EhCacheUtil  newInstance(InputStream inputstream){
        if(ehCacheUtil==null){
            ehCacheUtil = new EhCacheUtil(inputstream);
        }
        return  ehCacheUtil;
    }
	
    /**
     * 创建一个缓存对象,如果cachemanger中存在该缓存则直接返回,否则创建一个新的缓存返回
     * @param cacheName
     * @return
     */
    public Cache createCache(String cacheName){
    	Cache cache = getCache(cacheName);
    	if(cache==null){
    		cache = new Cache(cacheName,10000,true,false,300L,600L,true,3000L);
    	}
    	return cache;
    }
	
    /**
     * 获取一个缓存对象,如果存在就返回,不存在就返回null
     * @param cacheName
     * @return
     */
	public Cache  getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		return  cache;
	}
	
	/**
	 * 将某个对象添加到指定的缓存中
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName, Object key ,Object value){
		    Element element  = new Element(key,value);
			cacheManager.getCache(cacheName).put(element);
	}
	
	/**
	 * 在指定的缓存中根据key获取某个对象
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public  Object  getObject(String cacheName , Object key){
		Object object = null;
		if(cacheManager.getCache(cacheName).isKeyInCache(key)){
			 object = cacheManager.getCache(cacheName).get(key);
		}
		return object ;
	}
	
	/**
	 * 删掉某个缓存中的对象
	 * @param cacheName
	 * @param key
	 */
    public  void  removeObject(String cacheName,Object  key){
    	if(cacheManager.getCache(cacheName).isKeyInCache(key)){
		    cacheManager.getCache(cacheName).remove(key);
		}
    }
}

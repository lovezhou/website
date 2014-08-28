/**
 * @(#)CacheUtils.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.cache;

import java.util.Date;

import com.jessrun.platform.util.DateUtils;
import com.jessrun.platform.util.DateUtils.TimeUnit;
import com.jessrun.platform.util.StringUtils;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2012-1-10
 */
public class CacheUtils {

    /**
     * 计算过期日期<br>
     * 以idleTime优先计算
     * 
     * @author luoyifan
     * @param pageCache
     * @return
     */
    public static Date calculateExpireDate(String idleTime, int timeToIdleSeconds) {
        Date date = new Date();
        if (!StringUtils.isNullOrEmpty(idleTime)) {
            String[] expireTimeArray = idleTime.split(":");
            int expireHour = Integer.parseInt(expireTimeArray[0]);
            int expireMinute = Integer.parseInt(expireTimeArray[1]);
            int hour = DateUtils.getValue(date, TimeUnit.HOURS);
            int minute = DateUtils.getValue(date, TimeUnit.MINUTES);
            date = DateUtils.set(date, TimeUnit.HOURS, expireHour);
            date = DateUtils.set(date, TimeUnit.MINUTES, expireMinute);
            boolean addToday = (hour < expireHour) || (hour == expireHour && minute < expireMinute);
            if (!addToday) {
                date = DateUtils.add(date, TimeUnit.DAYS, 1);
            }
            return date;
        } else {
            return DateUtils.add(date, TimeUnit.SECONDS, timeToIdleSeconds);
        }

    }
}

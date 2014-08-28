package com.jessrun.common.solr;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;

import com.jessrun.platform.util.DateUtils;
import com.jessrun.platform.util.DateUtils.TimeUnit;
import com.jessrun.platform.util.StringUtils;

public class SolrDocumentParser {

    private final Logger       log;
    private final boolean      useGMTDate;
    private final SolrDocument document;

    public SolrDocumentParser(Logger log, SolrDocument document, boolean useGMTDate){
        this.document = document;
        this.useGMTDate = useGMTDate;
        this.log = log;
    }

    public String getString(String key) {
        Object object = document.get(key);
        if (object == null) return null;
        String value = object.toString();
        return StringUtils.isNullOrEmpty(value) ? null : value;
    }

    /**
     * 当参数值为 ：true、是、yes、1、ok 的时候返回True (不区分大小写)<br/>
     * 当参数值为空是返回null 否则返回false
     * 
     * @param key
     * @return
     */
    public Boolean getBoolean(String key) {
        String str = getString(key);
        if (StringUtils.isNullOrEmpty(str)) return null;
        if (str.equalsIgnoreCase("true") || str.equals("1") || str.equals("是") || str.equalsIgnoreCase("yes")
            || str.equalsIgnoreCase("ok")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * 当参数值为 ：true、是、yes、1、ok 的时候返回True (不区分大小写)<br/>
     * 否则返回false 当参数值为空时 返回 false
     * 
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key) {
        Boolean value = getBoolean(key);
        if (value == null) return false;
        return value.booleanValue();
    }

    /**
     * 参数值转换为Integer 如果转换失败则返回null
     */
    public Integer getInteger(String key) {
        String str = getString(key);
        if (StringUtils.isNullOrEmpty(str)) return null;
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            log.info("parse int error : " + str, e);
            return null;
        }
    }

    /**
     * 参数值转换为 int 如果转换失败或参数值为 null则返回defaultValue
     * 
     * @author luoyifan
     * @param key
     * @param defaultValue
     * @return
     */
    public int getIntValue(String key, int defaultValue) {
        Integer integer = getInteger(key);
        if (integer != null) return integer.intValue();
        return defaultValue;
    }

    /**
     * 参数值转换为 int 如果转换失败或参数值为 null则返回 0
     * 
     * @author luoyifan
     * @param key
     * @param defaultValue
     * @return
     */
    public int getIntValue(String key) {
        return getIntValue(key, 0);
    }

    /**
     * 参数值转换为 Double 如果转换失败则返回 null
     * 
     * @author luoyifan
     * @param key
     * @param defaultValue
     * @return
     */
    public Double getDouble(String key) {
        String str = getString(key);
        if (StringUtils.isNullOrEmpty(str)) return null;
        try {
            return Double.valueOf(str);
        } catch (Exception e) {
            log.info("parse double error : " + str, e);
            return null;
        }
    }

    public double getDoubleValue(String key, double defaultValue) {
        Double d = getDouble(key);
        if (d != null) return d.doubleValue();
        return defaultValue;
    }

    public double getDoubleValue(String key) {
        return getDoubleValue(key, 0d);
    }

    /**
     * 参数值转换为 Long 如果转换失败则返回 null
     * 
     * @author luoyifan
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getLong(String key) {
        String str = getString(key);
        if (StringUtils.isNullOrEmpty(str)) return null;
        try {
            return Long.valueOf(str);
        } catch (Exception e) {
            log.info("parse long error : " + str, e);
            return null;
        }
    }

    public long getLongValue(String key, long defaultValue) {
        Long l = getLong(key);
        if (l != null) return l.longValue();
        return defaultValue;
    }

    public long getLongValue(String key) {
        return getLongValue(key, 0);
    }

    public Short getShort(String key) {
        String str = getString(key);
        if (StringUtils.isNullOrEmpty(str)) return null;
        try {
            return Short.valueOf(str);
        } catch (Exception e) {
            log.info("parse short error : " + str, e);
            return null;
        }
    }

    public short getShortValue(String key, short defaultValue) {
        Short s = getShort(key);
        if (s != null) return s.shortValue();
        return defaultValue;
    }

    public short getShortValue(String key) {
        return getShortValue(key, (short) 0);
    }

    /**
     * @author luoyifan
     * @param key
     * @return
     */
    public Date getDate(String key) {
        Object obj = document.get(key);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Date)) {
            log.error("key:" + key + " is not date");
        }
        Date date = (Date) obj;
        return useGMTDate ? DateUtils.add(date, TimeUnit.HOURS, -8) : date;
    }

    public java.sql.Date getSqlDate(String key) {
        Date date = getDate(key);
        if (date != null) return new java.sql.Date(date.getTime());
        else return null;
    }

    public BigDecimal getBigDecimal(String key) {
        String value = getString(key);
        if (StringUtils.isNullOrEmpty(value)) return null;
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            log.info("parse date error : " + value, e);
            return null;
        }
    }

    public Calendar getCalendar(String key) {
        Date date = getDate(key);
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}

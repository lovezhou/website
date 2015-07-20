package com.jessrun.platform.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

    private static final Logger     LOG                   = LoggerFactory.getLogger(DateUtils.class);

    private static final String     SHORT_DATE_FORMAT_STR = "yyyy-MM-dd";
    private static final String     LONG_DATE_FORMAT_STR  = "yyyy-MM-dd HH:mm:ss";

    private static final DateFormat SHORT_DATE_FORMAT     = new SimpleDateFormat(SHORT_DATE_FORMAT_STR);
    private static final DateFormat LONG_DATE_FORMAT      = new SimpleDateFormat(LONG_DATE_FORMAT_STR);
    private static final String     EARLY_TIME            = "00:00:00";
    private static final String     LATE_TIME             = "23:59:59";

    /**
     * 使用预设Format格式化Date成字符串
     * 
     * @return String
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, LONG_DATE_FORMAT_STR);
    }

    /**
     * 使用参数Format格式化Date成字符串
     * 
     * @return String
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串解析成 java.sql.Date 日期
     * 
     * @author luoyifan
     * @param shortDate
     * @param format
     * @return
     */
    public static java.sql.Date parserShortDate(String shortDateStr, String format) {
        if (StringUtils.isNullOrEmpty(shortDateStr)) return null;
        DateFormat dateFormate = new SimpleDateFormat(format);
        try {
            Date date = dateFormate.parse(shortDateStr);
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            LOG.error("parser java.sql.Date error", e);
            return null;
        }
    }

    /**
     * 字符串解析成日期
     * 
     * @author luoyifan
     * @param dateStr
     * @param format
     * @return
     */
    public static java.util.Date parserDate(String dateStr, String format) {
        if (StringUtils.isNullOrEmpty(dateStr)) return null;
        DateFormat dateFormate = new SimpleDateFormat(format);
        try {
            Date date = dateFormate.parse(dateStr);
            return new java.util.Date(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据TimeUnit增加指定日期的的时间
     * 
     * @author luoyifan
     * @param date 要增加的日期
     * @param timeUnit 增加的日历字段（只能取 DAYS 到 MILLISECONDS 之间的枚举，否则报错）
     * @param value 增加的值(当值为负数时表示减少)
     * @return
     */
    public static Date add(Date date, TimeUnit timeUnit, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calField(timeUnit), value);
        return cal.getTime();
    }

    /**
     * 根据TimeUnit设定指定日期的的时间
     * 
     * @author luoyifan
     * @param date 要设定的日期
     * @param timeUnit 设定的日历字段（只能取 DAYS 到 MILLISECONDS 之间的枚举，否则报错）
     * @param value 设定的值(当值为负数时表示减少)
     * @return
     */
    public static Date set(Date date, TimeUnit timeUnit, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(calField(timeUnit), value);
        return cal.getTime();
    }

    public static enum TimeUnit {
        YEAR, MONTH, WEEK_OF_YEAR, WEEK_OF_MONTH, DAYS, DAY_OF_MONTH, HOURS, MINUTES, SECONDS, MILLISECONDS;
    }

    private static int calField(TimeUnit timeUnit) {
        int field = 0;
        if (timeUnit == TimeUnit.YEAR) field = Calendar.YEAR;
        else if (timeUnit == TimeUnit.MONTH) field = Calendar.MONTH;
        else if (timeUnit == TimeUnit.WEEK_OF_YEAR) field = Calendar.WEEK_OF_YEAR;
        else if (timeUnit == TimeUnit.WEEK_OF_MONTH) field = Calendar.WEEK_OF_MONTH;
        else if (timeUnit == TimeUnit.DAYS) field = Calendar.DAY_OF_YEAR;
        else if (timeUnit == TimeUnit.DAY_OF_MONTH) field = Calendar.DAY_OF_MONTH;
        else if (timeUnit == TimeUnit.HOURS) field = Calendar.HOUR_OF_DAY;
        else if (timeUnit == TimeUnit.MINUTES) field = Calendar.MINUTE;
        else if (timeUnit == TimeUnit.SECONDS) field = Calendar.SECOND;
        else if (timeUnit == TimeUnit.MILLISECONDS) field = Calendar.MILLISECOND;
        else throw new RuntimeException("timeUnit error");
        return field;
    }

    /**
     * 根据TimeUnit得到指定日期的值
     * 
     * @author luoyifan
     * @param date
     * @param timeUnit
     * @return
     */
    public static int getValue(Date date, TimeUnit timeUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(calField(timeUnit));
    }

    /**
     * 根据TimeUnit清除指定的日历字段
     * 
     * @author luoyifan
     * @param date 要清除的日期
     * @param timeUnit 清除的日历字段（只能取 DAYS 到 MILLISECONDS 之间的枚举，否则报错）
     * @return
     */
    public static Date clear(Date date, TimeUnit timeUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.clear(calField(timeUnit));
        return cal.getTime();
    }

    /**
     * <br>
     * 第一个日期减去第二个日期后得到的天数</br> <br>
     * 如果减去的后的天数有不满足一整天的，则不计入天数内</br>
     * 
     * @param date 被减日期
     * @param day 减去的日期
     * @return 返回减去后的天数
     */
    public static long subtractDay(Date date, Date other) {
        return subtractSecond(date, other) / (24 * 60 * 60);
    }

    /**
     * 两个日期相减得到相差的毫秒数
     * 
     * @param date
     * @param other
     * @return
     */
    public static long subtractSecond(Date date, Date other) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long dateTimeInMillis = calendar.getTimeInMillis();
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTime(other);
        long otherTimeInMillis = otherCalendar.getTimeInMillis();
        return (dateTimeInMillis - otherTimeInMillis) / (1000);
    }

    /**
     * 字符串解析成 java.sql.Time 时间
     * 
     * @author luoyifan
     * @param timeStr
     * @param timeFormat
     * @return
     */
    public static java.sql.Time parserTime(String timeStr, String timeFormat) {
        DateFormat dateFormate = new SimpleDateFormat(timeFormat);
        try {
            Date date = dateFormate.parse(timeStr);
            return new java.sql.Time(date.getTime());
        } catch (ParseException e) {
            LOG.error("parser java.sql.Time error", e);
            return null;
        }
    }

    /**
     * 得到某个日期在这一天中时间最早的日期对象
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getEarlyInTheDay(Date date) {
        String dateString = SHORT_DATE_FORMAT.format(date) + " " + EARLY_TIME;
        try {
            return LONG_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("parser date error.", e);
        }
    }

    /**
     * 得到某个日期在这一天中时间最晚的日期对象
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getLateInTheDay(Date date) {
        String dateString = SHORT_DATE_FORMAT.format(date) + " " + LATE_TIME;
        try {
            return LONG_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("parser date error.", e);
        }
    }

    /**
     * 根据年龄计算出生日
     * 
     * @author luoyifan
     * @param age
     * @return
     */
    public static java.sql.Date getBirthday(int age) {
        Date date = new Date();
        date = add(date, TimeUnit.YEAR, -age);
        return new java.sql.Date(date.getTime());
    }

    /**
     * 得到当前日期的毫秒数
     * 
     * @return
     */
    public static long getNowTime() {
        return new Date().getTime();
    }

    /**
     * 得到指定日期所在周的最早日期(星期一)
     * 
     * @param date
     * @return
     */
    public static Date getEarlyIntheWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return getEarlyInTheDay(cal.getTime());
    }

    /**
     * 得到指定日期所在周的最晚日期(星期日)
     * 
     * @param date
     * @return
     */
    public static Date getLateInTheWeek(Date date) {
        return getLateInTheDay(add(getEarlyIntheWeek(date), TimeUnit.DAYS, 6));
    }

    /**
     * 得到指定日期所在月的最早日期(1号)
     * 
     * @param date
     * @return
     */
    public static Date getEarlyIntheMonth(Date date) {
        return getEarlyInTheDay(DateUtils.set(date, TimeUnit.DAY_OF_MONTH, 1));
    }

    /**
     * 得到指定日期所在月的最晚日期
     * 
     * @param date
     * @return
     */
    public static Date getLateInTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, maxDay);
        return getLateInTheDay(cal.getTime());
    }
}

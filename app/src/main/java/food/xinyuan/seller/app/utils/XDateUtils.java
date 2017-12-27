package food.xinyuan.seller.app.utils;

import food.xinyuan.seller.app.data.bean.common.DateDifference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by f-x on 2017/12/17  10:51
 * Description
 * 日期操作工具类.
 * SimpleDateFormat函数语法：
 * G 年代标志符
 * y 年
 * M 月
 * d 日
 * h 时 在上午或下午 (1~12)
 * H 时 在一天中 (0~23)
 * m 分
 * s 秒
 * S 毫秒
 * E 星期
 * D 一年中的第几天
 * F 一月中第几个星期几
 * w 一年中第几个星期
 * W 一月中第几个星期
 * a 上午 / 下午 标记符
 * k 时 在一天中 (1~24)
 * K 时 在上午或下午 (0~11)
 * z 时区
 */

public class XDateUtils {

    private XDateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = SEC * 60;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = MIN * 60;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = HOUR * 24;

    /**
     * 周与毫秒的倍数
     */
    public static final int WEEK = DAY * 7;

    /**
     * 月与毫秒的倍数
     */
    public static final int MONTH = DAY * 30;

    /**
     * 年与毫秒的倍数
     */
    public static final int YEAR = DAY * 365;

    /**
     * 默认格式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * SimpleDateFormat不是线程安全的，以下是线程安全实例化操作
     */
    private static final ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    /**
     * 获取SimpleDateFormat实例
     *
     * @param pattern 模式串
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        SimpleDateFormat format = local.get();
        format.applyPattern(pattern);
        return format;
    }

    /**
     * 获取当前时间的字符串
     *
     * @return
     */
    public static String getCurrentDate() {
        return format(new Date(), DEFAULT_PATTERN);
    }

    /**
     * 获取表示当前时间的字符串
     *
     * @param pattern 模式串
     * @return
     */
    public static String getCurrentDate(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 日期时间格式化
     *
     * @param date Date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat format = getSimpleDateFormat(DEFAULT_PATTERN);
        return format.format(date);
    }

    /**
     * 日期时间格式化
     *
     * @param date    Date
     * @param pattern 模式串
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat format = getSimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(long millis) {
        SimpleDateFormat format = getSimpleDateFormat(DEFAULT_PATTERN);
        return format.format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param millis  毫秒时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, String pattern) {
        SimpleDateFormat format = getSimpleDateFormat(pattern);
        return format.format(new Date(millis));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time, String pattern) {
        SimpleDateFormat format = getSimpleDateFormat(pattern);
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, String pattern) {
        return new Date(string2Millis(time, pattern));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_PATTERN);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param date    Date类型时间
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat format = getSimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param date Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param millis 毫秒时间戳
     * @return Date类型时间
     */
    public static Date millis2Date(long millis) {
        return new Date(millis);
    }


    /**
     * 获取与当前时间的时间差
     *
     * @param time 需要计算的时间，应小于当前时间
     * @return DateDifference实体类，内封装有获取相差的毫秒、秒、分钟、小时、天的方法
     */
    public static String getTwoDataDifference(long time, boolean isHistory) {
        return getTwoDataDifference(System.currentTimeMillis(), millis2Date(time), isHistory);
    }


    /**
     * 得到二个日期间的时间差
     *
     * @param time1 当前时间
     * @param date2 要求送达时间
     * @return DateDifference实体类，内封装有获取相差的毫秒、秒、分钟、小时、天的方法
     */
    public static String getTwoDataDifference(long time1, Date date2, boolean isHistory) {
        DateDifference difference = new DateDifference();
        long millis = date2.getTime() - time1;
        String s = "";
        Date date;
        if (millis > 0) {   //还剩
            date = millis2Date(millis);
            s = "还剩";
        } else {             //已超时
            millis = Math.abs(millis);
            date = millis2Date(millis);
            s = "已超时";
        }
        date = millis2Date(date2Millis(date) - 8 * HOUR);
        difference.setDay(date.getDay());
        difference.setHour(date.getHours());
        difference.setMinute(date.getMinutes());
        difference.setSecond(date.getSeconds());
        difference.setMillisecond(millis);
        if (isHistory) {
            return "用时" + difference.toString();
        } else {
            return s + difference.toString();
        }
    }


}

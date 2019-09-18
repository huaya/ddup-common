package com.maxlong.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class consist some static mothod to operate date.
 *
 * @author： ma.xl
 * @dateTime： 2018-8-31 9:46
 */
public final class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     * @return
     */
    public static String nowDate() {
        return nowDate(DateFormat.STYLE8);
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     * @return
     */
    public static String nowDate(String dateFormat) {
        SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
        return dft.format(new Date());
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     * @return
     */
    public static String nowDateTime(String dateFormat) {
        SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
        return dft.format(new Date());
    }

    /**
     * convert a date to string in a specifies fromat.
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String dateToStr(Date date, String dateFormat) {
        SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);
        return myFormat.format(date);
    }

    /**
     * parse a String to Date in a specifies fromat.
     *
     * @param dateStr
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String dateFormat) {
        SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);
        try {
            return myFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * date add Second
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addSecond(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        return calendar.getTime();
    }

    /**
     * date add Second return String
     *
     * @param date
     * @param num
     * @return
     */
    public static String addSecond(Date date, int num, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        return dateToStr(calendar.getTime(), dateFormat);
    }

    /**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     */
    public static final String addDay(String newDate, int num, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date currdate = format.parse(newDate);
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);
            ca.add(Calendar.DATE, num);
            return format.format(ca.getTime());
        } catch (ParseException e) {
            LOGGER.error("转化时间出错,", e);
            return null;
        }
    }

    /**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     */
    public static final String addDay(Date newDate, int num, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar ca = Calendar.getInstance();
        ca.setTime(newDate);
        ca.add(Calendar.DATE, num);
        return format.format(ca.getTime());
    }

    /**
     * convert long to date
     * @param dateLong
     * @return
     */
    public static Date longToDate(long dateLong) {
        return new Date(dateLong);
    }

    /**
     * convert long to date string
     * @param dateLong
     * @param dateFormat
     * @return
     */
    public static String longToDate(long dateLong, String dateFormat) {
        return dateToStr(new Date(dateLong), dateFormat);
    }

    /**
     * compare two date String with a pattern
     * @param date1
     * @param date2
     * @param pattern
     * @return
     */
    public static int compareDate(String date1, String date2, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date dt1 = dateFormat.parse(date1);
            Date dt2 = dateFormat.parse(date2);
            if(dt1.getTime() > dt2.getTime()){
                return 1;
            }else if(dt1.getTime() < dt2.getTime()){
                return -1;
            }else {
                return 0;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return 	0;
        }
    }

    /**
     * 检查日期格式是否合法
     * @param date
     * @param style
     * @return
     */
    public static boolean checkDateFormat(String date, String style) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(style);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return 	false;
        }
    }

}

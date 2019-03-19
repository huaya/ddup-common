package com.maxlong.study.utils;

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
public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
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
}

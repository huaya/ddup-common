package com.maxlong.study.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class consist some static mothod to operate date.
 * @author： ma.xl
 * @dateTime： 2018-8-31 9:46
 */
public class DateUtil {

    /**
     * convert a date to string in a specifies fromat.
     * @param date
     * @param dateFormat
     * @return
     */
    public static String dateToStr(Date date, String dateFormat){
        SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);
        return myFormat.format(date);
    }
}

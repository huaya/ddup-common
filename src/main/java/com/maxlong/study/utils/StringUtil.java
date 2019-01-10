package com.maxlong.study.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-10 18:26
 */
public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 字符串居中左右填充空格
     *
     * @param str
     * @param length
     * @return
     */
    public static String fillAroundStrWithBlank(String str, int length) {
        int shortLength = length - getLengthWithChiness(str);
        if (shortLength < 0) {
            return "";
        } else if (shortLength == 0) {
            return str;
        }

        if (shortLength % 2 != 0) {
            str = str + " ";
        }
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < shortLength / 2; i++) {
            blank.append(" ");
        }
        return blank + str + blank;

    }

    /**
     * 计算字符串长度，中文算两位
     *
     * @param value
     * @return
     */
    public static int getLengthWithChiness(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static String joinWithExludeBlank(final String separator, final String... strings){
        if (strings == null) {
            throw new IllegalArgumentException("Object varargs must not be null");
        }
        String sanitizedSeparator = separator == null ? "" : separator;

        List<String> list = new ArrayList<>();
        for (String string : strings) {
            if(StringUtils.isNotBlank(string)){
                list.add(string);
            }
        }
        final Iterator<String> iterator = list.iterator();
        final StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next());
            if (iterator.hasNext()) {
                result.append(sanitizedSeparator);
            }
        }
        return result.toString();
    }

}

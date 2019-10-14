package com.maxlong.study.utils;

import java.text.DecimalFormat;

/**
 * Created on 2017/12/21
 *
 * @author plus.wang
 * @description 数字小数点格式化
 */
public final class NumFormatUtil {

    public static final String DF_WITH = "0";

    public static final String DF_WITH2 = "#0.00";

    public static final String DF_WITH2D = "#0.##";

    public static final String DF_WITH3D = "#0.###";

    public static final String DF_WITH4D = "#0.#####%";

    public static final String DF_WITH4 = "#0.0000";

    private NumFormatUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @description 沪合约为整数
     */
    public static String numberFormat(double doubleValue, String formatPattern) {

        DecimalFormat df = new DecimalFormat(formatPattern);

        return df.format(doubleValue);

    }

    /**
     * 数字格式转换
     * @param doubleStr
     * @param formatPattern
     * @return
     */
    public static String numberFormat(String doubleStr, String formatPattern) {
        double doubleValue = Double.parseDouble(doubleStr);
        return numberFormat(doubleValue,formatPattern );
    }

    /**
     * 数字格式转换 带正负号
     * @param doubleStr
     * @param formatPattern
     * @return
     */
    public static String numFormatWithSign(String doubleStr, String formatPattern) {
        double doubleValue = Double.parseDouble(doubleStr);
        return numFormatWithSign(doubleValue,formatPattern );
    }

    /**
     * 数字格式转换 带正负号
     * @param doubleValue
     * @param formatPattern
     * @return
     */
    public static String numFormatWithSign(double doubleValue, String formatPattern) {
        String positivePrefix = "";
        if(doubleValue > 0) positivePrefix = "+";
        return positivePrefix + numberFormat(doubleValue,formatPattern );
    }
}

package com.maxlong.study.utils;

/**
 * This class consist some static method to operate bytes;
 * @author: ma.xl
 * @datetime:2018-8-31 11:33
 */
public class ByteUtil {

    /**
     * convert a byte array to hex string
     * @param bytes
     * @return
     */
    public static String bytes2hex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buffer.append(Integer.toString(bytes[i] >>> 4 & 0xF, 16)).append(Integer.toString(bytes[i] & 0xF, 16));
        }
        return buffer.toString();
    }
}

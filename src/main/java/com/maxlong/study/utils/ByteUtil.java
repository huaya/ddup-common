package com.maxlong.study.utils;

/**
 * This class consist some static method to operate bytes;
 * @author: ma.xl
 * @datetime:2018-8-31 11:33
 */
public class ByteUtil {

    private ByteUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * convert a byte array to hex string
     * @param bytes
     * @return
     */
    public static String bytes2hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            builder.append(Integer.toString(bytes[i] >>> 4 & 0xF, 16)).append(Integer.toString(bytes[i] & 0xF, 16));
        }
        return builder.toString();
    }
}

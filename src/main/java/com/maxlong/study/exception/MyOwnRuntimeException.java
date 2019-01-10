package com.maxlong.study.exception;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-1-10 17:35
 */
public class MyOwnRuntimeException extends RuntimeException {

    public MyOwnRuntimeException() {
    }

    public MyOwnRuntimeException(String message) {
        super(message);
    }

    public MyOwnRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyOwnRuntimeException(Throwable cause) {
        super(cause);
    }

    public MyOwnRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

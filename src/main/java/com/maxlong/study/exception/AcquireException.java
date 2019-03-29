package com.maxlong.study.exception;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-29 11:43
 */
public class AcquireException extends RuntimeException {

    public AcquireException() {
    }

    public AcquireException(String message) {
        super(message);
    }

    public AcquireException(String message, Throwable cause) {
        super(message, cause);
    }

    public AcquireException(Throwable cause) {
        super(cause);
    }

    public AcquireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
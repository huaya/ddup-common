package com.maxlong.study.exception;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-29 11:33
 */
public class UnLockException extends RuntimeException {


    public UnLockException() {
    }

    public UnLockException(String message) {
        super(message);
    }

    public UnLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLockException(Throwable cause) {
        super(cause);
    }

    public UnLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

package com.maxlong.study.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;

/**
 * 锁
 * <p/>
 * Created by IntelliJ IDEA.
 * User: Nano
 * Date: 2015/11/29
 * Time: 13:07
 * To change this template use File | Settings | File and Code Templates.
 */
public interface Lock<T> {

    /**
     * 加锁
     *
     * @return T
     * throws LockLockException
     */
    T lock() throws LockException;

    /**
     * 释放锁
     *
     * @throws UnLockException
     */
    public void unlock() throws UnLockException;

}

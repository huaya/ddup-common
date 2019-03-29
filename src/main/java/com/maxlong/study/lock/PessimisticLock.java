package com.maxlong.study.lock;

import com.maxlong.study.exception.LockException;
import com.maxlong.study.exception.UnLockException;

import java.util.concurrent.TimeUnit;

/**
 * 分布式悲观锁
 * <p/>
 * Created by IntelliJ IDEA.
 * User: Nano
 * Date: 2015/11/29
 * Time: 12:49
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class PessimisticLock implements Lock<Boolean> {

    /**
     * 加锁
     *
     * @return boolean
     * throws LockLockException
     */
    public abstract Boolean lock() throws LockException;

    /**
     * 在指定的时间内获得锁
     *
     * @param time
     * @param unit
     * @return
     * @throws LockException
     */
    public abstract Boolean lock(long time, TimeUnit unit) throws LockException;

    /**
     * 释放锁
     *
     * @throws UnLockException
     */
    public abstract void  unlock() throws UnLockException;

}

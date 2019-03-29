package com.maxlong.study.lock;

import com.maxlong.study.exception.AcquireException;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
/**
 * 信号量
 *
 * Created by IntelliJ IDEA.
 * User: Nano
 * Date: 2015/11/29
 * Time: 15:13
 * To change this template use File | Settings | File and Code Templates.
 */
public interface Semaphore<T> {

    T acquire() throws AcquireException;

    T acquire(long time, TimeUnit unit)throws AcquireException;

    Collection<T> acquire(int qty) throws AcquireException;

    Collection<T> acquire(int qty, long time, TimeUnit unit) throws AcquireException;

    void realse(T o) ;

    void realse(Collection<T> oos);

}

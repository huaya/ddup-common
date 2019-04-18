package com.maxlong.study.utils;

import java.util.concurrent.*;

/**
 * 描述：
 * 作者： ma.xl
 * 版本： 1.0.0
 * 时间： 2018-8-28 15:33
 */
public class TreadPoolHelper {

    private TreadPoolHelper() {}

    private static class TreadPoolHelperHolder{
        public static TreadPoolHelper treadPoolHelper = new TreadPoolHelper();
    }

    public TreadPoolHelper getInstance(){
        return TreadPoolHelperHolder.treadPoolHelper;
    }

    public static ExecutorService createCacheTheadPool(){
        return Executors.newCachedThreadPool();
    }

    public static ExecutorService createSingleTheadPool(){
        return Executors.newSingleThreadExecutor();
    }

    public static ExecutorService createFixTheadPool(int nThreads){
        return Executors.newFixedThreadPool(nThreads);
    }

}

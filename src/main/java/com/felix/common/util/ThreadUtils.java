package com.felix.common.util;

import java.util.concurrent.locks.LockSupport;

/**
 * @author felix
 */
public class ThreadUtils {

    /**
     * 获取当前线程的名称
     *
     * @return 当前线程的名称
     */
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 使当前线程休眠指定的毫秒数
     *
     * @param milliSeconds 线程休眠的毫秒数
     */
    public static void sleepMilliSeconds(long milliSeconds) {
        LockSupport.parkNanos(milliSeconds * 1000L * 1000L);
    }

}

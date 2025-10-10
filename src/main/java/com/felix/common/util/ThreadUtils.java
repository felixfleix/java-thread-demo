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
     * 获取当前线程的ID
     *
     * @return 当前线程的ID
     */
    public static Long getCurThreadId() {
        return Thread.currentThread().threadId();
    }

    /**
     * 获取当前线程的实例
     *
     * @return 当前线程的实例
     */
    public static Thread getCurThread() {
        return Thread.currentThread();
    }

    /**
     * 使当前线程休眠指定的毫秒数
     *
     * @param milliSeconds 线程休眠的毫秒数
     */
    public static void sleepMilliSeconds(long milliSeconds) {
        LockSupport.parkNanos(milliSeconds * 1000L * 1000L);
    }

    /**
     * 使当前线程休眠指定的秒数
     *
     * @param seconds 线程休眠的秒数
     */
    public static void sleepSeconds(long seconds) {
        LockSupport.parkNanos(seconds * 1000L * 1000L * 1000L);
    }

}

package com.felix.common.util;

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

}

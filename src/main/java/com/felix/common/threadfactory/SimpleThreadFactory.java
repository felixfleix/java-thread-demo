package com.felix.common.threadfactory;

import com.felix.common.util.Print;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class SimpleThreadFactory implements ThreadFactory {

    /**
     * 线程编号
     */
    private static final AtomicInteger THREAD_NO = new AtomicInteger(0);

    /**
     * 线程名称前缀
     */
    private static final String THREAD_NAME_PREFIX = "SimpleThread-";

    @Override
    public Thread newThread(Runnable r) {
        String threadName = THREAD_NAME_PREFIX + THREAD_NO.incrementAndGet();
        Print.tco("创建一个线程，名称为：" + threadName);
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(true);
        return thread;
    }
}

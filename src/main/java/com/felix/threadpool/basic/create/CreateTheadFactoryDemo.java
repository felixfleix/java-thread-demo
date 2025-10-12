package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class CreateTheadFactoryDemo {

    static class SimpleThreadFactory implements ThreadFactory {

        private static final AtomicInteger THREAD_NO = new AtomicInteger(0);

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

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2, new SimpleThreadFactory());

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
        }
//        ThreadUtils.sleepSeconds(30);
        pool.close();
    }

}

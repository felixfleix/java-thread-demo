package com.felix.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author felix
 */
public class ThreadUtils {

    /**
     * 自定义线程工厂类，用于创建线程池中的线程
     */
    public static class CustomThreadFactory implements ThreadFactory {

        /**
         * 线程池编号
         */
        private static final AtomicInteger THREAD_POOL_NUM = new AtomicInteger(0);
        /**
         * 线程组
         */
        private final ThreadGroup threadGroup;

        /**
         * 线程编号
         */
        private static final AtomicInteger THREAD_NUM = new AtomicInteger(0);

        /**
         * 线程标签
         */
        private final String threadTag;


        public CustomThreadFactory(String threadTag) {
            // 创建线程组
            SecurityManager s = System.getSecurityManager();
            this.threadGroup = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            // 创建线程标签
            this.threadTag = "appPool-" + THREAD_POOL_NUM.incrementAndGet() + "-" + threadTag + "-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(this.threadGroup, r
                    , this.threadTag + THREAD_NUM.incrementAndGet(), 0);
            // 设置线程为非守护线程
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }

            // 设置线程优先级为普通优先级
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }

            return thread;
        }
    }

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

    /**
     * 关闭线程池
     *
     * @param threadPool 线程池
     */
    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        // 线程池为空或已终止，无需关闭
        if (threadPool == null || threadPool.isTerminated()) {
            return;
        }
        try {
            threadPool.shutdown();
        } catch (SecurityException e) {
            // 安全异常，线程池可能已关闭或正在关闭
            return;
        }
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 线程池未在60秒内终止，强制关闭
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    Print.tco("线程池任务未正常执行结束！");
                }
            }
        } catch (InterruptedException e) {
            // 线程池关闭过程中被中断，强制关闭
            threadPool.shutdownNow();
        }

        // 线程池还未终止，循环1000次，每次等待10毫秒，检查是否终止
        if (!threadPool.isTerminated()) {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                Print.cfo(e.getMessage());
            } catch (Throwable e) {
                Print.cfo(e.getMessage());
            }

        }
    }

}

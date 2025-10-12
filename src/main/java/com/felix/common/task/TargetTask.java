package com.felix.common.task;

import com.felix.common.util.Print;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class TargetTask implements Runnable {

    /**
     * 线程编号
     */
    private static final AtomicInteger THREAD_NO = new AtomicInteger(0);

    /**
     * 线程休眠时间间隔
     */
    private static final int SLEEP_GAP = 3000;

    /**
     * 线程名称
     */
    private final String threadName;

    public TargetTask() {
        threadName = "TargetTask-" + THREAD_NO.incrementAndGet();
    }

    @Override
    public void run() {
        Print.tco("任务：" + threadName + " 开始执行");
        try {
            Thread.sleep(SLEEP_GAP);
        } catch (InterruptedException e) {
            Print.tco("任务：" + threadName + " 执行被中断");
            e.printStackTrace();
            return;
        }
        Print.tco(threadName + " 运行结束");
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public String toString() {
        return "TargetTask{" +
                "threadName='" + threadName + '\'' +
                '}';
    }
}

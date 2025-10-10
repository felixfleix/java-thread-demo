package com.felix.multithread.basic.use;

import com.felix.common.util.Print;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class YieldDemo {

    private static final int MAX_TURN = 100;

    private static final AtomicInteger index = new AtomicInteger(0);

    private static final Map<String, AtomicInteger> metric = new ConcurrentHashMap<>();

    private static int THREAD_NO = 1;

    private static void printMetric() {
        Print.tco("metric = " + metric);
    }

    static class YieldThread extends Thread {
        public YieldThread() {
            super("YieldThread-" + THREAD_NO++);
            metric.put(getName(), new AtomicInteger(0));
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN && index.get() < MAX_TURN; i++) {
                Print.tco(getName() + "线程优先级为：" + getPriority());
                index.incrementAndGet();

                metric.get(getName()).incrementAndGet();

                if (i % 2 == 0) {
                    Thread.yield();
                }
            }
            printMetric();
            Print.tco(getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        YieldThread yieldThread1 = new YieldThread();
        yieldThread1.setPriority(Thread.MAX_PRIORITY);

        YieldThread yieldThread2 = new YieldThread();
        yieldThread2.setPriority(Thread.MIN_PRIORITY);

        Print.tco("启动线程：");
        yieldThread1.start();
        yieldThread2.start();
    }

}

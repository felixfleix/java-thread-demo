package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * 再java21及java8版本的测试下，不同优先级的线程执行结果差不多，推测可能是因为线程调度的策略不同，导致不同优先级的线程执行时间不同
 *
 * @author felix
 */
public class PriorityDemo {

    /**
     * 使用继承的方式而不是其它方式来构建Thread，是因为考虑到需要创建多个线程来进行对比，如果使用其它方式，除了创建必要的thread对象外，还需要
     * 创建诸如Runnable等对象，浪费空间
     */
    static class DemoThread extends Thread {

        private volatile boolean stop = false;

        static int THREAD_NO = 1;

        long opportunities = 0;

        public DemoThread() {
            super("DemoThread-" + THREAD_NO++);
        }

        public synchronized void stopThread() {
            stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                opportunities++;
            }
        }
    }

    public static void main(String[] args) {
        DemoThread[] threads = new DemoThread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new DemoThread();
            threads[i].setPriority(i + 1);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        ThreadUtils.sleepMilliSeconds(1000);

        for (int i = 0; i < threads.length; i++) {
            threads[i].stopThread();
        }

        for (int i = 0; i < threads.length; i++) {
            Print.cfo(threads[i].getName() + "-优先级为-" + threads[i].getPriority()
                    + "-机会值为-" + threads[i].opportunities);
        }
    }


}

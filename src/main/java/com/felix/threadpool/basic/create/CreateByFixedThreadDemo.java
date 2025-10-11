package com.felix.threadpool.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class CreateByFixedThreadDemo {

    private static final int SLEEP_GAP = 3000;

    private static final AtomicInteger THREAD_NO = new AtomicInteger(0);

    static class TargetTask implements Runnable {

        private final String threadName;

        public TargetTask() {
            threadName = "TargetTask-" + THREAD_NO.incrementAndGet();
        }

        @Override
        public void run() {
            Print.tco("任务：" + threadName + "开始执行。");
            ThreadUtils.sleepMilliSeconds(SLEEP_GAP);
            Print.tco("任务：" + threadName + "执行完毕。");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new TargetTask());
            executorService.submit(new TargetTask());
        }

        ThreadUtils.sleepSeconds(5);
        executorService.close();

        Print.tco(ThreadUtils.getCurThreadName() + " 主线程执行完毕。");
    }

}

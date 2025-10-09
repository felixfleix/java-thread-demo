package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author felix
 */
public class CreateDemo4 {

    private static final int MAX_TURN = 5;

    private static final int COMPUTE_TIMES = 100000000;

    private static final ExecutorService POOL = Executors.newFixedThreadPool(3);

    static class DemoThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(ThreadUtils.getCurThreadName() + " 第" + (i + 1) + "轮。");
                ThreadUtils.sleepMilliSeconds(10);
            }
        }
    }

    static class RunnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            Print.cfo(ThreadUtils.getCurThreadName() + "开始运行。");
            ThreadUtils.sleepMilliSeconds(1000);

            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10000;
            }

            long used = System.currentTimeMillis() - startTime;
            Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
            return used;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        POOL.execute(new DemoThread());
        POOL.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAX_TURN; i++) {
                    Print.cfo(ThreadUtils.getCurThreadName() + " 第" + (i + 1) + "轮。");
                    ThreadUtils.sleepMilliSeconds(10);
                }
            }
        });

        RunnableTask runnableTask = new RunnableTask();
        Future<Long> future = POOL.submit(runnableTask);
        Long usedTime = future.get();
        Print.cfo(ThreadUtils.getCurThreadName() + " 异步任务的执行结果为：" + usedTime);
//        POOL.close();
    }

}

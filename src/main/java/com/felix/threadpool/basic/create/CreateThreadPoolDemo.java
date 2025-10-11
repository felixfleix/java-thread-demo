package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.RandomUtils;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author felix
 */
public class CreateThreadPoolDemo {

    private static void submitTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> future = pool.submit(() -> RandomUtils.randInRange(200, 300));

        try {
            Integer i = future.get();
            Print.tco("异步调用执行结果：" + i);
        } catch (InterruptedException e) {
            Print.tco("异步调用被中断");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Print.tco("异步调用执行异常");
            e.printStackTrace();
        }

        ThreadUtils.sleepSeconds(2);
        pool.close();
    }

    static class TargetTaskWithError extends TargetTask {
        @Override
        public void run() {
            super.run();
            throw new RuntimeException("任务：" + getThreadName() + " 执行异常");
        }

    }

    private static void submitWithErrorTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<?> future = pool.submit(new TargetTaskWithError());

        try {
            if (future.get() == null) {
                Print.tco("异步调用执行结果为null");
            }
        } catch (Exception e) {
            Print.tco("异步调用执行异常：" + e.getCause().getMessage());
        }

        ThreadUtils.sleepSeconds(10);
        pool.close();
    }

    public static void main(String[] args) {
//        submitTest();
        submitWithErrorTest();
    }

}

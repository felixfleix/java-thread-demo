package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author felix
 */
public class CreateDemo3 {

    private static final int COMPUTE_TIMES = 100000000;

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

    public static void main(String[] args) {
        RunnableTask task = new RunnableTask();
        FutureTask<Long> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();

        ThreadUtils.sleepMilliSeconds(500);

        Print.cfo(ThreadUtils.getCurThreadName() + " 让子弹飞一会！");
        Print.cfo(ThreadUtils.getCurThreadName() + " 做一点自己的事情。");

        for (int i = 0; i < COMPUTE_TIMES / 2; i++) {
            int j = i * 10000;
        }

        Print.cfo(ThreadUtils.getCurThreadName() + " 获取并发任务的执行结果。");
        try {
            Print.cfo(thread.getName() + "线程占用时间为：" + futureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
    }


}

package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.threadfactory.SimpleThreadFactory;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author felix
 */
public class CreateThreadPoolDemo4 {

    static class CustomIgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            Print.tco(r + " rejected; " + " - getTaskCount: " + executor.getTaskCount());
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2
                , 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2)
                , new SimpleThreadFactory(), new CustomIgnorePolicy());

        // 预启动所有核心线程
        executor.prestartAllCoreThreads();

        for (int i = 0; i < 10; i++) {
            executor.execute(new TargetTask());
        }

        ThreadUtils.sleepSeconds(10);
        Print.tco("关闭线程池。");
        executor.close();
    }

}

package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author felix
 */
public class CreateThreadPoolDemo3 {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4
                , 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2)) {


            @Override
            protected void terminated() {
                Print.tco("调度器已经终止。");
                super.terminated();
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                Print.tco(r + " 任务的前置即将被执行。");
                START_TIME.set(System.currentTimeMillis());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                long duration = System.currentTimeMillis() - START_TIME.get();
                Print.tco(r + " 任务的后置被执行。耗时：" + duration + "ms");
                START_TIME.remove();
            }
        };


        for (int i = 1; i <= 5; i++) {
            executor.execute(new TargetTask());
        }

        ThreadUtils.sleepSeconds(10);
        Print.tco("关闭线程池");
        executor.close();

    }

}

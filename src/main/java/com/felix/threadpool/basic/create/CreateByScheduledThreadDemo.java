package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author felix
 */
public class CreateByScheduledThreadDemo {

    private static final int SLEEP_GAP = 500;
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executorService.scheduleAtFixedRate(new TargetTask(), 0, SLEEP_GAP, TimeUnit.MILLISECONDS);
        }

        ThreadUtils.sleepSeconds(8);
        executorService.close();

        Print.tco(ThreadUtils.getCurThreadName() + " 主线程执行完毕。");
    }

}

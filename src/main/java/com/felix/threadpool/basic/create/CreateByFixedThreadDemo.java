package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author felix
 */
public class CreateByFixedThreadDemo {
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

package com.felix.threadpool.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author felix
 */
public class CreateThreadPoolDemo2 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 100
                , 100, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

        for (int i = 0; i < 5; i++) {
            final int taskIndex = i;
            executor.execute(()-> {
                Print.tco("taskIndex=" + taskIndex + "开始执行。");
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            Print.tco("- activeCount=" + executor.getActiveCount()
                    + " taskCount=" + executor.getTaskCount());
            ThreadUtils.sleepSeconds(1);
        }

    }

}

package com.felix.threadpool.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class CreateBySingleThreadDemo1 {

    private static final int SLEEP_GAP = 1000;

    private static final AtomicInteger THREAD_NO = new AtomicInteger(0);


    static class TargetTask extends Thread {


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
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new TargetTask());
            executorService.submit(new TargetTask());
        }

        // close()方法会等待所有任务执行完毕，然后关闭线程池
        executorService.close();
        // 等待所有任务执行完毕，线程池关闭后，才会执行下面的代码
        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

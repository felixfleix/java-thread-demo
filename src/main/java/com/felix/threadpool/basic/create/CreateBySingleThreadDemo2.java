package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author felix
 */
public class CreateBySingleThreadDemo2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        TargetTask task = new TargetTask();

        Future<?> submit = executorService.submit(task);

        new Thread(() -> {
            Print.tco("线程：" + ThreadUtils.getCurThreadName() + "开始执行。");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Print.tco("线程：" + ThreadUtils.getCurThreadName() + "被中断。");
                return;
            }
            submit.cancel(true);
            Print.tco("线程：" + ThreadUtils.getCurThreadName() + "执行完毕。");
        }).start();

        // close()方法会等待所有任务执行完毕，然后关闭线程池
        executorService.close();
        // 等待所有任务执行完毕，线程池关闭后，才会执行下面的代码
        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

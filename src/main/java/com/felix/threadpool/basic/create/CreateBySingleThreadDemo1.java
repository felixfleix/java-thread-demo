package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author felix
 */
public class CreateBySingleThreadDemo1 {

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

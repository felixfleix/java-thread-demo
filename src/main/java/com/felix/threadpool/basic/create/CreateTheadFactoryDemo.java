package com.felix.threadpool.basic.create;

import com.felix.common.task.TargetTask;
import com.felix.common.threadfactory.SimpleThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author felix
 */
public class CreateTheadFactoryDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2, new SimpleThreadFactory());

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
        }
//        ThreadUtils.sleepSeconds(30);
        pool.close();
    }

}

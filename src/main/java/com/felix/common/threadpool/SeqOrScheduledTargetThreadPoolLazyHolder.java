package com.felix.common.threadpool;

import com.felix.common.util.ShutdownHookThread;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author felix
 */
public class SeqOrScheduledTargetThreadPoolLazyHolder {

    public static final ScheduledExecutorService EXECUTOR_SERVICE =
            new ScheduledThreadPoolExecutor(1, new ThreadUtils.CustomThreadFactory("seq"));


    static {
        Runtime.getRuntime().addShutdownHook(new ShutdownHookThread("定时和顺序任务线程池", () -> {
            ThreadUtils.shutdownThreadPoolGracefully(EXECUTOR_SERVICE);
            return null;
        }));
    }

}

package com.felix.common.util;

import java.util.concurrent.Callable;

/**
 * @author felix
 */
public class ShutdownHookThread extends Thread {

    /**
     * 标识线程是否已关闭
     */
    private volatile boolean hasShutdown = false;

    private final Callable<?> callable;

    public ShutdownHookThread(String name, Callable<?> callable) {
        super("JVM退出钩子（" + name + "）");
        this.callable = callable;
    }

    @Override
    public void run() {
        synchronized (this){
            if (!this.hasShutdown) {
                this.hasShutdown = true;
                long beginTime = System.currentTimeMillis();
                try {
                    this.callable.call();
                } catch (Exception e) {
                    Print.cfo(getName() + " error: " + e.getMessage());
                }
                long consumingTimeTotal = System.currentTimeMillis() - beginTime;
                Print.cfo(getName() + " 耗时： " + consumingTimeTotal + "ms");
            }
        }
        super.run();
    }
}

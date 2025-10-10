package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author felix
 */
public class StatusDemo {

    private static final int MAX_TURN = 5;

    private static int THREAD_NO = 0;

    private static final List<Thread> THREAD_LIST = new ArrayList<>();

    private static void printThreadStatus() {
        for (Thread thread : THREAD_LIST) {
            Print.tco(thread.getName() + "的状态为：" + thread.getState());
        }
    }

    private static void addThread(Thread thread) {
        THREAD_LIST.add(thread);
    }

    static class StatusDemoThread extends Thread {

        public StatusDemoThread() {
            super("StatusDemoThread-" + (++THREAD_NO));
            addThread(this);
        }
        @Override
        public void run() {
            Print.cfo(getName() + "的状态为：" + getState());
            for (int i = 0; i < MAX_TURN; i++) {
                ThreadUtils.sleepMilliSeconds(500);
                printThreadStatus();
            }
            Print.tco(getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        // 添加主线程
        THREAD_LIST.add(Thread.currentThread());

        StatusDemoThread thread1 = new StatusDemoThread();
        Print.cfo(thread1.getName() + "的状态为：" + thread1.getState());
        StatusDemoThread thread2 = new StatusDemoThread();
        Print.cfo(thread2.getName() + "的状态为：" + thread2.getState());
        StatusDemoThread thread3 = new StatusDemoThread();
        Print.cfo(thread3.getName() + "的状态为：" + thread3.getState());

        thread1.start();

        ThreadUtils.sleepMilliSeconds(500);
        thread2.start();

        ThreadUtils.sleepMilliSeconds(500);
        thread3.start();

        ThreadUtils.sleepMilliSeconds(500);

    }

}

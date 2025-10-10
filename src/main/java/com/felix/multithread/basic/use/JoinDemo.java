package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class JoinDemo {

    private static final int SLEEP_GAP = 5000;

    private static int THREAD_COUNT = 1;

    static class SleepThread extends Thread {

        public SleepThread() {
            super("SleepThread-" + THREAD_COUNT++);
        }

        @Override
        public void run() {
            try {
                Print.tco(getName() + "开始睡眠。");
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Print.tco(getName() + "被中断。");
                return;
            }
            Print.tco(getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new SleepThread();
        Print.tco(ThreadUtils.getCurThreadName() + "开始启动线程1。");
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new SleepThread();
        Print.tco(ThreadUtils.getCurThreadName() + "开始启动线程2。");
        thread2.start();

        try {
            thread2.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

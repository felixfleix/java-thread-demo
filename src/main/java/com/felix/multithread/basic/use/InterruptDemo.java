package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class InterruptDemo {

    private static final int SLEEP_GAP = 5000;
    private static int THREAD_NO = 1;

    static class SleepThread extends Thread {

        public SleepThread() {
            super("SleepThread-" + THREAD_NO++);
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
        SleepThread sleepThread1 = new SleepThread();
        sleepThread1.start();

        SleepThread sleepThread2 = new SleepThread();
        sleepThread2.start();

        // 线程1正在睡眠中被中断，报错
        ThreadUtils.sleepMilliSeconds(2000);
        sleepThread1.interrupt();

        // 线程2实际已经执行结束，触发中断，无影响
        ThreadUtils.sleepMilliSeconds(5000);
        sleepThread2.interrupt();

        // 线程3先触发中断，然后进入睡眠，报错
        SleepThread sleepThread3 = new SleepThread();
        sleepThread3.interrupt();
        sleepThread3.start();

        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }
}

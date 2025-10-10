package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class SleepDemo {

    private static final int SLEEP_GAP = 5000;

    private static final int MAX_TURN = 50;

    private static int THREAD_NO = 1;

    static class SleepThread extends Thread {

        public SleepThread() {
            super("SleepThread-" + THREAD_NO++);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.tco(getName() + "的第" + (i + 1) + "轮睡眠");
                ThreadUtils.sleepMilliSeconds(SLEEP_GAP);
            }
            Print.tco(getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SleepThread().start();
        }
        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

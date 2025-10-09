package com.felix.multithread.basic.create;

import com.felix.common.util.Print;

/**
 * @author felix
 */
public class CreateDemo {

    private static final int MAX_TURN = 5;
    private static int THREAD_NO = 1;
    private static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class DemoThread extends Thread {

        public DemoThread() {
            super("DemoThread-" + THREAD_NO++);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfc(getName() + "运行第" + (i + 1) + "轮。");
            }
            Print.cfc(getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        // 主线程中顺序创建Thread，保证THREAD_NO的线程安全性
        for (int i = 0; i < 2; i++) {
            thread = new DemoThread();
            thread.start();
        }
        Print.cfc(getCurThreadName() + "运行结束。");
    }

}

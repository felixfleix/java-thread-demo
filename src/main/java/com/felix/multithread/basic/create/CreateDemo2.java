package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;


/**
 * @author felix
 */
public class CreateDemo2 {

    private static final int MAX_TURN = 5;
    private static int THREAD_NO = 1;

    static class RunTarget implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(ThreadUtils.getCurThreadName() + "运行第" + (i + 1) + "轮。");
            }
            Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
        }
    }


     public static void main(String[] args) {
        Thread thread = null;
        // 主线程中顺序创建Thread，保证THREAD_NO的线程安全性
        for (int i = 0; i < 2; i++) {
            thread = new Thread(new RunTarget(), "DemoThread-" + THREAD_NO++);
            thread.start();
        }
        Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

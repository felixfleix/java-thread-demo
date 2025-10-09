package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class CreateDemo2_2 {

    private static final int MAX_TURN = 5;

    private static int THREAD_NO = 1;

    public static void main(String[] args) {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new Thread(() -> {
                for (int j = 0; j < MAX_TURN; j++) {
                    Print.cfo(ThreadUtils.getCurThreadName() + "运行第" + (j + 1) + "轮。");
                }
                Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
            }, "DemoThread-" + THREAD_NO++);
            thread.start();
        }
        Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

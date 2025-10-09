package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class CreateDemo2_1 {

    private static final int MAX_TURN = 5;
    private static int THREAD_NO = 1;


    public static void main(String[] args) {
        Thread thread = null;
        // 主线程中顺序创建Thread，保证THREAD_NO的线程安全性
        for (int i = 0; i < 2; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < MAX_TURN; i++) {
                        Print.cfc(ThreadUtils.getCurThreadName() + "运行第" + (i + 1) + "轮。");
                    }
                }
            }, "DemoThread-" + THREAD_NO++);
            thread.start();
        }
        Print.cfc(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

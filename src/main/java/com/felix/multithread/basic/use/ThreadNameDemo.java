package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class ThreadNameDemo {

    private static final int MAX_TURN = 5;

    static class RunTarget implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                ThreadUtils.sleepMilliSeconds(500);
                Print.tco("第" + (i + 1) + "轮循环");
            }
        }
    }

    public static void main(String[] args) {
        RunTarget runTarget = new RunTarget();
        new Thread(runTarget).start();
        new Thread(runTarget).start();
        new Thread(runTarget).start();
        new Thread(runTarget, "手动命名线程-A").start();
        new Thread(runTarget, "手动命名线程-B").start();
        ThreadUtils.sleepMilliSeconds(10000);
    }

}

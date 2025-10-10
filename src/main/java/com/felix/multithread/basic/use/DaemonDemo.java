package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class DaemonDemo {

    private static final int MAX_TURN = 4;

    private static final int SLEEP_GAP = 500;

    static class DaemonThread extends Thread {

        public DaemonThread() {
            super("DaemonThread");
        }

        @Override
        public void run() {
            Print.tco("守护线程开始");
            for (int i = 0; ; i++) {
                Print.tco("守护线程运行第" + (i + 1) + "轮。");
                Print.tco("守护线程的守护状态为：" + isDaemon());
                ThreadUtils.sleepMilliSeconds(SLEEP_GAP);
            }
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread userThread = new Thread(() -> {
            Print.tco("用户线程开始");
            for (int i = 0; i < MAX_TURN; i++) {
                Print.tco("用户线程运行第" + (i + 1) + "轮。");
                ThreadUtils.sleepMilliSeconds(SLEEP_GAP);
            }
        }, "UserThread");
        userThread.start();

        Print.tco(ThreadUtils.getCurThreadName() + "的守护状态为：" + ThreadUtils.getCurThread().isDaemon());
        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class DaemonDemo2 {

    private static final int SLEEP_GAP = 5000;
    private static int THREAD_NO = 1;

    static class NormalThread extends Thread {

        public NormalThread() {
            super("NormalThread-" + THREAD_NO++);
        }

        @Override
        public void run() {
            for (int i = 0; ; i++) {
                Print.tco(getName() + ", 守护状态: " + isDaemon());
                ThreadUtils.sleepMilliSeconds(SLEEP_GAP);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                NormalThread normalThread = new NormalThread();
                normalThread.start();
            }
        });
        thread.setDaemon(true);
        thread.start();

        ThreadUtils.sleepMilliSeconds(10000);
        Print.tco(ThreadUtils.getCurThreadName() + "运行结束。");
    }


}

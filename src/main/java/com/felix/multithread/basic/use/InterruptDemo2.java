package com.felix.multithread.basic.use;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class InterruptDemo2 {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Print.tco("线程启动了。");

                while (true) {
                    Print.tco(isInterrupted());
                    ThreadUtils.sleepSeconds(5);
                    if (isInterrupted()) {
                        Print.tco("线程结束了。");
                        return;
                    }
                }
            }
        };

        thread.start();
        ThreadUtils.sleepSeconds(2);

        thread.interrupt();
    }

}

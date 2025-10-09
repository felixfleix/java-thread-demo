package com.felix.multithread.basic.create;

import com.felix.common.util.Print;

/**
 * @author felix
 */
public class EmptyThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread();
        Print.cfc("线程名称；" + thread.getName());
        Print.cfc("线程ID；" + thread.threadId());
        Print.cfc("线程状态；" + thread.getState());
        Print.cfc("线程优先级；" + thread.getPriority());
        Print.cfc(getCurThreadName() + "运行结束。");
        thread.start();
    }

    private static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

}

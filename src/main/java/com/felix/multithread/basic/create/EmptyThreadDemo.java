package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class EmptyThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread();
        Print.cfo("线程名称；" + thread.getName());
        Print.cfo("线程ID；" + thread.threadId());
        Print.cfo("线程状态；" + thread.getState());
        Print.cfo("线程优先级；" + thread.getPriority());
        Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
        thread.start();
    }

}

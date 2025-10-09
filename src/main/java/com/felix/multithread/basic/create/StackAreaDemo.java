package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

/**
 * @author felix
 */
public class StackAreaDemo {

    public static void main(String[] args) {
        Print.cfo("线程ID：" + Thread.currentThread().threadId());
        Print.cfo("线程名：" + Thread.currentThread().getName());
        Print.cfo("线程状态：" + Thread.currentThread().getState());
        Print.cfo("线程优先级：" + Thread.currentThread().getPriority());
        int a = 1, b = 1;
        int c = a / b;
        anotherFun();
        ThreadUtils.sleepMilliSeconds(100000);
    }


    public static void anotherFun() {
        int a = 1, b = 1;
        int c = a / b;
        anotherFun2();
    }

    public static void anotherFun2() {
        int a = 1, b = 1;
        int c = a / b;
    }

}

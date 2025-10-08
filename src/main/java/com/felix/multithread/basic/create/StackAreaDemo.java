package com.felix.multithread.basic.create;

import com.felix.common.util.Print;

/**
 * @author felix
 */
public class StackAreaDemo {

    public static void main(String[] args) throws InterruptedException {
        Print.cfc("线程ID：" + Thread.currentThread().threadId());
        Print.cfc("线程名：" + Thread.currentThread().getName());
        Print.cfc("线程状态：" + Thread.currentThread().getState());
        Print.cfc("线程优先级：" + Thread.currentThread().getPriority());
        int a = 1, b = 1;
        int c = a / b;
        anotherFun();
        Thread.sleep(10000000);
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

package com.felix.multithread.basic.create;

import com.felix.common.util.Print;
import com.felix.common.util.ThreadUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author felix
 */
public class SalesDemo {

    private static final int MAX_ACCOUNT = 5;

    static class StoreGoods extends Thread {
        private int goodsAccount = MAX_ACCOUNT;
        public StoreGoods(String name) {
            super(name);
        }
        @Override
        public void run() {
            for (int i = 0; i < MAX_ACCOUNT; i++) {
                if (goodsAccount > 0) {
                    Print.cfo(getName() + "卖出了一件，还剩" + (--goodsAccount) + "件。");
                    ThreadUtils.sleepMilliSeconds(10);
                }
            }
            Print.cfo(getName() + "卖完了。");
        }
    }

    static class MallGoods implements Runnable {
        private final AtomicInteger goodsAccount = new AtomicInteger(MAX_ACCOUNT);
        @Override
        public void run() {
            for (int i = 0; i < MAX_ACCOUNT; i++) {
                if (goodsAccount.get() > 0) {
                    Print.cfo(ThreadUtils.getCurThreadName() + "卖出了一件，还剩" + (goodsAccount.decrementAndGet()) + "件。");
                    ThreadUtils.sleepMilliSeconds(10);
                }
            }
            Print.cfo(ThreadUtils.getCurThreadName() + "卖完了。");
        }
    }

    public static void main(String[] args) {
        Print.hint("商店版本的销售");
        for (int i = 0; i < 3; i++) {
            Thread thread = new StoreGoods("店员-" + (i + 1));
            thread.start();
        }

        ThreadUtils.sleepMilliSeconds(1000);

        Print.hint("商场版本的销售");
        MallGoods mallGoods = new MallGoods();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(mallGoods, "商场销售员-" + (i + 1));
            thread.start();
        }

        Print.cfo(ThreadUtils.getCurThreadName() + "运行结束。");
    }

}

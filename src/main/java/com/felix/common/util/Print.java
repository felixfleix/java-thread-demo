package com.felix.common.util;

/**
 * @author felix
 */
public class Print {

    /**
     * 打印当前线程执政执行的类名与方法名
     *
     * @param s 当前线程信息
     */
    public synchronized static void cfc(Object s) {
        System.out.println("[" + ReflectionUtil.getNickCallClassMethod() + "]: " + s);
    }

}

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
        System.out.println("[" + ReflectionUtils.getNickCallClassMethod() + "]: " + s);
    }

    /**
     * 打印当前线程的名称与当前线程执行的类名与方法名
     *
     * @param s 待输出的字符串
     */
    public synchronized static void tcfc(Object s) {
        String cft = "[" + ThreadUtils.getCurThreadName() + "|" + ReflectionUtils.getNickCallClassMethod() + "]";
        System.out.println(cft + ": " + s);
    }

    /**
     * 打印提示信息
     *
     * @param s 提示信息
     */
    public static void hint(Object s) {
        tcfc("/-- " + s + " --/");
    }

}

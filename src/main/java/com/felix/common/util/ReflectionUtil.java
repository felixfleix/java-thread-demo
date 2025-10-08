package com.felix.common.util;

/**
 * @author felix
 */
public class ReflectionUtil {

    /**
     * 获取调用者的类名和方法名
     *
     * @return 调用者的类名和方法名，e.g. ClassA.methodA
     */
    public static String getNickCallClassMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] className = stackTrace[3].getClassName().split("\\.");
        return className[className.length - 1] + "." + stackTrace[3].getMethodName();
    }

}

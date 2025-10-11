package com.felix.common.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @author felix
 */
public class RandomUtils {

    /**
     * 生成一个随机数，范围[1, mod]
     *
     * @param mod 随机数范围，大于0
     * @return 随机数，范围[1, mod]
     */
    public static int randInMod(int mod) {
        return randInModLower(mod) + 1;
    }

    /**
     * 生成一个随机数，范围[0, mod)
     *
     * @param mod 随机数范围，大于0
     * @return 随机数，范围[0, mod)
     */
    public static int randInModLower(int mod) {
        return ThreadLocalRandom.current().nextInt(mod);
    }

    /**
     * 生成一个随机数，范围[low, high]
     *
     * @param low  随机数范围下限，大于等于0
     * @param high 随机数范围上限，大于等于low
     * @return 随机数，范围[low, high]
     */
    public static int randInRange(int low, int high) {
        return randInModLower(high - low) + low;
    }

}

package com.blinkfox.utils;

import java.util.Random;

/**
 * 按权重随机的工具类
 * Created by blinkfox on 2016/10/19.
 */
public class WeightRandomUtils {

    /**
     * 检测权重和值的类属性是否正确
     * @param values
     * @param weigths
     * @return
     */
    private static boolean isFail(Object[] values, int[] weigths) {
        if (values == null || weigths == null || (values.length != weigths.length)) {
            return true;
        }
        return false;
    }

    /**
     * 判断数组是否为空
     * @param arr
     * @return
     */
    private static boolean isNotEmpty(Object[] arr) {
        return (arr == null || arr.length == 0) ? false : true;
    }

    /**
     * 获取权重的总和
     * @param weights
     * @return
     */
    private static int getSumWeight(int[] weights) {
        int sum = 0;
        if (weights != null && weights.length > 0) {
            // 数组求和
            for (int w: weights) {
                sum += w;
            }
        }
        return sum;
    }

    /**
     * 从数组中获取按均匀分布的结果value
     * @param values
     * @return
     */
    public static Object getRandom(Object[] values) {
        if (isNotEmpty(values)) {
            return values[new Random().nextInt(values.length)];
        }
        return null;
    }

    /**
     * 从对象数组中获取按权重随机的结果value
     * @param values, 如: {"A", "B", "C", "D"}
     * @param weights, 如: {10, 20, 30, 40}
     * @return
     */
    public static Object getRandom(Object[] values, int[] weights) {
        if (isFail(values, weights)) {
            return null;
        }

        int sum = 0;
        double randNum = (new Random()).nextDouble() * getSumWeight(weights);

        for (int i = 0; i < weights.length; i ++) {
            sum += weights[i];
            if (randNum <= sum) {
                return values[i];
            }
        }
        return null;
    }

}
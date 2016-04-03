package com.blinkfox.test;

import java.util.Random;

/**
 * 生成随机数的工具类
 * Created by blinkfox on 16-4-3.
 */
public class RandomCharUtil {

    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomAllChar(int n) {
        StringBuffer sb = new StringBuffer("");
        int allLen = allChar.length();
        Random random = new Random();

        // 循环获取n个随机数
        for (int i = 0; i < n; i++) {
            sb.append(allChar.charAt(random.nextInt(allLen)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("获取的12位数据数是：" + getRandomAllChar(12));
    }

}

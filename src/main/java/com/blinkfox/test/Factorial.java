package com.blinkfox.test;

/**
 * 计算正整数的阶乘(n!)各个位数数字之和
 * Created by blinkfox on 2016-03-06.
 */
public class Factorial {

    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
        int n = 50000;

        long startTime = System.currentTimeMillis();
        int[] arr = new int[n * 5];
        int len = 0, num, i;

        arr[0] = 1;
        for (; n > 1; n--) {
            for (num = i = 0; i <= len; i++) {
                arr[i] = (num += arr[i] * n) % 10;
                num /= 10;
            }

            while (num > 0) {
                arr[++len] = num % 10;
                num /= 10;
            }
        }

        int sum = 0;
//        StringBuilder sb = new StringBuilder("");
        for (; len >= 0; len--) {
            sum = sum + arr[len];
//            sb.append(String.valueOf(arr[len]));
        }
//        System.out.println(n + "的阶乘为:" + sb.toString());
        System.out.println("陈家银：" + (System.currentTimeMillis() - startTime) + "：" + sum);
    }

}

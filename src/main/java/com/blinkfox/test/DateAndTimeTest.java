package com.blinkfox.test;

import java.time.LocalDate;

/**
 * Created by Blinkfox on 2016-04-18.
 */
public class DateAndTimeTest {

    /**
     * 获取当地的当天日期
     */
    private static void getNowDate() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期是：" + today);
    }

    /**
     * 获取当前的年、月、日信息
     */
    private static void getYearMonthDay() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("当前的年 : %d  月 : %d  日 : %d%n", year, month, day);
    }

    /**
     * 获取特定的日期
     */
    private static void getParticularDate() {
        LocalDate dateOfBirth = LocalDate.of(2016, 4, 18);
        System.out.println("你的出生日期是：" + dateOfBirth);
    }

    public static void main(String[] args) {
        // 获取当地的当天日期
        getNowDate();

        // 获取当前的年、月、日信息
        getYearMonthDay();

        // 获取特定的日期
        getParticularDate();
    }

}
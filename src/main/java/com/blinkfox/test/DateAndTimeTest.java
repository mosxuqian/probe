package com.blinkfox.test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

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

    /**
     * 判断今天和指定日期是否是同一天
     */
    private static void isSameDate() {
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2016, 4, 18);
        if (date1.equals(today)) {
            System.out.printf("今天 %s 和 date1 %s 是同一天!%n", today, date1);
        }
    }

    /**
     * 检查像生日这种周期性事件
     */
    private static void checkRecurringDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2016, 4, 18);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if(currentMonthDay.equals(birthday)){
            System.out.println("好高兴今天是您的生日!!");
        }else{
            System.out.println("对不起，今天不是您的生日!!");
        }
    }

    /**
     * 获取当前时间
     */
    private static void getNowTime() {
        LocalTime time = LocalTime.now();
        System.out.println("当前时间是:" + time);
    }

    /**
     * 两小时后的时间
     */
    private static void plusHour() {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2); // 添加两小时
        System.out.println("当前时间:" + time + ",两小时后的时间: " +  newTime);
    }

    /**
     * 一周后的日期
     */
    private static void plusWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("今天是:" + today + ",一周以后的日期: " + nextWeek);
    }

    /**
     * 一年前和一年后的日期
     */
    private static void preAndNextYear() {
        LocalDate today = LocalDate.now();
        LocalDate preYear = today.minus(1, ChronoUnit.YEARS);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("今天是:" + today + ",一年前的日期: " + preYear + ",一年后的日期: " + nextYear);
    }

    /**
     * Java 8中的clock类的使用
     */
    private static void clock() {
        // 得到UTC的时区的日期时间clock对象
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // 得到基于当前时区的日期时间clock对象
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
    }

    public static void main(String[] args) {
        // 获取当地的当天日期
        getNowDate();

        // 获取当前的年、月、日信息
        getYearMonthDay();

        // 获取特定的日期
        getParticularDate();

        // 判断今天和指定日期是否是同一天
        isSameDate();

        // 检查像生日这种周期性事件
        checkRecurringDate();

        // 获取当前时间
        getNowTime();

        // 两小时后的时间
        plusHour();

        // 一周后的日期
        plusWeek();

        // 一年前和一年后的日期
        preAndNextYear();

        // Java 8中的clock类的使用
        clock();
    }

}
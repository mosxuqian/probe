package com.blinkfox.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    /**
     * 判断某个日期是否先于或晚于某个日期
     */
    private static void beforeAfterDate() {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = LocalDate.of(2016, 4, 20);
        if (tomorrow.isAfter(today)) {
            System.out.println("明天晚于今天！");
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("昨天先于今天！");
        }
    }

    /**
     * 在Java 8中处理时区
     */
    private static void zoneId() {
        // Java 8中某时区下的日期和时间
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
    }

    /**
     * 信用卡到期计算
     */
    private static void YearMonthTest() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("该月的天数 %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("您的信用卡到期是： %s%n", creditCardExpiry);
    }

    /**
     * 在Java 8中检查是否闰年
     */
    private static void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("今年是闰年！");
        } else {
            System.out.println("今年不是闰年！");
        }
    }

    /**
     * 计算两个日期之间的天数
     */
    private static void datePeriod() {
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2016, Month.APRIL, 21);
        Period periodToNext = Period.between(today, java8Release);
        System.out.println("2016年4月21日距离今天的天数：" + periodToNext.getDays() );
    }

    /**
     * 包含时差信息的日期和时间
     */
    private static void zoneOffset() {
        LocalDateTime datetime = LocalDateTime.of(2016, Month.APRIL, 19, 23, 35);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("包含时差信息的日期和时间 : " + date);
    }

    /**
     * Java 8中获取当前的时间戳
     */
    private static void getTimeStamp() {
        Instant timestamp = Instant.now();
        System.out.println("时间戳是：" + timestamp);
    }

    /**
     * 解析某种格式的日期
     */
    private static void parseDate() {
        String day = "20160418";
        LocalDate formatted = LocalDate.parse(day, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("从字符串中解析的日期: %s 是 %s %n", day, formatted);
    }

    /**
     * 使用自定义格式化工具解析日期
     */
    private static void customFormatDate() {
        String day = "2016 04 18";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate holiday = LocalDate.parse(day, formatter);
            System.out.printf("成功解析字符串：%s, 时间是：%s%n", day, holiday);
        } catch (DateTimeParseException ex) {
            System.out.printf("%s 解析失败!", day);
            ex.printStackTrace();
        }
    }

    /**
     * 把日期转换成字符串
     */
    private static void dateToStr() {
        LocalDateTime arrivalDate  = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy  hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("格式化的日期时间:  %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s 不能格式化!%n", arrivalDate);
            ex.printStackTrace();
        }
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

        // 判断某个日期是否先于或晚于某个日期
        beforeAfterDate();

        // 处理时区
        zoneId();

        // 信用卡到期计算
        YearMonthTest();

        // 在Java 8中检查是否闰年
        isLeapYear();

        // 计算两个日期之间的天数
        datePeriod();

        // 包含时差信息的日期和时间
        zoneOffset();

        // Java 8中获取当前的时间戳
        getTimeStamp();

        // 解析某种格式的日期
        parseDate();

        // 使用自定义格式化工具解析日期
        customFormatDate();

        // 把日期转换成字符串
        dateToStr();
    }

}
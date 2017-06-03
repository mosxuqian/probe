package com.blinkfox.learn.commons.lang;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.pmw.tinylog.Logger;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtilsDemo.
 * Created by blinkfox on 2017/6/3.
 */
public class DateUtilsDemo {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) throws ParseException {
        Logger.info("时间:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Logger.info("时间:{}", DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));
        Logger.info(":{}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        Logger.info(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Logger.info(DateUtils.isSameDay(new Date(), new Date()));

        Date d1 = DateUtils.parseDate("2017-06-03 23:51:44", "yyyy-MM-dd HH:mm:ss");
        Date d2 = DateUtils.parseDate("2017年06月03日 23时51分44秒", "yyyy-MM-dd HH:mm:ss",
                "yyyy年MM月dd日 HH时mm分ss秒");
        Logger.info("d1:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d1));
        Logger.info("d2:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d2));

        Date d3 = DateUtils.addYears(new Date(), 3);
        Logger.info("d3:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d3));

        Date d4 = DateUtils.setYears(new Date(), 2028);
        Logger.info("d4:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d4));

        Date d5 = DateUtils.round(new Date(), Calendar.YEAR);
        Logger.info("d5:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d5));
        Date d6 = DateUtils.round(new Date(), Calendar.MONTH);
        Logger.info("d6:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d6));
        Date d7 = DateUtils.round(new Date(), Calendar.HOUR_OF_DAY);
        Logger.info("d7:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d7));
        Date d9 = DateUtils.round(new Date(), Calendar.DAY_OF_MONTH);
        Logger.info("d9:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d9));
        Date d11 = DateUtils.round(new Date(), Calendar.HOUR);
        Logger.info("d11:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d11));
        Date d12 = DateUtils.round(new Date(), Calendar.MINUTE);
        Logger.info("d12:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d12));
        Date d13 = DateUtils.round(new Date(), Calendar.SECOND);
        Logger.info("d13:{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(d13));

        print("year", DateUtils.truncate(new Date(), Calendar.YEAR));
        print("MONTH", DateUtils.truncate(new Date(), Calendar.MONTH));
        print("HOUR_OF_DAY", DateUtils.truncate(new Date(), Calendar.HOUR_OF_DAY));
        print("DAY_OF_MONTH", DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
        print("HOUR", DateUtils.truncate(new Date(), Calendar.HOUR));
        print("MINUTE", DateUtils.truncate(new Date(), Calendar.MINUTE));
        print("SECOND", DateUtils.truncate(new Date(), Calendar.SECOND));

        print("year", DateUtils.ceiling(new Date(), Calendar.YEAR));
        print("MONTH", DateUtils.ceiling(new Date(), Calendar.MONTH));
        print("HOUR_OF_DAY", DateUtils.ceiling(new Date(), Calendar.HOUR_OF_DAY));
        print("DAY_OF_MONTH", DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH));
        print("HOUR", DateUtils.ceiling(new Date(), Calendar.HOUR));
        print("MINUTE", DateUtils.ceiling(new Date(), Calendar.MINUTE));
        print("SECOND", DateUtils.ceiling(new Date(), Calendar.SECOND));

        print("YEAR", DateUtils.getFragmentInDays(new Date(), Calendar.YEAR));
        print("MONTH", DateUtils.getFragmentInDays(new Date(), Calendar.MONTH));
    }

    /**
     * 打印日期时间.
     * @param date 日期时间
     */
    private static void print(String prefix, Date date) {
        Logger.info("{}-date:{}", prefix, FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(date));
    }

    private static void print(String prefix, long l) {
        Logger.info("{}-long:{}", prefix, l);
    }

}
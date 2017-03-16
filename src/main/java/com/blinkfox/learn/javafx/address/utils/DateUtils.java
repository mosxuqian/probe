package com.blinkfox.learn.javafx.address.utils;

import org.pmw.tinylog.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Java8日期工具类
 * Created by blinkfox on 2017-03-16.
 */
public class DateUtils {

    /* 标准日期格式 */
    private static final String STAND_DATE = "yyyy-MM-dd";

    /* 日期格式化对象 */
    private static final DateTimeFormatter STAND_DATE_FORMATTER = DateTimeFormatter.ofPattern(STAND_DATE);

    /**
     * 私有构造方法
     */
    private DateUtils() {
        super();
    }

    /**
     * 将LocalDate转换成标准的日期格式
     * @param date LocalDate实例
     * @return str
     */
    public static String getDateStr(LocalDate date) {
        if (date == null) {
            return "";
        }
        return STAND_DATE_FORMATTER.format(date);
    }

    /**
     * 将日期字符串转换成LocalDate日期
     * @param dateStr 日期字符串
     * @return LocalDate实例
     */
    public static LocalDate getStrByDate(String dateStr) {
        try {
            return STAND_DATE_FORMATTER.parse(dateStr, LocalDate::from);
        } catch (Exception e) {
            Logger.error(e, "转换日期字符串为日期出错！");
            return null;
        }
    }

}
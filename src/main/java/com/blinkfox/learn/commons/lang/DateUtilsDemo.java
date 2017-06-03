package com.blinkfox.learn.commons.lang;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.pmw.tinylog.Logger;

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
    public static void main(String[] args) {
        Logger.info("时间:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Logger.info("时间:{}", DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));
        Logger.info("时间:{}", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        Logger.info(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
package com.blinkfox.test.other;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Java时间工具类.
 * Created by blinkfox on 2017-03-29.
 */
public final class TimeUtils {

    /* 标准日期时间格式 */
    private static final String STAND_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 私有构造方法.
     */
    private TimeUtils() {
        super();
    }

    /**
     * 将时间戳转换为标准的的日期时间格式.
     * @param time 没有毫秒数的时间戳
     * @return string
     */
    public static String timeToStr(int time) {
        return new SimpleDateFormat(STAND_DATE_TIME).format(new Date(time * 1000L));
    }

}
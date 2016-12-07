package com.blinkfox.test.tinylog;

import org.pmw.tinylog.Logger;
import org.pmw.tinylog.LoggingContext;

import java.util.Date;

/**
 * tinylog测试类
 * Created by blinkfox on 2016/12/7.
 */
public class Application {

    public static void main(String[] args) {
        Logger.trace("Hello trace");
        Logger.debug("Hello debug");
        Logger.info("Hello info");
        Logger.warn("Hello warn");
        Logger.error("Hello error");

        Date date = new Date();
        Logger.info(date);

        Logger.info("{} + {} = 3", 1, 2);

        String a = null;
        try {
            a.toString();
        } catch (NullPointerException e) {
            Logger.error(e, "a is {}", a);
        }

        LoggingContext.put("userName", "张三");
    }

}
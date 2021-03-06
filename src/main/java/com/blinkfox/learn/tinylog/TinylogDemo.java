package com.blinkfox.learn.tinylog;

import org.pmw.tinylog.Logger;
import org.pmw.tinylog.LoggingContext;
import java.util.Date;

/**
 * tinylog测试类
 * Created by blinkfox on 2016/12/7.
 */
public class TinylogDemo {

    /**
     * 私有构造方法
     */
    private TinylogDemo() {
        super();
    }

    /**
     * main 方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Logger.trace("Hello trace");
        Logger.debug("Hello debug");
        Logger.info("Hello info");
        Logger.warn("Hello warn");
        Logger.error("Hello error");

        Date date = new Date();
        Logger.info(date);

        Logger.info("{} + {} = 3", 1, 2);

        for (int i = 0; i < 1000; i++) {
            Logger.info("循环打印日志, {}", i);
        }

        String a = null;
        try {
            a.toString();
        } catch (NullPointerException e) {
            Logger.error(e, "a is {}", a);
        }

        LoggingContext.put("userName", "张三");
    }

}
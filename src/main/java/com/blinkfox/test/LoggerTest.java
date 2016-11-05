package com.blinkfox.test;

import java.util.logging.Logger;

/**
 * java.util.logging日志使用示例
 * Created by blinkfox on 2016/11/5.
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(LoggerTest.class.getName());
        log.severe("这是 severe 级别的日志");
        log.warning("这是 warning 级别的日志");
        log.info("这是 info 级别的日志");
        log.config("这是 config 级别的日志");
    }

}
package com.blinkfox.test;

import com.blinkfox.utils.Log;

import java.util.logging.Logger;

/**
 * java.util.logging日志使用示例
 * Created by blinkfox on 2016/11/5.
 */
public class LoggerTest {

    private static final Log logger = Log.get(LoggerTest.class);

    public static void main(String[] args) {
        Logger log = Logger.getLogger(LoggerTest.class.getName());
        log.severe("这是 severe 级别的日志");
        log.warning("这是 warning 级别的日志");
        log.info("这是 info 级别的日志");
        log.config("这是 config 级别的日志");

        log.info("-----------以下是LogHelper的使用-----------");
        logger.info("info日志输出");
        logger.warn("warn级别的消息");
        logger.warn("error级别的消息");
        logger.error("error信息输出", new RuntimeException());
    }

}
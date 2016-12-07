package com.blinkfox.test.tinylog;

import org.pmw.tinylog.Logger;

/**
 * tinylog测试类
 * Created by blinkfox on 2016/12/7.
 */
public class Application {

    public static void main(String[] args) {
        Logger.debug("Hello debug");
        Logger.info("Hello info");
        Logger.warn("Hello warn");
        Logger.error("Hello error");
    }

}
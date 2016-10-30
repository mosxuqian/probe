package com.blinkfox.zealot.demo;

import com.blinkfox.zealot.config.ZealotConfig;

/**
 * Zealot配置类的demo示例配置
 * Created by blinkfox on 2016/10/30.
 */
public class DemoZealotConfig extends ZealotConfig {

    public static final String MY_TEST = "mytest";

    /**
     * 配置访问的Key和对应的路径xml
     */
    @Override
    public void config() {
        context.put(MY_TEST, "/zealot/mytest.xml");
    }

}
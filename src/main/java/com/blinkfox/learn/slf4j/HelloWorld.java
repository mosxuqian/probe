package com.blinkfox.learn.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 第一个SLF4J的示例
 * Created by blinkfox on 2017-03-14.
 */
public class HelloWorld {

    private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

    /**
     * 私有构造方法
     */
    private HelloWorld() {
        super();
    }

    /**
     * main方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        String name = "World";
        log.info("Hello {}!", name);
    }

}
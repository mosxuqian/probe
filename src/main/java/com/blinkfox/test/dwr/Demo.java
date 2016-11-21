package com.blinkfox.test.dwr;

/**
 * dwr demo示例类
 * Created by blinkfox on 2016/11/21.
 */
public class Demo {

    /**
     * sayHello 测试方法
     * @param word 关键词
     * @return
     */
    public String sayHello(String word) {
        System.out.println("-----the word is:" + word);
        return new StringBuilder("Hello ").append(word).append("!").toString();
    }

}
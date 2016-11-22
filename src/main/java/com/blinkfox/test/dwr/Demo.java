package com.blinkfox.test.dwr;

import com.blinkfox.zealot.helpers.StringHelper;

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
        if (StringHelper.isBlank(word)) {
            throw new RuntimeException("你输入的关键字为空");
        }
        return new StringBuilder("Hello ").append(word).append("!").toString();
    }

}
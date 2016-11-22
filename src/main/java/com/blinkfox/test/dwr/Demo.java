package com.blinkfox.test.dwr;

import com.alibaba.fastjson.JSON;
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

    /**
     * 关于Person操作的业务方法
     * @param person
     */
    public String doSomethingWithPerson(Person person) {
        System.out.println("-----进入Person方法");
        if (person == null) {
            throw new RuntimeException("person对象为空");
        }

        String personJson = JSON.toJSONString(person);
        System.out.println("-----person的json信息是:" + personJson);
        return personJson;
    }

}
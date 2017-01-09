package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Injection;

/**
 * 依赖注入需要的Person类
 * Created by blinkfox on 2017-01-09.
 */
public class Person {

    private String name; // 姓名

    private int age; // 年龄

    @Injection
    private Car car; // 车

}
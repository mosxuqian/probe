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

    /* getter 和 setter 方法*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
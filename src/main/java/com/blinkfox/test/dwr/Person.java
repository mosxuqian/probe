package com.blinkfox.test.dwr;

import java.util.Date;

/**
 * DWR测试的Bean
 * Created by blinkfox on 2016/11/23.
 */
public class Person {

    private String name; // 姓名
    private int age; // 年龄
    private Date[] appointments; // 时间数组

    /**
     * 空构造方法
     */
    public Person() {
        super();
    }

    /*getter和setter方法*/
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

    public Date[] getAppointments() {
        return appointments;
    }

    public void setAppointments(Date[] appointments) {
        this.appointments = appointments;
    }

}
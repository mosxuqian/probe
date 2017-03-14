package com.blinkfox.learn.guice.two;

/**
 * 手机类
 * Created by blinkfox on 2017-03-14.
 */
public class Phone {

    // 手机名称
    private String name;

    // 手机号
    private String number;

    /**
     * 私有构造方法
     */
    public Phone() {
        super();
    }

    /**
     * 全构造方法
     * @param name 名称
     * @param number 手机号
     */
    public Phone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /* getter和setter方法 */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * toString
     * @return str
     */
    @Override
    public String toString() {
        return "Phone{name='" + name + "', number='" + number + "'}";
    }

}
package com.blinkfox.learn.guice.two;

import com.blinkfox.utils.Log;
import com.google.inject.Inject;

/**
 * 一个人有一台笔记和一个手机的类
 * Created by blinkfox on 2017-03-14.
 */
public class Person {

    @Inject
    private Laptop laptop;

    @Inject
    private Phone phone;

    private static final Log log = Log.get(Person.class);

    /**
     * 空构造方法
     */
    public Person() {
        super();
    }

    /* getter和setter方法 */
    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    /**
     * 打印信息
     */
    public void print() {
        log.info(laptop.toString());
        log.info(phone.toString());
    }

}
package com.blinkfox.test;

/**
 * Created by blinkfox on 16-5-13.
 */
public class Person {

    private int id;

    private String name;

    private String phone;

    private String address;

    /* 构造方法 */
    public Person() {

    }

    public Person(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    /* getter和setter方法*/
    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }
}
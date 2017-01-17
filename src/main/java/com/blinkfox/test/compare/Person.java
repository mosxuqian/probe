package com.blinkfox.test.compare;

import java.util.Date;

/**
 * 实体类
 * Created by blinkfox on 2017/1/18.
 */
public class Person {

    private String id;

    private String name;

    private int age;

    private Date birthday;

    /**
     * 空构造方法
     */
    public Person() {
    }

    /**
     * 全构造方法
     * @param id
     * @param name
     * @param age
     * @param birthday
     */
    public Person(String id, String name, int age, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    /* getter 和 setter 方法 */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
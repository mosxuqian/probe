package com.blinkfox.bean;

import java.util.Date;

/**
 * 用户信息Bean.
 * Created by blinkfox on 2017/6/13.
 */
public class UserInfo {

    /* 唯一标识 */
    private String id;

    /* 姓名name */
    private String name;

    /* 昵称 */
    private String nickname;

    /* 邮箱 */
    private String email;

    /* 性别 */
    private int sex;

    /* 生日 */
    private Date birthday;

    /**
     * 空构造方法.
     */
    public UserInfo() {
        super();
    }

    /* getter和setter方法. */
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
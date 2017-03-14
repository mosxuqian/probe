package com.blinkfox.learn.guice.one;

/**
 * Guice简单示例
 * Created by blinkfox on 2017-03-14.
 */
public class Player {

    // 名称
    private String name;

    /**
     * 空构造方法
     */
    public Player() {
        super();
    }

    /* getter和setter方法 */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString方法
     * @return str
     */
    @Override
    public String toString() {
        return "Player{name='" + name + "'}";
    }

}
package com.blinkfox.myioc.test;

import com.blinkfox.myioc.annotation.Provider;

/**
 * 引擎类
 * Created by blinkfox on 2017/1/8.
 */
@Provider
public class Engine {

    // 名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
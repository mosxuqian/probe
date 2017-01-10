package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;

/**
 * 引擎类
 * Created by blinkfox on 2017/1/8.
 */
@Provider(value = "myEngine")
public class Engine {

    @Injection
    private Material material;

    // 名称
    private String name;

    /* getter 和 setter 方法*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString方法
     * @return
     */
    public String toString() {
        String materialName = material == null ? "null" : material.getName();
        return new StringBuilder("engine name:" + name + ",material name:" + materialName).toString();
    }

}
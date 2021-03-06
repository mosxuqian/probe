package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.myioc.consts.Scope;

/**
 * 制造汽车需要的材料 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider(scope = Scope.PROTOTYPE)
public class Material {

    private String name;

    /* getter 和 setter 方法*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
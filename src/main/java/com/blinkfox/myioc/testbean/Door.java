package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.myioc.consts.Scope;

/**
 * 门 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider(value = "myDoor", scope = Scope.PROTOTYPE)
public class Door {

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

}
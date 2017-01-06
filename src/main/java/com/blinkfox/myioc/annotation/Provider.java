package com.blinkfox.myioc.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入提供者的注解，用来注解到类、接口(包括注解类型) 或enum声明中
 * Created by blinkfox on 2017/1/7.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Provider {

    String value();

}
package com.blinkfox.myioc.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入需要使用的注入Bean的注解，用来注解到字段中
 * Created by blinkfox on 2017/1/7.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Injection {

    String value();

}
package com.blinkfox.test.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义统计方法耗时打印日志的注解
 * Created by blinkfox on 2017-01-04.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CostTime {

    /**
     * 执行超过该 ms 数则打印日志，默认 0ms，即默认都打印
     * @return
     */
    public long value() default 0;

}
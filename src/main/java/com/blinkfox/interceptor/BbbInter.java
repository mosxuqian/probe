package com.blinkfox.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 测试拦截器B
 * Created by blinkfox on 16/7/27.
 */
public class BbbInter implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        String methodName = inv.getMethodName();
        System.out.println("执行了BbbInter的方法,方法名是:methodName:" + methodName);
        inv.invoke();
    }

}
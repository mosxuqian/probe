package com.blinkfox.test.reflect;

/**
 * 耗时注解使用测试示例
 * Created by blinkfox on 2017-01-04.
 */
public class CostTimeTest {

    private static A a;

    private static B b;

    static {
        CostTimeProxy aproxy = new CostTimeProxy();
        a = (A) aproxy.getProxy(A.class);
        CostTimeProxy bproxy = new CostTimeProxy();
        b = (B) bproxy.getProxy(B.class);
    }

    public static void main(String[] args) {
        a.doSomeThing();
        b.doSomeThing2();
    }

}
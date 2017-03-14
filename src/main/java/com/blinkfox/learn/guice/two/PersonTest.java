package com.blinkfox.learn.guice.two;

import com.blinkfox.utils.Log;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Persion测试类
 * Created by blinkfox on 2017-03-14.
 */
public class PersonTest {

    private static Person person;

    private static final Log log = Log.get(PersonTest.class);

    @BeforeClass
    public static void init() {
        Injector injector = Guice.createInjector();
        person = injector.getInstance(Person.class);
        person.setLaptop(new Laptop("MacBook Pro 2016", "苹果", 13000.00));
        person.setPhone(new Phone("iPhone7", "13589071356"));
        log.info("初始化Person信息完成...");
    }

    /**
     * 测试打印
     */
    @Test
    public void testPrint() {
        Assert.assertNotNull(person);
        person.print();
    }

}
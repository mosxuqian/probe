package com.blinkfox.myioc.test;

import com.blinkfox.myioc.core.AppContext;
import com.blinkfox.myioc.testbean.Car;
import com.blinkfox.utils.Log;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * AppContext类的单元测试
 * Created by blinkfox on 2017-01-11.
 */
public class AppContextTest {

    private static final Log log = Log.get(IocManagerTest.class);

    @BeforeClass
    public static void init() {
        AppContext.init("com.blinkfox");
        log.info("初始化完毕...");
    }

    @Test
    public void testGetBean() {
        Car car = (Car) AppContext.getBean("car");
        car.start();
        assertNotNull(car);
    }

}
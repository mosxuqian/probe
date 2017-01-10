package com.blinkfox.myioc.test;

import com.blinkfox.myioc.testbean.Engine;
import com.blinkfox.myioc.testbean.Material;
import com.blinkfox.utils.Log;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * 动态属性注入的单元测试类
 * Created by blinkfox on 2017-01-10.
 */
public class EngineTest {

    private static final Log log = Log.get(EngineTest.class);

    @Test
    public void testDynamicInject() throws IllegalAccessException {
        // 构造实例属性等信息
        Engine engine = new Engine();
        engine.setName("发动引擎");
        Material material = new Material();
        material.setName("使用材料");

        Class engineCls = engine.getClass();
        Field[] fields = engineCls.getDeclaredFields();
        for (Field field: fields) {
            log.info("---------");
            field.setAccessible(true);
            Object val = field.get(engine);
            log.info("属性name:" + field.getName() + ",    属性值value:" + val);

            if ("material".equals(field.getName())) {
                field.set(engine, material);
            }
            log.info("属性name:" + field.getName() + ",    属性值value:" + val);
        }
        log.info("engine:" + engine.toString());
    }

}
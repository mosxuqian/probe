package com.blinkfox.myioc.test;

import com.blinkfox.myioc.testbean.Engine;
import com.blinkfox.myioc.testbean.Material;
import com.blinkfox.myioc.tools.ReflectHelper;
import com.blinkfox.utils.Log;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 动态属性注入的单元测试类
 * Created by blinkfox on 2017-01-10.
 */
public class EngineTest {

    private static final Log log = Log.get(EngineTest.class);

    @Test
    public void testDynamicInject() {
        // 构造实例属性等信息
        Engine engine = new Engine();
        engine.setName("发动引擎");
        Material material = new Material();
        material.setName("使用材料");
        log.info("赋值前engine:" + engine.toString());

        // 动态赋值
        Class engineCls = engine.getClass();
        ReflectHelper.setFieldValue(engineCls, engine, material, "material");

        // 断言和输出结果
        String engineStr = engine.toString();
        log.info("赋值后engine:" + engineStr);
        assertEquals("engine name:发动引擎,material name:使用材料", engineStr);
    }

}
package com.blinkfox.myioc.test;

import static org.junit.Assert.*;
import com.blinkfox.myioc.tools.StringHelper;
import com.blinkfox.utils.Log;
import org.junit.Test;

/**
 * StringHelper类的单元测试类
 * Created by blinkfox on 2017/1/9.
 */
public class StringHelperTest {

    private static final Log log = Log.get(StringHelperTest.class);

    @Test
    public void testGetCamelByClsName() {
        String className = "com.blinkfox.myioc.testbean.HelloEngine";
        String name = StringHelper.getCamelByClsName(className);
        log.info("驼峰式命名的name:" + name);
        assertEquals("helloEngine", name);
    }

}
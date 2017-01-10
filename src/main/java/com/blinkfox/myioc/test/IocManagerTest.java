package com.blinkfox.myioc.test;

import com.blinkfox.myioc.core.IocManager;
import com.blinkfox.utils.Log;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * IocManager单元测试来
 * Created by blinkfox on 2017-01-09.
 */
public class IocManagerTest {

    private static final Log log = Log.get(IocManagerTest.class);

    @Test
    public void testInitProviderBeanMap() {
        Map<String, Object> beanMap = IocManager.INSTANCE.initProviderBeanMap("com.blinkfox");
        for (String providerId: beanMap.keySet()) {
            log.info("ProviderInfo的id:" + providerId);
        }
        assertEquals(6, beanMap.size());
    }

}
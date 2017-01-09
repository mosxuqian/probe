package com.blinkfox.myioc.test;

import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.core.IocManager;
import com.blinkfox.utils.Log;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * IocManager单元测试来
 * Created by blinkfox on 2017-01-09.
 */
public class IocManagerTest {

    private static final Log log = Log.get(IocManagerTest.class);

    @Test
    public void testInitProviderInfo() {
        List<ProviderInfo> providerInfos = IocManager.INSTANCE.initProviderInfo("com.blinkfox");
        for (ProviderInfo info: providerInfos) {
            log.info("ProviderInfo的id:" + info.getId() + ",cls:" + info.getCls() + ",scope:" + info.getScope());
        }
        assertEquals(4, providerInfos.size());
    }

}
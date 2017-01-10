package com.blinkfox.myioc.test;

import static org.junit.Assert.*;
import com.blinkfox.myioc.bean.DataContainer;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.core.IocAnnoScanner;
import com.blinkfox.utils.Log;
import org.junit.Test;
import java.util.Map;

/**
 * 注解扫描测试类
 * Created by blinkfox on 2017/1/8.
 */
public class ScannerTest {

    private static final Log log = Log.get(ScannerTest.class);

    @Test
    public void testGetProviderAndInjections() {
        String packages = "com.blinkfox";
        DataContainer container = IocAnnoScanner.INSTANCE.getProviderInfoMaps(packages);
        Map<String, ProviderInfo> providerInfoMap = container.getProviderInfoMap();
        for (Map.Entry<String, ProviderInfo> entry: providerInfoMap.entrySet()) {
            String clsName = entry.getKey();
            ProviderInfo info = entry.getValue();
            log.info("className:" + clsName + ",ProviderInfo的id:" + info.getId() + ",clsName:" +
                    info.getCls().getName() + ",scope:" + info.getScope() + ",fields:" + info.getFields().toString());
        }

        assertNotNull(providerInfoMap);
    }

}
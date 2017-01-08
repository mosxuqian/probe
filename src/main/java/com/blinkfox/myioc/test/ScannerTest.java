package com.blinkfox.myioc.test;

import static org.junit.Assert.*;
import com.blinkfox.myioc.core.IocAnnoScanner;
import com.blinkfox.utils.Log;
import org.junit.Test;
import java.util.List;
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
        Map<String, List<String>> iocMap = IocAnnoScanner.INSTANCE.getProviderAndInjections(packages);
        for (Map.Entry<String, List<String>> entry: iocMap.entrySet()) {
            String className = entry.getKey();
            List<String> fields = entry.getValue();
            log.info("className:" + className + ",fields:" + fields.toString());
        }
        assertNotNull(iocMap);
    }

}
package com.blinkfox.hatch.adept;

import com.blinkfox.hatch.adept.config.AdeptConfigManager;
import com.blinkfox.hatch.adept.test.MyAdeptConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * HelloWorld.
 * Created by blinkfox on 2017/5/30.
 */
public class AdeptConfigManagerTest {

    /**
     * 初始化加载Adept配置.
     */
    @BeforeClass
    public static void init() {
        AdeptConfigManager.getInstance().initLoad(MyAdeptConfig.class);
    }

    /**
     * 清除配置信息.
     */
    @AfterClass
    public static void destroy() {
        AdeptConfigManager.getInstance().destroy();
    }

}
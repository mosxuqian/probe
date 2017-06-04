package com.blinkfox.hatch.adept;

import com.blinkfox.hatch.adept.config.AdeptConfigManager;
import com.blinkfox.hatch.adept.config.ConfigInfo;
import javax.sql.DataSource;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * HelloWorld.
 * Created by blinkfox on 2017/5/30.
 */
public class AdeptConfigManagerTest {

    @BeforeClass
    public static void init() {
        AdeptConfigManager.INSTANCE.initLoad("com.blinkfox.hatch.adept.test.MyAdeptConfig");
    }

    @Test
    public void testGetDataSource() {
        DataSource ds = ConfigInfo.getInstance().getDataSource();
        Assert.assertNotNull(ds);
    }

    @AfterClass
    public static void destroy() {
        AdeptConfigManager.INSTANCE.destroy();
    }

}
package com.blinkfox.hatch.adept.test;

import com.blinkfox.hatch.adept.config.AdeptConfigManager;
import com.blinkfox.hatch.adept.core.Adept;
import javax.sql.DataSource;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Adept测试类.
 * Created by blinkfox on 2017/6/5.
 */
public class AdeptTest {

    @BeforeClass
    public static void init() {
        AdeptConfigManager.getInstance().initLoad(MyAdeptConfig.class);
    }

    @Test
    public void testGetDataSource() {
        DataSource ds = Adept.getDataSource();
        Assert.assertNotNull(ds);
    }

    @AfterClass
    public static void destroy() {
        AdeptConfigManager.getInstance().destroy();
    }

}
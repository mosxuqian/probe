package com.blinkfox.hatch.adept.test;

import com.blinkfox.hatch.adept.config.AdeptConfigManager;
import com.blinkfox.hatch.adept.core.Adept;
import java.sql.Connection;
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

    /**
     * 初始化加载Adept配置.
     */
    @BeforeClass
    public static void init() {
        AdeptConfigManager.getInstance().initLoad(MyAdeptConfig.class);
    }

    /**
     * 测试获取数据源.
     */
    @Test
    public void testGetDataSource() {
        DataSource ds = Adept.getDataSource();
        Assert.assertNotNull(ds);
    }

    /**
     * 测试获取默认的数据库连接池.
     */
    @Test
    public void testGetConnection() {
        Connection conn = Adept.getConnection();
        Assert.assertNotNull(conn);
    }

    /**
     * 测试从指定的数据源获取数据库连接池.
     */
    @Test
    public void testGetConnectionFromDataSource() {
        Connection conn = Adept.getConnection(Adept.getDataSource());
        Assert.assertNotNull(conn);
    }

    /**
     * 测试获取实例.
     */
    @Test
    public void testNewInstance() {
        Adept adept = Adept.newInstance();
        Assert.assertNotNull(adept);
    }

    /**
     * 清除配置信息.
     */
    @AfterClass
    public static void destroy() {
        AdeptConfigManager.getInstance().destroy();
    }

}
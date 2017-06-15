package com.blinkfox.hatch.adept.test;

import com.blinkfox.bean.UserInfo;
import com.blinkfox.hatch.adept.config.AdeptConfigManager;
import com.blinkfox.hatch.adept.core.Adept;
import com.blinkfox.hatch.adept.core.results.BeanHandler;
import com.blinkfox.hatch.adept.core.results.MapHandler;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
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

    /* 查询所有用户的SQL语句. */
    private static final String ALL_USER_SQL = "SELECT * FROM user";

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
     * 测试获取实例.
     */
    @Test
    public void testNewInstance() {
        Adept adept = Adept.newInstance();
        Assert.assertNotNull(adept);
    }

    /**
     * 测试获取实例.
     */
    @Test
    public void testQuery() {
        String sql = "SELECT * FROM user AS u WHERE u.age > ?";
        ResultSet rs = Adept.quickStart().query(sql, 19).getRs();
        Assert.assertNotNull(rs);
    }

    /**
     * 测试获取mapList的实例.
     */
    @Test
    public void testToMapList() {
        String sql = "SELECT id AS bh, name AS myName, email AS myEmail, birthday FROM user AS u WHERE u.age > ?";
        List<Map<String, Object>> maps = Adept.quickStart().query(sql, 19).end();
        Assert.assertNotNull(maps);
    }

    /**
     * 测试获取mapList的实例.
     */
    @Test
    public void testToMap() {
        String sql = "SELECT COUNT(*) AS user_count FROM user AS u";
        Map<String, Object> map = Adept.quickStart().query(sql).end(MapHandler.newInstance());
        Assert.assertNotNull(map);

        // 获取用户总数，并断言数量
        long userCount = (long) map.get("user_count");
        Assert.assertEquals(6L, userCount);
    }

    /**
     * 测试获取mapList的实例.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEndByClass() {
        Map<String, Object> map = (Map<String, Object>) Adept.quickStart().query(ALL_USER_SQL).end(MapHandler.class);
        Adept.quickStart().query("").end(BeanHandler.newInstance(UserInfo.class));
        Assert.assertNotNull(map);
    }

    /**
     * 清除配置信息.
     */
    @AfterClass
    public static void destroy() {
        AdeptConfigManager.getInstance().destroy();
    }

}
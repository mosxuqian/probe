package com.blinkfox.learn.guice.one;

import com.blinkfox.utils.Log;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 测试示例
 * Created by blinkfox on 2017-03-14.
 */
public class PlayerTest {

    private static final Log log = Log.get(PlayerTest.class);

    private static Player player;

    @BeforeClass
    public static void init() {
        Injector injector = Guice.createInjector();
        player = injector.getInstance(Player.class);
        player.setName("张三");
        log.info("初始化完成...");
    }

    /**
     * 打印Player信息
     */
    @Test
    public void testToString() {
        Assert.assertNotNull(player);
        log.info(player.toString());
    }

}
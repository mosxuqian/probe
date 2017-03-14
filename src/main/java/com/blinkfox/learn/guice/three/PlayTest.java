package com.blinkfox.learn.guice.three;

import com.blinkfox.utils.Log;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Play的测试类
 * Created by blinkfox on 2017-03-14.
 */
public class PlayTest {

    private IPlay player;

    private static Injector injector;

    private static final Log log = Log.get(PlayTest.class);

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new PlayerModule());
        log.info("初始化IPlay完成...");
    }

    /**
     * run
     */
    @Test
    public void testRun() {
        player = injector.getInstance(GoodPlayer.class);
        Assert.assertNotNull(player);
        player.run();
    }

    /**
     * jump
     */
    @Test
    public void testJump() {
        player = injector.getInstance(BadPlayer.class);
        Assert.assertNotNull(player);
        player.jump();
    }

}
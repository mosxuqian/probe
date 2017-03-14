package com.blinkfox.learn.guice.four;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Mock连接的测试类
 * Created by blinkfox on 2017-03-14.
 */
public class ConnectTest {

    private static MockConnection connection;

    /**
     * 初始化加载
     */
    @BeforeClass
    public static void init() {
        Injector injector = Guice.createInjector((Module) binder -> {
            binder.bind(MockConnection.class).toProvider(ConnectionProvider.class);
        });
        connection = injector.getInstance(MockConnection.class);
        Assert.assertNotNull(connection);
    }

    /**
     * 测试连接
     */
    @Test
    public void testConnect() {
        connection.connect();
    }

    /**
     * 测试断开连接
     */
    @Test
    public void testDisConnect() {
        connection.disConnect();
    }

}
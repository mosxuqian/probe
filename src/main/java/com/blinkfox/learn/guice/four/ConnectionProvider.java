package com.blinkfox.learn.guice.four;

import com.blinkfox.utils.Log;
import com.google.inject.Provider;

/**
 * ConnectionProvider
 * Created by blinkfox on 2017-03-14.
 */
public class ConnectionProvider implements Provider<MockConnection> {

    private static final Log log = Log.get(ConnectionProvider.class);

    /**
     * 重写get方法
     * @return MockConnection实例
     */
    @Override
    public MockConnection get() {
        // 做一些自定义的操作
        log.info("做自定义操作...");
        return new MockConnection();
    }

}
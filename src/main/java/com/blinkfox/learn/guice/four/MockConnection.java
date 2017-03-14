package com.blinkfox.learn.guice.four;

import com.blinkfox.utils.Log;

/**
 * MockConnection
 * Created by blinkfox on 2017-03-14.
 */
public class MockConnection {

    private static final Log log = Log.get(MockConnection.class);

    /**
     * 创建连接
     */
    public void connect() {
        log.info("连接到了测试数据库...");
    }

    /**
     * 销毁连接
     */
    public void disConnect() {
        log.info("断开了测试数据库...");
    }

}
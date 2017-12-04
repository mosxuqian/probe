package com.blinkfox.test.other.db;

import java.io.PrintWriter;
import java.sql.DriverManager;

import org.apache.derby.drda.NetworkServerControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Derby网络数据库测试示例.
 *
 * @author blinkfox on 2017-12-04.
 */
public class NetworkServerDerbyTest {

    private static final Logger log = LoggerFactory.getLogger(NetworkServerDerbyTest.class);

    /** Derby驱动,在derbyclient.jar里面. */
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";

    /** 连接Derby的url. */
    private static final String URL = "jdbc:derby://localhost:1527/NetworkDB;create=true";

    /**
     * main方法.
     * <p>创建Derby网络服务器,默认端口是1527,也可以通过运行<Derby_Home>/frameworks/NetworkServer/bin/startNetworkServer.bat
     来创建并启动Derby网络服务器,如果是Unix,用startNetworkServer.ksh</p>
     *
     * @param args 数组参数
     */
    public static void main(String[] args) {
        NetworkServerControl derbyServer = null;
        try {
            //NetworkServerControl类在derbynet.jar里面
            derbyServer = new NetworkServerControl();
            PrintWriter pw = new PrintWriter(System.out); //用系统输出作为Derby数据库的输出
            derbyServer.start(pw); //启动Derby服务器
            Class.forName(DRIVER);
            DriverManager.getConnection(URL);
        } catch (Exception e) {
            log.error("操作Derby网络数据库异常!", e);
        } finally {
            if (derbyServer != null) {
                try {
                    derbyServer.shutdown();
                } catch (Exception e) {
                    log.error("关闭Derby网络数据库异常!", e);
                }
            }
        }
    }

}
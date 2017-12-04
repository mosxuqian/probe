package com.blinkfox.test.other.db;

import com.blinkfox.learn.jdbc.JdbcDaoHelper;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Derby内嵌数据库测试示例.
 *
 * @author blinkfox on 2017-12-04.
 */
public class EmbeddedDerbyTest {

    private static final Logger log = LoggerFactory.getLogger(EmbeddedDerbyTest.class);

    /** Derby驱动,在derby.jar里面. */
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    /** 连接Derby的url，create=true表示当数据库不存在时就创建它. */
    private static final String URL = "jdbc:derby:EmbeddedDB;create=true";

    /**
     * main方法.
     *
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL);//启动嵌入式数据库
            st = conn.createStatement();
            st.execute("create table foo (FOOID INT NOT NULL, FOONAME VARCHAR(30) NOT NULL)"); //创建foo表
            st.executeUpdate("insert into foo(FOOID,FOONAME) values (1, 'blinkfox')"); //插入一条数据
            rs = st.executeQuery("select * from foo");//读取刚插入的数据
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                log.info("查询结果：id = {}; name = {}", id, name);
            }
        } catch (Exception e) {
            log.error("使用Derby数据库出错!", e);
        } finally {
            JdbcDaoHelper.close(rs);
            JdbcDaoHelper.close(st);
            JdbcDaoHelper.close(conn);
        }
    }

}
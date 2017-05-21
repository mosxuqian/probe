package com.blinkfox.learn.jdbc;

import com.blinkfox.utils.others.PropHelper;

import java.sql.*;
import java.util.Properties;
import org.pmw.tinylog.Logger;

/**
 * 数据库访问空间类.
 * Created by blinkfox on 2017/5/21.
 */
public class DaoFactory {

    /* MariaDB驱动... */
    private static String driver = "";
    private static String url = "";
    private static String user = "";
    private static String pwd = null;

    /* 获取数据库连接相关的配置属性. */
    static {
        Properties props = PropHelper.INSTANCE.loadPropFile("props/config.properties");
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("username");
        pwd = props.getProperty("password");
    }

    /**
     * 获取数据库连接.
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            Logger.error(e, "注册数据库，获取数据库连接出错！");
            return null;
        }
    }

    /**
     * 关闭数据库连接.
     * @param conn 数据库连接
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.error(e, "关闭数据库连接失败！");
            }
        }
    }

    /**
     * 关闭PreparedStatement.
     * @param ps PreparedStatement
     */
    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.error(e, "关闭PreparedStatement失败！");
            }
        }
    }

    /**
     * 关闭ResultSet.
     * @param rs PreparedStatement
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.error(e, "关闭ResultSet失败！");
            }
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Connection conn = getConnection();
        Logger.info("conn:{}", conn);
        close(conn);
    }

}
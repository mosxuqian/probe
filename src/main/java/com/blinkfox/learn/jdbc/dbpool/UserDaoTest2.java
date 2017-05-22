package com.blinkfox.learn.jdbc.dbpool;

import com.blinkfox.learn.jdbc.JdbcDaoHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.pmw.tinylog.Logger;

/**
 * UserDaoTest2.
 * Created by blinkfox on 2017/5/22.
 */
public class UserDaoTest2 {

    /**
     * 插入用户信息.
     */
    private static void insertUser() {
        long time = System.currentTimeMillis();
        String sql = "INSERT INTO user(name, nickname, email, password) VALUES (?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(DataSourceHelper.getDataSource());
        try {
            runner.update(sql, time + "abcname", time + "abc", time + "abc@gmail.com", time + "123456");
            Logger.info("插入用户信息成功！");
        } catch (SQLException e) {
            Logger.error(e, "插入用户信息失败！");
        }
    }

    /**
     * 更新用户信息.
     */
    private static void updateUser() {
        long time = System.currentTimeMillis();
        String sql = "UPDATE user SET name = ?, nickname = ?, email = ?, password = ? WHERE id = ?";
        QueryRunner runner = new QueryRunner(DataSourceHelper.getDataSource());
        try {
            runner.update(sql, time + "abcname", time + "abc", time + "abc@gmail.com", time + "123456", 4);
            Logger.info("更新用户信息成功！");
        } catch (SQLException e) {
            Logger.error(e, "更新用户信息失败！");
        }
    }

    /**
     * 更新用户信息2,获取连接并手动管理事务.
     */
    private static void updateUser2() {
        Connection conn = DataSourceHelper.getConnection();
        if (conn == null) {
            Logger.warn("获取的数据源连接失败！");
            return;
        }

        long time = System.currentTimeMillis();
        String sql = "UPDATE user SET name = ?, nickname = ?, email = ?, password = ? WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            conn.setAutoCommit(false);
            runner.update(conn, sql, time + "abcname", time + "abc", time + "abc@gmail.com", time + "123456", 4);
            conn.commit();
            Logger.info("更新用户信息2成功！");
        } catch (SQLException e) {
            JdbcDaoHelper.rollback(conn);
            Logger.error(e, "更新用户信息2失败！");
        } finally {
            JdbcDaoHelper.close(conn);
        }
    }

    /**
     * 删除用户信息.
     */
    private static void deleteUser() {
        String sql = "DELETE FROM user WHERE id = ?";
        QueryRunner runner = new QueryRunner(DataSourceHelper.getDataSource());
        try {
            runner.update(sql, 7);
            Logger.info("删除用户信息成功!");
        } catch (SQLException e) {
            Logger.error(e, "删除user信息出错！");
        }
    }

    /**
     * 查询用户信息.
     */
    private static void queryUsers() {
        String sql = "SELECT * FROM user AS u WHERE u.age > ? AND u.nickname LIKE ?";
        QueryRunner runner = new QueryRunner(DataSourceHelper.getDataSource());
        try {
            List<Map<String, Object>> resultMaps = runner.query(sql, new MapListHandler(), 19, "%三%");
            Logger.info("查询用户信息成功!, resultMaps:{}", resultMaps);
        } catch (SQLException e) {
            Logger.error(e, "查询user信息出错！");
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // insertUser();
        // updateUser();
        updateUser2();
        // deleteUser();
        queryUsers();
    }

}
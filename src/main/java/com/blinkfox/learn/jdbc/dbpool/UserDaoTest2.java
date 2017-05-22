package com.blinkfox.learn.jdbc.dbpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
            Logger.info("删除用户信息成功!");
        } catch (SQLException e) {
            Logger.error(e, "删除user信息出错！");
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // insertUser();
        updateUser();
        // deleteUser();
        queryUsers();
    }

}
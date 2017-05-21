package com.blinkfox.learn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pmw.tinylog.Logger;

/**
 * UserDao.
 * Created by blinkfox on 2017/5/21.
 */
public class UserDao {

    /**
     * 插入用户信息.
     */
    private static void insertUser() {
        Connection conn = DaoFactory.getConnection();
        if (conn == null) {
            return;
        }

        long time = System.currentTimeMillis();
        String sql = "INSERT INTO user(name, nickname, email, password) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,  time + "abcname");
            ps.setString(2, time + "abc");
            ps.setString(3, time + "abc@gmail.com");
            ps.setString(4, time + "123456");
            ps.executeUpdate();
            Logger.info("插入用户信息成功!");
        } catch (SQLException e) {
            Logger.error(e, "执行插入user信息出错！");
        } finally {
            DaoFactory.close(ps);
            DaoFactory.close(conn);
        }
    }

    /**
     * 更新用户信息.
     */
    private static void updateUser() {
        Connection conn = DaoFactory.getConnection();
        if (conn == null) {
            return;
        }

        long time = System.currentTimeMillis();
        String sql = "UPDATE user SET name = ?, nickname = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,  time + "abcname");
            ps.setString(2, time + "abc");
            ps.setString(3, time + "abc@gmail.com");
            ps.setString(4, time + "123456");
            ps.setInt(5, 4);
            ps.executeUpdate();
            Logger.info("更新用户信息成功!");
        } catch (SQLException e) {
            Logger.error(e, "执行更新user信息出错！");
        } finally {
            DaoFactory.close(ps);
            DaoFactory.close(conn);
        }
    }

    /**
     * 删除用户信息.
     */
    private static void deleteUser() {
        Connection conn = DaoFactory.getConnection();
        if (conn == null) {
            return;
        }

        String sql = "DELETE FROM user WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 5);
            ps.executeUpdate();
            Logger.info("删除用户信息成功!");
        } catch (SQLException e) {
            Logger.error(e, "删除更新user信息出错！");
        } finally {
            DaoFactory.close(ps);
            DaoFactory.close(conn);
        }
    }

    /**
     * 删除用户信息.
     */
    private static void queryUsers() {
        Connection conn = DaoFactory.getConnection();
        if (conn == null) {
            return;
        }

        String sql = "SELECT * FROM user AS u WHERE u.age > ? AND u.nickname LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 19);
            ps.setString(2, "%三%");
            rs = ps.executeQuery();

            // 将得到的结果集转换成maps
            List<Map<String, Object>> resultMaps = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", rs.getInt(1));
                map.put("name", rs.getString(2));
                map.put("nickname", rs.getString(3));
                map.put("email", rs.getString(4));
                map.put("password", rs.getString(5));
                resultMaps.add(map);
            }
            Logger.info("查询用户信息成功!, resultMaps:{}", resultMaps);
        } catch (SQLException e) {
            Logger.error(e, "查询user信息出错！");
        } finally {
            DaoFactory.close(rs);
            DaoFactory.close(ps);
            DaoFactory.close(conn);
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // insertUser();
        updateUser();
        //deleteUser();
        queryUsers();
    }

}
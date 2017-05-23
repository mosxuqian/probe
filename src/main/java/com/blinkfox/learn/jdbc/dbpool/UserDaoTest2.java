package com.blinkfox.learn.jdbc.dbpool;

import com.blinkfox.learn.jdbc.JdbcDaoHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
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
     * 查询用户信息2.
     */
    private static void queryUsers2() {
        ResultSetHandler<List<Object[]>> handler = new ResultSetHandler<List<Object[]>>() {
            @Override
            public List<Object[]> handle(ResultSet rs) throws SQLException {
                List<Object[]> results = new ArrayList<>();
                while (rs.next()) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int cols = metaData.getColumnCount();
                    Object[] objArr = new Object[cols];
                    for (int i = 0; i < cols; i++) {
                        objArr[i] = rs.getObject(i + 1);
                    }
                    results.add(objArr);
                }

                return results;
            }
        };

        String sql = "SELECT * FROM user AS u WHERE u.age > ?";
        QueryRunner runner = new QueryRunner(DataSourceHelper.getDataSource());
        try {
            List<Object[]> resultArrs = runner.query(sql, handler, 19);
            Logger.info("查询用户信息2成功!, resultMaps:{}", resultArrs);
        } catch (SQLException e) {
            Logger.error(e, "查询user信息2出错！");
        }
    }

    /**
     * 测试使用AsyncQueryRunner来异步查询用户信息.
     */
    private static void queryUsers3() {
        //创建一个线程池
        ExecutorService executorService = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000),
                new ThreadPoolExecutor.CallerRunsPolicy());
        AsyncQueryRunner asyncRunner = new AsyncQueryRunner(executorService,
                new QueryRunner(DataSourceHelper.getDataSource()));

        long start = System.currentTimeMillis();
        List<Map<String, Object>> resultMaps = null;
        // 没查询一条数据延时5秒钟
        String sql = "SELECT SLEEP(5), u.* FROM user AS u WHERE u.age > ?";
        try {
            Future<List<Map<String, Object>>> callback = asyncRunner.query(sql, new MapListHandler(), 19);
            Logger.info("得到callback耗时：{} ms", (System.currentTimeMillis() - start));

            long start2 = System.currentTimeMillis();
            resultMaps = callback.get(); // 这一步是同步的耗时的操作
            Logger.info("从callback中获取数据耗时：{} ms", (System.currentTimeMillis() - start2));
        } catch (Exception e) {
            Logger.error(e, "查询user信息3出错！");
        }
        Logger.info("查询用户信息3成功!, resultMaps:{},\n最终耗时：{} ms",
                resultMaps, (System.currentTimeMillis() - start));
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // insertUser();
        // updateUser();
        // updateUser2();
        // deleteUser();
        // queryUsers2();
        queryUsers3();
    }

}
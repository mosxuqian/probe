package com.blinkfox.learn.jdbc.sq2o;

import com.blinkfox.learn.jdbc.dbpool.DataSourceHelper;

import java.util.List;
import java.util.Map;
import org.pmw.tinylog.Logger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * UserTest2.
 * Created by blinkfox on 2017/5/25.
 */
public class UserTest2 {

    /**
     * 查询用户信息.
     */
    private static void queryUsers() {
        String sql = "SELECT * FROM user AS u WHERE u.age > :age";
        Sql2o sql2o = new Sql2o(DataSourceHelper.getDataSource());
        try (Connection conn = sql2o.open()) {
            List<Map<String, Object>> resultMaps = conn.createQuery(sql)
                    .addParameter("age", 19)
                    .executeAndFetchTable().asList();
            Logger.info("查询用户信息成功!, resultMaps:{}", resultMaps);
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        queryUsers();
    }

}
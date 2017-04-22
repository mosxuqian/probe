package com.blinkfox.test.zealot;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.ZealotKhala;
import java.util.Arrays;
import org.junit.Test;
import org.pmw.tinylog.Logger;

/**
 * 测试ZealotKhala的使用.
 * Created by blinkfox on 2017-04-20.
 */
public class ZealotKhalaTest {

    /**
     * 测试查询的sql.
     */
    @Test
    public void testSelectSql() {
        String userName = "zhang";
        String startBirthday = "1990-03-25";
        String endBirthday = "2010-08-28";
        Integer[] sexs = new Integer[]{0, 1};

        SqlInfo sqlInfo = ZealotKhala.start()
                .select("u.id, u.name, u.email, d.birthday, d.address")
                .from("user AS u")
                .leftJoin("user_detail AS d").on("u.id = d.user_id")
                .where("u.id != ''")
                .andLike("u.name", userName, userName != null)
                .doAnything(true, (join, params) -> {
                    join.append("abc111");
                    params.add(5);
                    Logger.info("执行了自定义操作，可任意拼接字符串和有序参数...");
                })
                .andMoreThan("u.age", 21)
                .andLessThan("u.age", 13)
                .andMoreEqual("d.birthday", startBirthday)
                .andLessEqual("d.birthday", endBirthday)
                .andBetween("d.birthday", startBirthday, endBirthday)
                .andIn("u.sex", sexs)
                .orderBy("d.birthday").desc()
                .end();
        String sql = sqlInfo.getSql();
        Object[] arr = sqlInfo.getParamsArr();

        // 断言并输出sql信息
        assertEquals("SELECT u.id, u.name, u.email, d.birthday, d.address FROM user AS u "
                + "LEFT JOIN user_detail AS d ON u.id = d.user_id WHERE u.id != '' AND u.name LIKE ? "
                + "abc111 AND u.age > ? AND u.age < ? AND d.birthday >= ? AND d.birthday <= ? "
                + "AND d.birthday BETWEEN ? AND ? AND u.sex in (?, ?) ORDER BY d.birthday DESC", sql);
        assertArrayEquals(new Object[]{"%zhang%", 5, 21, 13, "1990-03-25", "2010-08-28",
                "1990-03-25", "2010-08-28", 0, 1} ,arr);
        Logger.info("-- testSql()方法生成的sql信息:\n" + sql + "\n-- 参数为:" + Arrays.toString(arr));
    }

}
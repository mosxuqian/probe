package com.blinkfox.test;

import com.blinkfox.zealot.core.Khala;

/**
 * Zealot.jar中的Khala测试类
 * Created by blinkfox on 2016/11/12.
 */
public class KhalaTest {

    public static void main(String[] args) {
        String sql = Khala.getInstance().start()
                .select("u.id, u.name, u.email, ud.addr")
                .from("user as u")
                .leftJoin("user_detail as ud").on("u.id = ud.user_id")
                .where("u.name like ?").and("u.email like ?")
                .groupBy("u.id desc")
                .end();
        System.out.println("拼接的sql为:" + sql);
    }

}
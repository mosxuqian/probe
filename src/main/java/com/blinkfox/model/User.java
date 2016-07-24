package com.blinkfox.model;

import com.jfinal.plugin.activerecord.Model;
import java.util.List;

/**
 * 用户信息的Model类
 * Created by blinkfox on 16/7/24.
 */
public class User extends Model<User> {

    public static final User userDao = new User();

    /**
     * 根据用户邮箱查询出该用户信息
     * @param email
     * @return
     */
    public User queryUserByEmail(String email) {
        List<User> users = userDao.find("select * from user where email = ?", email);
        return (users != null && users.size() > 0) ? users.get(0) : null;
    }

}
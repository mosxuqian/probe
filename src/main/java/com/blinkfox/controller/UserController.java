package com.blinkfox.controller;

import com.alibaba.druid.util.StringUtils;
import com.blinkfox.model.User;
import com.jfinal.core.Controller;
import java.util.List;

/**
 * 用户信息相关的控制器
 * Created by blinkfox on 16/7/24.
 */
public class UserController extends Controller {

    public void index() {
        renderText("Hello UserController!");
    }

    /**
     * 根据用户邮箱获取用户信息
     */
    public void getUserByEmail() {
        String email = getPara("email");
        System.out.println("--------user email param:" + email);
        if (StringUtils.isEmpty(email)) {
            renderText("未获取到用户邮箱参数信息!");
            return;
        }

        User user = User.userDao.queryUserByEmail(email);
        if (user == null) {
            System.out.println("---------------未获取到用户信息");
            renderText("未获取到用户信息");
            return;
        }

        renderJson(user);
    }

    /**
     * 获取所有用户信息
     */
    public void getAllUsers() {
        List<User> users = User.userDao.queryAllUsers();
        setAttr("users", users);
        render("/app/test/users.html");
    }

    /**
     * 下载文件的方法
     */
    public void testFile() {
        renderFile("/doc/blinkfox.txt");
    }

    public void testNull() {
        renderNull();
    }

}
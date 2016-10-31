package com.blinkfox.controller;

import com.alibaba.druid.util.StringUtils;
import com.blinkfox.interceptor.AaaInter;
import com.blinkfox.interceptor.BbbInter;
import com.blinkfox.interceptor.DemoInterceptor;
import com.blinkfox.model.User;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.Zealot;
import com.blinkfox.zealot.demo.DemoZealotConfig;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息相关的控制器
 * Created by blinkfox on 16/7/24.
 */
@Before({DemoInterceptor.class, AaaInter.class})
public class UserController extends Controller {

    @Clear()
    @Before({BbbInter.class})
    public void index() {
        System.out.println("Hello UserController");
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
    @Clear({AaaInter.class})
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

    /**
     *
     */
    public void userRecord() {
        Map<String, Object> map = new HashMap<String, Object>();

        User.userDao.findById(2).set("name", "lisi").set("nick_name", "李思").update();

        User user1 = User.userDao.findByIdLoadColumns(2, "name, nick_name, email, age");
        String nickName = user1.getStr("nick_name");
        int age = user1.getInt("age");

        Page<User> users = User.userDao.paginate(1, 3, "select *", "from user where age > ?", 15);

        map.put("user1", user1);
        map.put("nickName", nickName);
        map.put("age", age);
        map.put("users", users);

        renderJson(map);
    }

    public void userZealot() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nickName", "张");
        paramMap.put("email", "san");
        paramMap.put("startAge", 23);
        paramMap.put("endAge", 28);
        paramMap.put("startBirthday", "1990-01-01 00:00:00");
        paramMap.put("endBirthday", "1991-01-01 23:59:59");
        paramMap.put("sexs", new Integer[]{0, 1});
        SqlInfo sqlInfo = Zealot.getSqlInfo(DemoZealotConfig.USER_SPACE, "queryUserInfo", paramMap);
        String sql = sqlInfo.getSql();
        Object[] params = sqlInfo.getParamsArr();

        List<User> users = User.userDao.find(sql, params);
        renderJson(users);
    }

}
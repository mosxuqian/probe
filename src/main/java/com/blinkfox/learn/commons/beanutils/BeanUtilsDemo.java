package com.blinkfox.learn.commons.beanutils;

import com.blinkfox.bean.UserInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.pmw.tinylog.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * BeanUtilsDemo.
 * Created by blinkfox on 2017/6/14.
 */
public class BeanUtilsDemo {

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        List<Map<String, Object>> userMaps = new ArrayList<Map<String, Object>>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            setUserInfoProperty(userInfos);
            // setUserInfo(userInfos);
            // setUserInfoByMap(userMaps);
        }
        Logger.info("BeanUtils动态设置属性值耗时:{} ms", (System.currentTimeMillis() - start));
    }

    private static void setUserInfoProperty(List<UserInfo> userInfos) throws InvocationTargetException, IllegalAccessException {
        UserInfo userInfo = new UserInfo();
        BeanUtils.setProperty(userInfo, "id", "1");
        BeanUtils.setProperty(userInfo, "name", "zhangsan");
        BeanUtils.setProperty(userInfo, "nickname", "张三");
        BeanUtils.setProperty(userInfo, "email", "zhansan@163.com");
        BeanUtils.setProperty(userInfo, "sex", 1);
        BeanUtils.setProperty(userInfo, "birthday", new Date());
        userInfos.add(userInfo);
    }

    private static void setUserInfo(List<UserInfo> userInfos) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("2");
        userInfo.setName("lisi");
        userInfo.setNickname("李四");
        userInfo.setEmail("lisi@163.com");
        userInfo.setSex(1);
        userInfo.setBirthday(new Date());
        userInfos.add(userInfo);
    }

    private static void setUserInfoByMap(List<Map<String, Object>> userMaps) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("id", "3");
        userMap.put("name", "wangwu");
        userMap.put("nickname", "王五");
        userMap.put("wangwu", "wangwu@163.com");
        userMap.put("sex", 1);
        userMap.put("birthday", new Date());
        userMaps.add(userMap);
    }

}
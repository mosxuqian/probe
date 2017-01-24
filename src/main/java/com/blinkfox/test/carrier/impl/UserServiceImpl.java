package com.blinkfox.test.carrier.impl;

import com.blinkfox.carrier.annotation.Injection;
import com.blinkfox.carrier.annotation.Provider;
import com.blinkfox.model.User;
import com.blinkfox.test.carrier.IUserDao;
import com.blinkfox.test.carrier.IUserService;

/**
 * Created by blinkfox on 2017/1/25.
 */
@Provider("userService")
public class UserServiceImpl implements IUserService {

    @Injection
    private IUserDao userDao;

    /**
     * 根据用户ID查询某用户信息
     * @param userId
     * @return
     */
    @Override
    public User findUserById(String userId) {
        return userDao.queryUserById(userId);
    }

}
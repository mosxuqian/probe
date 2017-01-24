package com.blinkfox.test.carrier;

import com.blinkfox.model.User;

/**
 * Created by blinkfox on 2017/1/25.
 */
public interface IUserService {

    /**
     * 根据用户ID查询某用户信息
     * @param userId
     * @return
     */
    User findUserById(String userId);

}
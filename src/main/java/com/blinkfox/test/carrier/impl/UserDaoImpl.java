package com.blinkfox.test.carrier.impl;

import com.blinkfox.carrier.annotation.Provider;
import com.blinkfox.config.MyZealotConfig;
import com.blinkfox.model.User;
import com.blinkfox.test.carrier.IUserDao;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.Zealot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blinkfox on 2017/1/25.
 */
@Provider("userDao")
public class UserDaoImpl implements IUserDao {

    /**
     * 根据用户ID查询某用户信息
     * @param userId
     * @return
     */
    @Override
    public User queryUserById(String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", userId);
        SqlInfo sqlInfo = Zealot.getSqlInfo(MyZealotConfig.USER_ZEALOT, "queryUserById", paramMap);
        List<User> users = User.userDao.find(sqlInfo.getSql(), sqlInfo.getParamsArr());
        return (users != null && users.size() > 0) ? users.get(0) : null;
    }

}
package com.blinkfox.config;

import com.blinkfox.handler.UserIdEmailHandler;
import com.blinkfox.zealot.config.AbstractZealotConfig;
import com.blinkfox.zealot.config.entity.NormalConfig;
import com.blinkfox.zealot.config.entity.XmlContext;

/**
 * 我继承的zealotConfig配置类
 * Created by blinkfox on 2016/11/4.
 */
public class MyZealotConfig extends AbstractZealotConfig {

    public static final String USER_ZEALOT = "user_zealot";

    @Override
    public void configNormal(NormalConfig normalConfig) {
        normalConfig.setPrintSqlInfo(true);
    }

    @Override
    public void configXml(XmlContext ctx) {
        ctx.add(USER_ZEALOT, "/zealot/zealot-user.xml");
    }

    @Override
    public void configTagHandler() {
        // 自定义userIdEmail标签和处理器
        add("userIdEmail", UserIdEmailHandler.class);
        // 有and前缀的自定义标签
        add("andUserIdEmail", " and " ,UserIdEmailHandler.class);
    }

}
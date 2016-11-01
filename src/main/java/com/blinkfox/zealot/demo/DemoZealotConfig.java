package com.blinkfox.zealot.demo;

import com.blinkfox.zealot.bean.XmlContext;
import com.blinkfox.zealot.config.ZealotConfig;
import com.blinkfox.zealot.core.concrete.LikeHandler;

/**
 * Zealot配置类的demo示例配置
 * Created by blinkfox on 2016/10/30.
 */
public class DemoZealotConfig extends ZealotConfig {

    public static final String MY_TEST = "my_test";
    public static final String USER_SPACE = "user_space";

    /**
     * 配置xml文件的标识和资源路径
     * @param ctx
     */
    @Override
    public void configXml(XmlContext ctx) {
        ctx.add(MY_TEST, "/zealot/mytest.xml");
        ctx.add(USER_SPACE, "/zealot/zealot-user.xml");
    }

    /**
     * 配置标签和其对应的处理类
     */
    @Override
    public void configTagHandler() {
        add("myLike", LikeHandler.class);
        add("andMyLike", " and " ,LikeHandler.class);
    }

}
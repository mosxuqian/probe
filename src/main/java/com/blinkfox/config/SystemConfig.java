package com.blinkfox.config;

import com.blinkfox.controller.IndexController;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;

/**
 * JFinal的配置类
 * Created by blinkfox on 16/7/24.
 */
public class SystemConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        // 设置异常错误转向页面
        me.setError401View("/app/common/401.html");
        me.setError403View("/app/common/403.html");
        me.setError404View("/app/common/404.html");
        me.setError500View("/app/common/500.html");
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {
        // 配置项目基础路径
        me.add(new ContextPathHandler("base"));
    }

}
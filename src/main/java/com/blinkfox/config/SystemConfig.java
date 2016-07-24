package com.blinkfox.config;

import com.blinkfox.controller.IndexController;
import com.blinkfox.controller.UserController;
import com.blinkfox.model.User;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * JFinal的配置类
 * Created by blinkfox on 16/7/24.
 */
public class SystemConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        // 加载配置文件
        loadPropertyFile("/props/config.properties");
        me.setDevMode(getPropertyToBoolean("devMode", false));

        // 设置异常错误转向页面
        me.setError401View("/app/common/401.html");
        me.setError403View("/app/common/403.html");
        me.setError404View("/app/common/404.html");
        me.setError500View("/app/common/500.html");
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/user", UserController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        // 配置Druid数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("url"), getProperty("username"),
                getProperty("password"), getProperty("driver"));
        me.add(druidPlugin);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        me.add(arp);

        // 映射数据库表为user，id是主键(默认为'id'可不写)，实体类User
        arp.addMapping("user", "id", User.class);
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
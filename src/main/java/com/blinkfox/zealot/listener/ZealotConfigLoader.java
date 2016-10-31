package com.blinkfox.zealot.listener;

import com.blinkfox.zealot.config.ZealotConfig;
import com.blinkfox.zealot.helpers.Dom4jHelper;
import org.dom4j.Document;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Iterator;
import java.util.Map;

/**
 * Zealot配置的servlet监听器的初始化加载类
 * Created by blinkfox on 2016/10/30.
 */
public class ZealotConfigLoader implements ServletContextListener {

    // zealot配置类对象
    private ZealotConfig zealotConfig;

    // zealotConfig对应的类全路径常量字符串
    private static final String CONFIG_CLASS = "zealotConfigClass";

    /**
     * ZealotConfig销毁时执行的方法
     * @param arg0
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    /**
     * 应用服务器启动时执行
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("----------Zealot应用程序启动了contextInitialized");
        createZealotConfig(event);
        Map<String, String> mappers = ZealotConfig.getContext();

        // 遍历每个zealot配置文件，将其文档缓存到内存缓存中
        for (Iterator<Map.Entry<String, String>> it = mappers.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("-----key:" + key + ",value:" + value);
            Document document = Dom4jHelper.getDocument(value);
            if (document != null) {
                ZealotConfig.getZealots().put(key, document);
            }
        }
    }

    /**
     * 初始化zealotConfig的之类，并执行初始化mapper到缓存中
     * @param event
     */
    private void createZealotConfig(ServletContextEvent event) {
        String configClass = event.getServletContext().getInitParameter(CONFIG_CLASS);
        System.out.println("----------启动得到的参数name:" + configClass);
        if (configClass == null) {
            throw new RuntimeException("请在web.xml设置zealotConfigClass参数");
        }

        Object temp = null;
        try {
            temp = Class.forName(configClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("不能创建zealotConfig实例: " + configClass, e);
        }

        if (temp instanceof ZealotConfig) {
            zealotConfig = (ZealotConfig) temp;
            zealotConfig.config();
        }
    }

}
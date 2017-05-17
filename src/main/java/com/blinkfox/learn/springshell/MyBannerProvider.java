package com.blinkfox.learn.springshell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * MyBannerProvider.
 * Created by blinkfox on 2017/5/18.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBannerProvider extends DefaultBannerProvider {

    /**
     * 获取欢迎banner.
     * @return 欢迎信息字符串
     */
    @Override
    public String getBanner() {
        StringBuilder sb = new StringBuilder()
                .append("=======================================").append(OsUtils.LINE_SEPARATOR)
                .append("*                                     *").append(OsUtils.LINE_SEPARATOR)
                .append("*            HelloWorld               *").append(OsUtils.LINE_SEPARATOR)
                .append("*                                     *").append(OsUtils.LINE_SEPARATOR)
                .append("=======================================").append(OsUtils.LINE_SEPARATOR)
                .append("Version:").append(this.getVersion());
        return sb.toString();
    }

    /**
     * 获取版本.
     * @return 版本
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * 获取欢迎信息.
     * @return 字符串
     */
    @Override
    public String getWelcomeMessage() {
        return "欢迎访问Hello World CLI!";
    }

    /**
     * 获取提供者的名字.
     * @return 名称
     */
    @Override
    public String getProviderName() {
        return "Hello World Banner!";
    }

}
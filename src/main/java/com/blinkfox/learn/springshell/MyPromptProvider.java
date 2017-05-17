package com.blinkfox.learn.springshell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * MyPromptProvider.
 * Created by blinkfox on 2017/5/18.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyPromptProvider extends DefaultPromptProvider {

    /**
     * 获取提示符.
     * @return 提示符
     */
    @Override
    public String getPrompt() {
        return "hello>";
    }

    /**
     * 获取提供名.
     * @return 字符串
     */
    @Override
    public String getProviderName() {
        return "my prompt provider";
    }

}
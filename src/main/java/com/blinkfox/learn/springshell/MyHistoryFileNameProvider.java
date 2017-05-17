package com.blinkfox.learn.springshell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.stereotype.Component;

/**
 * MyHistoryFileNameProvider.
 * Created by blinkfox on 2017/5/18.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyHistoryFileNameProvider extends DefaultHistoryFileNameProvider {

    /**
     * 获取历史记录.
     * @return 历史记录日志名称
     */
    @Override
    public String getHistoryFileName() {
        return "my.log";
    }

    /**
     * 获取提供名.
     * @return 字符串
     */
    @Override
    public String getProviderName() {
        return "my history file name provider";
    }

}
package com.blinkfox.learn.springshell;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * HelloWorldCommands.
 * Created by blinkfox on 2017/5/17.
 */
@Component
public class HelloWorldCommands implements CommandMarker {

    private boolean simpleCommandExecuted = false;

    /**
     * isSimpleAvailable.
     * @return boolean
     */
    @CliAvailabilityIndicator({"hw simple"})
    private boolean isSimpleAvailable() {
        return true;
    }

    /**
     * isComplexAvailable.
     * @return  boolean
     */
    @CliAvailabilityIndicator({"hw complex", "hw enum"})
    public boolean isComplexAvailable() {
        return simpleCommandExecuted;
    }

    /**
     * 简单的输出hello world信息.
     * @return str
     */
    @CliCommand(value = "hw simple", help = "打印一个简单的Hello World信息...")
    public String simple(
            @CliOption(key = {"message"}, mandatory = true, help = "Hello World信息") final String message,
            @CliOption(key = {"location"}, help = "你在哪里说的Hello", specifiedDefaultValue = "学校",
                    unspecifiedDefaultValue = "公司") final String location) {
        simpleCommandExecuted = true;
        return new StringBuilder("消息是：【").append(message).append("】，位置是：【")
                .append(location).append("】").toString();
    }

}
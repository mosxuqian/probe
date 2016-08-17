package com.blinkfox.patterns.command;

/**
 * 调用者 Invoker 类
 * Created by blinkfox on 16/8/17.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void action() {
        this.command.execute();
    }
}
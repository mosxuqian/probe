package com.blinkfox.patterns.command;

/**
 * 具体的 Command 命令类2
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteCommand2 extends Command {

    // 对哪个receiver类进行处理
    private Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 必须实现的命令
     */
    @Override
    public void execute() {

    }

}
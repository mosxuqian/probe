package com.blinkfox.patterns.command;

/**
 * 具体的 Command 命令类1
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteCommand1 extends Command {

    // 对哪个receiver类进行处理
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 必须实现的一个命令
     */
    @Override
    public void execute() {
        this.receiver.doSomething();
    }

}
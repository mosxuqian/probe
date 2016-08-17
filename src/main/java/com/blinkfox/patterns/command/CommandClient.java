package com.blinkfox.patterns.command;

/**
 * 命令模式的场景类
 * Created by blinkfox on 16/8/17.
 */
public class CommandClient {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Receiver receiver = new ConcreteReceiver1();
        Command command = new ConcreteCommand1(receiver);

        // 把命令交给调用者执行
        invoker.setCommand(command);
        invoker.action();
    }

}
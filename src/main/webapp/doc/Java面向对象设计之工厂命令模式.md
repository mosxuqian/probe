# Java面向对象设计之工厂命令模式

标签： Java 设计模式

---

### 一、模式动机

在软件设计中，我们经常需要向某些对象发送请求，但是并不知道请求的接收者是谁，也不知道被请求的操作是哪个，我们只需在程序运行时指定具体的请求接收者即可，此时，可以使用命令模式来进行设计，使得请求发送者与请求接收者消除彼此之间的耦合，让对象之间的调用关系更加灵活。

命令模式可以对发送者和接收者完全解耦，发送者与接收者之间没有直接引用关系，发送请求的对象只需要知道如何发送请求，而不必知道如何完成请求。这就是命令模式的模式动机。

### 二、模式定义

命令模式(`Command Pattern`)：将一个请求封装为一个对象，从而使我们可用不同的请求对客户进行参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。命令模式是一种对象行为型模式，其别名为动作(`Action`)模式或事务(`Transaction`)模式。

### 三、模式结构

#### 1. 角色组成

命令模式包含如下角色：

- Command: 抽象命令类
- ConcreteCommand: 具体命令类
- Invoker: 调用者
- Receiver: 接收者
- Client: 客户类

#### 2. 结构图

![命令模式结构图][1]

[1]: http://static.blinkfox.com/Command.jpg

### 四、示例代码

首先，是抽象的 Receiver 类和具体的 Receiver 类：

```java
/**
 * 通用的抽象 Receiver 接收者
 * Created by blinkfox on 16/8/17.
 */
public abstract class Receiver {

    /**
     * 定义每个接收者都必须完成的业务
     */
    public abstract void doSomething();

}
```

```java
/**
 * 具体的 Receiver 类1
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteReceiver1 extends Receiver {

    @Override
    public void doSomething() {
        System.out.println("ConcreteReceiver1 处理的业务逻辑...");
    }

}
```

```java
/**
 * 具体的 Receiver 类2
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteReceiver2 extends Receiver {

    @Override
    public void doSomething() {
        System.out.println("ConcreteReceiver2 处理的业务逻辑...");
    }

}
```

其实，是抽象的 Command 类和具体的 Command 类：

```java
/**
 * 抽象的 Command 类
 * Created by blinkfox on 16/8/17.
 */
public abstract class Command {

    /**
     * 命令的抽象执行命令的方法
     */
    public abstract void execute();

}
```

```java
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
```

```java
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
```

最后，调用者 Invoker 类：

```java
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
```

以下是命令模式的客户端场景类：

```java
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
```
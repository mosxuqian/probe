# Java面向对象设计之中介者模式

标签： Java 设计模式

---

### 一、模式动机

在用户与用户直接聊天的设计方案中，用户对象之间存在很强的关联性，将导致系统出现如下问题：

- 系统结构复杂：对象之间存在大量的相互关联和调用，若有一个对象发生变化，则需要跟踪和该对象关联的其他所有对象，并进行适当处理。
- 对象可重用性差：由于一个对象和其他对象具有很强的关联，若没有其他对象的支持，一个对象很难被另一个系统或模块重用，这些对象表现出来更像一个不可分割的整体，职责较为混乱。
- 系统扩展性低：增加一个新的对象需要在原有相关对象上增加引用，增加新的引用关系也需要调整原有对象，系统耦合度很高，对象操作很不灵活，扩展性差。
- 在面向对象的软件设计与开发过程中，根据“单一职责原则”，我们应该尽量将对象细化，使其只负责或呈现单一的职责。
- 对于一个模块，可能由很多对象构成，而且这些对象之间可能存在相互的引用，为了减少对象两两之间复杂的引用关系，使之成为一个松耦合的系统，我们需要使用中介者模式，这就是中介者模式的模式动机。

### 二、模式定义

中介者模式(`Mediator Pattern`)定义：用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。中介者模式又称为调停者模式，它是一种对象行为型模式。

### 三、模式结构

#### 1. 角色组成

中介者模式包含如下角色：

- Mediator: 抽象中介者
- ConcreteMediator: 具体中介者
- Colleague: 抽象同事类
- ConcreteColleague: 具体同事类

#### 2. 结构图

![命令模式结构图][1]

[1]: http://static.blinkfox.com/Mediator.jpg

### 四、示例代码

首先，是抽象的 Mediator 类和具体的 Receiver 类：

```java
/**
 * 通用抽象中介者类
 * Created by blinkfox on 16/8/21.
 */
public abstract class Mediator {

    // 定义同事类1
    protected ConcreteColleague1 colleague1;

    // 定义同事类2
    protected ConcreteColleague2 colleague2;

    /* getter 和 setter 方法 */
    public ConcreteColleague1 getColleague1() {
        return colleague1;
    }

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public ConcreteColleague2 getColleague2() {
        return colleague2;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    /**
     * 中介者模式的抽象业务逻辑1
     */
    public abstract void doSomething1();

    /**
     * 中介者模式的抽象业务逻辑2
     */
    public abstract void doSomething2();

}
```

```java
/**
 * 具体的通用中介者类
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteMediator extends Mediator {

    /**
     * 中介者模式的具体业务逻辑1
     */
    @Override
    public void doSomething1() {
        super.colleague1.selfMethod1();
        super.colleague2.selfMethod2();
    }

    /**
     * 中介者模式的具体业务逻辑2
     */
    @Override
    public void doSomething2() {
        super.colleague1.selfMethod1();
        super.colleague2.selfMethod2();
    }

}
```

其实，是抽象的 Colleague 类和具体的 Colleague 类：

```java
/**
 * 抽象的同事类
 * Created by blinkfox on 16/8/21.
 */
public abstract class Colleague {

    // 中介者
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

}
```

```java
/**
 * 具体的同事类1
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    /**
     * 自有方法
     */
    public void selfMethod1() {
        System.out.println("------ConcreteColleague1-处理自己的业务逻辑1--------");
    }

    /**
     * 依赖方法
     */
    public void depMethod1() {
        System.out.println("------ConcreteColleague1-委托给中介者的业务逻辑1--------");
        super.mediator.doSomething1();
    }

}
```

```java
/**
 * 具体的同事类2
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    /**
     * 自有方法2
     */
    public void selfMethod2() {
        System.out.println("------ConcreteColleague2-处理自己的业务逻辑2--------");
    }

    /**
     * 依赖方法2
     */
    public void depMethod2() {
        System.out.println("------ConcreteColleague2-委托给中介者的业务逻辑2--------");
        super.mediator.doSomething2();
    }

}
```

以下是中介者模式的客户端场景类：

```java
/**
 * 中介者模式的场景类
 * Created by blinkfox on 16/8/21.
 */
public class MediatorClient {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.depMethod1();
        colleague2.depMethod2();
        mediator.doSomething1();
        mediator.doSomething2();
    }

}
```
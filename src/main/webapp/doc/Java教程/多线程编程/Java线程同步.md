# Java线程同步

**当两个或两个以上的线程需要共享资源，它们需要某种方法来确定资源在某一刻仅被一个线程占用。达到此目的的过程叫做同步（synchronization）**。像你所看到的，Java为此提供了独特的，语言水平上的支持。

**同步的关键是管程（也叫信号量semaphore）的概念**。管程是一个互斥独占锁定的对象，或称互斥体（mutex）。在给定的时间，仅有一个线程可以获得管程。**当一个线程需要锁定，它必须进入管程**。所有其他的试图进入已经锁定的管程的线程必须挂起直到第一个线程退出管程。这些其他的线程被称为等待管程。一个拥有管程的线程如果愿意的话可以再次进入相同的管程。

如果你用其他语言例如C或C++时用到过同步，你会知道它用起来有一点诡异。这是因为很多语言它们自己不支持同步。相反，对同步线程，程序必须利用操作系统资源。幸运的是Java通过语言元素实现同步，大多数的与同步相关的复杂性都被消除。

你可以用两种方法同步化代码。两者都包括`synchronized`关键字的运用，下面分别说明这两种方法。

## 使用同步方法

Java中同步是简单的，因为所有对象都有它们与之对应的隐式管程。进入某一对象的管程，就是调用被`synchronized`关键字修饰的方法。当一个线程在一个同步方法内部，所有试图调用该方法（或其他同步方法）的同实例的其他线程必须等待。为了退出管程，并放弃对对象的控制权给其他等待的线程，拥有管程的线程仅需从同步方法中返回。

为理解同步的必要性，让我们从一个应该使用同步却没有用的简单例子开始。下面的程序有三个简单类。首先是`Callme`，它有一个简单的方法`call()`。`call()`方法有一个名为`msg`的`String`参数。该方法试图在方括号内打印`msg`字符串。有趣的事是在调用`call()`打印左括号和msg字符串后，调用`Thread.sleep(1000)`，该方法使当前线程暂停`1`秒。

下一个类的构造函数`Caller`，引用了`Callme`的一个实例以及一个`String`，它们被分别存在`target`和`msg`中。构造函数也创建了一个调用该对象的`run()`方法的新线程。该线程立即启动。`Caller`类的`run()`方法通过参数`msg`字符串调用`Callme`实例`target`的`call()`方法。最后，`Synch`类由创建`Callme`的一个简单实例和`Caller`的三个具有不同消息字符串的实例开始。

Callme的同一实例传给每个Caller实例。

```java
// This program is not synchronized.
class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
       }
       System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }
    public void run() {
        target.call(msg);
    }
}

class Synch {
    public static void main(String args[]) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");
        // wait for threads to end
        try {
          ob1.t.join();
          ob2.t.join();
          ob3.t.join();
       } catch(InterruptedException e) {
          System.out.println("Interrupted");
       }
    }
}
```

该程序的输出如下：

```bash
Hello[Synchronized[World]
]
]
```

在本例中，通过调用`sleep()`，`call()`方法允许执行转换到另一个线程。该结果是三个消息字符串的混合输出。该程序中，没有阻止三个线程同时调用同一对象的同一方法。这是一种竞争，因为三个线程争着完成方法。例题用`sleep()`使该影响重复和明显。在大多数情况，竞争是更为复杂和不可预知的，因为你不能确定何时上下文转换会发生。这使程序时而运行正常时而出错。

为达到上例所想达到的目的，必须有权连续的使用`call()`。也就是说，在某一时刻，必须限制只有一个线程可以支配它。为此，你只需在`call()`定义前加上关键字`synchronized`，如下：

```java
class Callme {
    synchronized void call(String msg) {
        ...
```

这防止了在一个线程使用`call()`时其他线程进入`call()`。在`synchronized`加到`call()`前面以后，程序输出如下：

```bash
[Hello]
[Synchronized]
[World]
```

任何时候在多线程情况下，你有一个方法或多个方法操纵对象的内部状态，都必须用`synchronized`关键字来防止状态出现竞争。记住，一旦线程进入实例的同步方法，没有其他线程可以进入相同实例的同步方法。然而，该实例的其他不同步方法却仍然可以被调用。

## 同步语句

尽管在创建的类的内部创建同步方法是获得同步的简单和有效的方法，但它并非在任何时候都有效。这其中的原因，请跟着思考。假设你想获得不为多线程访问设计的类对象的同步访问，也就是，该类没有`synchronized`方法。而且，该类不是你自己，而是第三方创建的，你不能获得它的源代码。这样，你不能在相关方法前加`synchronized`修饰符。怎样才能使该类的一个对象同步化呢？很幸运，解决方法很简单：你只需将对这个类定义的方法的调用放入一个`synchronized`块内就可以了。

下面是synchronized语句的普通形式：

```java
synchronized(object) {
    // statements to be synchronized
}
```

其中，`object`是被同步对象的引用。如果你想要同步的只是一个语句，那么不需要花括号。一个同步块确保对`object`成员方法的调用仅在当前线程成功进入`object`管程后发生。

下面是前面程序的修改版本，在run( )方法内用了同步块：

```java
// This program uses a synchronized block.
class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    // synchronize calls to call()
    public void run() {
        synchronized(target) { // synchronized block
            target.call(msg);
        }
    }
}

class Synch1 {
    public static void main(String args[]) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");

        // wait for threads to end
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
```

这里，`call()`方法没有被`synchronized`修饰。而`synchronized`是在`Caller`类的`run()`方法中声明的。这可以得到上例中同样正确的结果，因为每个线程运行前都等待先前的一个线程结束。
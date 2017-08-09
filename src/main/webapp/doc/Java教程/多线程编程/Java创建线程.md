# Java创建线程

标签： Java基础教程

---

大多数情况，通过实例化一个`Thread`对象来创建一个线程。Java定义了两种方式：

- 实现 Runnable 接口
- 可以继承 Thread 类

下面依次介绍每一种方式。

## 实现Runnable接口

创建线程的最简单的方法就是创建一个实现`Runnable`接口的类。`Runnable`抽象了一个执行代码单元。你可以通过实现`Runnable`接口的方法创建每一个对象的线程。为实现`Runnable`接口，一个类仅需实现一个`run()`的简单方法，该方法声明如下：

```java
public void run()
```

在`run()`中可以定义代码来构建新的线程。理解下面内容是至关重要的：`run()`方法能够像主线程那样调用其他方法，引用其他类，声明变量。仅有的不同是`run()`在程序中确立另一个并发的线程执行入口。当`run()`返回时，该线程结束。

在你已经创建了实现`Runnable`接口的类以后，你要在类内部实例化一个`Thread`类的对象。`Thread`类定义了好几种构造函数。我们会用到的如下：

```java
Thread(Runnable threadOb, String threadName)
```

该构造函数中，`threadOb`是一个实现`Runnable`接口类的实例。这定义了线程执行的起点。新线程的名称由`threadName`定义。

建立新的线程后，它并不运行直到调用了它的`start()`方法，该方法在`Thread`类中定义。本质上，`start()`执行的是一个对`run()`的调用。 `start()`方法声明如下：

```java
void start()
```

下面的例子是创建一个新的线程并启动它运行：

```java
// Create a second thread.
class NewThread implements Runnable {

    Thread t;

    NewThread() {
        // Create a new, second thread
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
        t.start(); // Start the thread
    }

    // This is the entry point for the second thread.
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

class ThreadDemo {
    public static void main(String args[]) {
        new NewThread(); // create a new thread
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
           System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}
```

在`NewThread`构造函数中，新的`Thread`对象由下面的语句创建：

```java
t = new Thread(this, "Demo Thread");
```

通过前面的语句`this`表明在`this`对象中你想要新的线程调用`run()`方法。然后，`start()`被调用，以`run()`方法为开始启动了线程的执行。这使子线`for`循环开始执行。调用`start()`之后，`NewThread`的构造函数返回到`main()`。当主线程被恢复，它到达`for循环。两个线程继续运行，共享CPU，直到它们的循环结束。该程序的输出如下：

```bash
Child thread: Thread[Demo Thread,5,main]
Main Thread: 5
Child Thread: 5
Child Thread: 4
Main Thread: 4
Child Thread: 3
Child Thread: 2
Main Thread: 3
Child Thread: 1
Exiting child thread.
Main Thread: 2
Main Thread: 1
Main thread exiting.
```

如前面提到的，在多线程程序中，通常主线程必须是结束运行的最后一个线程。实际上，一些老的`JVM`，如果主线程先于子线程结束，Java的运行时间系统就可能“挂起”。前述程序保证了主线程最后结束，因为主线程沉睡周期1000毫秒，而子线程仅为500毫秒。这就使子线程在主线程结束之前先结束。简而言之，你将看到等待线程结束的更好途径。

## 继承Thread类

创建线程的另一个途径是创建一个新类来扩展`Thread`类，然后创建该类的实例。当一个类继承`Thread`时，它必须重载`run()`方法，这个`run()`方法是新线程的入口。它也必须调用`start()`方法去启动新线程执行。下面用扩展thread类重写前面的程序：

```java
// Create a second thread by extending Thread
class NewThread extends Thread {
    NewThread() {
        // Create a new, second thread
        super("Demo Thread");
        System.out.println("Child thread: " + this);
        start(); // Start the thread
    }

    // This is the entry point for the second thread.
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

class ExtendThread {
    public static void main(String args[]) {
        new NewThread(); // create a new thread
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
           }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}
```

该程序生成和前述版本相同的输出。子线程是由实例化`NewThread`对象生成的，该对象从`Thread`类派生。注意`NewThread`中`super()`的调用。该方法调用了下列形式的`Thread`构造函数：

```java
public Thread(String threadName)
```

这里，`threadName`指定线程名称。

## 选择合适方法

到这里，你一定会奇怪为什么Java有两种创建子线程的方法，哪一种更好呢。所有的问题都归于一点。`Thread`类定义了多种方法可以被派生类重载。对于所有的方法，惟一的必须被重载的是`run()`方法。这当然是实现`Runnable`接口所需的同样的方法。很多Java程序员认为类仅在它们被加强或修改时应该被扩展。因此，如果你不重载`Thread`的其他方法时，最好只实现`Runnable`接口。这当然由你决定。然而，在本章的其他部分，我们应用实现`Runnable`接口的类来创建线程。
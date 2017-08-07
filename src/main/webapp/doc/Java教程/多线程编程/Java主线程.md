# Java主线程

当Java程序启动时，一个线程立刻运行，该线程通常叫做程序的主线程（main thread），因为它是程序开始时就执行的。主线程的重要性体现在两方面：

- 它是产生其他子线程的线程；
- 通常它必须最后完成执行，因为它执行各种关闭动作。

尽管主线程在程序启动时自动创建，但它可以由一个`Thread`对象控制。为此，你必须调用方法`currentThread()`获得它的一个引用，`currentThread()`是`Thread`类的公有的静态成员。它的通常形式如下：

```java
static Thread currentThread( )
```

该方法返回一个调用它的线程的引用。一旦你获得主线程的引用，你就可以像控制其他线程那样控制主线程。

让我们从复习下面例题开始：

```java
// Controlling the main Thread.
class CurrentThreadDemo {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t);
        // change the name of the thread
        t.setName("My Thread");
        System.out.println("After name change: " + t);

        try {
            for (int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Main thread interrupted");
        }
    }

}
```

在本程序中，当前线程（自然是主线程）的引用通过调用`currentThread()`获得，该引用保存在局部变量`t`中。然后，程序显示了线程的信息。接着程序调用`setName()`改变线程的内部名称。线程信息又被显示。然后，一个循环数从`5`开始递减，每数一次暂停一秒。暂停是由`sleep()`方法来完成的。`sleep()`语句明确规定延迟时间是`1000`毫秒。注意循环外的`try/catch`块。

`Thread`类的`sleep()`方法可能引发一个`InterruptedException`异常。这种情形会在其他线程想要打搅沉睡线程时发生。本例只是打印了它是否被打断的消息。在实际的程序中，你必须灵活处理此类问题。下面是本程序的输出：

```bash
Current thread: Thread[main,5,main]
After name change: Thread[My Thread,5,main]
5
4
3
2
1
```

注意`t`作为语句`println()`中参数运用时输出的产生。该显示顺序：线程名称，优先级以及组的名称。默认情况下，主线程的名称是`main`。它的优先级是`5`，这也是默认值，`main`也是所属线程组的名称。一个线程组（`thread group`）是一种将线程作为一个整体集合的状态控制的数据结构。这个过程由专有的运行时环境来处理，在此就不赘述了。线程名改变后，`t`又被输出。这次，显示了新的线程名。

让我们更仔细的研究程序中`Thread`类定义的方法。`sleep()`方法按照毫秒级的时间指示使线程从被调用到挂起。它的通常形式如下：

```java
static void sleep(long milliseconds) throws InterruptedException
```

挂起的时间被明确定义为毫秒。该方法可能引发`InterruptedException`异常。

`sleep()`方法还有第二种形式，显示如下，该方法允许你指定时间是以毫秒还是以纳秒为周期。

```java
static void sleep(long milliseconds, int nanoseconds) throws InterruptedException
```

第二种形式仅当允许以纳秒为时间周期时可用。如上述程序所示，你可以用`setName()`设置线程名称，用`getName()`来获得线程名称（该过程在程序中没有体现）。这些方法都是`Thread`类的成员，声明如下：

```java
final void setName(String threadName)
final String getName()
```

这里，`threadName`特指线程名称。
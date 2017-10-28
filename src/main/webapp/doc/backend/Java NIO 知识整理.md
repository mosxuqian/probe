# Java NIO 知识整理

---

## 概述

Java NIO是`java 1.4`之后新出的一套IO接口，这里的的新是相对于原有标准的Java IO和Java Networking接口。NIO提供了一种完全不同的操作方式。

> NIO中的N可以理解为Non-blocking，不单纯是New。

NIO包含下面几个核心的组件：

- Channels
- Buffers
- Selectors

整个NIO体系包含的类远远不止这几个，但是在笔者看来`Channel`, `Buffer`和`Selector`组成了这个核心的API。其他的一些组件，比如`Pipe`和`FileLock`仅仅只作为上述三个的负责类。

## Channels and Buffers

标准的IO编程接口是面向字节流和字符流的。而**NIO是面向通道和缓冲区的，数据总是从通道中读到buffer缓冲区内，或者从buffer写入到通道中**。通常来说NIO中的所有IO都是从Channel开始的。

> **Java NIO**: Channels read data into Buffers, and Buffers write data into Channels.

有很多的Channel，Buffer类型。下面列举了主要的几种：

- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel

正如你看到的，这些channel基于文件IO、UDP和TCP的网络IO。下面是核心的Buffer实现类的列表：

- ByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- intBuffer
- LongBuffer
- ShortBuffer

这些Buffer涵盖了可以通过IO操作的基础类型：`byte`, `short`, `int`, `long`, `float`, `double`以及`characters`. NIO实际上还包含一种`MappedBytesBuffer`, 一般用于和内存映射的文件。

## Non-blocking IO

Java NIO使我们可以进行非阻塞IO操作。比如说，单线程中从通道读取数据到buffer，同时可以继续做别的事情，当数据读取到buffer中后，线程再继续处理数据。写数据也是一样的。

## Selectors

NIO中有一个选择器`slectors`的概念。选择器selector可以检测多个通道的事件状态（例如：链接打开，数据到达）这样单线程就可以操作多个通道的数据。如果你的程序中有大量的链接，同时每个链接的IO带宽不高的话，这个特性将会非常有帮助。比如聊天服务器。 下面是一个单线程中Slector维护3个Channel的示意图：

![Selectors](http://tutorials.jenkov.com/images/java-nio/overview-selectors.png)

> **Java NIO**: A Thread uses a Selector to handle 3 Channel's.

要使用Selector的话，我们必须把Channel注册到Selector上，然后就可以调用Selector的select()方法。这个方法会进入阻塞，直到有一个channel的状态符合条件。

---

参考文档：

- [Java NIO 简明教程](https://java-nio.avenwu.net/)
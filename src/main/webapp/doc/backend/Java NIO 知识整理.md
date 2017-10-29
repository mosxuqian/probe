# Java NIO 知识整理

---

## 一、概述

Java NIO是`java 1.4`之后新出的一套IO接口，这里的的新是相对于原有标准的Java IO和Java Networking接口。NIO提供了一种完全不同的操作方式。

> NIO中的N可以理解为Non-blocking，不单纯是New。

NIO包含下面几个核心的组件：

- Channels
- Buffers
- Selectors

整个NIO体系包含的类远远不止这几个，但是在笔者看来`Channels`, `Buffers`和`Selectors`组成了这个核心的API。其他的一些组件，比如`Pipe`和`FileLock`仅仅只作为上述三个的负责类。

### Channels and Buffers

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
- MappedByteBuffer

这些Buffer涵盖了可以通过IO操作的基础类型：`byte`, `short`, `int`, `long`, `float`, `double`以及`characters`. NIO实际上还包含一种`MappedBytesBuffer`, 一般用于和内存映射的文件。

### Selectors

NIO中有一个选择器`slectors`的概念。选择器selector可以检测多个通道的事件状态（例如：链接打开，数据到达）这样单线程就可以操作多个通道的数据。如果你的程序中有大量的链接，同时每个链接的IO带宽不高的话，这个特性将会非常有帮助。比如聊天服务器。 下面是一个单线程中Slector维护3个Channel的示意图：

![Selectors](http://tutorials.jenkov.com/images/java-nio/overview-selectors.png)

> **Java NIO**: A Thread uses a Selector to handle 3 Channel's.

要使用Selector的话，我们必须把Channel注册到Selector上，然后就可以调用Selector的select()方法。这个方法会进入阻塞，直到有一个channel的状态符合条件。

### Non-blocking IO

Java NIO使我们可以进行非阻塞IO操作。比如说，单线程中从通道读取数据到buffer，同时可以继续做别的事情，当数据读取到buffer中后，线程再继续处理数据。写数据也是一样的。

## 二、Channel通道

Java NIO Channel通道和流非常相似，主要有以下几点区别：

- 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
- 通道可以异步读写。
- 通道总是基于缓冲区Buffer来读写。

正如上面提到的，我们可以从通道中读取数据写入到buffer；也可以中buffer内读取数据写入到通道中。

### Channel的实现

下面列出Java NIO中最重要的集中Channel的实现：

- `FileChannel`: 用于文件的数据读写。
- `DatagramChannel`: 用于UDP的数据读写。
- `SocketChannel`: 用于TCP的数据读写。
- `ServerSocketChannel`: 允许我们监听TCP链接请求，每个请求会创建会一个`SocketChannel`。

### Channel的基础示例

这有一个利用FileChannel读取数据到Buffer的例子：

```java
RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
FileChannel inChannel = aFile.getChannel();
ByteBuffer buf = ByteBuffer.allocate(48);

int bytesRead = inChannel.read(buf);
while (bytesRead != -1) {
    System.out.println("Read " + bytesRead);
    buf.flip();

    while(buf.hasRemaining()){
        System.out.print((char) buf.get());
    }

    buf.clear();
    bytesRead = inChannel.read(buf);
}
aFile.close();
```

> **注意**: `buf.flip()`的调用。首先把数据读取到Buffer中，然后调用`flip()`方法。接着再把数据读取出来。

## 三、Buffer缓冲区

Java NIO Buffers用于和NIO Channels进行交互。正如你已经知道的，我们从channel中读取数据到buffer里，从buffer把数据写入到channel中。
**buffer本质上就是一块内存区，可以用来写入数据，并在稍后读取出来**。这块内存被NIO Buffer包裹起来，对外提供一系列的读写方便开发的接口。

### Buffer基本用法

利用Buffer读写数据，通常遵循四个步骤：

- 把数据写入buffer；
- 调用`flip()`方法；
- 从Buffer中读取数据；
- 调用`buffer.clear()`或者`buffer.compact()`。

当写入数据到buffer中时，buffer会记录已经写入的数据大小。当需要读数据时，通过`flip()`方法把buffer从写模式调整为读模式；在读模式下，可以读取所有已经写入的数据。

当读取完数据后，需要清空buffer，以满足后续写入操作。清空buffer有两种方式：调用`clear()`或`compact()`方法。`clear()`会清空整个buffer，`compact()`则只清空已读取的数据，未被读取的数据会被移动到buffer的开始位置，写入位置则近跟着未读数据之后。

### Buffer的容量，位置，上限（Capacity, Position and Limit）

buffer缓冲区实质上就是一块内存，用于写入数据，也供后续再次读取数据。这块内存被NIO Buffer管理，并提供一系列的方法用于更简单的操作这块内存。
一个Buffer有三个属性是必须掌握的，分别是：

- `capacity`: 容量
- `position`: 位置
- `limit`: 限制

position和limit的具体含义取决于当前buffer的模式。capacity在两种模式下都表示容量。

下面有张示例图，描诉了不同模式下position和limit的含义：

![position和limit的含义](http://tutorials.jenkov.com/images/java-nio/buffers-modes.png)

> **Buffer capacity, position and limit in write and read mode.**

### 容量（Capacity）

作为一块内存，buffer有一个固定的大小，叫做`capacity`容量。也就是最多只能写入容量值得字节，整形等数据。一旦buffer写满了就需要清空已读数据以便下次继续写入新的数据。

### 位置（Position）

当写入数据到Buffer的时候需要从一个确定的位置开始，默认初始化时这个位置position为`0`，一旦写入了数据比如一个字节，整形数据，那么position的值就会指向数据之后的一个单元，position最大可以到`capacity-1`。

当从Buffer读取数据时，也需要从一个确定的位置开始。buffer从写入模式变为读取模式时，position会归零，每次读取后，position向后移动。

### 上限（Limit）

在写模式，limit的含义是我们所能写入的最大数据量。它等同于buffer的容量。

一旦切换到读模式，limit则代表我们所能读取的最大数据量，他的值等同于写模式下position的位置。

数据读取的上限时buffer中已有的数据，也就是limit的位置（原position所指的位置）。

### 分配一个Buffer（Allocating a Buffer）

为了获取一个Buffer对象，你必须先分配。每个Buffer实现类都有一个`allocate()`方法用于分配内存。下面看一个实例,开辟一个`48`字节大小的buffer：

```java
ByteBuffer buf = ByteBuffer.allocate(48);
```

开辟一个`1024`个字符的CharBuffer：

```java
CharBuffer buf = CharBuffer.allocate(1024);
```

### 写入数据到Buffer（Writing Data to a Buffer）

写数据到Buffer有两种方法：

- 从Channel中写数据到Buffer
- 手动写数据到Buffer，调用`put`方法

下面是一个实例，演示从Channel写数据到Buffer：

```java
int bytesRead = inChannel.read(buf); //read into buffer.
```

通过`put()`写数据：

```java
buf.put(127);
```

`put()`方法有很多不同版本，对应不同的写数据方法。例如把数据写到特定的位置，或者把一个字节数据写入buffer。看考JavaDoc文档可以查阅的更多数据。

### 翻转（flip()）

`flip()`方法可以吧Buffer从写模式切换到读模式。调用`flip()`方法会把position归零，并设置limit为之前的position的值。 也就是说，现在position代表的是读取位置，limit标示的是已写入的数据位置。

### 从Buffer读取数据（Reading Data from a Buffer）

冲Buffer读数据也有两种方式：

- 从buffer读数据到channel
- 从buffer直接读取数据，调用`get()`方法

读取数据到channel的例子：

```java
//read from buffer into channel.
int bytesWritten = inChannel.write(buf);
```

调用`get()`读取数据的例子：

```java
byte aByte = buf.get();
```

`get()`方法也有诸多版本，对应了不同的读取方式。

### rewind()

`Buffer.rewind()`方法将position置为`0`，这样我们可以重复读取buffer中的数据。limit保持不变。

### clear() and compact()

一旦我们从buffer中读取完数据，需要复用buffer为下次写数据做准备。只需要调用`clear()`或`compact()`方法。

`clear()`方法会重置position为0，limit为capacity，也就是整个Buffer清空。实际上Buffer中数据并没有清空，我们只是把标记为修改了。

如果Buffer还有一些数据没有读取完，调用`clear()`就会导致这部分数据被“遗忘”，因为我们没有标记这部分数据未读。

针对这种情况，如果需要保留未读数据，那么可以使用`compact()`。 因此`compact()`和`clear()`的区别就在于对未读数据的处理，是保留这部分数据还是一起清空。

### mark() and reset()

通过`mark()`方法可以标记当前的position，通过`reset()`方法来恢复mark的位置，这个非常像canva的save和restore：

```java
buffer.mark();
//call buffer.get() a couple of times, e.g. during parsing.
buffer.reset();  //set position back to mark.
```

### equals() and compareTo()

可以用`eqauls()`和`compareTo()`比较两个buffer。

#### equals()

使用`equals()`判断两个buffer相对，需满足：

- 类型相同
- buffer中剩余字节数相同
- 所有剩余字节相等

从上面的三个条件可以看出，`equals()`只比较buffer中的部分内容，并不会去比较每一个元素。

#### compareTo()

`compareTo()`也是比较buffer中的剩余元素，只不过这个方法适用于比较排序的。

## 四、Scatter / Gather

Java NIO发布时内置了对`scatter / gather`的支持。`scatter / gather`是通过通道读写数据的两个概念。

`Scattering read`指的是从通道读取的操作能把数据写入多个buffer，也就是sctters代表了数据从一个channel到多个buffer的过程。

`gathering write`则正好相反，表示的是从多个buffer把数据写入到一个channel中。

`Scatter / gather`在有些场景下会非常有用，比如需要处理多份分开传输的数据。举例来说，假设一个消息包含了header和body，我们可能会把header和body保存在不同独立buffer中，这种分开处理header与body的做法会使开发更简明。

### Scattering Read

"scattering read"是把数据从单个Channel写入到多个buffer，下面是示意图：

![Scattering Read](http://tutorials.jenkov.com/images/java-nio/scatter.png)

> **Java NIO: Scattering Read**

用代码来表示的话如下：

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body   = ByteBuffer.allocate(1024);

ByteBuffer[] bufferArray = { header, body };

channel.read(bufferArray);
```

观察代码可以发现，我们把多个buffer写在了一个数组中，然后把数组传递给`channel.read()`方法。`read()`方法内部会负责把数据按顺序写进传入的buffer数组内。一个buffer写满后，接着写到下一个buffer中。

实际上，`scattering read`内部必须写满一个buffer后才会向后移动到下一个buffer，因此这并不适合消息大小会动态改变的部分，也就是说，如果你有一个header和body，并且header有一个固定的大小（比如128字节）,这种情形下可以正常工作。

### Gathering Write

`gathering write`把多个buffer的数据写入到同一个channel中，下面是示意图：

![Gathering Write](http://tutorials.jenkov.com/images/java-nio/gather.png)

> **Java NIO: Gathering Write**

用代码表示的话如下：

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body   = ByteBuffer.allocate(1024);

//write data into buffers

ByteBuffer[] bufferArray = { header, body };

channel.write(bufferArray);
```

类似的传入一个buffer数组给write，内部机会按顺序将数组内的内容写进channel，这里需要注意，写入的时候针对的是buffer中position到limit之间的数据。也就是如果buffer的容量是128字节，但它只包含了58字节数据，那么写入的时候只有58字节会真正写入。因此`gathering write`是可以适用于可变大小的message的，这和`scattering read`不同。

---

参考文档：

- [Java NIO 简明教程](https://java-nio.avenwu.net/)
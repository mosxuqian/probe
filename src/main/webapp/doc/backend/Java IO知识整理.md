# Java IO知识整理

## 各IO类关系梳理

- InputStream: JavaIO中的顶级的字节输入流的抽象类，定义了最基础的输入、读取的相关方法。实现了`Closeable`接口。
  - FileInputStream: 继承自`InputStream`的文件输入流类，用于从本地文件中读取字节数据。
  - ByteArrayInputStream: 继承自`InputStream`的字节数组输入流类，它包含一个内部缓冲区，该缓冲区包含从流中读取的字节；通俗点说，它的内部缓冲区就是一个字节数组，而 ByteArrayInputStream 本质就是通过字节数组来实现的。InputStream通过`read()`向外提供接口，供它们来读取字节数据；而 ByteArrayInputStream 的内部额外的定义了一个计数器，它被用来跟踪`read()`方法要读取的下一个字节。
  - StringBufferInputStream: 继承自`InputStream`的字节输入流类，其中读取的字节由字符串的内容提供的输入流。该类已过时，不推荐使用。
  - PipedInputStream: 继承自`InputStream`的管道输入流类，在使用管道通信时，必须与 PipedOutputStream 配合使用。让多线程可以通过管道进行线程间的通讯。
  - ObjectInputStream
  - SequenceInputStream
  - FilterInputStream: 继承自`InputStream`的过滤输入流类，是用来“封装其它的输入流，并为它们提供额外的功能”。
    - BufferedInputStream
    - DataInputStream
    - PushbackInputStream
- OutputStream: JavaIO中的顶级的字节输出流的抽象类，定义了最基础的输出、写入的相关方法。实现了`Closeable`和`Flushable`接口。
  - FileOutputStream: 继承自`OutputStream`的文件输出流类，用于向本地文件中写入字节数据。
  - ByteArrayOutputStream: 继承自`OutputStream`的字节数组输出流类，ByteArrayOutputStream 中的数据会被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。
  - PipedOutputStream: 继承自`OutputStream`的管道输出流类，在使用管道通信时，必须与 PipedInputStream 配合使用。让多线程可以通过管道进行线程间的通讯。
  - ObjectOutputStream
  - FilterOutputStream: 继承自`OutputStream`的过滤输出流类，是用来“封装其它的输出流，并为它们提供额外的功能”。
    - BufferedOutputStream
    - DataOutputStream
    - PrintStream
- Reader
  - BufferedReader
  - InputStreamReader
    - FileReader
  - FilterReader
    - PushbackReader
  - StringReader
  - PipedReader
  - ByteArrayReader
- Writer
  - BufferedWriter
  - OutputStreamWriter
    - FileWriter
  - FilterWriter
  - StringWriter
  - PipedWriter
  - CharArrayWriter
  - PrinterWriter

## 接口主要方法梳理

### InputStream中的主要方法

- `abstract int read()`: 从流中读取数据，读取一个字节，返回值为所读得字节
- `int read(byte b[])`: 从流中读取数据，读取多个字节，放置到字节数组 b 中，通常读取的字节数量为 b 的长度，返回值为实际独取的字节的数量。
- `int read(byte b[], int off, int len)`: 从流中读取数据，读取 len 个字节，放置到以下标 off 开始字节数组 b 中，返回值为实际读取的字节的数量。
- `long skip(long n)`: 读指针跳过n个字节不读，返回值为实际跳过的字节数量。
- `int available()`: 返回值为流中尚未读取的字节的数量。
- `void close()`: 关闭输入流。
- `synchronized void mark(int readlimit)`: 记录当前指针的所在位置，`readlimit`表示读指针读出的`readlimit`个字节后，所标记的指针位置才实效。
- `synchronized void reset()`: 把读指针重新指向用 mark 方法所记录的位置。
- `boolean markSupported()`: 当前的流是否支持读指针的记录功能。

> **注**：其中`read()`返回的是读入的一个字节所对应的 int 值(0-255),而`read(byte[] b)`和`read(byte[] b, int off, int len)`返回的是读入的字节数。

### OutputStream中的主要方法

- `abstract void write(int b)`: 输出数据，往流中写一个字节 b。
- `void write(byte b[])`: 输出数据，往流中写一个字节数组 b。
- `void write(byte b[], int off, int len)`: 输出数据，把字节数组 b 中从下标 off 开始，长度为 len 的字节写入到流中。
- `void flush()`: 刷空输出流，并输出所有被缓存的字节。由于某些流支持缓存功能，该方法将把缓存中所有内容强制输出到流中。
- `void close()`: 关闭输出流。

### FileInputStream中的特有方法

- `FileInputStream(String name)`: 构造方法，通过打开一个到实际文件的连接来创建一个FileInputStream，该文件通过文件系统中的路径名 name 指定。
- `FileInputStream(File file)`: 构造方法，通过打开一个到实际文件的连接来创建一个 FileInputStream ，该文件通过文件系统中的 File 对象 file 指定。
- `FileInputStream(FileDescriptor fdObj)`: 构造方法，通过使用文件描述符 fdObj 创建一个 FileInputStream，该文件描述符表示到文件系统中某个实际文件的现有连接。
- `final FileDescriptor getFD()`: 返回表示到文件系统中实际文件的连接的 FileDescriptor 对象，该文件系统正被此 FileInputStream 使用。
- `FileChannel getChannel()`: 返回与此文件输入流有关的唯一 FileChannel 对象。
- `void finalize()`: 确保在不再引用文件输入流时调用其 close 方法。

### FileOutputStream中的特有方法

- `FileOutputStream(String name)`: 构造方法，创建一个向具有指定名称的文件中写入数据的输出文件流。
- `FileOutputStream(String name, boolean append)`: 构造方法，创建一个向具有指定名称的文件中写入数据的输出文件流。如果第二个参数为 true，则将字节写入文件末尾处，而不是写入文件开始处。默认为 false，则是覆盖。
- `FileOutputStream(File file)`: 构造方法，创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
- `FileOutputStream(File file, boolean append)`: 构造方法，创建一个向指定 File 对象表示的文件中写入数据的文件输出流。如果第二个参数为 true，则将字节写入文件末尾处，而不是写入文件开始处。默认为 false，则是覆盖。
- `FileOutputStream(FileDescriptor fdObj)`: 构造方法，创建一个向指定文件描述符处写入数据的输出文件流，该文件描述符表示一个到文件系统中的某个实际文件的现有连接。
- `final FileDescriptor getFD()`: 返回与此流有关的文件描述符。
- `FileChannel getChannel()`: 返回与此文件输出流有关的唯一 FileChannel 对象。
- `void finalize()`: 清理到文件的连接，并确保在不再引用此文件输出流时调用此流的 close 方法。

### ByteArrayInputStream中的特有方法

- `ByteArrayInputStream(byte buf[])`: 构造方法，创建一个内容为 buf 的字节流。
- `ByteArrayInputStream(byte buf[], int offset, int length)`: 构造方法，创建一个内容为 buf 的字节流，并且是从 offset 开始读取数据，读取的长度为 length。

### ByteArrayOutputStream中的特有方法

- `ByteArrayOutputStream()`: 构造方法，默认创建的字节数组大小是 32 的字节数组输出流。
- `ByteArrayOutputStream(int size)`: 构造方法，创建一个指定数组大小为 size 的字节数组输出流。
- `synchronized void writeTo(OutputStream out)`: 将该字节数组输出流的数据全部写入到输出流 out 中。
- `synchronized byte toByteArray()[]`: 将字节数组输出流转换成字节数组。
- `synchronized int size()`: 得到字节数组输出流中的当前计数值。
- `synchronized String toString()`: 根据平台的默认字符编码将缓冲区中的字节内容字节转换成字符串。
- `synchronized String toString(String charsetName)`: 根据指定的字符编码 charsetName 将缓冲区中的字节内容字节转换成字符串。

### PipedInputStream中的特有方法

- `PipedInputStream()`: 构造方法，默认创建缓冲区大小是 1024 字节的管道输入流对象。
- `PipedInputStream(int pipeSize)`: 构造方法，创建缓冲区大小是 pipeSize 字节的管道输入流对象。
- `PipedInputStream(PipedOutputStream src)`: 构造方法，创建与管道输出流关联的管道输入流对象，默认的缓冲区大小是 1024 字节。
- `PipedInputStream(PipedOutputStream src, int pipeSize)`: 构造方法，创建缓冲区大小是 pipeSize 字节，且与管道输出流关联的管道输入流对象。
- `void connect(PipedOutputStream src)`: 与管道输出流对象进行连接绑定。
- `synchronized void receive(int b)`: 接收 int 类型的数据 b。它只会在 PipedOutputStream 的`write(int b)`中会被调用。

### PipedOutputStream中的特有方法

- `PipedOutputStream()`: 空构造方法。
- `PipedOutputStream(PipedInputStream snk)`: 构造方法，创建与管道输入流关联的管道输出流对象。
- `synchronized void connect(PipedInputStream snk)`: 与管道输入流对象进行连接绑定。

## 使用示例

### FileInputStream 和 FileOutputStream

```java
/**
 * 测试复制文件a.txt中的内容到b.txt文件中.
 */
private static void testCopyByFileStream() {
    try (
        InputStream in = new FileInputStream("G:/test/a.txt");
        OutputStream out = new FileOutputStream("G:/test/b.txt", true)
    ) {
        int len;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
    } catch (IOException e) {
        log.error("文件读取写入失败!", e);
    }
}
```

### ByteArrayOutputStream

```java
/**
 * 测试将内容写入到ByteArrayOutputStream中并打印出来，不需要关闭流.
 */
private static void testByByteArrayStream() {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream(8);
    String str = "Hello World!";
    try {
        byteOut.write(str.getBytes());
    } catch (IOException e) {
        log.error("写入字节数据出错!", e);
    }

    byte[] buf = byteOut.toByteArray();
    for (byte b : buf) {
        log.info("{}", (char) b);
    }
}
```

### PipedInputStream 和 PipedOutputStream

```java
/**
 * 发送消息的线程.
 *
 * @author blinkfox on 2017/10/19.
 */
public class Sender extends Thread {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    /** 管道输出流对象,它和管道输入流(PipedInputStream)对象绑定.从而可以将数据发送给“管道输入流”. */
    private PipedOutputStream pipedOut;

    public Sender(PipedOutputStream pipedOut) {
        this.pipedOut = pipedOut;
    }

    public PipedOutputStream getPipedOut() {
        return pipedOut;
    }

    @Override
    public void run() {
        String strInfo = "Hello World!" ;
        try {
            pipedOut.write(strInfo.getBytes());
            pipedOut.close();
        } catch (IOException e) {
            log.error("向管道中写入数据出错!", e);
        }
    }

}
```

```java
/**
 * 接收消息的线程.
 *
 * @author blinkfox on 2017/10/19.
 */
public class Receiver extends Thread {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /** 管道输入流对象,它和管道输出流(PipedOutputStream)对象绑定.从而可以接收“管道输出流”的数据. */
    private PipedInputStream pipedIn;

    public Receiver(PipedInputStream pipedIn) {
        this.pipedIn = pipedIn;
    }

    public PipedInputStream getPipedIn() {
        return pipedIn;
    }

    @Override
    public void run() {
        byte[] buf = new byte[2048];
        try {
            int len = pipedIn.read(buf);
            log.info("{}", new String(buf, 0, len));
            pipedIn.close();
        } catch (IOException e) {
            log.error("从管道中读取数据出错!", e);
        }
    }

}
```

```java
/**
 * PipedInputStream 和 PipedOutputStream 的测试类.
 *
 * @author blinkfox on 2017/10/19.
 */
public class PipedStreamTest {

    private static final Logger log = LoggerFactory.getLogger(PipedStreamTest.class);

    /**
     * 主入口方法.
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        Sender sender = new Sender(new PipedOutputStream());
        Receiver receiver = new Receiver(new PipedInputStream());

        try {
            // 将管道输入流和管道的输出流进行连接.
            receiver.getPipedIn().connect(sender.getPipedOut());

            // 启动线程
            sender.start();
            receiver.start();
        } catch (IOException e) {
            log.info("发送接收消息出错!", e);
        }
    }

}
```

## 设计模式

- 装饰器模式
- 适配器模式

## 设计的优缺点

### 优点

- 功能强大，防止了过多的类膨胀
- 符合开闭原则，开发通过各种装配能实现各种功能

### 缺点

- 读写文件，API使用繁琐，不便于操作
- IO相关的过多的检查异常，不符合当前的主流设计思路

### 改进
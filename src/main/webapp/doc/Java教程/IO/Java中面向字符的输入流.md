# Java中面向字符的输入流

---

## 字符流概述

字符流是针对字符数据的特点进行过优化的，因而提供一些面向字符的有用特性，**字符流的源或目标通常是文本文件**。 `Reader`和`Writer`是`java.io`包中所有字符流的父类。由于它们都是抽象类，所以应使用它们的子类来创建实体对象，利用对象来处理相关的读写操作。`Reader`和`Writer`的子类又可以分为两大类：

- 节点流：用来从数据源读入数据或往目的地写出数据
- 处理流：对数据执行某种处理

**面向字符的输入流类都是Reader的子类**，其类层次结构如下图所示：

![Reader的子类](http://static.blinkfox.com/java-io-char1.jpg)

下面是`Reader`的主要子类及说明：

- `CharArrayReader`：从字符数组读取的输入流
- `BufferedReader`：缓冲输入字符流
  - `LineNumberReader`：为输入数据附加行号
- `PipedReader`：输入管道
- `InputStreamReader`：将字节转换到字符的输入流
  - `FileReader`：从文件读取的输入流
- `FilterReader`：过滤输入流
  - `PushbackReader`：返回一个字符并把此字节放回输入流
- `StringReader`：从字符串读取的输入流

`Reader` 所提供的方法如下，可以利用这些方法来获得流内的位数据。

- `void close()`：关闭输入流
- `void mark()`：标记输入流的当前位置
- `boolean markSupported()`：测试输入流是否支持 mark
- `int read()`：从输入流中读取一个字符
- `int read(char[] ch)`：从输入流中读取字符数组
- `int read(char[] ch, int off, int len)`：从输入流中读`len`长的字符到`ch`内
- `boolean ready()`：测试流是否可以读取
- `void reset()`：重定位输入流
- `long skip(long n)`：跳过流内的`n`个字符

## 使用 FileReader 类读取文件

`FileReader`类是`Reader`子类`InputStreamReader`类的子类，因此`FileReader`类既可以使用`Reader`类的方法也可以使用`InputStreamReader`类的方法来创建对象。

在使用`FileReader`类读取文件时，必须先调用`FileReader()`构造方法创建`FileReader`类的对象，再调用`read()`方法。`FileReader构造方法的格式为：

```java
public FileReader(String name);  //根据文件名创建一个可读取的输入流对象
```

使用示例：

```java
import java.io.*;

class FileReaderTest {

    public static void main(String args[]) throws IOException {
        char a[] = new char[1000]; // 创建可容纳 1000 个字符的数组
        FileReader b = new FileReader("test.txt");
        int num = b.read(a); // 将数据读入到数组 a 中，并返回字符数
        String str = new String(a, 0, num); // 将字符串数组转换成字符串
        System.out.println("读取的字符个数为：" + num + ", 内容为：\n");
        System.out.println(str);
    }

}
```

> **注意**：需要注意的是，Java 把一个汉字或英文字母作为一个字符对待，回车或换行作为两个字符对待。

## 使用 BufferedReader 类读取文件

`BufferedReader`类是用来读取缓冲区中的数据。使用时必须创建`FileReader`类对象，再以该对象为参数创建`BufferedReader`类的对象。`BufferedReader`类有两个构造方法，其格式为：

```java
public BufferedReader(Reader in);  // 创建缓冲区字符输入流
public BufferedReader(Reader in, int size);  // 创建输入流并设置缓冲区大小
```

使用示例：

```java
import java.io.*;

class BufferedReaderTest {

    public static void main(String args[]) throws IOException {
        String OneLine;
        int count = 0;
        try {
            FileReader a = new FileReader("ep10_1.txt");
            BufferedReader b = new BufferedReader(a);
            while((OneLine = b.readLine()) != null) {  // 每次读取 1 行
                count++;  // 计算读取的行数
                System.out.println(OneLine);
            }
            System.out.println("\n 共读取了" + count + "行");
            b.close();
        } catch (IOException io) {
            System.out.println("出错了!" + io);
        }
    }

}
```

需要注意的是，执行`read()`或`write()`方法时，可能由于`IO`错误，系统抛出`IOException`异常，需要将执行读写操作的语句包括在`try`块中，并通过相应的`catch`块来处理可能产生的异常。
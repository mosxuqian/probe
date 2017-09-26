# Java面向字符的输出流

面向字符的输出流都是类`Writer`的子类，其类层次结构如下图所示。

![Writer类层次结构](http://static.blinkfox.com/java-io-writer1.jpg)

下面列出`Writer`的主要子类及说明：

- CharArrayWriter: 写到字符数组的输出流
- BufferedWriter: 缓冲输出字符流
- PipedWriter: 输出管道
- OutputStreamWriter: 转换字符到字节的输出流
  - FileWriter: 输出到文件的输出流
- FilterWriter: 过滤输出流
- StringWriter: 输出到字符串的输出流
- PrintWriter: 包含`print()`和`println()`的输出流

`Writer`所提供的方法如下：

- `void close()`: 关闭输出流
- `void flush()`: 将缓冲区中的数据写到文件中
- `void writer(int c)`: 将单一字符`c`输出到流中
- `void writer(String str)`: 将字符串`str`输出到流中
- `void writer(char[] ch)`: 将字符数组`ch`输出到流
- `void writer(char[] ch, int offset, int length)`: 将一个数组内自`offset`起到`length`长的字符输出到流

## 使用 FileWriter 类写入文件

`FileWriter`类是`Writer`子类`OutputStreamWriter`类的子类，因此`FileWriter`类既可以使用`Writer`类的方法也可以使用`OutputStreamWriter`类的方法来创建对象。

在使用`FileWriter`类写入文件时，必须先调用`FileWriter()`构造方法创建`FileWriter`类的对象，再调用`writer()`方法。`FileWriter`构造方法的格式为：

```java
public FileWriter(String name);  // 根据文件名创建一个可写入的输出流对象
public FileWriter(String name, Boolean a);  // a 为真，数据将追加在文件后面
```

代码示例如下：

```java
import java.io.*;
class FileWriterTest {
    public static void main(String args[]) {
        try {
            FileWriter a = new FileWriter("test.txt");
            for (int i = 32; i < 126; i++) {
                a.write(i);
            }
            a.close();
        } catch(IOException e){}
    }
}
```

`BufferedWriter`类是用来将数据写入到缓冲区。使用时必须创建`FileWriter`类对象，再以该对象为参数创建`BufferedWriter`类的对象，最后需要用`flush()`方法将缓冲区清空。`BufferedWriter`类有两个构造方法，其格式为：

```java
public BufferedWriter(Writer out);  //创建缓冲区字符输出流
public BufferedWriter(Writer out,int size);  //创建输出流并设置缓冲区大小
```

代码示例如下：

```java
import java.io.*;
class BufferedWriterTest {
    public static void main(String args[]) {
        String str = new String();
        try {
            BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("test2.txt"));
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                out.write(str);  //将读取到的 1 行数据写入输出流
                out.newLine();  //写入换行符
            }
            out.flush();
            in.close();
            out.close();
        } catch(IOException e){
            System.out.println("出现错误"+e);
        }
    }
}
```

需要注意的是，调用`out`对象的`write()`方法写入数据时，不会写入回车，因此需要使用`newLine()`方法在每行数据后加入回车，以保证目标文件与源文件相一致。
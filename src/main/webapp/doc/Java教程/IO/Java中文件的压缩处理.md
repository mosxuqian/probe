# Java中文件的压缩处理

`Java.util.zip`包中提供了可对文件的压缩和解压缩进行处理的类，它们继承自字节流类`OutputSteam`和`InputStream`。其中`GZIPOutputStream`和`ZipOutputStream`可分别把数据压缩成`GZIP`和`Zip`格式，`GZIPInpputStream`和`ZipInputStream`又可将压缩的数据进行还原。

## 将文件写入压缩文件的一般步骤如下：

1. 生成和所要生成的压缩文件相关联的压缩类对象。
2. 压缩文件通常不只包含一个文件，将每个要加入的文件称为一个压缩入口，使用`ZipEntry(String FileName)`生成压缩入口对象。
3. 使用`putNextEntry(ZipEntry entry)`将压缩入口加入压缩文件。
4. 将文件内容写入此压缩文件。
5. 使用`closeEntry()`结束目前的压缩入口，继续下一个压缩入口。

## 将文件从压缩文件中读出的一般步骤如下：

1. 生成和所要读入的压缩文件相关联的压缩类对象。
2. 利用`getNextEntry()`得到下一个压缩入口。

## 示例

输入若干文件名，将所有文件压缩为`test.zip`，再从压缩文件中解压并显示。

```java
import java.io.*;
import java.util.*;
import java.util.zip.*;

class ZipTest {

    public static void main(String args[]) throws IOException{
        FileOutputStream a = new FileOutputStream("test.zip");

        //处理压缩文件
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(a));
        for (int i = 0; i< args.length; i++) {  //对命令行输入的每个文件进行处理
            System.out.println("Writing file" + args[i]);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(args[i]));
            out.putNextEntry(new ZipEntry(args[i]));  //设置 ZipEntry 对象
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);  //从源文件读出，往压缩文件中写入
            }
            in.close();
        }
        out.close();

        //解压缩文件并显示
        System.out.println("Reading file");
        FileInputStream d = new FileInputStream("test.zip");
        ZipInputStream inout = new ZipInputStream(new BufferedInputStream(d));
        ZipEntry z;
        while ((z = inout.getNextEntry()) != null) {  //获得入口
            System.out.println("Reading file" + z.getName());  //显示文件初始名
            int x;
            while ((x=inout.read())!=-1) {
                System.out.write(x);
            }
            System.out.println();
        }
        inout.close();
    }

}
```
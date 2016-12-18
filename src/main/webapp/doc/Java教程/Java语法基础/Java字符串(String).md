# Java字符串(String)

标签：Java基础教程

---

从表面上看，字符串就是双引号之间的数据，例如“abc”、“http://blinkfox.com”等。在Java中，可以使用下面的方法定义字符串：

> String stringName = "string content";

例如：

```java
String url = "http://blinkfox.com";
String webName = "闪烁之狐";
```

字符串可以通过`+`连接，基本数据类型与字符串进行“+”操作一般也会自动转换为字符串，例如：

```java
public class Demo {
    public static void main(String[] args){
        String stuName = "小明";
        int stuAge = 17;
        float stuScore = 92.5f;
       
        String info = stuName + "的年龄是 " + stuAge + "，成绩是 " + stuScore;
        System.out.println(info);
    }
}
```

运行结果：

> 小明的年龄是 17，成绩是 92.5

**String字符串与数组有一个共同点，就是它们被初始化后，长度是不变的，并且内容也不变。**如果要改变它的值，就会产生一个新的字符串，如下所示：

```java
String str = "Hello ";
str += "World!";
```

这个赋值表达式看起来有点像简单的接龙，在str后面直接加上一个“World!”字符串，形成最后的字符串“Hello World!”。其运行原理是这样的：程序首先产生了str1字符串，并在内存中申请了一段空间。此时要追加新的字符串是不可能的，因为字符串被初始化后，长度是固定的。如果要改变它，只有放弃原来的空间，重新申请能够容纳“Hello World!”字符串的内存空间，然后将“Hello World!”字符串放到内存中。

实际上，String 是java.lang包下的一个类，按照标准的面向对象的语法，其格式应该为：

> String stringName = new String("string content");

例如：

```java
String url = new String("http://blinkfox.com");
```

但是由于String特别常用，所以Java提供了一种简化的语法。

使用简化语法的另外一个原因是，按照标准的面向对象的语法，在内存使用上存在比较大的浪费。例如String str = new String(“abc”);实际上创建了两个String对象，一个是”abc”对象，存储在常量空间中，一个是使用new关键字为对象str申请的空间。

### 字符串操作

String对象有很多方法，可以方便的操作字符串。

#### 1. length()方法

`length()`返回字符串的长度，例如：

```java
String str1 = "微学苑";
String str2 = "weixueyuan";
System.out.println("The lenght of str1 is " + str1.length());
System.out.println("The lenght of str2 is " + str2.length());
```

输出结果：

> The lenght of str1 is 3
> The lenght of str2 is 10

可见，无论是字母、数字，还是汉字，每个字符的长度都是1。

#### 2. charAt()方法

`charAt()`方法的作用是按照索引值获得字符串中的指定字符。Java规定，字符串中第一个字符的索引值是0，第二个字符的索引值是1，依次类推。例如：

```java
String str = "123456789";
System.out.println(str.charAt(0) + "    " + str.charAt(5) + "    " + str.charAt(8));
```

输出结果：

> 1    6    9

#### 3. contains()方法

`contains()`方法用来检测字符串是否包含某个子串，例如：

```java
String str = "weixueyuan";
System.out.println(str.contains("yuan"));
```

输出结果：

> true

#### 4. replace()方法

字符串替换，用来替换字符串中所有指定的子串，例如：

```java
String str1 = "The url of weixueyuan is blinkfox.com!";
String str2 = str1.replace("blinkfox", "闪烁之狐");
System.out.println(str1);
System.out.println(str2);
```

输出结果：

> The url of weixueyuan is blinkfox!
> The url of blinkfox is 闪烁之狐.com!

**注意：**`replace()`方法不会改变原来的字符串，而是生成一个新的字符串。

#### 5. split()方法

以指定字符串作为分隔符，对当前字符串进行分割，分割的结果是一个数组，例如：

```java
import java.util.*;
public class Demo {
    public static void main(String[] args){
        String str = "blink_fox_is_good";
        String strArr[] = str.split("_");
        System.out.println(Arrays.toString(strArr));
    }
}
```

运行结果：

> [blink, fox, is, good]

以上仅仅列举了几个常用的String对象的方法，更多方法和详细解释请参考API文档。
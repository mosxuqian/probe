# Java数据类型以及变量的定义

标签：Java基础教程

---

Java 是一种强类型的语言，声明变量时必须指明数据类型。变量(variable)的值占据一定的内存空间。不同类型的变量占据不同的大小。

Java中共有8种基本数据类型，包括4 种整型、2 种浮点型、1 种字符型、1 种布尔型，请见下图。

![Java基本数据类型][1]

对于整型数据，通常情况下使用int类型。但如果表示投放广岛长崎的原子弹释放出的能量，就需要使用long类型了。byte和short类型主要用于特定的应用场合，例如，底层的文件处理或者需要控制占用存储空间量的大数组。

在Java中，整型数据的长度与平台无关，这就解决了软件从一个平台移植到另一个平台时给程序员带来的诸多问题。与此相反，C/C++整型数据的长度是与平台相关的，程序员需要针对不同平台选择合适的整型，这就可能导致在64位系统上稳定运行的程序在32位系统上发生整型溢出。

八进制有一个前缀0，例如010对应十进制中的8；十六进制有一个前缀0x，例如0xCAFE；从Java 7开始，可以使用前缀0b来表示二进制数据，例如`0b1001`对应十进制中的9。同样从Java 7开始，可以使用下划线来分隔数字，类似英文数字写法，例如`1_000_000` 表示1,000,000，也就是一百万。下划线只是为了让代码更加易读，编译器会删除这些下划线。

另外，不像 C/C++，Java 不支持无符号类型(unsigned)。

float类型有效数字最长为7位，有效数字长度包括了整数部分和小数部分。例如：

```java
float x = 223.56F;
float y = 100.00f;
```

注意：每个float类型后面都有一个标志“F”或“f”，有这个标志就代表是float类型。

double类型有效数字最长为`15`位。与`float`类型一样，double后面也带有标志“D”或“d”。例如：

```java
double x = 23.45D;
double y = 422.22d;
double z = 562.234;
```

注意：不带任何标志的浮点型数据，系统默认是double类型。

大多数情况下都是用double类型，float的精度很难满足需求。

不同数据类型应用举例：

```java
public class Demo {
    public static void main(String[] args){
        // 字符型
        char webName1 = '微';
        char webName2 = '学';
        char webName3 = '苑';
        System.out.println("网站的名字是：" + webName1 + webName2 + webName3);
       
        // 整型
        short x=22;  // 十进制
        int y=022;  // 八进制
        long z=0x22L;  // 十六进制
        System.out.println("转化成十进制：x = " + x + ", y = " + y + ", z = " + z);
       
        // 浮点型
        float m = 22.45f;
        double n = 10;
        System.out.println("计算乘积：" + m + " * " + n + "=" + m*n);
    }
}
```

运行结果：

> 网站的名字是：微学苑
> 转化成十进制：x = 22, y = 18, z = 34
> 计算乘积：22.45 * 10.0=224.50000762939453

从运行结果可以看出，即使浮点型数据只有整数没有小数，在控制台上输出时系统也会自动加上小数点，并且小数位全部置为0。

### 对布尔型的说明

如果你有编程经验，了解布尔型，请跳过下面的教程，下面的教程针对只有C语言基础的读者（C语言没有布尔型）。

在C语言中，如果判断条件成立，会返回1，否则返回0，例如：

```c
#include <stdio.h>

int main(){
    int x = 100>10;
    int y = 100<10;
    printf("100>10 = %d\n", x);
    printf("100<10 = %d\n", y);

    return 0;
}
```

运行结果：

> 100>10 = 1
> 100<10 = 0

但是在Java中不一样，条件成立返回true，否则返回false，即布尔类型。例如：

```java
public class Demo {
    public static void main(String[] args){
        // 字符型
        boolean a = 100>10;
        boolean b = 100<10;
        System.out.println("100>10 = " + a);
        System.out.println("100<10 = " + b);
       
        if(a){
            System.out.println("100<10是对的");
        }else{
            System.out.println("100<10是错的");
        }
    }
}
```

运行结果：
> 100>10 = true
> 100<10 = false
> 100<10是对的

实际上，true等同于1，false等同于0，只不过换了个名称，并单独地成为一种数据类型。

  [1]: http://static.blinkfox.com/Javajblx.png
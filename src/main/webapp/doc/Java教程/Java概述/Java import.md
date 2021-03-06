# Java import

标签（空格分隔）： Java基础教程

---

如果你希望使用Java包中的类，就必须先使用`import`语句导入。

import语句与C语言中的 #include 有些类似，语法为：

```java
    import package1[.package2…].classname;
```

其中，package 为包名，classname 为类名。例如：

```java
import java.util.Date;  // 导入 java.util 包下的 Date 类
import java.util.Scanner;  // 导入 java.util 包下的 Scanner 类
import javax.swing.*;  // 导入 javax.swing 包下的所有类，* 表示所有类
```

> **注意：**
1. import 只能导入包所包含的类，而不能导入包。
2. 如果要导入包下所有的类，可以使用*，例如 import java.util.*;。

Java编译器默认为所有的Java程序导入了JDK的`java.lang`包中所有的类（import java.lang.*;），其中定义了一些常用类，如System、String、Object、Math等，因此我们可以直接使用这些类而不必显式导入。但是使用其他类必须先导入。

前面讲到的”Hello World“程序使用了`System.out.println();`语句，System类位于`java.lang`包，虽然我们没有显式导入这个包中的类，但是Java编译器默认已经为我们导入了，否则程序会执行失败。

### Java类的搜索路径

Java程序运行时要导入相应的类，也就是加载`.class`文件的过程。

假设有如下的`import`语句：

```java
import p1.Test;
```

该语句表明要导入p1包中的Test类。

安装JDK时，我们已经设置了环境变量`CLASSPATH`来指明类库的路径，它的值为`.;%JAVA_HOME%\lib`，而`JAVA_HOME`又为`D:\Program Files\jdk1.7.0_71`，所以`CLASSPATH`等价于`.;D:\Program\Files\jdk1.7.0_71\lib`。

Java 运行环境将依次到下面的路径寻找并载入字节码文件`Test.class`：

```java
.p1\Test.class（"."表示当前路径）
D:\Program Files\jdk1.7.0_71\lib\p1\Test.class
```

如果在第一个路径下找到了所需的类文件，则停止搜索，否则继续搜索后面的路径，如果在所有的路径下都未能找到所需的类文件，则编译或运行出错。

你可以在`CLASSPATH`变量中增加搜索路径，例如`.;%JAVA_HOME%\lib;C:\javalib`，那么你就可以将类文件放在`C:\javalib`目录下，Java运行环境一样会找到。
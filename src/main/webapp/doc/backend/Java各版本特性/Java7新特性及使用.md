# Java6新特性及使用

## 新特性列表

以下是Java7中的引入的部分新特性。关于Java7更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)。

- switch支持String
- try-with-resources
- catch多个异常
- 实例创建类型推断
- 数字字面量下划线分割
- 二进制字面量
- 增强的文件系统
- fork/join 框架
- 其它
  - JDBC4.1规范
  - 支持动态类型语言
  - JSR341-Expression Language Specification
  - JSR203-More New I/O APIs for the Java Platform
  - 桌面客户端增强

## 一、switch支持String

`switch`现在可以接受`String`类型的参数。示例代码如下：

```java
String s = ...
switch(s) {
case "quux":
    processQuux(s);
// fall-through
case "foo":
case "bar":
    processFooOrBar(s);
    break;
case "baz":
    processBaz(s);
    // fall-through
default:
    processDefault(s);
    break;
}
```

## 二、try-with-resources

Java中某些资源是需要手动关闭的，如`InputStream`，`Writer`，`Sockets`，`Connection`等。这个新的语言特性允许try语句本身申请更多的资源，这些资源作用于try代码块，并自动关闭。

Java7之前的写法：

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader(path));
    return br.readLine();
} catch (Exception e) {
    log.error("BufferedReader Exception", e);
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (Exception e) {
            log.error("BufferedReader close Exception", e);
        }
    }
}
```

Java7及之后的写法：

```java
try (BufferedReader br = new BufferedReader(new FileReader(path)) {
    return br.readLine();
} catch (Exception e) {
    log.error("BufferedReader Exception", e);
}
```

## 三、catch多个异常

自Java7开始，`catch`中可以一次性捕捉多个异常做统一处理。示例如下：

Java7之前的写法：

```java
public void handle() {
    ExceptionThrower thrower = new ExceptionThrower();
    try {
        thrower.manyExceptions();
    } catch (ExceptionA a) {
        System.out.println(a.getClass());
    } catch (ExceptionB b) {
        System.out.println(b.getClass());
    } catch (ExceptionC c) {
        System.out.println(c.getClass());
    }
}
```

Java7及之后的写法：

```java
public void handle() {
    ExceptionThrower thrower = new ExceptionThrower();
    try {
        thrower.manyExceptions();
    } catch (ExceptionA | ExceptionB ab) {
        System.out.println(ab.getClass());
    } catch (ExceptionC c) {
        System.out.println(c.getClass());
    }
}
```

## 四、实例创建类型推断

从Java7开始，泛型类的实例化也不用繁琐的将泛型声明再写一遍。示例如下：

Java7之前的写法：

```java
Map<String, List<String>> map = new HashMap<String, List<String>>();
```

Java7及之后的写法：

```java
Map<String, List<String>> map = new HashMap<>();
```

## 五、数字字面量下划线分割

很长的数字可读性不好，在Java 7中可以使用下划线分隔长`int`以及`long`型整数了。如：

```java
long creditCardNumber = 1234_5678_9012_3456L;
public static final int ONE_MILLION = 1_000_000;
public static final float PI = 3.14_15F;
```

## 六、二进制字面量

现在可以使用0b前缀创建二进制字面量：

```java
int binary = 0b1001_1001;
```

使用二进制字面量这种表示方式，使用非常简短的代码就可将二进制字符转换为数据类型，如在`byte`或`short`。

```java
byte aByte = (byte) 0b001;
short aShort = (short) 0b010;
```

## 七、增强的文件系统

Java7 推出了全新的`NIO2.0 API`以此改变针对文件管理的不便，使得在`java.nio.file`包下使用`Path`、`Paths`、`Files`、`WatchService`、`FileSystem`等常用类型可以很好的简化开发人员对文件管理的编码工作。

### 1. Path接口和Paths类

`Path`接口的某些功能其实可以和`java.io`包下的`File`类等价，当然这些功能仅限于只读操作。在实际开发过程中，开发人员可以联用`Path`接口和`Paths`类，从而获取文件的一系列上下文信息。

- `int getNameCount()`: 获取当前文件节点数
- `Path getFileName()`: 获取当前文件名称
- `Path getRoot()`: 获取当前文件根目录
- `Path getParent()`: 获取当前文件上级关联目录

联用`Path`接口和`Paths`类型获取文件信息：

```java
Path path = Paths.get("G:/test/test.xml");
System.out.println("文件节点数:" + path.getNameCount());
System.out.println("文件名称:" + path.getFileName());
System.out.println("文件根目录:" + path.getRoot());
System.out.println("文件上级关联目录:" + path.getParent());
```

### 2. Files类

联用`Path`接口和`Paths`类可以很方便的访问到目标文件的上下文信息。当然这些操作全都是只读的，如果开发人员想对文件进行其它非只读操作，比如文件的创建、修改、删除等操作，则可以使用`Files`类型进行操作。

Files类型常用方法如下：

- `Path createFile()`: 在指定的目标目录创建新文件
- `void delete()`: 删除指定目标路径的文件或文件夹
- `Path copy()`: 将指定目标路径的文件拷贝到另一个文件中
- `Path move()`: 将指定目标路径的文件转移到其他路径下，并删除源文件

使用`Files`类型复制、粘贴文件示例：

```java
Files.copy(Paths.get("/test/src.xml"), Paths.get("/test/target.xml"));
```

使用`Files`类型来管理文件，相对于传统的I/O方式来说更加方便和简单。因为具体的操作实现将全部移交给`NIO2.0 API`，开发人员则无需关注。

### 3. WatchService

Java7 还为开发人员提供了一套全新的文件系统功能，那就是文件监测。在此或许有很多朋友并不知晓文件监测有何意义及目，那么请大家回想下调试成热发布功能后的Web容器。当项目迭代后并重新部署时，开发人员无需对其进行手动重启，因为Web容器一旦监测到文件发生改变后，便会自动去适应这些“变化”并重新进行内部装载。Web容器的热发布功能同样也是基于文件监测功能，所以不得不承认，文件监测功能的出现对于Java文件系统来说是具有重大意义的。

文件监测是基于事件驱动的，事件触发是作为监测的先决条件。开发人员可以使用`java.nio.file`包下的`StandardWatchEventKinds`类型提供的3种字面常量来定义监测事件类型，值得注意的是监测事件需要和`WatchService`实例一起进行注册。

`StandardWatchEventKinds`类型提供的监测事件：

- `ENTRY_CREATE`：文件或文件夹新建事件；
- `ENTRY_DELETE`：文件或文件夹删除事件；
- `ENTRY_MODIFY`：文件或文件夹粘贴事件；

使用`WatchService`类实现文件监控完整示例：

```java
public static void testWatch() {
    /* 监控目标路径 */
    Path path = Paths.get("G:/");
    try {
        /* 创建文件监控对象. */
        WatchService watchService = FileSystems.getDefault().newWatchService();

        /* 注册文件监控的所有事件类型. */
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        /* 循环监测文件. */
        while (true) {
            WatchKey watchKey = watchService.take();

            /* 迭代触发事件的所有文件 */
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(event.context().toString() + " 事件类型：" + event.kind());
            }

            if (!watchKey.reset()) {
                return;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

通过上述程序示例我们可以看出，使用`WatchService`接口进行文件监控非常简单和方便。首先我们需要定义好目标监控路径，然后调用`FileSystems`类型的`newWatchService()`方法创建`WatchService`对象。接下来我们还需使用`Path`接口的`register()`方法注册`WatchService`实例及监控事件。当这些基础作业层全部准备好后，我们再编写外围实时监测循环。最后迭代`WatchKey`来获取所有触发监控事件的文件即可。

---

参考文档：

-[JavaSE7 Features and Enhancements](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)
-[Java7的新特性](https://segmentfault.com/a/1190000004417830)
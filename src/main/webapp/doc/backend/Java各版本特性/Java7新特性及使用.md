# Java6新特性及使用

## 新特性列表

以下是Java7中的引入的部分新特性。关于Java7更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)。

- switch支持String
- 集合语法增强
- try-with-resources
- catch多个异常
- 实例创建类型推断
- 支持数字字面量下划线分割
- 二进制字面量
- 增强的文件操作
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

## 二、集合语法增强

现在Java初始化创建部分集合数据时的方式更简单了。使用示例及对比如下：

Java7之前的写法：

```java
List<String> list = new ArrayList<String>();
list.add("item");
String item = list.get(0);

Set<String> set = new HashSet<String>();
set.add("item");
Map<String, Integer> map = new HashMap<String, Integer>();
map.put("key", 1);
int value = map.get("key");
```

Java7及之后的写法：

```java
List<String> list = ["item"];
String item = list[0];

Set<String> set = {"item"};

Map<String, Integer> map = {"key" : 1};
int value = map["key"];
```

## 三、try-with-resources

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

## 四、catch多个异常

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

## 五、实例创建类型推断

从Java7开始，泛型类的实例化也不用繁琐的将泛型声明再写一遍。示例如下：

Java7之前的写法：

```java
Map<String, List<String>> map = new HashMap<String, List<String>>();
```

Java7及之后的写法：

```java
Map<String, List<String>> map = new HashMap<>();
```

---

参考文档：

-[JavaSE7 Features and Enhancements](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)
-[Java7的新特性](https://segmentfault.com/a/1190000004417830)
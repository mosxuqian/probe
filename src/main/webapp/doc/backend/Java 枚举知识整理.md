# Java 枚举知识整理

## 概述

### 定义

**枚举**（`enum`全称为`enumeration`）类型是`Java 5`新增的类型，存放在`java.lang`包中，允许用常量来表示特定的数据片断，而且全部都以类型安全的形式来表示。

### 定义格式

 创建枚举类型要使用`enum`关键字，隐含了所创建的类型都是`java.lang.Enum`类的子类（`java.lang.Enum`是一个抽象类）。枚举类型符合通用模式`Class Enum<E extends Enum<E>>`，而`E`表示枚举类型的名称。枚举类型的每一个值都将映射到`protected Enum(String name, int ordinal)`构造函数中。在这里每个值的名称都被转换成一个字符串，并且序数设置表示了此设置被创建的顺序。

枚举类的定义格式如下：

```java
enum 类名 {

//枚举值

}
```

### 要点

- 需要的数据不能是任意的，而必须是一定范围内的值
- 枚举类也是一个特殊的类，构造方法默认的修饰符是`private`的
- 枚举值默认的修饰符是`public static final`，必须要位于枚举类的第一个语句
- 枚举类可以定义自己的成员变量、成员函数和带参构造方法，自定义带参构造方法时，枚举值需要传参
- 枚举类可以存在抽象的方法，但是枚举值必须要实现抽象的方法

## 主要应用

### 表达常量

在`Java 5`之前，定义常量的最佳方式是在`final`修饰的常量类中定义：`public static fianl...`修饰的属性，且须将构造方法设为`private`。代码示例如下：

```java
public final class ColorConst {

    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;

    private ColorConst() {}

}
```

但，**不建议在接口中定义常量**。在`《Effective Java》`一书中提到过：

> **The constant interface pattern is a poor use of interfaces**. That a class uses some constants internally is an implementation detail. Implementing a constant interface causes this implementation detail to leak into the class's exported API. It is of no consequence to the users of a class that the class implements a constant interface. In fact, it may even confuse them. Worse, it represents a commitment: if in a future release the class is modified so that it no longer needs to use the constants, it still must implement the interface to ensure binary compatibility. If a nonfinal class implements a constant interface, all of its subclasses will have their namespaces polluted by the constants in the interface.There are several constant interfaces in the java platform libraries, such as java.io.ObjectStreamConstants. These interfaces should be regarded as anomalies and should not be emulated.

大意是：**如果某个实现了常量接口的类被修改不再需要常量了，也会因为序列化兼容原因不得不保持该实现，而且非`final`类实现常量接口会导致所有子类被污染**。

现在好了，有了枚举，可以把相关的常量分组到一个枚举类型里，而且枚举提供了比常量更多的方法。

```java
public enum ColorEnum {

    RED, GREEN, BLUE

}
```

> **注意**：枚举类的名称一般以`Enum`结尾，比如`ColorEnum`等。如果你写个枚举类，取名为`Color`，那么没人能快速知道它是一个枚举类。
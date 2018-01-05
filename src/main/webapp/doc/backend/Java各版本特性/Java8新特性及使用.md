# Java8新特性及使用

## 新特性列表

以下是Java8中的引入的部分新特性。关于Java8新特性更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)。

- 方法引用
- 接口默认方法和静态方法
- Lambda 表达式
- 函数式接口
- 重复注解
- 扩展注解的支持
- Stream
- Optional
- Date/Time API
- Base64
- JavaFx
- 其它
  - JDBC4.2规范
  - 更好的类型推测机制
  - 编译器优化
  - HashMap性能提升
  - IO/NIO 的改进
  - JavaScript引擎Nashorn
  - 并行（parallel）数组
  - 并发（Concurrency）
  - 类依赖分析器jdeps
  - JVM的PermGen空间被移除

## 一、方法引用

方法引用是使用一对冒号`::`并通过方法的名字来指向一个方法。方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

下面，我们在`Car`类中定义了`4`个方法作为例子来区分 Java 中`4`种不同方法的引用。

```java
class Car {

    // Supplier是jdk1.8的接口，这里和Lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

}
```

### 1. 构造器引用

它的语法是`Class::new`，或者更一般的`Class<T>::new`实例如下：

```java
final Car car = Car.create(Car::new);
final List<Car> cars = Arrays.asList(car);
```

### 2. 静态方法引用

它的语法是`Class::static_method`，实例如下：

```java
cars.forEach(Car::collide);
```

### 3. 特定类的任意对象的方法引用

它的语法是`Class::method`实例如下：

```java
cars.forEach(Car::repair);
```

### 4. 特定对象的方法引用

它的语法是`instance::method`实例如下：

```java
final Car police = Car.create(Car::new);
cars.forEach(police::follow);
```

以下是方法引用的实例：

```java
import java.util.ArrayList;
import java.util.List;

/**
 * Tester.
 *
 * @author blinkfox on 2018-01-05.
 */
public class Tester {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        list.forEach(System.out::println);
    }

}
```

实例中我们将`System.out::println`方法作为静态方法来引用。执行以上程序，输出结果为：

```bash
张三
李四
王五
```

## 二、接口默认方法和静态方法

Java 8用默认方法与静态方法这两个新概念来扩展接口的声明。与传统的接口又有些不一样，它允许在已有的接口中添加新方法，而同时又保持了与旧版本代码的兼容性。

### 1. 接口默认方法

默认方法与抽象方法不同之处在于抽象方法必须要求实现，但是默认方法则没有这个要求。相反，每个接口都必须提供一个所谓的默认实现，这样所有的接口实现者将会默认继承它（如果有必要的话，可以覆盖这个默认实现）。让我们看看下面的例子：

```java
private interface Defaulable {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default implementation";
    }
}

private static class DefaultableImpl implements Defaulable {
}

private static class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}
```

`Defaulable`接口用关键字`default`声明了一个默认方法`notRequired()`，`Defaulable`接口的实现者之一`DefaultableImpl`实现了这个接口，并且让默认方法保持原样。`Defaulable`接口的另一个实现者`OverridableImpl`用自己的方法覆盖了默认方法。

### 2. 接口静态方法

Java 8带来的另一个有趣的特性是接口可以声明（并且可以提供实现）静态方法。在接口中定义静态方法，使用static关键字，例如：

```java
private interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}
```

下面的一小段代码片段把上面的默认方法与静态方法黏合到一起。

```java
public static void main(String[] args) {
    Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
    System.out.println(defaulable.notRequired());

    defaulable = DefaulableFactory.create(OverridableImpl::new);
    System.out.println(defaulable.notRequired());
}
```

在JVM中，默认方法的实现是非常高效的，并且通过字节码指令为方法调用提供了支持。默认方法允许继续使用现有的Java接口，而同时能够保障正常的编译过程。这方面好的例子是大量的方法被添加到`java.util.Collection`接口中去：`stream()`，`parallelStream()`，`forEach()`，`removeIf()`等。

尽管默认方法非常强大，但是在使用默认方法时我们需要小心注意一个地方：在声明一个默认方法前，请仔细思考是不是真的有必要使用默认方法，因为默认方法会带给程序歧义，并且在复杂的继承体系中容易产生编译错误。

## 三、Lambda 表达式

`Lambda`表达式（也称为闭包）是整个Java 8发行版中最受期待的在Java语言层面上的改变，Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中）。

一个`Lambda`可以由用逗号分隔的参数列表、`–>`符号与函数体三部分表示。

首先看看在老版本的Java中是如何排列字符串的：

```java
List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
Collections.sort(names, new Comparator<String>() {

    @Override
    public int compare(String a, String b) {
        return b.compareTo(a);
    }

});
```

只需要给静态方法`Collections.sort`传入一个List对象以及一个比较器来按指定顺序排列。通常做法都是创建一个匿名的比较器对象然后将其传递给sort方法。
在Java 8 中你就没必要使用这种传统的匿名对象的方式了，Java 8提供了更简洁的语法，lambda表达式：

```java
Collections.sort(names, (String a, String b) -> {
    return b.compareTo(a);
});
```

看到了吧，代码变得更段且更具有可读性，但是实际上还可以写得更短：

```java
Collections.sort(names, (String a, String b) -> b.compareTo(a));
```

对于函数体只有一行代码的，你可以去掉大括号`{}`以及`return`关键字，但是你还可以写得更短点：

```java
Collections.sort(names, (a, b) -> b.compareTo(a));
```

Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型。

## 四、函数式接口

`Lambda`表达式是如何在Java的类型系统中表示的呢？每一个Lambda表达式都对应一个类型，通常是接口类型。而**函数式接口**是指仅仅只包含一个抽象方法的接口，每一个该类型的Lambda表达式都会被匹配到这个抽象方法。因为**默认方法**不算抽象方法，所以你也可以给你的函数式接口添加默认方法。

我们可以将Lambda表达式当作任意只包含一个抽象方法的接口类型，确保你的接口一定达到这个要求，你只需要给你的接口添加`@FunctionalInterface`注解，编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的。

示例如下：

```java
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
Integer converted = converter.convert("123");
System.out.println(converted); // 123
```

> **注**：如果`@FunctionalInterface`如果没有指定，上面的代码也是对的。

## 五、重复注解

自从Java 5引入了注解机制，这一特性就变得非常流行并且广为使用。然而，使用注解的一个限制是相同的注解在同一位置只能声明一次，不能声明多次。Java 8打破了这条规则，引入了重复注解机制，这样相同的注解可以在同一地方声明多次。

重复注解机制本身必须用`@Repeatable`注解。事实上，这并不是语言层面上的改变，更多的是编译器的技巧，底层的原理保持不变。让我们看一个快速入门的例子：

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RepeatingAnnotations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    };

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    public static void main(String[] args) {
        for(Filter filter: Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }

}
```

正如我们看到的，这里有个使用`@Repeatable(Filters.class)`注解的注解类`Filter`，`Filters`仅仅是`Filter`注解的数组，但Java编译器并不想让程序员意识到`Filters`的存在。这样，接口`Filterable`就拥有了两次`Filter`（并没有提到`Filter`）注解。

同时，反射相关的API提供了新的函数`getAnnotationsByType()`来返回重复注解的类型（请注意`Filterable.class.getAnnotation(Filters.class`)`经编译器处理后将会返回Filters的实例）。

---

参考文档：

- [What's New in JDK 8](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)
- [Java 8新特性终极指南](http://www.importnew.com/11908.html)
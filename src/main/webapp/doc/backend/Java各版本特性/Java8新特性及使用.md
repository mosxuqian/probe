# Java8新特性及使用

## 新特性列表

以下是Java8中的引入的部分新特性。关于Java8新特性更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)。

- 方法引用
- 接口默认方法和静态方法
- 函数式接口
- Lambda 表达式
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
public static void main( String[] args ) {
    Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
    System.out.println(defaulable.notRequired());

    defaulable = DefaulableFactory.create(OverridableImpl::new);
    System.out.println(defaulable.notRequired());
}
```

在JVM中，默认方法的实现是非常高效的，并且通过字节码指令为方法调用提供了支持。默认方法允许继续使用现有的Java接口，而同时能够保障正常的编译过程。这方面好的例子是大量的方法被添加到`java.util.Collection`接口中去：`stream()`，`parallelStream()`，`forEach()`，`removeIf()`等。

尽管默认方法非常强大，但是在使用默认方法时我们需要小心注意一个地方：在声明一个默认方法前，请仔细思考是不是真的有必要使用默认方法，因为默认方法会带给程序歧义，并且在复杂的继承体系中容易产生编译错误。

---

参考文档：

- [What's New in JDK 8](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)
- [Java 8新特性终极指南](http://www.importnew.com/11908.html)
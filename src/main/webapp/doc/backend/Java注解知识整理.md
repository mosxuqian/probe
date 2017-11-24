# Java注解知识整理

## 概述

### 什么是注解

注解(`Annotation`)是一种应用于类、方法、参数、变量、构造器及包声明中的特殊修饰符，它是一种由JSR-175标准选择用来描述元数据的一种工具。Java从`Java5`开始引入了注解。在注解出现之前，程序的元数据只是通过java注释和javadoc，但是注解提供的功能要远远超过这些。注解不仅包含了元数据，它还可以作用于程序运行过程中、注解解释器可以通过注解决定程序的执行顺序。

比如，下面这段代码：

```java
@Override
public String toString() {
    return "This is String.";
}
```

上面的代码中，我重写了`toString()`方法并使用了`@Override`注解。但是，即使我们不使用`@Override`注解标记代码，程序也能够正常执行。那么，该注解表示什么？这么写有什么好处吗？事实上，`@Override`告诉编译器这个方法是一个重写方法(描述方法的元数据)，如果父类中不存在该方法，编译器便会报错，提示该方法没有重写父类中的方法。如果我不小心拼写错误，例如将`toString()`写成了`toStrring(){double r}`，而且我也没有使用`@Override`注解，那程序依然能编译运行。但运行结果会和我期望的大不相同。现在我们了解了什么是注解，并且使用注解有助于阅读程序。

### 为什么要引入注解

使用注解之前(甚至在使用之后)，XML被广泛的应用于描述元数据。不知何时开始一些应用开发人员和架构师发现XML的维护越来越糟糕了。他们希望使用一些和代码紧耦合的东西，而不是像XML那样和代码是松耦合的(在某些情况下甚至是完全分离的)代码描述。如果你在Google中搜索“XML vs. annotations”，会看到许多关于这个问题的辩论。最有趣的是XML配置其实就是为了分离代码和配置而引入的。上述两种观点可能会让你很疑惑，两者观点似乎构成了一种循环，但各有利弊。下面我们通过一个例子来理解这两者的区别。

假如你想为应用设置很多的常量或参数，这种情况下，XML是一个很好的选择，因为它不会同特定的代码相连。如果你想把某个方法声明为服务，那么使用注解会更好一些，因为这种情况下需要注解和方法紧密耦合起来，开发人员也必须认识到这点。

另一个很重要的因素是注解定义了一种标准的描述元数据的方式。在这之前，开发人员通常使用他们自己的方式定义元数据。例如，使用标记接口，注释，`transient`关键字等等。每个程序员按照自己的方式定义元数据，而不像注解这种标准的方式。

目前，许多框架将`XML`和`Annotation`两种方式结合使用，平衡两者之间的利弊。

## Java基本注解

在`java.lang`包下，JAVA提供了5个基本注解：

- `@Override`：限定重写父类的方法。对于子类中被`@Override`修饰的方法，如果存在对应的被重写的父类方法，则正确；如果不存在，则报错。`@Override`只能作用于方法，不能作用于其他程序元素。
- `@Deprecated`：用于表示某个程序元素（类、方法等）已过时。如果使用了被`@Deprecated`修饰的类或方法等，编译器会发出警告。
- `@SuppressWarnings`：抑制编译器警告。指示被`@SuppressWarnings`修饰的程序元素（以及该程序元素中的所有子元素，例如类以及该类中的方法）取消显示指定的编译器警告。例如，常见的`@SuppressWarnings（value="unchecked"）`。`SuppressWarnings`注解的常见参数值的简单说明：
  - `deprecation`：使用了不赞成使用的类或方法时的警告(使用`@Deprecated`使得编译器产生的警告)；
  - `unchecked`：执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型; 关闭编译器警告
  - `fallthrough`：当 Switch 程序块直接通往下一种情况而没有 Break 时的警告;
  - `path`：在类路径、源文件路径等中有不存在的路径时的警告;
  - `serial`：当在可序列化的类上缺少 serialVersionUID 定义时的警告;
  - `finally`：任何 finally 子句不能正常完成时的警告;
  - `all`：关于以上所有情况的警告。
- `@SafeVarargs`：@SafeVarargs是JDK 7 专门为抑制“堆污染”警告提供的。
- `@FunctionalInterface`：Java8新增的函数式接口。Java8规定：如果接口中只有一个抽象方法（可以包含多个默认方法或多个`static`方法），该接口称为函数式接口。如以下代码：

```java
@FunctionalInterface
public interface Fun {

    static void foo() {
        System.out.println("foo类方法")；
    }

    default void bar() {
        System.out.println("bar默认方法")；
    }

    void test(); //只定义了一个抽象方法

}
```

> **注**：如在上面的接口中再加一个抽象方法`abc()`，则会编译出错。

## 元注解

**元注解(`meta-annotation`)**是指注解的注解。Java5定义了5个标准的元注解类型，它们被用来提供对其它注解的类型作说明。接下来介绍这五个元注解。

### 1. @Retention

`@Retention`指明了该注解被保留的时间长短。包含一个名为`value`的成员变量，该value成员变量是`RetentionPolicy`枚举类型。使用`@Retention`时，必须为其value指定值。value成员变量的值只能是如下3个：

- `SOURCE`：只保留在源代码中，编译器编译时，直接丢弃这种注解，不记录在`.class`文件中。
- `CLASS`：编译器把注解记录在`class`文件中。当运行Java程序时，JVM中不可获取该注解信息，这是默认值。
- `RUNTIME`：编译器把注解记录在`class`文件中。当运行Java程序时，JVM可获取该注解信息，程序可以通过反射获取该注解的信息。

### 2. @Target

`@Target`指定注解用于修饰哪些程序元素。`@Target`也包含一个名为`value`的成员变量，该value成员变量类型为`ElementType[]`，`ElementType`也为枚举类型，值有如下几个：

- `TYPE`：修饰类、接口或枚举类型
- `FIELD`：修饰成员变量（包括枚举常量）
- `METHOD`：修饰方法
- `PARAMETER`：修饰参数
- `CONSTRUCTOR`：修饰构造器
- `LOCAL_VARIABLE`：修饰局部变量
- `ANNOTATION_TYPE`：修饰注解
- `PACKAGE`：修饰包
- `TYPE_PARAMETER`：Java8新增，修饰类型参数。
- `TYPE_USE`：Java8新增，可以在任何类型上使用

#### 类型注解（Java8新增）

在 Java8 之前的版本中，只能允许在声明式前使用注解。而在 Java8 版本中，注解可以被用在任何使用 Type 的地方，例如：初始化对象时 (new)，对象类型转化时，使用 implements 表达式时，或者使用 throws 表达式时。

```java
//初始化对象时
String myString = new @NotNull String();

//对象类型转化时
myString = (@NonNull String) str;

//使用 implements 表达式时
class MyList<T> implements @ReadOnly List<@ReadOnly T>{
    ...
}
 //使用 throws 表达式时
public void validateValues() throws @Critical ValidationFailedException{
    ...
 }
```

定义一个类型的方法与普通的注解类似，只需要指定`Target`为`ElementType.TYPE_PARAMETER`或者`ElementType.TYPE_USE`，或者同时指定这两个`Target`。

```java
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public  @interface MyAnnotation {
    ...
}
```

`ElementType.TYPE_PARAMETER`表示这个注解可以用在 Type 的声明式前，而`ElementType.TYPE_USE`表示这个注解可以用在所有使用 Type 的地方（如：泛型，类型转换等）

与 Java 8 之前的注解类似的是，类型也可以通过设置 Retention 在编译后保留在 class 文件中（RetentionPolicy.CLASS）或者运行时可访问（RetentionPolicy.RUNTIME）。但是与之前不同的是，类型注解有两个新的特性：在本地变量上的注解可以保留在`class`文件中，以及泛型类型可以被保留甚至在运行时被访问。

虽然类型可以保留在 class 文件中，但是它并不会改变程序代码本身的行为。例如在一个方法前加上注解，调用此方法返回的结果和不加注解的时候一致。

Java8 通过引入类型，使得开发者可以在更多的地方使用注解，从而能够更全面地对代码进行分析以及进行更强的类型检查。

### 3. @Inherited

`@Inherited`指定注解具有继承性。如果某个类使用了`@xxx`注解（定义该注解时使用了`@Inherited`修饰）修饰，则其子类将自动被`@xxx`修饰。

### 4. @Documented

如果定义注解A时，使用了`@Documented`修饰定义，则在用Javadoc命令生成API文档后，所有使用注解A修饰的程序元素，将会包含注解A的说明。

### 5. @Repeatable（Java8新增）

`@Repeatable`表示可重复注解。在实际应用中，可能会出现需要对同一个声明式或者类型加上相同的注解（包含不同的属性值）的情况。例如系统中除了管理员之外，还添加了超级管理员这一权限，对于某些只能由这两种角色调用的特定方法，可以使用可重复注解。

```java
@Access(role="SuperAdministrator")
@Access(role="Administrator")
public void doCheck() {
    ...
}
```

Java8之前版本的 JDK 并不允许开发者在同一个声明式前加注同样的注解，（即使属性值不同）这样的代码在编译过程中会提示错误。而 Java8 解除了这一限制，开发者可以根据各自系统中的实际需求在所有可以使用注解的地方使用可重复注解。

由于兼容性的缘故，可重复注解并不是所有新定义的注解的默认特性，需要开发者根据自己的需求决定新定义的注解是否可以重复注解。Java 编译器会自动把可重复注解储存到指定的注解容器中。而为了触发编译器进行这一操作，开发者需要进行以下的定义：

首先，在需要重复标注特性的注解前加上`@Repeatable`标签，示例如下：

```java
@Repeatable(AccessContainer.class)
public @interface Access {

    String role();

}
```

`@Repeatable`标签后括号中的值即为指定的注解容器的类型。在这个例子中，注解容器的类型是`AccessContainer`，Java 编译器会把重复的 Access 对象保存在 AccessContainer 中。

AccessContainer 中必须定义返回数组类型的 value 方法。数组中元素的类型必须为对应的可重复注解类型。具体示例如下：

```java
public @interface AccessContainer {
    Access[] value();
}
```

可以通过 Java 的反射机制获取注解的 Annotation。一种方式是通过 AnnotatedElement 接口的`getAnnotationByType(Class<T>)`。首先获得 Container Annotation，然后再通过 Container Annotation 的 value 方法获得可重复注解。另一种方式是用过 AnnotatedElement 接口的`getAnnotations(Class<T>)`方法一次性返回可重复注解。

可重复注解使得开发者可以根据具体的需求对同一个声明式或者类型加上同一类型的注解，从而增加代码的灵活性和可读性。

---

参考文档：

- [Java注解教程及自定义注解](http://www.importnew.com/17413.html)
- [Java 8 Annotation 新特性在软件质量和开发效率方面的提升](https://www.ibm.com/developerworks/cn/java/j-lo-java8annotation/)
- [Java内置系统注解和元注解](http://blog.csdn.net/u014207606/article/details/52291951)
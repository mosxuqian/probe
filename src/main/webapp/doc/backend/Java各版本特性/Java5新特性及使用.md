# Java5新特性及使用

## 特性列表

- 泛型
- 枚举
- 装箱拆箱
- 变长参数
- 注解
- foreach循环
- 静态导入
- 格式化
- 线程框架/数据结构
- Arrays工具类/StringBuilder/instrument

## 一、泛型(Generics)

### 1. 概述

Java语言引入泛型的好处是安全简单。可以将运行时错误提前到编译时错误。在Java5之前，没有泛型的情况的下，通过对类型Object的引用来实现参数的**任意化**，**任意化**带来的缺点是要做显式的强制类型转换，而这种转换是要求开发者对实际参数类型可以预知的情况下进行的。对于强制类型转换错误的情况，编译器可能不提示错误，在运行的时候才出现异常，这是一个安全隐患。泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，提高代码的重用率。

泛型的本质是**参数化类型**，也就是说所操作的数据类型被指定为一个参数。这种参数类型可以用在类、接口和方法中，分别称为泛型类、泛型接口、泛型方法。

### 2. 泛型类、泛型接口

泛型类中的类型参数几乎可以用于任何可以使用接口名、类名的地方。以下是Jdk中Map接口的定义：

```java
public interface Map<K,V> {

    V get(Object key);

    V put(K key, V value);

}
```

当声明或者实例化一个泛型的对象时，必须指定类型参数的值：

```java
Map<Integer, String> map = new HashMap<Integer, String>();
```

对于常见的泛型模式，推荐的名称是：

- K: 键
- V: 值
- E: 异常类
- T: 泛型

### 3. 泛型方法

#### (1). 定义泛型方法

泛型方法使得该方法能独立于类而产生变化。以下是一个基本的指导原则：**无论何时，只要你能做到，你就应该尽量使用泛型方法**。也就是说，如果使用泛型方法可以取代将整个类泛型化，那么就应该只使用泛型方法，因为它可以使事情更清楚明白。要定义泛型方法，只需**将泛型参数列表置于返回值之前**，就像下面这样：

```java
public class GenericMethods {

    //当方法操作的引用数据类型不确定的时候，可以将泛型定义在方法上
    public <T> void f(T x){
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f(99);
        gm.f("abc");
    }

}
```

#### (2). 可变参数泛型方法

泛型方法与可变参数列表能很好地共存。

```java
public class GenericVarargs {

    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        for(T item:args) {
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        List ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A","B","C");
        System.out.println(ls);
        ls = makeList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }

}
```

> **注**：静态方法无法访问类上定义的泛型。如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。

### 4. 泛型擦除

看以下一段代码：

```java
public class ErasedTypeEquivalence {

    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // 输出true.
    }

}
```

从以上代码的执行结果可以知道，`ArrayList<String>`和`ArrayList<Integer>`是相同的类型。Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。也就是说，成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。

要想在表达式中使用类型，需要显式地传递类型的class对象。

```java
class Building {

}
```

```java
class House extends Building {

}
```

```java
public class ClassTypeCapture<T> {

    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
        System.out.println(ctt1.f(new Building())); // true
        System.out.println(ctt1.f(new House())); // true
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<House>(House.class);
        System.out.println(ctt2.f(new Building())); // false
        System.out.println(ctt2.f(new House())); // true
    }
}
```

### 5. 通配符及泛型边界

- 通配符(`?`): 当操作类型时，不需要使用类型的具体功能时，只使用`Object`类中的功能。那么可以用`?`通配符来表未知类型。例如：`Class<?> classType = Class.forName("java.lang.String");`。
- 上界(`? extends T`): 可以接收`T`类型或者其子类型的对象。
- 下界(`? super E`): 可以接收`T`类型或者其父类型的对象。

### 6. 泛型总结

- 泛型的类型参数只能是类类型，不能是基本数据类型。
- 泛型的类型参数可以有多个。
- 所有泛型类的类型参数在编译时都会被擦除。
- 创建泛型对象时请指明类型，让编译器尽早的做参数检查。
- 不能创建泛型数组。如果想要创建泛型数组，建议使用`ArrayList`。
- 使用带泛型的类创建对象时，等式两边指定的泛型必须一致。
- 泛型的好处：
  - 类型安全。
  - 消除强制类型转换。
  - 提高性能。

---

参考文档:

- [Java5的新特性](https://segmentfault.com/a/1190000004417288)
- [Oracle Java文档](https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html)
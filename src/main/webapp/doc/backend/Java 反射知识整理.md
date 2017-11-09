# Java 反射知识整理

---

## 一、概述

### 1. 简介

Java反射(`Reflection`)机制就是在运行状态中，对于任意一个类，都能够知道这个类的属性和方法。对于任意一个对象能够调用它的任意一个属性和方法。这种动态获取的信息和动态调用对象的方法的功能称为Java语言的反射机制。Java程序中一般的对象的类型都是在编译期就确定下来的，而Java反射机制可以动态地创建对象并调用其属性，这样的对象的类型在编译期是未知的。所以我们可以通过反射机制直接创建对象，即使这个对象的类型在编译期是未知的。

反射的核心是JVM在**运行时**才动态加载类或调用方法/访问属性，它不需要事先（写代码的时候或编译期）知道运行对象是谁。反射机制就是通过`java.lang.Class`类来实现的，在Java中，Object 类是所有类的根类，而Class类就是描述Java类的类。

> **注**：因为Class类也是类，所以Object也包括Class类。

### 2. 主要功能

Java反射框架主要提供以下功能：

- 在运行时判断任意一个对象所属的类；
- 在运行时构造任意一个类的对象；
- 在运行时判断任意一个类所具有的成员变量和方法（通过反射甚至可以调用private方法）；
- 在运行时调用任意一个对象的方法；
- 修改构造函数、方法、属性的可见性。

### 3. 主要用途

**反射最重要的用途就是开发各种通用框架**。很多框架（比如Spring）都是配置化的（比如通过XML文件配置JavaBean,Action之类的），为了保证框架的通用性，它们可能需要根据配置文件加载不同的对象或类，调用不同的方法，这个时候就必须用到反射——运行时动态加载需要加载的对象。对与框架开发人员来说，反射虽小但作用非常大，它是各种容器实现的核心。

## 二、反射的使用

### 1. 获取Class对象

反射的各种功能都需要通过Class对象来实现，因此，需要知道如何获取Class对象，主要有以下几种方式。

#### 使用 Class.forName() 的静态方法

`Class.forName(String className)`方法可以通过类或接口的名称（一个字符串或完全限定名）来获取对应的Class对象。

```java
Class<?> cls = Class.forName("com.blinkfox.Zealot");
```

#### 直接获取某个类的class(最安全/性能最好)

```java
Class<String> cls = String.class;
```

#### 调用某个对象的 getClass() 方法

```java
Class<String> cls = str.getClass();
```

### 2. 判断是否为某个类的实例

一般地，我们用`instanceof`关键字来判断是否为某个类的实例。同时我们也可以借助反射中Class对象的`isInstance()`方法来判断是否为某个类的实例，它是一个Native方法：

```java
public native boolean isInstance(Object obj);
```

### 3. 创建实例

通过反射来生成对象主要有两种方式。

#### 使用Class对象的newInstance()方法

```java
Class<?> c = String.class;
Object str = c.newInstance();
```

#### 通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法

```java
// 获取String所对应的Class对象
Class<?> c = String.class;
// 获取String类带一个String参数的构造器
Constructor constructor = c.getConstructor(String.class);
// 根据构造器创建实例
Object obj = constructor.newInstance("23333");
System.out.println(obj);
```

> **注**：这种方法可以用指定的构造器构造类的实例。
# MVEL 2.x模板指南

## 简介

> [MVEL][1]最初作为Mike Brock创建的 Valhalla项目的表达式计算器（expression evaluator）。Valhalla本身是一个早期的类似 Seam 的“开箱即用”的Web 应用框架，而 Valhalla 项目现在处于休眠状态， MVEL则成为一个继续积极发展的项目。相比最初的OGNL、JEXL和JUEL等项目，而它具有远超它们的性能、功能和易用性 - 特别是集成方面。它不会尝试另一种JVM语言，而是着重解决嵌入式脚本的问题。关于MVEL的语法请参考[MVEL 2.x语法指南][2]

MVEL 2.0提供了一个新的，更强大的，统一的模板引擎，汇集了1.2中引入的许多模板概念。 不幸的是，1.2中的模板引擎的架构不足以用于常规维护，并且决定从头开始完全重写模板引擎。

## 一、MVEL 2.0基本模板

VEL模板由纯文本文档中的`orb-tags`组成。 Orb标记表示引擎将在运行时计算模板的动态元素。

如果你熟悉FreeMarker，这种类型的语法将不会完全陌生。

### 1. 一个简单的模板

```java
Hello, @{person.getSex() == 'F' ? 'Ms.' : 'Mr.'} @{person.name}

This e-mail is to thank you for your interest in MVEL Templates 2.0.
```

此模板展示了可以在简单文本中嵌入表达式。当计算结果时，输出可能如下所示：

```java
Hello, Ms. Sarah Peterson

This e-mail is to thank you for your interest in MVEL Templates 2.0.
```

### 2. 转义@符号


当然，由于@符号用于表示`orb-tag`的开头，因此您可能需要对其进行转义，以防止其被编译器处理。幸运的是，只有一种情况，即当你实际上需要输出'@{'字符串在您的模板上时。

由于编译器需要@和{组合触发orb识别，你可以自由使用@符号而不转义它们。例如：

```java
Email any questions to: foo@bar.com

@{date}
@include{'disclaimer.html'}
```

但是在你需要一个@符号挨着一个orb-tag的情况下，你需要通过重复它两次来避免它：

```java
@{username}@@@{domain}
```

这是两个@转义一个符号，第三个@是标签的开始。如果你感觉这看起来太乱，你可以使用替代方法，即使用表达式标签，如下所示：

```java
@{username}@{'@'}@{domain}
```

## 二、MVEL 2.0 Orb标签

本文包含了MVEL 2.0模板引擎中所有开箱即用的orb标签。

### 1. @{}表达式

@{}表达式是orb-tag的最基本形式。它包含一个对字符串求值的值表达式，并附加到输出模板中。例如：

```java
Hello, my name is @{person.name}
```

### 2. @code{}静默代码标签

静默代码标记允许您在模板中执行MVEL表达式代码。它不返回值，并且不以任何方式影响模板的格式。

```java
@code{age = 23; name = 'John Doe'}
@{name} is @{age} years old
```
该模板将计算出：John Doe is 23 years old。

### 3. @if{}@else{}控制流标签

@if{}和@else{}标签在MVEL模板中提供了完全的if-then-else功能。 例如：

```java
@if{foo != bar}
   Foo not a bar!
@else{bar != cat}
   Bar is not a cat!
@else{}
   Foo may be a Bar or a Cat!
@end{}
```

MVEL模板中的所有块必须用`@end{}`标签来终止，除非是`if-then-else`结构，其中`@else{}`标记表示前一个控制语句的终止。

[1]: https://github.com/mvel/mvel
[2]: http://blinkfox.com/mvel-2-xyu-fa-zhi-nan/
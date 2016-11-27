# MVEL 2.x语法指南

[MVEL][1]全称为：MVFLEX Expression Language，是用来计算Java语法所编写的表达式值的表达式语言。MVEL的语法很大程度上受到Java语法的启发，但为了使表达式语法更高效,还是有一些基本差异，例如可以像正则表达式一样直接支持集合、数组和字符串匹配的运算。

除了表达式语言之外，MVEL还用作配置和字符串构造的模板语言。这里还有一个关于[MVEL][2]介绍信息的wiki页面是：https：//en.wikipedia.org/wiki/MVEL。

MVEL 2.x表达式主要包括以下特性：

- 属性表达式
- 布尔表达式
- 方法调用
- 变量赋值
- 函数定义

## 一、基本语法

MVEL是基于Java语法的表达式语言，具有特定于MVEL的一些明显差异。与Java不同，MVEL是动态类型化（可选类型化），意味着在源代码中不需要类型限定。

MVEL可以方便的集成到产品中使用。Maven的集成方式如下：

```xml
<dependency>
    <groupId>org.mvel</groupId>
    <artifactId>mvel2</artifactId>
    <version>2.2.8.Final</version>
</dependency>
```

一个MVEL表达式，简单的可以是单个标识符，复杂的则可能是一个充满了方法调用和内部集合创建的庞大的布尔表达式。使用MVEL提供的API。可以动态得到表达式的执行结果。

### 1. 简单属性表达式

```java
user.name
```

在这个表达式中，我们只有一个标识符（user.name），在MVEL中我们称它为属性表达式，因为表达式的唯一目的就是从上下文中提取出变量或者对象的属性。属性表达式是最常见的用途之一，通过它，MVEL可以用来作为一个高性能，易使用的反射优化器。

MVEL甚至可以用来计算布尔表达式：

```java
user.name =='John Doe'
```

与Java一样，MVEL支持所有优先级规则，包括通过括号来控制执行顺序。

```java
(user.name == 'John Doe') && ((x * 2) - 1) > 20
```

### 2. 复合语句

您可以使用分号来表示语句的终止，使用任意数量的语句编写脚本。分号在所有情况下都是必需的，除非在脚本中只有一个语句或最后一个语句。

```java
statement1; statement2; statement3
```

> **注意**：statement3语句后可以缺少分号。

另外，换行不能替代分号来作为一个语句的结束标识。

### 3. 返回值

MVEL是被设计为一个集成语言作为核心，允许开发人员提供简单的脚本设置绑定和逻辑。因此，MVEL表达式使用“last value out”原则（输出最后值原则）。这意味着，尽管MVEL支持return关键字，但却没必要使用它。例如：

```java
a = 10;
b = (a = a * 2) + 10;
a;
```

在该示例中，表达式返回a的值，因为`a;`是表达式的最后一个值。它在功能上与下面的脚本等价：

```java
a = 10;
b = (a = a * 2) + 10;
return a;
```

[1]: https://github.com/mvel/mvel
[2]: https：//en.wikipedia.org/wiki/MVEL



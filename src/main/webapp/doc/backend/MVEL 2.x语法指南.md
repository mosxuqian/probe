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

## 二、值判断

在MVEL中所有的判断是否相等，都是对值的判断，而没有对引用的判断，因此表达式`foo == 'bar'`等价于Java中的`foo.equals("bar")`。

### 1. 判断空值

MVEL提供了一个特殊的字符来表示值为空的情况，叫作`empty`，例如：

```java
foo == empty
```

若foo满足空的任何条件，这个表达式值都为true。

### 2. 判断Null值

MVEL中，`null`和`nil`都可以用来表示一个Null值，如：

```java
foo == null;
foo == nil; // 和null一样
```

### 3. 强制转换

当两个不同类型且没有可比性的值进行比较时，MVEL会应用类型强制转换系统，即将左边的值强制转换成右边的值的类型，反之亦然。如：

```java
"123" == 123;
```

这个表达式的值为true,因为为了执行比较，强制类型转换系统会隐式的将数字`123`转换成字符串。

## 三、内联Lists、Maps和数组Arrays

MVEL允许你使用简单优雅的语法来表示Lists，Mpas和数组Arrays。 且看下面的示例：

```java
["Bob" : new Person("Bob"), "Michael" : new Person("Michael")]
```

这个表达式的功能等价于：

```java
Map map = new HashMap();
map.put("Bob", new Person("Bob"));
map.put("Michael", new Person("Michael"));
```

用这种结构描述MVEL内部数据结构，功能非常强大，你可以在任何地方使用它，甚至可以作为方法的参数使用，如：

```java
something.someMethod(["foo" : "bar"]);
```

### 1. Lists

Lists用以下格式来表示："[item1, item2, ...]"，如：

```java
["Jim", "Bob", "Smith"]
```

### 2. Maps

Maps用以下格式来表示："[key1 : value1, key2: value2, …]"，如：

```java
["Foo" : "Bar", "Bar" : "Foo"]
```

### 3. 数组Arrays

数组Arrays用以下格式来表示："{item1, item2, …}"，如：

```java
{"Jim", "Bob", "Smith"}
```

### 4. 数组强制转换

关于内联数组，需要知道的一个非常重要的方面是，它可以被强制转换成其它类型的数组，当你声明一个数组时，是不直接指定其类型的，但你可以通过将其传递给一个接收int[]类型参数的方法来指定。如：

```java
foo.someMethod({1,2,3,4});
```

在这种情况下，当MVEL发现目标方法接收的是一个int[]，会自动的将{1,2,3,4}转换成int[]类型。

## 四、属性导航

MVEL属性导航遵循在其他语言（如Groovy，OGNL，EL等）中bean属性表达式中公认惯例的使用方式。和其它语言必须通过底层的方法来控制权限不同的是，MVEL提供了一种单一的，统一的语法来访问属性，静态字段和maps等。

### 1. Bean属性

大多数java开发者都熟悉getter/setter模式，并在java对象中用它来封装属性的访问权限。例如，你可能会通过下面的方式访问一个对象的属性：

```java
user.getManager().getName();
```

为了简化此操作，您可以使用以下表达式访问相同的属性：

```java
user.manager.name
```

> **注意：**当一个对象中的字段的作用域是public时，MVEL仍然倾向于通过get方法来访问其属性。

### 2. Bean的安全属性导航

有时，当你的表达式中会含有null元素时，这时就需要你进行一个为空判断，否则就会发生错误。当你使用null-safe操作符时你可以简化这个操作：

```java
user.?manager.name
```

它的功能相当于：

```java
if (user.manager != null) { return user.manager.name; } else { return null; }
```

### 3. 集合

集合的遍历也可以通过简单的语法来实现：

#### (1). List的访问

List可以像访问数组一样访问，如：

```java
user[5]
```

这等价与java中的代码：

```java
user.get(5);
```

#### (2). Map的访问

Map的访问和访问数组也非常相似，不同的是，在访问Map时索引值可以是任意对象，如：

```java
user["foobar"]
```

这等价与java中的代码：

```java
user.get("foobar");
```

当Map的key是String类型时，还可以使用特殊的方式来访问，如：

```java
user.foobar
```

### 4. 字符串作数组

为了能使用属性的索引（迭代也是如此），所有的字符串都可以看成是一个数组，在MVEL中你可以用下面的方式来获取一个字符串变量的第一个字符：

```java
foo = "My String";
foo[0]; // returns 'M'
```

## 五、文字常量

在脚本语言中，一段文字（常量）用来代表一个固定的值。

### 1. 字符串常量

字符串常量可以用一对单引号或一对双引号来界定。如：

```java
"This is a string literal"
'This is also string literal'
```

#### 字符串转义字符

- \\ - 代表一个反斜杠。
- \n - 换行符
- \r - 回车符
- \u#### - Unicode字符 (如: /uAE00)
- \### - 八进制字符 (如: /73)

### 2. 数字常量

整数可以表示为十进制（基数为10），8进制（基数为8），或十六进制（基数为16）。

一个十进制数字，不从零开始（相对于8进制、16进制而言），可以表示任意数，如：

```java
125 // 十进制
```

一个八进制数，以0为前缀，后面跟着0到7内的数字。

```java
0353 // 八进制
```

一个十六进制，以0X为前缀，后面可以跟着0-9，A-F范围内的数字。

```java
0xAFF0 // 十六进制
```

### 3. 浮点型常量

浮点数由整数和由点/周期字符表示的小数部分组成，带有可选的类型后缀。

```java
10.503 // double型
94.92d // double型
14.5f // float型
```

### 4. 大数字常量

您可以使用后缀B和I（必须大写）来表示BigDecimal和BigInteger文字，如：

```java
104.39484B // BigDecimal
8.4I // BigInteger
```

### 5. 布尔常量

布尔型常量用保留关键字true和false来表示。

### 6. 空常量

用null或nil来表示。

## 六、类型常量

类型常量的处理方式与Java中的相同，格式为："<PackageName>.<ClassName>"。

所以一个类可以这样限定：

```java
java.util.HashMap
```

或者如果类已经通过或者通过外部配置被导入，则它被简单地通过其非限定名称来引用：

```java
HashMap
```

### 嵌套类

嵌套类不能通过MVEL 2.0中的标准点表示法（如Java中）来访问。 相反，你必须用$符号限定这些类。

```java
org.proctor.Person$BodyPart
```

[1]: https://github.com/mvel/mvel
[2]: https：//en.wikipedia.org/wiki/MVEL
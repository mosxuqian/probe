# JavaScript基础教程

标签： Javascript

---

## 一、语言的性质（The nature of the language）

本节对`JavaScript`的性质做简要介绍，以帮你理解一些疑问。

`JavaScript`和`ECMAScript`（JavaScript versus ECMAScript）
编程语言称为`JavaScript`，语言标准被称为`ECMAScript`。他们有不同名字的原因是因为“Java”已经被注册为商标（属于Oracle）。目前，只有`Mozilla`被正式允许使用“JavaScript”名称，因为很久以前他们得到一份许可。因此，开放的语言标准拥有不同的名字。当前的`JavaScript`版本是`ECMAScript 6`，`ECMAScript 7`当前是开发版。

### 影响（Influences）

`JavaScript`之父，`Brendan Eich`别无选择必须[迅速创建一门语言][1]。（否则，会更糟糕，Netscape将使用其他技术）。他借鉴了几门其他语言：

- JavaScript借鉴了Java的语法和如何区分原始值和对象。
- JavaScript的函数设计受Scheme和AWK的启发——他们（的函数）都是第一类（first-class）对象，并且在语言中广泛使用。闭包使他们（函数）变成强大的工具。
- Self影响了JavaScript独一无二的面向对象编程(OOP)风格。它的核心思想（在这里我们没有提到）非常优雅，基于此创建的语言非常少。但后面会提到一个简单的模式照顾大部分用例。JavaScript面向对象编程的杀手级特性是你可以直接创建对象。不需要先创建类或其他类似的东西。
- Perl和Python影响了JavaScript字符串，数组和正则表达式的操作。

`JavaScript`直到`ECMAScript 3`才加入异常处理，这解释了为什么这门语言经常自动转换类型和经常静默失败：最初没有抛出异常的功能。

一方面，`JavaScript`有很多怪癖，并且缺失很多功能（块级变量作用域（`block-sciped variables`），模块（modules）支持子类型（subtyping）等）。另一方面，它有几个非常强大的特性，允许你弥补上面的问题。在其他语言中，你要学习语言特性。在JavaScript中，你需要经常学习模式代替。

  [1]: http://yanhaijing.com/javascript/2013/06/22/javascript-designing-a-language-in-10-days/
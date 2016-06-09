# JavaScript基础教程

标签： Javascript

---

## 前言

[JavaScript][1]是目前所有主流浏览器上唯一支持的脚本语言，这也是早期`JavaScript`的唯一用途。其主要作用是在不与服务器交互的情况下修改`HTML`页面内容，因此其最关键的部分是`DOM`（文档对象模型），也就是`HTML`元素的结构。通过`Ajax`可以使`HTML`页面通过`JavaScript`，在不重新加载页面的情况下从服务器上获取数据并显示，大幅提高用户体验。通过`JavaScript`，使`Web`页面发展成胖客户端成为可能。

## 一、语言的性质（The nature of the language）

本节对`JavaScript`的性质做简要介绍，以帮你理解一些疑问。

`JavaScript`和`ECMAScript`（JavaScript versus ECMAScript）
编程语言称为`JavaScript`，语言标准被称为`ECMAScript`。他们有不同名字的原因是因为“Java”已经被注册为商标（属于Oracle）。目前，只有`Mozilla`被正式允许使用“JavaScript”名称，因为很久以前他们得到一份许可。因此，开放的语言标准拥有不同的名字。当前的`JavaScript`版本是`ECMAScript 6`，`ECMAScript 7`当前是开发版。

### 影响（Influences）

`JavaScript`之父，`Brendan Eich`别无选择必须[迅速创建一门语言][2]。（否则，会更糟糕，Netscape将使用其他技术）。他借鉴了几门其他语言：

- JavaScript借鉴了Java的语法和如何区分原始值和对象。
- JavaScript的函数设计受Scheme和AWK的启发——他们（的函数）都是第一类（first-class）对象，并且在语言中广泛使用。闭包使他们（函数）变成强大的工具。
- Self影响了JavaScript独一无二的面向对象编程(OOP)风格。它的核心思想（在这里我们没有提到）非常优雅，基于此创建的语言非常少。但后面会提到一个简单的模式照顾大部分用例。JavaScript面向对象编程的杀手级特性是你可以直接创建对象。不需要先创建类或其他类似的东西。
- Perl和Python影响了JavaScript字符串，数组和正则表达式的操作。

`JavaScript`直到`ECMAScript 3`才加入异常处理，这解释了为什么这门语言经常自动转换类型和经常静默失败：最初没有抛出异常的功能。

`JavaScript`在最初的时候并不是一个完善的语言，因此也导致`JavaScript`遗留了很多令人诟病的问题。在开发稍大规模的应用时会显得力不从心，但是由于`JavaScript`本身是一种非常灵活的语言，因此在它的基础上开发程序库比较容易，因此出现了一大批非常优秀的第三方库，如[jQuery][3]，[ExtJS][4]，[underscorejs][5]，[backbone][6]等等，由于这些第三方库，`JavaScript`变得非常简单。其中`jQuery`的使用非常广泛，它大幅简化了`DOM`和`Ajax`，已经成为了很多网站的标配。`jQuery`虽然基于`JavaScript`，但它提供了另外一种编程范式，也就是逻辑式编程，与`SQL`和正则表达式类似。

  [1]: https://developer.mozilla.org/zh-CN/docs/Web/JavaScript
  [2]: http://yanhaijing.com/javascript/2013/06/22/javascript-designing-a-language-in-10-days/
  [3]: http://jquery.com/
  [4]: http://extjs.org.cn/
  [5]: http://underscorejs.org/
  [6]: http://backbonejs.org/
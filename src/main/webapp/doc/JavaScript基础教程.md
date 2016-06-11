# JavaScript基础教程

标签： Javascript

---

## 一、JavaScript介绍

[JavaScript][1]是目前所有主流浏览器上唯一支持的脚本语言，这也是早期`JavaScript`的唯一用途。其主要作用是在不与服务器交互的情况下修改`HTML`页面内容，因此其最关键的部分是`DOM`（文档对象模型），也就是`HTML`元素的结构。通过`Ajax`可以使`HTML`页面通过`JavaScript`，在不重新加载页面的情况下从服务器上获取数据并显示，大幅提高用户体验。通过`JavaScript`，使`Web`页面发展成胖客户端成为可能。

### 语言的性质

本节对`JavaScript`的性质做简要介绍，以帮你理解一些疑问。

`JavaScript`和`ECMAScript`（JavaScript versus ECMAScript）
编程语言称为`JavaScript`，语言标准被称为`ECMAScript`。他们有不同名字的原因是因为“Java”已经被注册为商标（属于Oracle）。目前，只有`Mozilla`被正式允许使用“JavaScript”名称，因为很久以前他们得到一份许可。因此，开放的语言标准拥有不同的名字。当前的`JavaScript`版本是`ECMAScript 6`，`ECMAScript 7`当前是开发版。

`JavaScript`之父，`Brendan Eich`[迅速了创建一门编程语言][2]。（否则，Netscape将使用其他技术）。他借鉴了几门其他语言的一些特性：

- JavaScript借鉴了Java的语法和如何区分原始值和对象。
- JavaScript的函数设计受Scheme和AWK的启发——他们（的函数）都是第一类（first-class）对象，并且在语言中广泛使用。闭包使他们（函数）变成强大的工具。
- Self影响了JavaScript独一无二的面向对象编程(OOP)风格。它的核心思想（在这里我们没有提到）非常优雅，基于此创建的语言非常少。但后面会提到一个简单的模式照顾大部分用例。JavaScript面向对象编程的杀手级特性是你可以直接创建对象。不需要先创建类或其他类似的东西。
- Perl和Python影响了JavaScript字符串，数组和正则表达式的操作。

`JavaScript`在最初的时候并不是一个完善的语言，因此也导致`JavaScript`遗留了很多令人诟病的问题。在开发稍大规模的应用时会显得力不从心，但是由于`JavaScript`本身是一种非常灵活的语言，因此在它的基础上开发程序库比较容易，因此出现了一大批非常优秀的第三方库，如[jQuery][3]，[ExtJS][4]，[underscorejs][5]，[backbone][6]等等，由于这些第三方库，`JavaScript`变得非常简单。其中`jQuery`的使用非常广泛，它大幅简化了`DOM`和`Ajax`，已经成为了很多网站的标配。`jQuery`虽然基于`JavaScript`，但它提供了另外一种编程范式，也就是逻辑式编程，与`SQL`和正则表达式类似。

### JavaScript能做什么

![JavaScript项目在Github所占比例][7]

如上图，`JavaScript`作为[Github][8]上最流行、最火的编程语言，几乎无所不能。这里是[PuYart][9]的关于[`JavaScript`就要统治世界了][10]的文章，可以让我们了解`JavaScript`到底能做什么的一些介绍。

1. Web前端(各种前端工具类库、前端框架、动画效果、数据可视化等)
2. 服务端开发([Node.js][11])
3. 移动应用或者`Hybrid App`(Cordova)
4. 桌面应用([NW.js][12]、[Electron][13])
5. 游戏([Unity3D][14]、[Cocos2d-js][15]、[Pomelo][16])
6. VR([JavaScript在VR世界的应用][17])
7. 硬件、嵌入式物联网等([Tessel：用JavaScript做嵌入式开发][18])
8. 操作系统([NodeOS][19])

> Atwood's Law: any application that can be written in JavaScript, will eventually be written in JavaScript.(Atwood定律：凡是能用JavaScript写出来的，最终都会用JavaScript写出来。)

## 二、 JavaScript语法

### 语句和表达式

了解`JavaScript`的语法，先来了解两个主要的语法类型：语句和表达式。

- 语句通常是“做某些事情”。程序是一组语句的序列。举个例子，下面声明（创建）一个变量 `foo`：

```javascript
var foo;
```

- 表达式是产生“值”。他们通常位于赋值操作的右边、函数参数等。举个例子：

```javascript
3 * 7
```

语句和表达式之间的区别最好通过实例说明，`JavaScript`（像Java）有两种不同的方式实现`if-then-else`。一种是用语句：

```javascript
var x;
if (y >= 0) {
    x = y;
} else {
    x = -y;
}
```

另一种是表达式：

```javascript
var x = y >= 0 ? y : -y;
```

你可以将后者作为函数参数（但前者不行）：

```javascript
myFunction(y >= 0 ? y : -y)
```

最后，每当`JavaScript`期待一个语句，你也可以用一个表达式代替。例如：

```javascript
foo(bar(7, 1));
```

`foo(...);`是一个语句（也叫做表达式语句），`bar(7, 1)`则是一个表达式。他们都实现函数调用。

### 流程控制语句和语句块

流程控制语句，其语句体可以是单条语句。举两个例子：

```javascript
if (obj !== null) obj.foo();

while (x > 0) x--;
```

然而，任何语句总能被语句块代替，花括号包含零或多条语句。因此，你也可以这样写：

```javascript
if (obj !== null) {
    obj.foo();
}

while (x > 0) {
    x--;
}
```

为便于程序的阅读和维护，推荐使用后一种方式，即语句块方式。

### 分号

`JavaScript`中的分号是[可选的][20]。但省略（分号）可能会带来意想不到的结果，所以我建议还是写上分号。

正如上面所看到的，分号作为语句的结尾，但语句块不需要。仅有一种情况下你能看到语句块后面有分号——**函数表达式后面的函数体块**。**表达式作为语句的结尾，后面是分号**：

```javascript
var x = 3 * 7;
var f = function () { };
```

### 注释

`JavaScript`的注释有两种形式：单行注释和多行注释。单行注释以`//`开头，以换行符结尾：

```javascript
x++; // 单行（single-line）注释
```

多行注释用`/**/`包裹

```javascript
/*
 这是多行注释
 多行哦
 */
```

  [1]: https://developer.mozilla.org/zh-CN/docs/Web/JavaScript
  [2]: http://yanhaijing.com/javascript/2013/06/22/javascript-designing-a-language-in-10-days/
  [3]: http://jquery.com/
  [4]: http://extjs.org.cn/
  [5]: http://underscorejs.org/
  [6]: http://backbonejs.org/
  [7]: http://static.blinkfox.com/js1.png
  [8]: https://github.com/
  [9]: https://segmentfault.com/u/puyart
  [10]: https://segmentfault.com/a/1190000003767058
  [11]: https://nodejs.org/
  [12]: http://nwjs.io/
  [13]: http://electron.atom.io/
  [14]: http://unity3d.com/cn/
  [15]: http://www.cocos.com/doc/article/index?type=cocos2d-x&url=/doc/cocos-docs-master/manual/framework/cocos2d-js/catalog/../1-about-cocos2d-js/1-1-a-brief-history/zh.md
  [16]: http://pomelo.netease.com/
  [17]: https://www.phodal.com/blog/why-javascript-will-use-vr-world/
  [18]: http://blog.jobbole.com/46055/
  [19]: http://node-os.com/
  [20]: http://www.2ality.com/2011/05/semicolon-insertion.html
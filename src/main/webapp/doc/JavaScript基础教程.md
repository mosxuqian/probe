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

## 三、变量和赋值

`JavaScript`中的变量在使用前必须先声明，否则会报错引用错误（Reference Error）：

```javascript
var foo;  // 声明变量“foo”
```

### 赋值

你可以在声明变量的同时为其赋值：

```javascript
var foo = 6;
```

你也可以给已经存在的变量重新赋值：

```javascript
foo = 4;  // 更改变量的值
```

### 复合赋值操作符

有很多复合赋值操作符，例如+=。下面的两个赋值操作等价：

```javascript
x += 1;
x = x + 1;
```

### 标识符和变量名

标识符就是事物的名字，在`JavaScript`中他们扮演不同的语法角色。例如，变量的名称是一个标识符。

大体上，标识符的第一个字符可以是任何`Unicode`字符、美元标志符（$）或下划线（_）。后面可以是任意字符和数字。因此，下面全是合法的标识符：

```javascript
arg0
_tmp
$elem
π
```

**注意：首字符不能是数字，如果是数字的话，该如何区分是数字还是变量呢？**

一些标识符是“保留关键字”——他们是语法的一部分，不能用作变量名。从技术上讲，下面三个标识符不是保留字，但也不应该作为变量名：

```javascript
Infinity NaN undefined
```

## 四、值

`JavaScript`有所有我们期待的编程语言值类型：布尔，数字，字符串，数组等。`JavaScript`中的所有值都有属性。每个属性有一个键（或名字）和一个值。你可以使用点（.）操作符读取属性：

```javascript
value.propKey
```

举个例子：字符串`abc`有属性`lenght`（长度）

```javascript
var str = 'abc';
console.log(str.length); // 得到3
```

上面的代码也可以写成下面这样：

```javascript
'abc'.length // 得到3
```

点操作符也可以用来给属性赋值：

```javascript
var obj = {};  // 空对象
obj.foo = 123; // 创建属性“foo”，设置它为123
console.log(obj.foo); // 得到123
```

你也可以通过它（.）调用方法：

```javascript
'hello'.toUpperCase(); // 得到HELLO
```

上面，我们在值`hello`上面调用方法`toUpperCase()`。

### 原始类型值和对象

JavaScript定义了不同值之间的区别：

- 原始值包括：`boolean`，`number`，`string`，`null`和`undefined`。
- 所有其他的值都是对象。实际上对象被定义为——所有不为原始值的值。

两者之间的主要区别在于他们是如何被比较的：每一个对象有一个独一无二的标志，并且仅和自己相等：

```javascript
var obj1 = {};  // 一个空对象
var obj2 = {};  // 另一个空对象
obj1 === obj2   // false
obj1 === obj1   // true
```

相反，所有原始值只要编码值相同就被认为是相同的：

```javascript
var prim1 = 123;
var prim2 = 123;
prim1 === prim2 // true
```

### 原始类型值

下面全是原始类型值（简称：原始值）：

- 布尔类型：true，false
- 数字类型：1736，1.351
- 字符串类型: ‘abc’，”abc”
- 两个“无值（non-values）”：undefined，null
原始值的特征：

- **值做比较时,“内容”做比较**。

```javascript
3 === 3 // true
'abc' === 'abc' // true
```

- **无法更改**：值的属性无法更改，无法添加和移除属性，获取未知属性总返回undefined。

```javascript
var str = 'abc';
str.foo = 3; // try to create property `foo` ⇒ no effect
str.foo  // unknown property ⇒  undefined
```

### 对象

#### 对象的类型

所有非原始值的值都是对象。最常见的几种对象类型是：

- 简单对象（类型是`Object`）能通过对象字面量创建：

```javascript
{
    firstName: ‘Jane’,
    lastName: ‘Doe’
}
```

上面的对象有两个属性：`firstName`属性的值是“Jane”，`lastName`属性的值是“Doe”。

- 数组（类型是`Array`）能通过数组字面量创建：

```javascript
[ ‘apple’, ‘banana’, ‘cherry’ ]
```

上面的数组有三个元素，可以通过数字索引访问。例如“apple”的索引是0。

- 正则表达式对象（类型是`RegExp`）能通过正则表达式字面量创建。

```javascript
/^a+b+$/
```

#### 对象的特征

- **比较的是引用**：比较的是标识符，每个值有自己的标识符。

```javascript
{} === {}  // 两个不同的空对象, false
var obj1 = {};
var obj2 = obj1;
obj1 === obj2   // true
```

- **默认可以更改**。

```javascript
var obj = {};
obj.foo = 123;
obj.foo //123
```

所有的数据结构（如数组）都是对象，但并不是所有的对象都是数据结构。例如：正则表达式是对象，但不是数据结构。

### undefined 和 null

`JavaScript`有两个“无值）”：`undefined`和`null`。

`undefined`的意思是“没有值”。未初始化的变量是`undefined`：

```javascript
var foo;
foo // undefined
```

读取不存在的属性时，将返回`undefined`：

```javascript
  > var obj = {}; // 空对象
  > obj.foo // undefined
```

缺省的参数也是`undefined`：

```javascript
function f(x) {
    return x;
}
f(); //undefined
```

`null`的意思是“没有对象”。它被用来表示对象的无值（参数，链上的对象等）。

通常情况下你应该把`undefined`和`null`看成是等价的，如果他们代表相同意义的无值的话。检查他们的一种方式是通过严格比较：

```javascript
if (x === undefined || x === null) {
    ...
}
```

另一种在实际中使用的方法是认为undefined 和 null 都是false：

```javascript
if (!x) {
    ...
}
```

> **警告**：false，0，NaN 和 “” 都被当作false。

### 包装类型

对象类型的实例`Foo`（包括内建类型，例如Array和其他自定义类型）从对象`Foo.prototype`上获取方法。你可以通过读取这个方法的方式（不是调用）验证这点：

```javascript
[].push === Array.prototype.push  // true
```

相反，**原始类型是没有类型的，所以每个原始类型有一个关联类型，称之为包装类型**：

- 布尔值的包装类型是 Boolean。布尔值从Boolean.prototype上获取方法：

```javascript
  > true.toString === Boolean.prototype.toString    //true
```

> 注意：包装类型名字的首字母是大写的B。如果在JavaScript中布尔值的类型可以访问，那么它可能会被转换为布尔对象。

- 数字值的包装类型是`Number`。
- 字符串值的包装类型是`String`。

包装类型也有实例（他们的实例是对象），但不常用。相反，包装类型有其他用处：**如果你将他们作为函数调用，他们可以将值转换为原始类型**。

```javascript
Number('123') //123
String(true)  //'true'
```

### 通过typeof和instanceof将值分类

有两个操作符可以用来将值分类：`typeof`主要用于原始值，`instanceof`主要用于对象。

#### typeof 使用方法如下：

`typeof «value»`

`typeof`返回描述`value`“类型”的一个字符串。例如：

```javascript
typeof true //'boolean'
typeof 'abc' //'string'
typeof {} // 空对象字面量,'object'
typeof [] // 空数组字面量,'object'
```

下面列出了`typeof`操作的所有结果：

```
操作数 结果
undefined	'undefined'
null	'object'
Boolean value	'boolean'
Number value	'number'
String value	'string'
Function	'function'
All other values	'object'
```

有两个结果和我们上面说的的原始值与对象是矛盾的：

- 函数的类型是`function`而不是`object`。因为函数（类型为“function”）是对象（类型是对象）的子类型，这不是一个错误。
- `null`的类型是`object`。这是一个bug，但从没被修复，因为修复后会破坏现有的代码。

#### instanceof使用方法如下：

`«value» instanceof «Constr»`

如果`value`是一个对象，并且`value` 是由构造函数`Constr`创建的（参考：类）。例如：

```javascript
var b = new Bar();  // 通过构造函数Bar创建对象
b instanceof Bar    //true
{} instanceof Object    //true
[] instanceof Array //true
```

### 深入阅读

- [探索JavaScript中Null和Undefined的深渊][21]

## 五、布尔

布尔类型原始值包括`true`和`false`。下面的操作符会得到布尔值：

- 二元逻辑运算符：&&（与），||（或）
- 前缀逻辑运算符：!（非）
- 等值运算符：=== !== == !=
- 比较运算符（字符串或数字）：> >= < <=

### 真值和假值

每当`JavaScript`希望一个布尔值时（例如：if语句的条件），可以使用任何值。它将被理解（转换）为`true`或`false`。下面的值被理解为`false`：

- undefined, null
- 布尔: false
- 数字: 0, NaN
- 字符串: ‘’

所有其他值被认为`true`。被理解为`false`的值称为假值，被理解为`true`的值称为真值。可以使用`Boolean`作为函数，测试值被理解为什么。

```javascript
Boolean(undefined)  //false
Boolean(0)    //false
Boolean(3)    //true
```

### 二元逻辑运算符

`JavaScript`中的**二元逻辑运算符是短路运算**——如果第一个操作数可以确定结果，第二个操作数将不被验证（运算）。例如，在下面的代码中，函数`foo()`永远不会被调用。

```javascript
false && foo()
true || foo()
```

此外，**二元逻辑运算符会返回操作数中的一个**，可能是一个布尔值，也可能不是。

- **与**：如果第一个操作数是假值，返回第一个。否则返回第二个操作数。

```javascript
NaN && 'abc'    //NaN
123 && 'abc'    //'abc'
```

- **或**：如果第一个操作数是真值，返回第一个。否则，返回第二个操作数。

```javascript
'abc' || 123    //'abc'
'' || 123   //123
```

### 等值运算符

在`JavaScript`中检测相等，你可以使用严格相等（`===`）和严格不等（`!==`）。或者你也可以使用非严格相等（`==`）和非严格不等（`!=`）。

> **经验规则：总是用严格运算符，假装非严格运算符不存在。严格相等更安全。**

### 深入阅读

- [在JavaScript中什么时候使用==是正确的？][22]

## 六、数字

`JavaScript`中的**所有数字都是浮点型**（虽然大部分的JavaScript引擎内部也使用整数）。至于为什么这样设计，查看这里（[每一个JavaScript开发者应该了解的浮点知识][23]）。

```javascript
1 === 1.0   //true
```

特殊数字：

- `NaN` (“不是一个数字 not a number”): 错误值。

```javascript
Number('xyz')  // 'xyz' 不能被转换为数字得到:NaN
```

- `Infinity`：也是最大错误值（无穷大）

```javascript
3 / 0   //Infinity
Math.pow(2, 1024)  // 数字太大了,得到Infinity
```

`Infinity`有时很有用，因为它比任何其他数字都大。同样，`-Infinity` 比其他任何数字都小。

- `JavaScript`有两个零，`+0`和`-0`。它（js引擎）通常不让你看到，并简单将两个零都显示为0：

```javascript
+0  //0
-0  //0
```

因此最好假装只有一个零（正如我们看到假值时所做的那样：**-0 和 +0 都是假值**）。

### 运算符

`JavaScript`中有下列算数运算符：

```javascript
加: number1 + number2
减: number1 - number2
乘: number1 * number2
除: number1 / number2
模: number1 % number2
自增: ++variable, variable++
自减: –variable, variable–
负值: -value
正值（转换为数字）: +value
```

全局对象`Math`通过函数提供更多算数运算操作。

`JavaScript`中也有位运算符（例如：&）。

## 七、字符串

字符串可以直接通过字符串字面量创建。这些字面量被单引号或双引号包裹。反斜线（\）转义字符并且产生一些控制字符。例如：

```javascript
'abc'
"abc"

'Did she say "Hello"?'
"Did she say \"Hello\"?"

'That\'s nice!'
"That's nice!"

'Line 1\nLine 2'  // 换行
'Backlash: \\'
```

可以通过方括号访问单个字符：

```javascript
var str = 'abc';
str[1]    //'b'
```
`length`属性是字符串的字符数量。

```javascript
'abc'.length  //3
```

> **提醒**：字符串是不可变的，如果你想改变现有字符串，你需要创建一个新的字符串。

### 字符串运算符

字符串可以通过加号操作符（+）拼接，如果其中一个操作数为字符串，会将另一个操作数也转换为字符串。

```javascript
var msgCount = 3;
'You have '+ msgCount + ' messages' //'You have 3 messages'
```

连续执行拼接操作可以使用`+=`操作符：

```javascript
var str = '';
str += 'Multiple ';
str += 'pieces ';
str += 'are concatenated.';
console.log(str); //'Multiple pieces are concatenated.'
```

### 字符串方法

字符串有许多有用的方法。例如：

```javascript
'abc'.slice(1)  // 复制子字符串,得到索引1及其之后的字符串，即：'bc'
'abc'.slice(1, 2)   //得到索引1和2之间的字符串，即：'b'

'\t xyz  '.trim()  // 移除空白字符，即：'xyz'

'mjölnir'.toUpperCase()   //转成大写，即：'MJÖLNIR'

'abc'.indexOf('b')  // 查找第一个b的索引，即：1
'abc'.indexOf('x')    //没有返回-1
```

## 八、语句

### 条件（Conditionals）
`if`语句通过布尔条件决定执行那个分支：

```javascript
if (myvar === 0) {
    // then
}

if (myvar === 0) {
    // then
} else {
    // else
}

if (myvar === 0) {
    // then
} else if (myvar === 1) {
    // else-if
} else if (myvar === 2) {
    // else-if
} else {
    // else
}
```

下面的`switch`语句，furit的值决定那个分支被执行。

```javascript
switch (fruit) {
    case 'banana':
        // ...
        break;
    case 'apple':
        // ...
        break;
    default:  // 所有其他情况
        // ...
}
```

### 循环（Loops）

for 循环的格式如下：

```javascript
for(初始化; 当条件成立时循环; 下一步操作)
```

例子：

```javascript
for (var i=0; i < arr.length; i++) {
    console.log(arr[i]);
}
```

当条件成立时`while`循环继续循环它的循环体。

```javascript
// 和上面的for循环相等
var i = 0;
while (i < arr.length) {
    console.log(arr[i]);
    i++;
}
```

当条件成立时，`do-while`循环继续循环。由于条件位于循环体之后，所以循环体总是被至少至少执行一次。

```javascript
do {
    // ...
} while(条件);
```

在所有的循环中：

- break中断循环
- continue开始一个新的循环迭代

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
  [21]: http://yanhaijing.com/javascript/2014/01/05/exploring-the-abyss-of-null-and-undefined-in-javascript/
  [22]: http://yanhaijing.com/javascript/2014/04/25/strict-equality-exemptions/
  [23]: http://yanhaijing.com/javascript/2014/03/14/what-every-javascript-developer-should-know-about-floating-points
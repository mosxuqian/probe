# 45个实用的JavaScript提示、技巧和最佳实践

JavaScript是一个绝冠全球的编程语言，可用于 Web 开发、移动应用开发（PhoneGap、Appcelerator）、服务器端开发（Node.js和Wakanda）等等。JavaScript还是很多新手踏入编程世界的第一个语言。既可以用来显示浏览器中的简单提示框，也可以通过nodebot或nodruino来控制机器人。能够编写结构清晰、性能高效的JavaScript代码的开发人员，现如今已成了招聘市场最受追捧的人。

在这篇文章里，我将分享一些JavaScript的技巧、秘诀和最佳实践，除了少数几个外，不管是浏览器的JavaScript引擎，还是服务器端JavaScript解释器，均适用。

本文中的示例代码，通过了在Google Chrome 30最新版（V8 3.20.17.15）上的测试。

### 1. 首次为变量赋值时务必使用`var`关键字

变量没有声明而直接赋值得话，默认会作为一个新的全局变量，要尽量避免使用全局变量。

### 2. 使用`===`取代`==`

`==`和`!=`操作符会在需要的情况下自动转换数据类型。但`===`和`!==`不会，它们会同时比较值和数据类型，这也使得它们要比`==`和`!=`快。

```javascript
[10] === 10    // is false
[10] == 10    // is true
'10' == 10     // is true
'10' === 10    // is false
 [] == 0     // is true
 [] ===  0     // is false
 '' == false   // is true but true == "a" is false
 '' === false  // is false
```

### 3. underfined、null、0、false、NaN、空字符串的逻辑结果均为false

### 4. 行尾使用分号

实践中最好还是使用分号，忘了写也没事，大部分情况下JavaScript解释器都会自动添加。对于为何要使用分号，可参考文章[JavaScript中关于分号的真相][1]。

[1]: http://davidwalsh.name/javascript-semicolons
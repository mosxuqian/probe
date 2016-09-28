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

### 5. 使用对象构造器

```javascript
function Person(firstName, lastName){
    this.firstName =  firstName;
    this.lastName = lastName;
}
var Saad = new Person("Saad", "Mousliki");
```

### 6. 小心使用typeof、instanceof和contructor

- typeof：JavaScript一元操作符，用于以字符串的形式返回变量的原始类型，注意，typeof null也会返回object，大多数的对象类型（数组Array、时间Date等）也会返回object
- contructor：内部原型属性，可以通过代码重写
- instanceof：JavaScript操作符，会在原型链中的构造器中搜索，找到则返回true，否则返回false

```javascript
var arr = ["a", "b", "c"];
typeof arr;   // 返回 "object" 
arr instanceof Array // true
arr.constructor();  //[]
```

### 7. 使用自调用函数

函数在创建之后直接自动执行，通常称之为自调用匿名函数`（Self-Invoked Anonymous Function）`或直接调用函数表达式`（Immediately Invoked Function Expression ）`。格式如下：

```javascript
(function(){
    // 置于此处的代码将自动执行
})();  
(function(a,b){
    var result = a+b;
    return result;
})(10,20)
```

### 8. 从数组中随机获取成员

```javascript
var items = [12, 548 , 'a' , 2 , 5478 , 'foo' , 8852, , 'Doe' , 2145 , 119];
var  randomItem = items[Math.floor(Math.random() * items.length)];
```

### 9. 获取指定范围内的随机数

这个功能在生成测试用的假数据时特别有数，比如介与指定范围内的工资数。

```javascript
var x = Math.floor(Math.random() * (max - min + 1)) + min;
```

### 10. 生成从0到指定值的数字数组

```javascript
var numbersArray = [] , max = 100;
for( var i=1; numbersArray.push(i++) < max;);  // numbers = [1,2,3 ... 100]
```

### 11. 生成随机的字母数字字符串

```javascript
function generateRandomAlphaNum(len) {
    var rdmString = '';
    for(; rdmString.length < len; rdmString += Math.random().toString(36).substr(2));
    return rdmString.substr(0, len);
}
```

### 12. 打乱数字数组的顺序

```javascript
var numbers = [5, 458 , 120 , -215 , 228 , 400 , 122205, -85411];
numbers = numbers.sort(function(){ return Math.random() - 0.5});
/* numbers 数组将类似于 [120, 5, 228, -215, 400, 458, -85411, 122205]  */
```

这里使用了JavaScript内置的数组排序函数，更好的办法是用专门的代码来实现（如Fisher-Yates算法），可以参见StackOverFlow上的[这个讨论][2]。

### 13. 字符串去空格

Java、C#和PHP等语言都实现了专门的字符串去空格函数，但JavaScript中是没有的，可以通过下面的代码来为String对象函数一个trim函数：

```javascript
String.prototype.trim = function(){
    return this.replace(/^\s+|\s+$/g, "");
};
```

新的JavaScript引擎已经有了`trim()`的原生实现。

### 14. 数组之间追加

```javascript
var array1 = [12 , "foo" , {name "Joe"} , -2458];
var array2 = ["Doe" , 555 , 100];
Array.prototype.push.apply(array1, array2);
/* array1 值为  [12 , "foo" , {name "Joe"} , -2458 , "Doe" , 555 , 100] */
```

### 15. 对象转换为数组

```javascript
var argArray = Array.prototype.slice.call(arguments);
```

### 16. 验证是否是数字

```javascript
function isNumber(n){
    return !isNaN(parseFloat(n)) && isFinite(n);
}
```

### 17. 验证是否是数组

```javascript
function isArray(obj){
    return Object.prototype.toString.call(obj) === '[object Array]' ;
}
```

但如果`toString()`方法被重写过得话，就行不通了。也可以使用下面的方法：

```javascript
Array.isArray(obj); // its a new Array method
```

如果在浏览器中没有使用`frame`，还可以用`instanceof`，但如果上下文太复杂，也有可能出错。

```javascript
var myFrame = document.createElement('iframe');
document.body.appendChild(myFrame);
var myArray = window.frames[window.frames.length-1].Array;
var arr = new myArray(a,b,10); // [a,b,10]  
// myArray 的构造器已经丢失，instanceof 的结果将不正常
// 构造器是不能跨 frame 共享的
arr instanceof Array; // false
```

### 18. 获取数组中的最大值和最小值

```javascript
var  numbers = [5, 458 , 120 , -215 , 228 , 400 , 122205, -85411]; 
var maxInNumbers = Math.max.apply(Math, numbers); 
var minInNumbers = Math.min.apply(Math, numbers);
```

### 19. 清空数组

```javascript
var myArray = [12 , 222 , 1000 ];  
myArray.length = 0; // myArray will be equal to [].
```

### 20. 不要直接从数组中delete或remove元素

如果对数组元素直接使用`delete`，其实并没有删除，只是将元素置为了undefined。数组元素删除应使用`splice`。

切忌：

```javascript
var items = [12, 548 ,'a' , 2 , 5478 , 'foo' , 8852, , 'Doe' ,2154 , 119 ]; 
items.length; // return 11 
delete items[3]; // return true 
items.length; // return 11 
/* items 结果为 [12, 548, "a", undefined × 1, 5478, "foo", 8852, undefined × 1, "Doe", 2154, 119] */
```

而应：

```javascript
var items = [12, 548 ,'a' , 2 , 5478 , 'foo' , 8852, , 'Doe' ,2154 , 119 ]; 
items.length; // return 11 
items.splice(3,1) ; 
items.length; // return 10 
/* items 结果为 [12, 548, "a", 5478, "foo", 8852, undefined × 1, "Doe", 2154, 119] */
```

删除对象的属性时可以使用`delete`。

### 21. 使用length属性截断数组

前面的例子中用`length`属性清空数组，同样还可用它来截断数组：

```javascript
var myArray = [12 , 222 , 1000 , 124 , 98 , 10 ];  
myArray.length = 4; // myArray will be equal to [12 , 222 , 1000 , 124].
```

与此同时，如果把`length`属性变大，数组的长度值变会增加，会使用`undefined`来作为新的元素填充。`length`是一个可写的属性。

```javascript
myArray.length = 10; // the new array length is 10 
myArray[myArray.length - 1] ; // undefined
```

### 22. 在条件中使用逻辑与或

```javascript
var foo = 10;  
foo == 10 && doSomething(); // is the same thing as if (foo == 10) doSomething(); 
foo == 5 || doSomething(); // is the same thing as if (foo != 5) doSomething();
```

逻辑或还可用来设置默认值，比如函数参数的默认值。

```javascript
function doSomething(arg1) {
    arg1 = arg1 || 10; // arg1 will have 10 as a default value if it’s not already set
}
```

[1]: http://davidwalsh.name/javascript-semicolons
[2]: http://stackoverflow.com/questions/962802/is-it-correct-to-use-javascript-array-sort-method-for-shuffling/962890#962890
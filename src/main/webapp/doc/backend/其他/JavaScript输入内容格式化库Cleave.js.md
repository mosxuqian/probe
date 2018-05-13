# JavaScript输入内容格式化库Cleave.js

标签：JavaScript

## 一、简介

[Cleave.js][1]是一款非常简单的输入框内容格式化工具，可以自动帮助你格式化输入的文本内容。`Cleave.js`遵循`Apache 2.0`开源授权协议。`Cleave.js`库的目的是想提供一个简单的方法，来格式化你的输入文字，增加输入文字的可读性。通过这个库，你不需要写任何正则表达式或者格式化后的文本，你仅仅只需要在后台校验你的数据。

## 二、特性

- 信用卡号/银行卡号格式化
- 电话号码格式化
- 日期格式化
- 数字格式
- 自定义分隔符、前缀和分块模式
- CommonJs/AMD模式
- ReactJS组件

## 三、使用示例

### 1. JavaScript脚本中使用

在[Github][2]中下载到`Cleave.js`的文件，并在你的 html 中引入如下`JavaScript`脚本：

```html
<script src="cleave.min.js"></script>
<script src="cleave-phone.{country}.js"></script>
```

> `cleave-phone.{country}.js`是手机号码在各个国家相对应的格式化插件。如果是中国，则引入`cleave-phone.cn.js`文件即可。

各种使用的代码示例如下：

```html
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Cleave.js使用示例</title>

    <style>
        input {
            width: 180px;
            height: 20px;
        }
    </style>
</head>
<body>

    银行卡号: <input class="credit-card" width="100px" type="text" placeholder="请输入您的银行卡号"/><br>

    电话号码: <input class="phone" width="100px" type="text" placeholder="请输入您的电话号码"/><br>

    日期: <input class="date" width="100px" type="text" placeholder="请输入日期"/><br>

    月份: <input class="month" width="100px" type="text" placeholder="请输入月份"/><br>

    金额(万元): <input class="money" width="100px" type="text" placeholder="中国金额将，万元隔开"/><br>

    自定义块: <input class="custom-block" width="100px" type="text" placeholder="自定义块数"/><br>

    自定义分隔符: <input class="custom-delimiter" width="100px" type="text" placeholder="自定义分隔符"/><br>

    自定义前缀: <input class="custom-prefix" width="100px" type="text" placeholder="自定义前缀"/><br>

    <script src="../../assets/plugins/cleave/cleave.min.js"></script>
    <script src="../../assets/plugins/cleave/cleave-phone.cn.js"></script>
    <script>
    (function () {
        new Cleave('.credit-card', {
            creditCard: true,
            onCreditCardTypeChanged: function (type) {
                console.log('开始输入银行卡号了...');
            }
        });
        new Cleave('.phone', {
            phone: true,
            phoneRegionCode: 'cn'
        });
        new Cleave('.date', {
            date: true,
            datePattern: ['Y', 'm', 'd']
        });
        new Cleave('.month', {
            date: true,
            datePattern: ['m', 'y']
        });
        new Cleave('.money', {
            numeral: true,
            numeralThousandsGroupStyle: 'wan'
        });
        new Cleave('.custom-block', {
            blocks: [4, 3, 3, 4],
            uppercase: true
        });
        new Cleave('.custom-delimiter', {
            delimiter: '·',
            blocks: [3, 3, 3, 3],
            uppercase: true
        });
        new Cleave('.custom-prefix', {
            prefix: 'PREFIX',
            delimiter: '-',
            blocks: [6, 4, 4, 4],
            uppercase: true
        });
    }());
</script>
</body>
</html>
```

### 2. ReactJS中的使用

在[ReactJS][3]组件中的引用和使用方式如下：

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import Cleave from 'cleave.js/react';

class MyComponent extends React.Component {
    onChange(event) {
        // formatted pretty value
        console.log(event.target.value);

        // raw value
        console.log(event.target.rawValue);
    }

    render() {
        return (
        <Cleave placeholder="Enter your credit card number"
                options={{creditCard: true}}
                onChange={this.onChange.bind(this)} />
        );
    }
}
```


  [1]: http://nosir.github.io/cleave.js/
  [2]: https://github.com/nosir/cleave.js
  [3]: https://facebook.github.io/react/
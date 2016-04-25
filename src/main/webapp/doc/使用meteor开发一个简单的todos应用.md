# 使用meteor开发一个简单的todos应用

[`Meteor`][1]是一个构建在Node.js之上的平台，用来开发实时网页程序。Meteor位于程序数据库和用户界面之间，保持二者之间的数据同步更新。因为Meteor是基于Node.js开发的，所以在客户端和服务器端都使用JavaScript作为开发语言。而且，Meteor程序的代码还能在前后两端共用。官方的介绍如下：

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

## 一、创建你的第一个应用

首先，确认要在你的电脑中已经安装好了`Meteor`,可以在终端中输入`meteor --help`来判断是否已安装，如果打印出了`Meteor`相关的帮助命令，则表示已经安装成功，同时你可以使用`meteor update`命令将你的`meteor`版本升级到最新。如果没有安装，则可以参考[这里][2]来进行安装。

切换到你需要创建项目的目录，在终端中输入如下命令来创建一个名为`simple-todos`的项目：

```bash
meteor create simple-todos
```

在你的目录下就会生成一个包含以下文件和文件夹的项目

```
client/main.js        # a JavaScript entry point loaded on the client
client/main.html      # an HTML file that defines view templates
client/main.css       # a CSS file to define your app's styles
server/main.js        # a JavaScript entry point loaded on the server
package.json          # a control file for installing NPM packages
.meteor               # internal Meteor files
.gitignore            # a control file for git
```

然后执行以下命令来运行你新创建的应用:

```bash
cd simple-todos
meteor npm install
meteor
```

在你的浏览器中打开[http://localhost:3000][3]查看运行的应用

> 在我们继续往下之前，你可以玩玩这个默认的应用程序。例如，试着用你最喜欢的文本编辑器编辑`client/main.html`中`<h1>`标签中的内容，当您保存文件时，浏览器中的页面会自动更新到新的内容。我们称这为“热代码推送”。

### ES2015 JavaScript特性

如果你还没有使用过下一代的`JavaScript`功能和特性,在接下来的开发过程中会感觉怪怪的。`Meteor`已经开始支持了许多的ES2015的特性，其中的包括如下一些功能：

- 箭头函数：`(arg) => {return result;}`
- 速记方法：`render() { ... }`
- 块级作用域：使用`const`和`let`代替`var`

通过`ecmascript文档`来查看Meteor对ES2015的特性支持，如果想知道更多的信息请查看如下文章：

- [Luke Hoban's "ES6 features"][4]
- [Kyle Simpson's "You don't know JS: ES6 and beyond"][5]
- [Nikolas C. Zakas "Understanding ECMAScript 6"][6]

我们接下来会使用这个默认的项目来构建一个简单的todos应用。

  [1]: https://www.meteor.com/
  [2]: http://blinkfox.com/meteorde-an-zhuang-he-ru-men/
  [3]: http://localhost:3000
  [4]: http://git.io/es6features
  [5]: https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond
  [6]: https://github.com/nzakas/understandinges6
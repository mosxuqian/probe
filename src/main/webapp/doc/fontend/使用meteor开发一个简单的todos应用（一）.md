# 使用meteor开发一个简单的todos应用（一）

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

## 二、定义模版定义视图

为了todos应用能运行，我们需要先使用下面的代码来替换默认创建的代码，然后再说明怎么做到的。

首先，删掉`client/main.html`中`<body>`及其以下的代码，仅仅保留`<head>`标签中的内容：

```html
<!-- client/main.html -->
<head>
  <title>simple</title>
</head>
```

然后我们创建`imports`目录和其下的一些文件:

```html
<!-- imports/ui/body.html -->
<body>
  <div class="container">
    <header>
      <h1>Todo List</h1>
    </header>
 
    <ul>
      {{#each tasks}}
        {{> task}}
      {{/each}}
    </ul>
  </div>
</body>
 
<template name="task">
  <li>{{text}}</li>
</template>
```

```javasript
// imports/ui/body.js
import { Template } from 'meteor/templating';
 
import './body.html';
 
Template.body.helpers({
  tasks: [
    { text: 'This is task 1' },
    { text: 'This is task 2' },
    { text: 'This is task 3' },
  ],
});
```

`imports/`目录下的文件只有导入了才会加载，所以我们需要在`client/main.js`中导入`imports/ui/body.js`。*注意：我们需要删除`client/main.js`中以前的代码*。

```javascript
// client/main.js
import '../imports/ui/body.js';
```

你可以阅读更多有关Meteor`imports`如何工作和构建项目结构的[文章][7]。

在我们的浏览器中，这个应用将看起来像下面这样：

![todos应用][8]

现在让我们找出这些代码是怎么做到的！

`Meteor`的模版定义在`HTML`文件中,`Meteor`解析`HTML`文件中三个顶级级别的标签：`<head>`, `<body>`, 和 `<template>`。在任何的`<head>`标签都添加到`HTML`的head发送到客户端，并在`<body>`标签都添加到body的部分，就像一个普通的HTML文件。任何在`<template>`标签中的内容都被编译成Meteor的模版，在html中通过`{{> task}}`引入，在js中通过`Template.templateName`引用。当然，如果是body标签,则在js中的通过`Template.body`来引用。

### 在模版中添加逻辑和数据

所有html中的代码都是通过[Meteor's Spacebars compiler][9]编译。`Spacebars`使用双大括号括起来的语法，如：`{{#each}}`和`{{＃if}}`，让你在视图中添加逻辑和数据。

### 添加CSS样式

在继续之前，为了让我们的应用看起来不错，在`simple-todos.css`文件中添加如下`CSS`。

```css
// Replace simple-todos.css with this code
/* CSS declarations go here */
body {
  font-family: sans-serif;
  background-color: #315481;
  background-image: linear-gradient(to bottom, #315481, #918e82 100%);
  background-attachment: fixed;

  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;

  padding: 0;
  margin: 0;

  font-size: 14px;
}

.container {
  max-width: 600px;
  margin: 0 auto;
  min-height: 100%;
  background: white;
}

header {
  background: #d2edf4;
  background-image: linear-gradient(to bottom, #d0edf5, #e1e5f0 100%);
  padding: 20px 15px 15px 15px;
  position: relative;
}

#login-buttons {
  display: block;
}

h1 {
  font-size: 1.5em;
  margin: 0;
  margin-bottom: 10px;
  display: inline-block;
  margin-right: 1em;
}

form {
  margin-top: 10px;
  margin-bottom: -10px;
  position: relative;
}

.new-task input {
  box-sizing: border-box;
  padding: 10px 0;
  background: transparent;
  border: none;
  width: 100%;
  padding-right: 80px;
  font-size: 1em;
}

.new-task input:focus{
  outline: 0;
}

ul {
  margin: 0;
  padding: 0;
  background: white;
}

.delete {
  float: right;
  font-weight: bold;
  background: none;
  font-size: 1em;
  border: none;
  position: relative;
}

li {
  position: relative;
  list-style: none;
  padding: 15px;
  border-bottom: #eee solid 1px;
}

li .text {
  margin-left: 10px;
}

li.checked {
  color: #888;
}

li.checked .text {
  text-decoration: line-through;
}

li.private {
  background: #eee;
  border-color: #ddd;
}

header .hide-completed {
  float: right;
}

.toggle-private {
  margin-left: 5px;
}

@media (max-width: 600px) {
  li {
    padding: 12px 15px;
  }

  .search {
    width: 150px;
    clear: both;
  }

  .new-task input {
    padding-bottom: 5px;
  }
}
```

切换到浏览器中，将自动刷新页面，todos应用的效果如下图所示：

![todos应用2][10]

## 三、在collection中存储tasks

`Collections`是`Meteor`中用来存储持久化数据的方式。`Collections`的特别之处就在于在服务端和客户端都能进行访问，而无需编写大量的服务端代码，同时也保证了数据的实时更新，所以视图组件中才会自动显示最新的数据。你可以阅读更多关于Meteor的[Collections文章][11]。

只需使用`MyCollection = new Mongo.Collection("my-collection");`就可以创建一个新的`collection`。为了创建collection，我们定义一个新的`todos`模块来创建`Mongo collection`并`exports`它。在`imports`目录中新建`api`目录，用于存放该应用的`API`，并在`api`目录中新建`tasks.js`，你可以阅读[这里的文章][12]来看如何构建自己的`Meteor`代码结构。`tasks.js`中添加如下代码：

```javascript
// imports/api/tasks.js
import { Mongo } from 'meteor/mongo';
 
export const Tasks = new Mongo.Collection('tasks');
```

我们需要在服务端`server/main.js`中导入`Mongo`创建的数据集。

```javascript
// server/main.js
import '../imports/api/tasks.js';
```

我们再更新客户端`imports/ui/body.js`中的代码，通过获取`collection`中的数据来替代原来写死的数据：

```javascript
// imports/ui/body.js
import { Template } from 'meteor/templating';
 
import { Tasks } from '../api/tasks.js';
 
import './body.html';
 
Template.body.helpers({
  tasks() {
    return Tasks.find({});
  },
});
```

当你修改了你的代码并保存了之后，你会发现页面的`todo list`数据都消失了。因为当前数据库中的数据是空的——让我们插入些`tasks`数据！

新开一个当前项目路径下的终端，执行`meteor mongo`将连接该todos应用的`Meteor`自带的`MongoDB`数据库。执行如下命令来插入一条tasks数据到`tasks`集合中。

```bash
db.tasks.insert({ text: "Hello world!", createdAt: new Date() });
```

此时在你的浏览器中将会看到你刚刚新插入的一条tasks数据。你可以看到，我们没有写任何代码到服务器端数据库连接到我们的前端代码——它都是自动发生的。

在控制台数据库中插入不同的tasks来完成一些任务。在接下来的步骤中，我们将看到如何将一些功能添加到我们的应用程序的界面，因此我们可以在不使用数据库控制台来添加tasks任务。

## 四、使用表单添加tasks

在这一步中，我们将使用输入字段的方式添加到用户列表中。

首先，让我们添加一个表单到我们的`HTML`中：

```html
<!-- imports/ui/body.html -->
<div class="container">
    <header>
      <h1>Todo List</h1>
    
      <form class="new-task">
        <input type="text" name="text" placeholder="Type to add new tasks" />
      </form>
    </header>
    
    <ul>
```

下面是监听表单提交事件的`javascript`代码：

```javascript
/* imports/ui/body.js */
   return Tasks.find({});
  },
});
 
Template.body.events({
  'submit .new-task'(event) {
    // Prevent default browser form submit
    event.preventDefault();
 
    // Get value from form element
    const target = event.target;
    const text = target.text.value;
 
    // Insert a task into the collection
    Tasks.insert({
      text,
      createdAt: new Date(), // current time
    });
 
    // Clear form
    target.text.value = '';
  },
});
```

现在，在你的应用有一个新的输入框。要添加一个任务，只需输入相应的内容并按下回车键。如果你打开一个新的浏览器窗口，且再次打开该应用程序，你会看到列表中的所有tasks数据在各客户端之间是自动同步的。

为了让列表中总是显示最新的tasks，在`imports/ui/body.js`中修改如下代码：

```javascript
// imports/ui/body.js
Template.body.helpers({
  tasks() {
    // Show newest tasks at the top
    return Tasks.find({}, { sort: { createdAt: -1 } });
  },
});
```

## 五、更新或删除tasks

到现在为止，我已经能够向`collection`中插入数据了，现在我们来学习如何更新或删除一条tasks。

我们新建一个`task`的模板,向其中添加一些新的特性、复选框和删除按钮：

```html
// imports/ui/task.html
<template name="task">
  <li class="{{#if checked}}checked{{/if}}">
    <button class="delete">&times;</button>
 
    <input type="checkbox" checked="{{checked}}" class="toggle-checked" />
 
    <span class="text">{{text}}</span>
  </li>
</template>
```

**我们同时也需要从`imports/ui/body.html`文件中移除老的`task`模板**。我们已经添加了一些UI元素，但是并不起任何作用，我们还需要添加一些事件`handlers`：

```javascript
// imports/ui/task.js
import { Template } from 'meteor/templating';
 
import { Tasks } from '../api/tasks.js';
 
import './task.html';
 
Template.task.events({
  'click .toggle-checked'() {
    // Set the checked property to the opposite of its current value
    Tasks.update(this._id, {
      $set: { checked: ! this.checked },
    });
  },
  'click .delete'() {
    Tasks.remove(this._id);
  },
});
```

由于`body`模板使用了`task`模板，所以我们需要引入它：

```javascript
// imports/ui/body.js
import { Tasks } from '../api/tasks.js';
 
import './task.js';
import './body.html';
 
Template.body.helpers({
```

现在，你就可以在你的应用中做到添加、修改、删除tasks的功能啦。

  [1]: https://www.meteor.com/
  [2]: http://blinkfox.com/meteorde-an-zhuang-he-ru-men/
  [3]: http://localhost:3000
  [4]: http://git.io/es6features
  [5]: https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond
  [6]: https://github.com/nzakas/understandinges6
  [7]: http://guide.meteor.com/structure.html
  [8]: http://7xnrn5.com1.z0.glb.clouddn.com/todos1.jpg
  [9]: https://github.com/meteor/meteor/blob/devel/packages/spacebars/README.md
  [10]: http://7xnrn5.com1.z0.glb.clouddn.com/todos2.jpg
  [11]: http://guide.meteor.com/collections.html
  [12]: http://guide.meteor.com/structure.html
# 使用meteor开发一个简单的todos应用（二）

[`Meteor`][1]是一个构建在Node.js之上的平台，用来开发实时网页程序。Meteor位于程序数据库和用户界面之间，保持二者之间的数据同步更新。因为Meteor是基于Node.js开发的，所以在客户端和服务器端都使用JavaScript作为开发语言。而且，Meteor程序的代码还能在前后两端共用。官方的介绍如下：

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

前面讲了如何开发了一个简单的todos应用，请参考[使用meteor开发一个简单的todos应用（一）][2]，接下来学习`Meteor`的其他部分。

## 一、让你的APP运行在Android和iOS上

> 当前，Meteor不支持在windows上构建APP，如果你在windows上使用Meteor，请跳过这一环节。

到目前为止，我们构建和测试我们的应用仅仅是在浏览器中，但是Meteor是被设计成跨平台运行的。把我们的todos应用通过一些简单的命令就可以让他运行在Android和iOS中。

### 运行在iOS模拟器中 (仅支持Mac)

如果你有Mac系统，你可以运行你的APP在你的iOS模拟器中，切换到你的项目目录中，执行如下命令：

```bash
meteor install-sdk ios
```

这将会安装iOS运行所必需的构建文件，当完成之后输入如下命令：

```bash
meteor add-platform ios
meteor run ios
```

你就能在你的iOS模拟器中看到你APP在运行了。

### 运行在Android模拟器中

在终端中切换到你应用的根目录下，键入如下命令：

```bash
meteor install-sdk android
```

这将会运行构建安卓应用必需的文件，当所有安装完之后，输入：

```bash
meteor add-platform android
```

在你同意许可条款之后，输入：

```bash
meteor run android
```

一些初始化之后，你会看到弹出一个Android模拟器，运行原生Android的应用程序。模拟器的运行有点慢，所以如果你想看看真的运行情况，你应该在你的真实设备上运行。

### 运行在Android设备上

首先，你需要完成前面安装Android所需要的工具包和平台支持，并且确保你的电脑连上了手机且[开启了USB调试][3]，然后运行下面的命令：

```bash
meteor run android-device
```

然后你的APP将会运行在你的手机上了。

## 二、使用Reactive Dict存储UI临时状态

接下来，我们将在我们的应用中添加客户端过滤的功能，让用户只能看到未完成的功能。我们将要学习使用[`Reactive Dict`][4]来在客户端存储状态。一个`Reactive Dict`就好像使用`key`、`value`的普通js对象，但是它是实时的。

现在，我们需要添加一个复选框在`body.html`模版页面中

```html
<!-- imports/ui/body.html -->
<header>
      <h1>Todo List</h1>
 
      <label class="hide-completed">
        <input type="checkbox" />
        Hide Completed Tasks
      </label>
 
      <form class="new-task">
        <input type="text" name="text" placeholder="Type to add new tasks" />
      </form>
```

接下来我们需要添加`reactive-dict`包，并在js中使用它,命令和代码如下：

```bash
meteor add reactive-dict
```

```javascript
// 在imports/ui/body.js中添加state
import { Template } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';
 
import { Tasks } from '../api/tasks.js';
 
import './task.js';
import './body.html';
 
Template.body.onCreated(function bodyOnCreated() {
  this.state = new ReactiveDict();
});
 
Template.body.helpers({
  tasks() {
    // Show newest tasks at the top
```

```javascript
// 在imports/ui/body.js中为复选框添加事件
    // Clear form
    target.text.value = '';
  },
  'change .hide-completed input'(event, instance) {
    instance.state.set('hideCompleted', event.target.checked);
  },
});
```

```javascript
// 在imports/ui/body.js添加helpers
Template.body.helpers({
  tasks() {
    const instance = Template.instance();
    if (instance.state.get('hideCompleted')) {
      // If hide completed is checked, filter tasks
      return Tasks.find({ checked: { $ne: true } }, { sort: { createdAt: -1 } });
    }
    // Otherwise, return all of the tasks
    return Tasks.find({}, { sort: { createdAt: -1 } });
  },
});
```

为了显示未完成的task数量，在`imports/ui/body.js`中添加代码：

```javascript
    // Otherwise, return all of the tasks
    return Tasks.find({}, { sort: { createdAt: -1 } });
  },
  incompleteCount() {
    return Tasks.find({ checked: { $ne: true } }).count();
  },
});
 
Template.body.events({
```

```html
<!-- imports/ui/body.html -->
<body>
  <div class="container">
    <header>
      <h1>Todo List ({{incompleteCount}})</h1>
 
      <label class="hide-completed">
        <input type="checkbox" />
```

### 三、添加用户账户

`Meteor`配备了账户系统和登录接口，让你在几分钟之类就可以集成多用户相关功能到你的APP中。为了激活帐户系统和UI，我们需要添加相应的包，切换到你的应用根目录执行如下命令：

```bash
meteor add accounts-ui accounts-password
```

在`imports/ui/body.html`中，引入如下代码添加一个下拉式的登录：

```html
        Hide Completed Tasks
      </label>
 
      {{> loginButtons}}
 
      <form class="new-task">
        <input type="text" name="text" placeholder="Type to add new tasks" />
      </form>
```

然后，新建一个`accounts-config.js`，并在你的javascript中添加下面的代码用于配置用户帐户仅仅是用户名，不是邮箱：

```javascript
// imports/startup/accounts-config.js
import { Accounts } from 'meteor/accounts-base';
 
Accounts.ui.config({
  passwordSignupFields: 'USERNAME_ONLY',
});
```

在`client/main.js`中引入`accounts-config.js`：

```javascript
import '../imports/startup/accounts-config.js';
import '../imports/ui/body.js';
```

现在用户可以创建自己的账户和登录了，但是登录和登出，并没有任何作用，接下来我们添加两个功能：

1. 只有登录的用户才能新建task输入框
2. 显示用户自己创建的task

为了做到这样，我们需要添加两个字段到`tasks`collection中：

1. owner —— 创建某task的用户ID
2. username —— 创建某task的用户名

让我们修改`imports/ui/body.js`中的代码如下：

```javascript
// imports/ui/body.js
import { Meteor } from 'meteor/meteor';
import { Template } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';
 
...some lines skipped...
    Tasks.insert({
      text,
      createdAt: new Date(), // current time
      owner: Meteor.userId(),
      username: Meteor.user().username,
    });
 
    // Clear form
```

在`imports/ui/body.html`中添加`#if`代码块，用于判断用户是否登录：

```html
 <!-- imports/ui/body.html -->
      {{> loginButtons}}
 
      {{#if currentUser}}
        <form class="new-task">
          <input type="text" name="text" placeholder="Type to add new tasks" />
        </form>
      {{/if}}
    </header>
 
    <ul>
```

为了在task中显示添加者的用户名，修改`imports/ui/task.html`中的代码：

```html
 
    <input type="checkbox" checked="{{checked}}" class="toggle-checked" />
 
    <span class="text"><strong>{{username}}</strong> - {{text}}</span>
  </li>
</template>
```

到此，你就可以创建和登录了，并且可以按用户来显示task任务和相关属性啦。

  [1]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://developer.android.com/tools/device.html#developer-device-options
  [4]: https://atmospherejs.com/meteor/reactive-dict
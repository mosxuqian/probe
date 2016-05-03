# 使用meteor开发一个简单的todos应用（三）

[`Meteor`][1]是一个构建在Node.js之上的平台，用来开发实时网页程序。Meteor位于程序数据库和用户界面之间，保持二者之间的数据同步更新。因为Meteor是基于Node.js开发的，所以在客户端和服务器端都使用JavaScript作为开发语言。而且，Meteor程序的代码还能在前后两端共用。官方的介绍如下：

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

前面讲了如何开发了一个简单的todos应用的增删改功能，隐藏或显示已完成任务功能，添加用户系统等功能。文章参看这里：

1. [使用meteor开发一个简单的todos应用（一）][2]
2. [使用meteor开发一个简单的todos应用（二）][3]

由于我们的应用的数据都暴露在客户端，这样应用的安全性存在问题。接下来我们学习`Meteor`的安全部分。

## 一、安全使用方法

### 移除`insecure`包

由于每个新建的`Meteor`项目都带有一个默认的`insecure`包，这个包允许我们在客户端编辑数据库中的数据，这在我们开发和练习中是很有用的，但是当应用需要公开使用时，就必须得去除它了，切换到你应用的根目录中，执行命令：

```bash
meteor remove insecure
```

当我们去掉这个包以后，会发现我们的应用的新增输入功能和按钮功能都无法使用了，因为客户端数据库的权限已经去除掉了。现在，我们需要重新修改我们的代码，来达到以前的功能。

### 定义方法

在`imports/api/tasks.js`中添加增、删、改task的方法：

```javascript
// imports/api/tasks.js
import { Meteor } from 'meteor/meteor';
import { Mongo } from 'meteor/mongo';
import { check } from 'meteor/check';
 
export const Tasks = new Mongo.Collection('tasks');
 
Meteor.methods({
  'tasks.insert'(text) {
    check(text, String);
 
    // Make sure the user is logged in before inserting a task
    if (! this.userId) {
      throw new Meteor.Error('not-authorized');
    }
 
    Tasks.insert({
      text,
      createdAt: new Date(),
      owner: this.userId,
      username: Meteor.users.findOne(this.userId).username,
    });
  },
  'tasks.remove'(taskId) {
    check(taskId, String);
 
    Tasks.remove(taskId);
  },
  'tasks.setChecked'(taskId, setChecked) {
    check(taskId, String);
    check(setChecked, Boolean);
 
    Tasks.update(taskId, { $set: { checked: setChecked } });
  },
});
```

现在我们已经定义好了我们的方法，接下来：

```javascript
// imports/ui/body.js
    const text = target.text.value;
 
    // Insert a task into the collection
    Meteor.call('tasks.insert', text);
 
    // Clear form
    target.text.value = '';
```

```javascript
// imports/ui/task.js
import { Meteor } from 'meteor/meteor';
import { Template } from 'meteor/templating';
 
import './task.html';
 
Template.task.events({
  'click .toggle-checked'() {
    // Set the checked property to the opposite of its current value
    Meteor.call('tasks.setChecked', this._id, !this.checked);
  },
  'click .delete'() {
    Meteor.call('tasks.remove', this._id);
  },
});
```

现在我们所有的输入和按钮功能又可以正常使用啦！

## 二、通过发布和订阅来过滤数据

现在我们已经在方法中提出了所有的敏感代码，我们还需要学习`Meteor`安全的另一部分。到目前为止，我们的数据库一直存在于客户端，意味着在客户端调用`Tasks.find()`方法我们就可以获取`collection`中的所有`task`数据，这是很不安全的。我们需要控制用户需要的数据，而不是所有数据。

像前面移除`insecure`包一样，我们需要移除`autopublish`包，移除之后看看发生了什么：

```bash
meteor remove autopublish
```

当应用程序刷新时，`task`列表里面显示为空了，为了做到服务器的哪些数据能够发送到客户端，就需要用到`Meteor`的发布和订阅功能了。首先让我们在`imports/api/tasks.js`文件中为所有`tasks`添加发布功能。

```javascript
 // imports/api/tasks.js
export const Tasks = new Mongo.Collection('tasks');
 
if (Meteor.isServer) {
  // This code only runs on the server
  Meteor.publish('tasks', function tasksPublication() {
    return Tasks.find();
  });
}
 
Meteor.methods({
  'tasks.insert'(text) {
    check(text, String);
```

然后，在`body`模版创建时，订阅刚才发布的内容：

```javascript
// imports/ui/body.js
 
Template.body.onCreated(function bodyOnCreated() {
  this.state = new ReactiveDict();
  Meteor.subscribe('tasks');
});
 
Template.body.helpers({
```

当你修改完这些代码后，会发现应用中的数据又回来了。

### 实现私有的tasks

首先，让我们添加'private'属性的task和一个按钮，这个按钮用来仅仅显示是某个用户的共有或私有任务。

```html
<!-- imports/ui/task.html -->
<template name="task">
  <li class="{{#if checked}}checked{{/if}} {{#if private}}private{{/if}}">
    <button class="delete">&times;</button>

    <input type="checkbox" checked="{{checked}}" class="toggle-checked" />

    {{#if isOwner}}
      <button class="toggle-private">
        {{#if private}}
          Private
        {{else}}
          Public
        {{/if}}
      </button>
    {{/if}}

    <span class="text"><strong>{{username}}</strong> - {{text}}</span>
  </li>
</template>
```

同时，我们需要修改下面三个js文件：

```javascript
// imports/ui/task.js
 
import './task.html';
 
Template.task.helpers({
  isOwner() {
    return this.owner === Meteor.userId();
  },
});
 
Template.task.events({
  'click .toggle-checked'() {
    // Set the checked property to the opposite of its current value
```

```javascript
// 在imports/api/tasks.js中定义设置tasks为private的方法
 
    Tasks.update(taskId, { $set: { checked: setChecked } });
  },
  'tasks.setPrivate'(taskId, setToPrivate) {
    check(taskId, String);
    check(setToPrivate, Boolean);
 
    const task = Tasks.findOne(taskId);
 
    // Make sure only the task owner can make a task private
    if (task.owner !== this.userId) {
      throw new Meteor.Error('not-authorized');
    }
 
    Tasks.update(taskId, { $set: { private: setToPrivate } });
  },
});
```

```javascript
// 在imports/ui/task.js中添加事件handler用于调setPrivate方法

  'click .delete'() {
    Meteor.call('tasks.remove', this._id);
  },
  'click .toggle-private'() {
    Meteor.call('tasks.setPrivate', this._id, !this.private);
  },
});
```

```javascript
// imports/api/tasks.js
 
if (Meteor.isServer) {
  // This code only runs on the server
  // Only publish tasks that are public or belong to the current user
  Meteor.publish('tasks', function tasksPublication() {
    return Tasks.find({
      $or: [
        { private: { $ne: true } },
        { owner: this.userId },
      ],
    });
  });
}
```

### 其它的安全方法

```javascript
// 在imports/api/tasks.js中添加其他额外的安全方法
  'tasks.remove'(taskId) {
    check(taskId, String);
 
    const task = Tasks.findOne(taskId);
    if (task.private && task.owner !== this.userId) {
      // If the task is private, make sure only the owner can delete it
      throw new Meteor.Error('not-authorized');
    }
 
    Tasks.remove(taskId);
  },
  'tasks.setChecked'(taskId, setChecked) {
    check(taskId, String);
    check(setChecked, Boolean);
 
    const task = Tasks.findOne(taskId);
    if (task.private && task.owner !== this.userId) {
      // If the task is private, make sure only the owner can check it off
      throw new Meteor.Error('not-authorized');
    }
 
    Tasks.update(taskId, { $set: { checked: setChecked } });
  },
  'tasks.setPrivate'(taskId, setToPrivate) {
```

现在我们已经完成了私有的task功能！

## 三、测试

我们已经为我们的应用程序添加了一些列功能，让我们添加测试功能，以确保我们的程序能像我们期望的那样运行。我们需要通过[Mocha][4]的`Javascript`测试框架来[测试驱动][5]。

```bash
meteor add practicalmeteor:mocha
```

我们可以通过调用一些特殊的命令，用“测试模式”来运行我们的应用程序：

```bash
meteor test --driver-package practicalmeteor:mocha
```

执行上面的命令，你会在你的浏览器窗口中看到一个空的测试结果。让我们来添加一个简单的测试：

```javascript
// 新建imports/api/tasks.tests.js文件
/* eslint-env mocha */
 
import { Meteor } from 'meteor/meteor';
 
if (Meteor.isServer) {
  describe('Tasks', () => {
    describe('methods', () => {
      it('can delete owned task', () => {
      });
    });
  });
}
```

在任何测试中，我们都应该确保我们测试运行前，数据库都有预期的。我们`Mocha`可以用`beforeEach`的结构来实现：

```javascript
// imports/api/tasks.tests.js
/* eslint-env mocha */
 
import { Meteor } from 'meteor/meteor';
import { Random } from 'meteor/random';
 
import { Tasks } from './tasks.js';
 
if (Meteor.isServer) {
  describe('Tasks', () => {
    describe('methods', () => {
      const userId = Random.id();
      let taskId;
 
      beforeEach(() => {
        Tasks.remove({});
        taskId = Tasks.insert({
          text: 'test task',
          createdAt: new Date(),
          owner: userId,
          username: 'tmeasday',
        });
      });
 
      it('can delete owned task', () => {
      });
    });
```

在我们测试前，我们已经创建了一个随机用户ID的task，从而为每个测试都运行不同的task。

现在我们来写个 测试方法来调用`task.remove`方法用于确保这个task已经被删除了。

```javascript
// imports/api/tasks.tests.js
import { Meteor } from 'meteor/meteor';
import { Random } from 'meteor/random';
import { assert } from 'meteor/practicalmeteor:chai';
 
import { Tasks } from './tasks.js';
 
...some lines skipped...
      });
 
      it('can delete owned task', () => {
        // Find the internal implementation of the task method so we can
        // test it in isolation
        const deleteTask = Meteor.server.method_handlers['tasks.remove'];
 
        // Set up a fake method invocation that looks like what the method expects
        const invocation = { userId };
 
        // Run the method with `this` set to the fake invocation
        deleteTask.apply(invocation, [taskId]);
 
        // Verify that the method does what we expected
        assert.equal(Tasks.find().count(), 0);
      });
    });
  });
```

在Meteor的测试中你还能做到更多，你可以阅读这篇关于[Meteor测试的文章][6]来了解更多。

  [1]: https://www.meteor.com
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-er/
  [4]: https://mochajs.org/
  [5]: http://guide.meteor.com/testing.html#test-driver
  [6]: http://guide.meteor.com/testing.html
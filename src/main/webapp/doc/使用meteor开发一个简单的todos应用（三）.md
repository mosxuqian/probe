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

  [1]: https://www.meteor.com
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-er/
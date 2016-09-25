# ʹ��meteor����һ���򵥵�todosӦ�ã�����

[`Meteor`][1]��һ��������Node.js֮�ϵ�ƽ̨����������ʵʱ��ҳ����Meteorλ�ڳ������ݿ���û�����֮�䣬���ֶ���֮�������ͬ�����¡���ΪMeteor�ǻ���Node.js�����ģ������ڿͻ��˺ͷ������˶�ʹ��JavaScript��Ϊ�������ԡ����ң�Meteor����Ĵ��뻹����ǰ�����˹��á��ٷ��Ľ������£�

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

ǰ�潲����ο�����һ���򵥵�todosӦ�õ���ɾ�Ĺ��ܣ����ػ���ʾ����������ܣ�����û�ϵͳ�ȹ��ܡ����²ο����

1. [ʹ��meteor����һ���򵥵�todosӦ�ã�һ��][2]
2. [ʹ��meteor����һ���򵥵�todosӦ�ã�����][3]

�������ǵ�Ӧ�õ����ݶ���¶�ڿͻ��ˣ�����Ӧ�õİ�ȫ�Դ������⡣����������ѧϰ`Meteor`�İ�ȫ���֡�

## һ����ȫʹ�÷���

### �Ƴ�`insecure`��

����ÿ���½���`Meteor`��Ŀ������һ��Ĭ�ϵ�`insecure`������������������ڿͻ��˱༭���ݿ��е����ݣ��������ǿ�������ϰ���Ǻ����õģ����ǵ�Ӧ����Ҫ����ʹ��ʱ���ͱ����ȥ�����ˣ��л�����Ӧ�õĸ�Ŀ¼�У�ִ�����

```bash
meteor remove insecure
```

������ȥ��������Ժ󣬻ᷢ�����ǵ�Ӧ�õ��������빦�ܺͰ�ť���ܶ��޷�ʹ���ˣ���Ϊ�ͻ������ݿ��Ȩ���Ѿ�ȥ�����ˡ����ڣ�������Ҫ�����޸����ǵĴ��룬���ﵽ��ǰ�Ĺ��ܡ�

### ���巽��

��`imports/api/tasks.js`���������ɾ����task�ķ�����

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

���������Ѿ�����������ǵķ�������������

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

�����������е�����Ͱ�ť�����ֿ�������ʹ������

## ����ͨ�������Ͷ�������������

���������Ѿ��ڷ�������������е����д��룬���ǻ���Ҫѧϰ`Meteor`��ȫ����һ���֡���ĿǰΪֹ�����ǵ����ݿ�һֱ�����ڿͻ��ˣ���ζ���ڿͻ��˵���`Tasks.find()`�������ǾͿ��Ի�ȡ`collection`�е�����`task`���ݣ����Ǻܲ���ȫ�ġ�������Ҫ�����û���Ҫ�����ݣ��������������ݡ�

��ǰ���Ƴ�`insecure`��һ����������Ҫ�Ƴ�`autopublish`�����Ƴ�֮�󿴿�������ʲô��

```bash
meteor remove autopublish
```

��Ӧ�ó���ˢ��ʱ��`task`�б�������ʾΪ���ˣ�Ϊ����������������Щ�����ܹ����͵��ͻ��ˣ�����Ҫ�õ�`Meteor`�ķ����Ͷ��Ĺ����ˡ�������������`imports/api/tasks.js`�ļ���Ϊ����`tasks`��ӷ������ܡ�

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

Ȼ����`body`ģ�洴��ʱ�����ĸղŷ��������ݣ�

```javascript
// imports/ui/body.js
 
Template.body.onCreated(function bodyOnCreated() {
  this.state = new ReactiveDict();
  Meteor.subscribe('tasks');
});
 
Template.body.helpers({
```

�����޸�����Щ����󣬻ᷢ��Ӧ���е������ֻ����ˡ�

### ʵ��˽�е�tasks

���ȣ����������'private'���Ե�task��һ����ť�������ť����������ʾ��ĳ���û��Ĺ��л�˽������

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

ͬʱ��������Ҫ�޸���������js�ļ���

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
// ��imports/api/tasks.js�ж�������tasksΪprivate�ķ���
 
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
// ��imports/ui/task.js������¼�handler���ڵ�setPrivate����

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

### �����İ�ȫ����

```javascript
// ��imports/api/tasks.js�������������İ�ȫ����
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

���������Ѿ������˽�е�task���ܣ�

## ��������

�����Ѿ�Ϊ���ǵ�Ӧ�ó��������һЩ�й��ܣ���������Ӳ��Թ��ܣ���ȷ�����ǵĳ������������������������С�������Ҫͨ��[Mocha][4]��`Javascript`���Կ����[��������][5]��

```bash
meteor add practicalmeteor:mocha
```

���ǿ���ͨ������һЩ���������á�����ģʽ�����������ǵ�Ӧ�ó���

```bash
meteor test --driver-package practicalmeteor:mocha
```

ִ����������������������������п���һ���յĲ��Խ���������������һ���򵥵Ĳ��ԣ�

```javascript
// �½�imports/api/tasks.tests.js�ļ�
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

���κβ����У����Ƕ�Ӧ��ȷ�����ǲ�������ǰ�����ݿⶼ��Ԥ�ڵġ�����`Mocha`������`beforeEach`�Ľṹ��ʵ�֣�

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

�����ǲ���ǰ�������Ѿ�������һ������û�ID��task���Ӷ�Ϊÿ�����Զ����в�ͬ��task��

����������д�� ���Է���������`task.remove`��������ȷ�����task�Ѿ���ɾ���ˡ�

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

��Meteor�Ĳ������㻹���������࣬������Ķ���ƪ����[Meteor���Ե�����][6]���˽���ࡣ

  [1]: https://www.meteor.com
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-er/
  [4]: https://mochajs.org/
  [5]: http://guide.meteor.com/testing.html#test-driver
  [6]: http://guide.meteor.com/testing.html
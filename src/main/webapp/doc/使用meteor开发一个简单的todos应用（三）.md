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

  [1]: https://www.meteor.com
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-er/
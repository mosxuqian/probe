# ʹ��meteor����һ���򵥵�todosӦ�ã�����

[`Meteor`][1]��һ��������Node.js֮�ϵ�ƽ̨����������ʵʱ��ҳ����Meteorλ�ڳ������ݿ���û�����֮�䣬���ֶ���֮�������ͬ�����¡���ΪMeteor�ǻ���Node.js�����ģ������ڿͻ��˺ͷ������˶�ʹ��JavaScript��Ϊ�������ԡ����ң�Meteor����Ĵ��뻹����ǰ�����˹��á��ٷ��Ľ������£�

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

ǰ�潲����ο�����һ���򵥵�todosӦ�ã���ο�[ʹ��meteor����һ���򵥵�todosӦ�ã�һ��][2]��������ѧϰ`Meteor`���������֡�

## һ�������APP������Android��iOS��

> ��ǰ��Meteor��֧����windows�Ϲ���APP���������windows��ʹ��Meteor����������һ���ڡ�

��ĿǰΪֹ�����ǹ����Ͳ������ǵ�Ӧ�ý�������������У�����Meteor�Ǳ���Ƴɿ�ƽ̨���еġ������ǵ�todosӦ��ͨ��һЩ�򵥵�����Ϳ�������������Android��iOS�С�

### ������iOSģ������ (��֧��Mac)

�������Macϵͳ��������������APP�����iOSģ�����У��л��������ĿĿ¼�У�ִ���������

```bash
meteor install-sdk ios
```

�⽫�ᰲװiOS����������Ĺ����ļ��������֮�������������

```bash
meteor add-platform ios
meteor run ios
```

����������iOSģ�����п�����APP�������ˡ�

### ������Androidģ������

���ն����л�����Ӧ�õĸ�Ŀ¼�£������������

```bash
meteor install-sdk android
```

�⽫�����й�����׿Ӧ�ñ�����ļ��������а�װ��֮�����룺

```bash
meteor add-platform android
```

����ͬ���������֮�����룺

```bash
meteor run android
```

һЩ��ʼ��֮����ῴ������һ��Androidģ����������ԭ��Android��Ӧ�ó���ģ�����������е���������������뿴����������������Ӧ���������ʵ�豸�����С�

### ������Android�豸��

���ȣ�����Ҫ���ǰ�氲װAndroid����Ҫ�Ĺ��߰���ƽ̨֧�֣�����ȷ����ĵ����������ֻ���[������USB����][3]��Ȼ��������������

```bash
meteor run android-device
```

Ȼ�����APP��������������ֻ����ˡ�

## ����ʹ��Reactive Dict�洢UI��ʱ״̬

�����������ǽ������ǵ�Ӧ������ӿͻ��˹��˵Ĺ��ܣ����û�ֻ�ܿ���δ��ɵĹ��ܡ����ǽ�Ҫѧϰʹ��[`Reactive Dict`][4]���ڿͻ��˴洢״̬��һ��`Reactive Dict`�ͺ���ʹ��`key`��`value`����ͨjs���󣬵�������ʵʱ�ġ�

���ڣ�������Ҫ���һ����ѡ����`body.html`ģ��ҳ����

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

������������Ҫ���`reactive-dict`��������js��ʹ����,����ʹ������£�

```bash
meteor add reactive-dict
```

```javascript
// ��imports/ui/body.js�����state
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
// ��imports/ui/body.js��Ϊ��ѡ������¼�
    // Clear form
    target.text.value = '';
  },
  'change .hide-completed input'(event, instance) {
    instance.state.set('hideCompleted', event.target.checked);
  },
});
```

```javascript
// ��imports/ui/body.js���helpers
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

Ϊ����ʾδ��ɵ�task��������`imports/ui/body.js`����Ӵ��룺

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

  [1]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://developer.android.com/tools/device.html#developer-device-options
  [4]: https://atmospherejs.com/meteor/reactive-dict
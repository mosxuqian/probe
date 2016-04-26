# ʹ��meteor����һ���򵥵�todosӦ��

[`Meteor`][1]��һ��������Node.js֮�ϵ�ƽ̨����������ʵʱ��ҳ����Meteorλ�ڳ������ݿ���û�����֮�䣬���ֶ���֮�������ͬ�����¡���ΪMeteor�ǻ���Node.js�����ģ������ڿͻ��˺ͷ������˶�ʹ��JavaScript��Ϊ�������ԡ����ң�Meteor����Ĵ��뻹����ǰ�����˹��á��ٷ��Ľ������£�

> Meteor is a full-stack JavaScript platform for developing modern web and mobile applications. Meteor includes a key set of technologies for building connected-client reactive applications, a build tool, and a curated set of packages from the Node.js and general JavaScript community.

## һ��������ĵ�һ��Ӧ��

���ȣ�ȷ��Ҫ����ĵ������Ѿ���װ����`Meteor`,�������ն�������`meteor --help`���ж��Ƿ��Ѱ�װ�������ӡ����`Meteor`��صİ���������ʾ�Ѿ���װ�ɹ���ͬʱ�����ʹ��`meteor update`������`meteor`�汾���������¡����û�а�װ������Բο�[����][2]�����а�װ��

�л�������Ҫ������Ŀ��Ŀ¼�����ն���������������������һ����Ϊ`simple-todos`����Ŀ��

```bash
meteor create simple-todos
```

�����Ŀ¼�¾ͻ�����һ�����������ļ����ļ��е���Ŀ

```
client/main.js        # a JavaScript entry point loaded on the client
client/main.html      # an HTML file that defines view templates
client/main.css       # a CSS file to define your app's styles
server/main.js        # a JavaScript entry point loaded on the server
package.json          # a control file for installing NPM packages
.meteor               # internal Meteor files
.gitignore            # a control file for git
```

Ȼ��ִ�������������������´�����Ӧ��:

```bash
cd simple-todos
meteor npm install
meteor
```

�����������д�[http://localhost:3000][3]�鿴���е�Ӧ��

> �����Ǽ�������֮ǰ��������������Ĭ�ϵ�Ӧ�ó������磬����������ϲ�����ı��༭���༭`client/main.html`��`<h1>`��ǩ�е����ݣ����������ļ�ʱ��������е�ҳ����Զ����µ��µ����ݡ����ǳ���Ϊ���ȴ������͡���

### ES2015 JavaScript����

����㻹û��ʹ�ù���һ����`JavaScript`���ܺ�����,�ڽ������Ŀ��������л�о��ֵֹġ�`Meteor`�Ѿ���ʼ֧��������ES2015�����ԣ����еİ�������һЩ���ܣ�

- ��ͷ������`(arg) => {return result;}`
- �ټǷ�����`render() { ... }`
- �鼶������ʹ��`const`��`let`����`var`

ͨ��`ecmascript�ĵ�`���鿴Meteor��ES2015������֧�֣������֪���������Ϣ��鿴�������£�

- [Luke Hoban's "ES6 features"][4]
- [Kyle Simpson's "You don't know JS: ES6 and beyond"][5]
- [Nikolas C. Zakas "Understanding ECMAScript 6"][6]

���ǽ�������ʹ�����Ĭ�ϵ���Ŀ������һ���򵥵�todosӦ�á�

## ��������ģ�涨����ͼ

Ϊ��todosӦ�������У�������Ҫ��ʹ������Ĵ������滻Ĭ�ϴ����Ĵ��룬Ȼ����˵����ô�����ġ�

���ȣ�ɾ��`client/main.html`��`<body>`�������µĴ��룬��������`<head>`��ǩ�е����ݣ�

```html
<!-- client/main.html -->
<head>
  <title>simple</title>
</head>
```

Ȼ�����Ǵ���`imports`Ŀ¼�����µ�һЩ�ļ�:

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

`imports/`Ŀ¼�µ��ļ�ֻ�е����˲Ż���أ�����������Ҫ��`client/main.js`�е���`imports/ui/body.js`��*ע�⣺������Ҫɾ��`client/main.js`����ǰ�Ĵ���*��

```javascript
// client/main.js
import '../imports/ui/body.js';
```

������Ķ������й�Meteor`imports`��ι����͹�����Ŀ�ṹ��[����][7]��

�����ǵ�������У����Ӧ�ý�������������������

![todosӦ��][8]

�����������ҳ���Щ��������ô�����ģ�

`Meteor`��ģ�涨����`HTML`�ļ���,`Meteor`����`HTML`�ļ���������������ı�ǩ��`<head>`, `<body>`, �� `<template>`�����κε�`<head>`��ǩ����ӵ�`HTML`��head���͵��ͻ��ˣ�����`<body>`��ǩ����ӵ�body�Ĳ��֣�����һ����ͨ��HTML�ļ����κ���`<template>`��ǩ�е����ݶ��������Meteor��ģ�棬��html��ͨ��`{{> task}}`���룬��js��ͨ��`Template.templateName`���á���Ȼ�������body��ǩ,����js�е�ͨ��`Template.body`�����á�

### ��ģ��������߼�������

����html�еĴ��붼��ͨ��[Meteor's Spacebars compiler][9]���롣`Spacebars`ʹ��˫���������������﷨���磺`{{#each}}`��`{{��if}}`����������ͼ������߼������ݡ�

### ���CSS��ʽ

�ڼ���֮ǰ��Ϊ�������ǵ�Ӧ�ÿ�����������`simple-todos.css`�ļ����������`CSS`��

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

�л���������У����Զ�ˢ��ҳ�棬todosӦ�õ�Ч������ͼ��ʾ��

![todosӦ��2][10]


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
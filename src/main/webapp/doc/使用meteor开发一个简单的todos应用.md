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

  [1]: https://www.meteor.com/
  [2]: http://blinkfox.com/meteorde-an-zhuang-he-ru-men/
  [3]: http://localhost:3000
  [4]: http://git.io/es6features
  [5]: https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond
  [6]: https://github.com/nzakas/understandinges6
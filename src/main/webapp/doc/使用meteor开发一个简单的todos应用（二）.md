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


  [1]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://developer.android.com/tools/device.html#developer-device-options
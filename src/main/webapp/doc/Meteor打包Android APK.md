## Meteor打包Android APK

### 一、使用jarsigner制作keystore

1、输入如下命令：

```bash
keytool -genkey -alias key.keystore -keyalg RSA -validity 30000 -keystore key.keystore
```

其中会要求输入签名密码和单位个人等相关信息

2、填写完毕后，输入`y`按回车即可在你的当前目录找到key.keystore文件

### 二、生成Meteor APK

首先进入你的所要打包的meteor工程，执行如下命令：

```bash
meteor build ~/demo --server=blinkfox.meteor.com
```

其中，`~/demo`即为你的项目目录，`blinkfox.meteor.com`为你应用访问的服务器地址

### 三、给APK签名

进入了~/android文件夹，就可以为apk签名了，执行如下命令：

```bash
cd android
jarsigner -verbose -keystore key.keystore -signedjar demo-signed.apk release-unsigned.apk key.keystore
```

其中，`demo-signed.apk`即为你签名后的APK名称，`release-unsigned.apk`为签名前的APK名称，看项目文件夹中就生产了签名后的apk文件
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


  [1]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [2]: http://blinkfox.com/shi-yong-meteorkai-fa-yi-ge-jian-dan-de-todosying-yong-yi/
  [3]: http://developer.android.com/tools/device.html#developer-device-options
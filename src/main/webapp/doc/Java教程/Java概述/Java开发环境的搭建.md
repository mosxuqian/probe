# Java开发环境的搭建

要进行Java开发，首先要安装JDK（Java Development Kit，Java开发工具箱）。

JDK是一系列工具的集合，这些工具是编译Java源码、运行Java程序所必需的，例如JVM、基础类库、编译器、打包工具等。不论是什么样的Java应用服务器，都是内置了某个版本的JDK，因此掌握JDK是学好Java的第一步。

JDK所提供的部分工具：

- java编译器：javac.exe
- java解释器：java.exe
- java文档生成器：javadoc.exe
- java调试器：jdb.exe

前面所说的Java版本，实际上是指JDK的版本。

最主流的JDK是Sun公司发布的JDK，除了Sun之外，还有很多公司和组织都开发了自己的JDK，例如IBM公司开发的JDK、BEA公司的Jrocket，还有GNU组织开发的JDK等等。IBM的JDK包含的JVM（Java Virtual Machine）运行效率要比Sun JDK包含的JVM高出许多，而专门运行在x86平台的Jrocket，在服务端运行效率也要比Sun JDK好很多，但不管怎么说，还是需要先把Sun JDK掌握好。

## 1. JDK的下载

JDK有不同的版本（J2SE、J2EE、J2ME），初学Java，一般都选择J2SE。J2SE的下载地址为：[http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html][1]

打开网址，会看到如下所示的页面：

![JDK下载页面][2]

下载JDK需要接受相应条款，默认是不能下载的。

选择相应的操作系统，点击下载链接即可下载。
## 2. JDK的安装

双击下载好的安装包进行安装，点击“下一步”，出现如下图所示的界面：

![JDK安装地址选择][3]

这里可以根据你的习惯改变JDK的安装目录，不过你要记住，后面会用到这个安装目录。

可以看到，JDK包含了Java开发工具（编译器、打包工具等）、源代码（基础类库）和公共JRE，这三项都是默认安装的，是Java开发所必须的，缺一不可。

点击“下一步”，等待安装。

![安装中][4]

JDK安装完成后，会提示你是否安装JRE，如下图所示：

![安装JRE][5]

JDK中已经包含了JRE，无需再次安装，点击“取消”即可。

![取消安装JRE][6]

![安装完成][7]

点击“关闭”，完成安装。

## 3. 环境变量的设置

如果你不了解环境变量的概念，请猛击这里：[什么是环境变量][8]

进入环境变量配置窗口，在“用户变量”中，设置3项属性，`JAVA_HOME`、`PATH`、`CLASSPATH`（大小写无所谓），若已存在则点击“编辑”，不存在则点击“新建”：

- JAVA_HOME：设为JDK的安装路径(如`D:\Program Files\jdk1.7.0_71`)，此路径下包括lib，bin，jre等文件夹（此变量最好设置，因为以后运行tomcat，eclipse等都需要依靠此变量）。
- Path：使得系统可以在任何路径下识别java命令，设为：`%JAVA_HOME%\bin`。`%JAVA_HOME%`就是引用前面指定的`JAVA_HOME`变量。
- CLASSPATH：Java运行环境加载类的路径，只有类在classpath中，才能被识别和加载，设为 `.;%JAVA_HOME%\lib`（注意前面的点号(.)，点号表示当前路径）。

打开一个CMD窗口，输入`java -version`或者`javac`命令，看到很多说明信息，证明已经安装并配置成功了。

## 4. Eclipse的安装

有了JDK，你可以编译Java源码，运行Java程序，但是还没有代码编辑器，没有版本管理工具，也不能方便的管理工程文件，不能与团队协作。安装Eclipse，你才能完成这些工作。

Eclipse是一款Java集成开发环境(IDE)。Java IDE有很多，有免费的，有收费的，有英文的，中文的，有多国语言的，Eclipse是最常用的一款，IT公司大部分员工都使用Eclipse。

Eclipse开源免费，使用人数最多，提供了丰富的插件和友好的编辑界面，资源占用比较低，速度比较快，本身就是用Java开发的。

> 注意：一定要设置环境变量，Eclipse的运行依赖于这些环境变量。

Eclipse的下载地址为：[http://www.eclipse.org/downloads/][9]

打开链接，看到下面的页面：

![Eclipse下载选择][10]

选择红色方框框起来的版本，下载即可。

Eclipse是免安装的，你可以将下载的压缩文件解压到任意目录，以后也可以随意更换目录。第一次启动Eclipse，会要求你设置默认的工程目录，你可以只设置一次，也可以每次启动都设置。

![Eclipse工作空间选择][11]

点击“OK”，Eclipse 就成功启动，弹出欢迎界面。

## 5. 安装简体中文语言包

Eclipse默认是英文的，如果你的英文能力比较弱，可以安装简体中文语言包。不过我建议你使用英文版，作为程序员，你绝对有必要提高你的英文水平，很多技术文档都是英文的，只要持之以恒，很快就能提高你的英文阅读能力。

语言包的下载地址为：[http://www.eclipse.org/babel/downloads.php][12]

首先查看你当前安装的Eclipse的版本。在Eclipse菜单栏中选择“Help --> About Eclipse”，弹出一个对话框：

![关于Eclipse][13]

打开链接，向下滚动鼠标，下载对应版本的语言包：

![对应版本的语言包][14]

进入下载页面，找到简体中文语言包：

![选择Eclipse中文包][15]

这里包含了Eclipse软件本身及其模块和插件的语言包，你可以一次全部下载，也可以需要的时候再来下载，红色方框框起来的是Eclipse软件本身的中文包，我们需要下载它。

将下载到的文件解压，得到一个名为eclipse的文件夹，它里面包含两个文件夹：features和plugins，复制features和plugins到你的eclipse安装目录，覆盖原程序文件即可。

![安装Eclipse中文包][16]

重启Eclipse，汉化完成，你可以享受中文版的Eclipse了。

[1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
[2]: http://www.weixueyuan.net/uploads/allimg/141124/1-141124195PM30.png
[3]: http://www.weixueyuan.net/uploads/allimg/141124/1-141124200Ac94.png
[4]: http://www.weixueyuan.net/uploads/allimg/141124/1-141124201120340.png
[5]: http://www.weixueyuan.net/uploads/allimg/141124/1-141124201231354.png
[6]: http://www.weixueyuan.net/uploads/allimg/141124/1-141124201404153.png
[7]: http://www.weixueyuan.net/uploads/allimg/141124/1-14112420142J04.png
[8]: http://www.weixueyuan.net/view/6310.html
[9]: http://www.eclipse.org/downloads/
[10]: http://www.weixueyuan.net/uploads/allimg/141125/1-141125092159412.png
[11]: http://www.weixueyuan.net/uploads/allimg/141125/1-1411250934205V.png
[12]: http://www.eclipse.org/babel/downloads.php
[13]: http://www.weixueyuan.net/uploads/allimg/141125/1-141125101313161.png
[14]: http://www.weixueyuan.net/uploads/allimg/141125/1-141125101G5936.png
[15]: http://www.weixueyuan.net/uploads/allimg/141125/1-141125102IWA.png
[16]: http://www.weixueyuan.net/uploads/allimg/141125/1-1411251031193V.png
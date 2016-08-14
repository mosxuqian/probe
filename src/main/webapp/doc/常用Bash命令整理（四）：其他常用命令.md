# 常用Bash命令整理（四）：其他常用命令

### 1. hostname - 查看主机名

`hostname`命令用于查看系统的主机名，或是修改系统的主机名。

`hostname`的常用命令如下：

```bash
# 显示系统的当前主机名
hostname

# 修改你系统的主机名
hostname blinkfox-system

# 使用 -F 选项，从指定的文件中读取主机名
hostname -F /root/hostname.txt
```

### 2. uptime - 查看系统运行时间

`uptime`命令用于打印系统的运行时间等信息。使用如下：

```bash
uptime
```

### 3. w、who - 列出登录的用户

`w`命令用于显示登录用户及他们当前运行的进程。输入的内容格式如下：

```bash
w

# 打印如下
22:42  up 18 days, 1 hr, 2 users, load averages: 1.23 1.79 1.75
USER     TTY      FROM              LOGIN@  IDLE WHAT
blinkfox console  -                日19   6days -
blinkfox s000     -                五23       - w
```

`who`命令有与`w`命令类似的用途，但它的功能比`w`命令更强大一些。语法格式如下：

```bash
who [OPTION]... [FILE | ARG1 ARG2]
```

`who`常用命令如下：

```bash
# 显示当前登录的所有用户信息
who

# 显示系统的启动时间
who -b

# 显示系统登录进程
who -l

# 显示与当前标准输入关联的用户信息
who -m

# 显示系统的运行级别
who -r

# 显示所有登录用户的用户名和登录用户数
who -q
```

### 4. uname - 查看系统信息

`uname`命令用于打印内核名称和版本、主机名等系统信息。命令的语法如下所示：

```bash
uname [OPTION]...
```

常用使用方式如下：

```bash
# 只打印内核的名称
uname

# 使用 -n 选项，只打印系统的主机名
uname -n

# 使用 -r 选项，打印内核版本信息
uname -r

# 使用 -m 选项，打印系统的硬件名称
uname -m

# 使用 -p 选项，打印系统的处理器类型信息
uname -p

# 使用 -i 选项，打印系统的硬件平台信息
uname -i

# 使用 -a 选项，打印上述所有示例中的信息
uname -a
```
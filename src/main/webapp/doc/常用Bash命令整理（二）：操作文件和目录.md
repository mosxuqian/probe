# 常用Bash命令整理（二）：操作文件和目录

标签：Linux Bash

### 1. touch - 创建文件

`touch`命令就可用于创建、变更和修改文件的时间戳。它是 Linux 操作系统的标准程序。`touch`命令又如下选项：

```bash
-a: 只改变访问时间 
-c: 不创建任何文件
-m: 只改变修改时间
-r: 使用指定文件的时间替代当前时间
-t: 使用 [[CC]YY]MMDDhhmm[.ss] 替代当前时间
```

touch 命令的常见用法如下：

```bash
# 创建一个名为 effyl 的新空文件
touch effyl

# 同时创建名称分别为 effyl myeffyl lueffyl 的三个文件
touch effyl myeffyl lueffyl

# 使用 -a 选项，可以改变或更新文件的最新访问时间，如果文件 effyl 不存在，则新创建一个
touch -a effyl

# 使用 -c 选项，可以避免创建一个新文件，并用当前时间更新文件的时间戳
touch -c effyl

# 使用 -m 选项，可以只改变文件的修改时间，而访问时间不变
touch -m effyl

# 使用 -c 和 -t 选项，来明确设置文件的时间
touch -c -t YYMMDDHHMM filename

# 如果想使用文件 myeffyl 的时间戳更新文件 effyl 的时间戳，可以使用 -r 选项
touch -r myeffyl effyl
```

### 2.mkdir - 创建目录

`mkdir`命令用于创建一个新目录。最基本的`mkdir`命令的使用方法如下所示：

```bash
# 在当前目录下创建一个给定的目录名
mkdir <dirname>

# 在 backup 中的相对路径创建一个名为 old 的目录
mkdir backup/old

# 在 backup 中的绝对路径中创建一个名为 old 的目录
mkdir /home/blinkfox/backup/old

# 使用 -p 选项，会自动创建所有还不存在的父目录
mkdir -p backup/old

# 使用 -m 选项，可以设置将要创建目录的权限
# 如：创建一个任何人都有读写访问权限的目录
mkdir -p -m 777 backup/old
```
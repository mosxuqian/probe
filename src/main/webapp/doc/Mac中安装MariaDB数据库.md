# Mac中安装MariaDB数据库

标签：数据库 MariaDB MySQL

## 一、MariaDB简介

> [MariaDB][1]数据库管理系统是`MySQL`的一个分支，主要由开源社区在维护，采用`GPL`授权许可`MariaDB`的目的是完全兼容`MySQL`，包括`API`和命令行，使之能轻松成为`MySQL`的代替品。在存储引擎方面，使用`XtraDB`来代替`MySQL`的`InnoDB`。`MariaDB`由`MySQL`的创始人Michael Widenius主导开发，他早前曾以10亿美元的价格，将自己创建的公司`MySQL`卖给了SUN，此后，随着SUN被甲骨文收购，`MySQL`的所有权也落入Oracle的手中。`MariaDB`名称来自Michael Widenius的女儿Maria的名字。

![MariaDB图片][2]

## 二、MariaDB安装步骤

如果你是Mac上的开发者，通过本文你可以在`OS X`上通过[Homebrew][3]来简单的获取安装最新稳定版本的`MariaDB`,接下来我们将一步步的来指导安装MariaDB数据库，如果你的Mac中已经安装好了[Xcode][4]和`Homebrew`的话，则直接跳到第四步。

### 1. 安装Xcode

使用如下命令来安装Xcode：

```bash
xcode-select --install
```

### 2. 安装Homebrew

安装Homebrew的命令：

```bash
ruby -e "$(curl -fsSL https://raw.github.com/Homebrew/homebrew/go/install)"
```

### 3. 检查Homebrew

```bash
brew doctor
```

### 4. 更新Homebrew

如果通过上面的命令检查到`Homebrew`不是最新的版本，可以通过如下命令来把`Homebrew`更新到最新：

```bash
brew update
```

### 5. 确认MariaDB的版本

在Homebrew仓库中确认MariaDB的版本：

```bash
brew info mariadb
```

### 6. 安装MariaDB

通过如下命令来下载安装`MariaDB`：

```bash
brew install mariadb
```

### 7. 运行数据库安装程序

分别执行下面的命令来实现安装：

```bash
unset TMPDIR
cd /usr/local/Cellar/mariadb/10.0.10/
mysql_install_db
```

### 8. 运行MariaDB

经过了上面的若干命令，已经安装好了`MariaDB`数据库，但是`MariaDB`数据库服务并没有启动，你可以通过这个命令来启动`MariaDB`数据库服务：

```bash
mysql.server start
```

### 9. 安全的完成安装

通过上面的启动`MariaDB`数据库服务，你已经可以连接MariaDB的数据库了，但是还不够安全，通过如下步骤可以完成更全面的设置，如：重设`root`用户的密码、移除匿名用户、移除默认的`test`数据库等等，具体的执行和设置如下：

```bash
➜  10.1.14: mysql_secure_installation

NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MariaDB
      SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!

In order to log into MariaDB to secure it, we'll need the current
password for the root user.  If you've just installed MariaDB, and
you haven't set the root password yet, the password will be blank,
so you should just press enter here.

Enter current password for root (enter for none):
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
Enter current password for root (enter for none):
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
Enter current password for root (enter for none):
OK, successfully used password, moving on...

Setting the root password ensures that nobody can log into the MariaDB
root user without the proper authorisation.

Set root password? [Y/n] Y
New password:
Re-enter new password:
Password updated successfully!
Reloading privilege tables..
 ... Success!


By default, a MariaDB installation has an anonymous user, allowing anyone
to log into MariaDB without having to have a user account created for
them.  This is intended only for testing, and to make the installation
go a bit smoother.  You should remove them before moving into a
production environment.

Remove anonymous users? [Y/n] Y
 ... Success!

Normally, root should only be allowed to connect from 'localhost'.  This
ensures that someone cannot guess at the root password from the network.

Disallow root login remotely? [Y/n] n
 ... skipping.

By default, MariaDB comes with a database named 'test' that anyone can
access.  This is also intended only for testing, and should be removed
before moving into a production environment.

Remove test database and access to it? [Y/n] Y
 - Dropping test database...
 ... Success!
 - Removing privileges on test database...
 ... Success!

Reloading the privilege tables will ensure that all changes made so far
will take effect immediately.

Reload privilege tables now? [Y/n] Y
 ... Success!

Cleaning up...

All done!  If you've completed all of the above steps, your MariaDB
installation should now be secure.

Thanks for using MariaDB!
```

### 10. 连接MariaDB数据

连接`MariaDB`数据库的命令：

```bash
mysql -u root -p
```

### 11. 验证MariaDB版本

```sql
MariaDB [(none)]> select @@version;
+-----------------+
| @@version       |
+-----------------+
| 10.1.14-MariaDB |
+-----------------+
1 row in set (0.00 sec)
```

## 三、MariaDB基础命令

下面是`MariaDB`的一些基础使用命令：

```sql
-- 显示数据库列表
show databases;

-- 切换到名为mysql的数据库，显示该库中的数据表
use mysql;
show tables;

-- 显示数据表table的结构
desc table;

-- 建数据库A与删数据库A
create database `database_A`;
drop database `database_A`;

-- 建表：
use database_A;
create table table_A(字段列表);
drop table table_A;

-- 显示表中的记录：
select * from table_A;

-- 清空表中记录：
delete from table_A;
```

  [1]: https://mariadb.org/
  [2]: http://static.blinkfox.com/mariadb20160716.png
  [3]: http://brew.sh/
  [4]: https://developer.apple.com/xcode/ide/cn/


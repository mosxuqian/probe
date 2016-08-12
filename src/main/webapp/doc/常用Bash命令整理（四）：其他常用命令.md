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
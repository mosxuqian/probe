# 常用Bash命令整理（三）：文本处理

### 1. sort - 文本排序

`sort`命令用于将文本文件的行排序。默认情况下，`sort`命令是按照字符串的字母顺序排序。

sort 的常用命令如下：

```bash
# 将文本内容按字母顺序排序
sort example.txt

# 使用 -u 选项，移除所有重复行后排序
sort -u example.txt

# 使用 -n 选项，将令数字按数值的大小排序
sort -n example.txt

# 使用 -r 选项，以倒序方式排序
sort -n -r example.txt

# 同时将 file1、file2 的内容排序
sort file1 file2
```

### 2.uniq - 文本去重

`uniq`命令用于移除或发现文件中重复的条目。

```bash
# 它将移除文件中重复的行并显示单一行
uniq example.txt

# 可以统计重复行出现的次数
uniq -c example.txt

# 使用 -d 选项，只显示文件中有重复的行并只显示一次
uniq -d example.txt

# 使用 -D 选项，显示文件中所有重复的行
uniq -D example.txt

# 使用 -u 选项，只显示文件中不重复的行
uniq -u example.txt

# 使用 -w 选项，限制 uniq 命令只比较每行的前 3 个字符是否重复
uniq -w 3 example.txt

# 使用 -s 选项，避免 uniq 命令比较每行的前 3 个字符，只比较后面的字符是否重复
uniq -s 3 example.txt

# 使用 -f 选项，避免 uniq 命令比较第一列的内容，只比较后面的字符是否重复
uniq -f 1 example.txt
```

### 3.tr - 替换或删除字符

`tr`命令主要用于删除文件中控制字符或进行字符转换。使用`tr`时要转换两个字符串：字符串 1 用于查询，字符串 2 用于处理各种转换。`tr`刚执行时，字符串 1 中的字符被映射到字符串 2 中的字符，然后转换操作开始。

`tr`命令的语法如下所示：

```bash
tr [OPTION]... SET1 [SET2]
```

常用命令示例：

```bash
# 若要将大括号转换为小括号
tr '{}' '()' < textfile > newfile

# 若要将大括号转换成方括号
tr '{}' '\[]' < textfile > newfile

# 若要将小写字符转换成大写，请输入：
tr 'a-z' 'A-Z' < textfile > newfile

# 若要创建一个文件中的单词列表
tr -cs '[:lower:][:upper:]' '[\n*]' < textfile > newfile

# 若要从某个文件中删除所有空字符
tr -d '\0' < textfile > newfile

# 若要用单独的换行替换每一序列的一个或多个换行，请输入：
tr -s '\n' < textfile > newfile

# 要以单个“#”字符替换 <space> 字符类中的每个字符序列
tr -s '[:space:]' '[#*]'
```
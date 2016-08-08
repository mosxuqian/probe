# 常用Bash命令整理（二）：文本处理

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
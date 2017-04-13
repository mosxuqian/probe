# apache commons相关考题

标签： commons 面试考题

---

## 基础知识整理

### 1、Apache Commons是常用工具包(官网共43个，以下仅列出常用的14个)

- BeanUtils 提供对 Java 反射和自省API的包装
- Collections 提供一个类包来扩展和增加标准的 Java Collection框架
- DBCP 提供数据库连接池服务
- DbUtils 是一个 JDBC helper 类库，完成数据库任务的简单的资源清除代码
- Digester 是一个 XML-Java对象的映射工具，用于解析 XML配置文件
- EL 提供在JSP2.0规范中定义的EL表达式的解释器
- FileUpload 使得在你可以在应用和Servlet中容易的加入强大和高性能的文件上传能力
- HttpClient 提供了可以工作于HTTP协议客户端的一个框架
- IO 是一个 I/O 工具集
- Lang 提供了许多许多通用的工具类集，提供了一些java.lang中类的扩展功能
- Logging 是一个各种 logging API实现的包裹类
- Math 是一个轻量的，自包含的数学和统计组件，解决了许多非常通用但没有及时出现在Java标准语言中的实践问题
- Net 是一个网络工具集，基于 NetComponents 代码，包括 FTP 客户端等等
- Pool 提供了通用对象池接口，一个用于创建模块化对象池的工具包，以及通常的对象池实现

### 2、Commons Lang常用类、方法

除了6个Exception类和2个已经deprecated的数字类之外，commons.lang包共包含了17个实用的类：

- ArrayUtils – 用于对数组的操作，如添加、查找、删除、子数组、倒序、元素类型转换等
- BitField – 用于操作位元，提供了一些方便而安全的方法
- BooleanUtils – 用于操作和转换 boolean 或者 Boolean 及相应的数组
- CharEncoding – 包含了 Java 环境支持的字符编码，提供是否支持某种编码的判断
- CharRange – 用于设定字符范围并做相应检查
- CharSet – 用于设定一组字符作为范围并做相应检查
- CharSetUtils – 用于操作 CharSet 
- CharUtils – 用于操作 char 值和 Character 对象
- ClassUtils – 用于对 Java 类的操作，不使用反射
- ObjectUtils – 用于操作 Java 对象，提供 null 安全的访问和其他一些功能
- RandomStringUtils – 用于生成随机的字符串
- SerializationUtils – 用于处理对象序列化，提供比一般 Java 序列化更高级的处理能力
- StringEscapeUtils – 用于正确处理转义字符，产生正确的 Java 、 JavaScript 、 HTML 、 XML 和 SQL 代码
- StringUtils – 处理 String 的核心类，提供了相当多的功能
- SystemUtils – 在 java.lang.System 基础上提供更方便的访问，如用户路径、 Java 版本、时区、操作系统等判断
- Validate – 提供验证的操作，有点类似 assert 断言
- WordUtils – 用于处理单词大小写、换行等

### 3、对象属性复制

```java
Student dest = new Student();
Student orig = new Student("Alex", "20140504", 25);
//复制所有属性，用clone更方便
BeanUtils.copyProperties(dest, orig);
```

### 4、Map和Bean相互转换

Bean转Map

```java
Student stu = new Student("ObjectToMap", "20140504", 25);
Map<String, String> mProp = BeanUtils.describe(stu);
```

```java
Map<String, Object> mProp = new HashMap<String, Object>();
mProp.put("name", "Sum");
……
Student tmpStu = new Student();
BeanUtils.populate(tmpStu, mProp);
```

### 5、对象克隆

```java
Student stu = (Student)BeanUtils.cloneBean(被克隆对象);
```

### 6、Collections介绍

- MultiMap 一个key对应多个value
- BidiMap 可以根据value检索key
- CaseInsensitiveMap 大小写不敏感的map
- Bag　能够对同一个对象存储多次，并追踪对象有多少份拷贝。
- LazyMap 当检索的值不存在是，自动创建键值对放入map
- CollectionUtils 集合排序,判断集合是否为空

### 7、HttpClient介绍

以下列出的是 HttpClient 提供的主要的功能，要知道更多详细的功能可以参见 HttpClient 的主页。
- 实现了所有 HTTP 的方法（GET,POST,PUT,HEAD 等）
- 支持自动转向
- 支持 HTTPS 协议
- 支持代理服务器等

### ８、使用 HttpClient 需要以下 6 个步骤：

- 创建 HttpClient 的实例
- 创建某种连接方法的实例，在这里是GetMethod。在 GetMethod 的构造函数中传入待连接的地址
- 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
- 读 response
- 释放连接。无论执行方法是否成功，都必须释放连接
- 对得到后的内容进行处理

### 9、Commons IO常用工具类

#### (1). Apache Commons IO 主要功能：

- 工具类
- 输入
- 输出
- 过滤器
- 比较器
- 文件监控器

#### (4). 主要工具类:

- FilenameUtils 这个工具类是用来处理文件名（译者注：包含文件路径）的，他可以轻松解决不同操作系统文件名称规范不同的问题（比如windows和Unix）（在Unix系统以及Linux系统中文件分隔符是“/”，不支持”\“，windows中支持”\“以及”/“）。
- FileUtils 提供文件操作（移动文件，读取文件，检查文件是否存在等等）的方法。  IOCase：提供字符串操作以及比较的方法。
- FileSystemUtils 提供查看指定目录剩余空间的方法。
- IOUtils 输入输出常用工具方法

#### (5). 主要问题:

- 需要把某个InputStream或者Reader的内容拷贝到Writer中。
- 需要把某个String字符串拷贝至OutputStream。
- Commons IO关闭流
- 需要将文件拷贝为另一个文件，或者需要将某个文件拷贝到目录中

```java
FileUtils.copyFile(src, dest)
FileUtils.copyFileToDirectory(src, dir)
FileUtils.writeStringToFile(dest, string)
```

- 有一个目录，其下有子目录和文件，如何删除这个文件以及其下的子目录和文件(deleteDirectory)
- 上述问题中，如果只想删除文件并保留文件夹，应该如何实现(cleanDirectory)
有一个文件，修改文件最近修改时间。FileUtils.touch(file)
- 有一个目录，其中有不同种类的文件，如何查询出其中的PDF文件使用IOFileFilter的子类，可以方便的过滤出需要的文件。
- 一个目录中有若干文件，如何按照修改时间降序取得文件列表。
- 现在有一个文件夹，需要监控这个文件夹中的文件或者文件夹操作

10. 分别写出以下代码的返回值

```java
StringUtils.isBlank(null) 
StringUtils.isBlank("") 
StringUtils.isBlank(" ")
StringUtils.isBlank("        ")
StringUtils.isBlank("\t \n \f \r")
StringUtils.isEmpty(null) 
StringUtils.isEmpty("")
StringUtils.isEmpty(" ")
StringUtils.isEmpty("        ")
```
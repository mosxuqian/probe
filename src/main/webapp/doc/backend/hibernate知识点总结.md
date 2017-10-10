# Hibernate知识点总结

## 总体概述

![Hibernate思维导图](http://img.blog.csdn.net/20150629151119258)

Hibernate大体上分为三个部分：

- 关于xml的配置
- Hibernate相关的api
- 具体的增删改查操作

## 关于xml的配置

Hibernate中涉及的xml主要有两部分:

### 描述连接数据库的配置文件 hibernate.cfg.xml

这一部分主要记录了**连接数据库**的必要信息，比如数据库驱动、数据库名称、用户名和密码，以及Hibernate访问不同数据库需要的方言配置，还有一部分就是对xxx.hbm.xml文件的引入。它是hibernate的核心配置文件。

### 描述类与表中的关系映射配置文件 xxx.hbm.xml

主要描述了**实体类与数据库中表中的映射关系**，我把里面的关系主要分为两部分：

- 一部分是关于类级别的，主要描述的是自身的属性和数据表中字段的对应关系；
- 另一部分是对级联关系的描述，比如一对一、一对多、多对对等实体与实体之间联系的描述，配置好之后，数据库会根据配置的级联关系生成对应的外键约束。

## Hibernate API

应用程序可以直接通过Hibernate API访问数据库。Hibernate API中的接口可分为以下几类。

- 用于配置Hibernate的接口：`Configuration`，配置Hibernate，启动Hibernate，创建SessionFactory对象；
- - 初始化Hibernate的接口：`SessionFactory`，初始化Hibernate，当数据存储源的代理，创建Session对象。
- 提供访问数据库的操作（如保存、更新、删除和查询对象）的接口。这些接口包括：`Session`、`Transaction`和`Query`接口。
- 回调接口，使应用程序接受Hibernate内部发生的事件，并作出相应的回应。这些接口包括：`Interceptor`、`Lifecycle`和`Validatable`接口。
- 用于扩展Hibernate的功能的接口，如`UserType`、`CompositeUserType`和`IdentifierGenerator`接口。如果需要的话，应用程序可以扩展这些接口。

Hibernate内部封装了JDBC、JTA（JavaTransaction API）和JNDI（Java Naming and Directory Interface）。JDBC提供底层的数据访问操作，只要用户提供了相应的JDBC驱动程序，Hibernate可以访问任何一个数据库系统。JNDI和JTA使Hibernate能够和J2EE应用服务器集成。

## 对象操作

Hibernate实现对象的增删改查操作依赖于Hibernate API函数，从而操作数据库实现。这块我也分成了两部分：

- 一部分是对类自身的的增删改查操作
- 一部分是和其他类相关联的增删该查操作。

但是使用Hibernate提供的封装完成对对象的操作，性能较低，可以通过在类与表中的关系映射配置文件中修改参数的方式，提高Hibernate的操作性能。
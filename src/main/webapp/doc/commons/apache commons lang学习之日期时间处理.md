# apache commons学习之日期时间处理

标签（空格分隔）： commons Java

---

> 码农不识Apache，码尽一生也枉然。

## FastDateFormat

`FastDateFormat`是一个快速且线程安全的时间操作类，它完全可以替代`SimpleDateFromat`。因为是线程安全的，所以你可以把它作为一个类的静态字段使用。构造方法为protected，不允许直接构造它的对象，可以通过工厂方法获取。FastDateFormat之所以是线程安全的，是因为这个类是无状态的：内部的成员在构造时就完成了初始化，并在对象存活期，不提供任何API供外界修改他们。

### getInstance(String pattern)

获取指定日期时间格式的`FastDateFormat`实例。

### format(Date date)

将日期时间格式化为字符串。

```java
FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()); // 2017-06-03 23:32:31
```

### format(long millis)

同`format(Date date)`相似。

### format(Calendar calendar)

同`format(Date date)`相似。

## DateFormatUtils

将时间转化为字符串的工具类。不可实例化对象且线程安全，依赖于`FastDateFormat`。

### 预定义的日期格式

`DateFormatUtils`预定义的日期格式有如下几种：

```java
public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
public static final FastDateFormat ISO_8601_EXTENDED_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
public static final FastDateFormat ISO_8601_EXTENDED_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
public static final FastDateFormat ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
public static final FastDateFormat SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
```

### format(Date date, String pattern)

将日期格式化为字符串。

```java
DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"); // 2017-06-03 23:03:53
DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()); // 2017-06-03T23:09:52
DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"); // 2017-06-03 23:16:59
```

### format(long millis, String pattern)

同`format(Date date, String pattern)`相似。

### format(Calendar calendar, String pattern)

同`format(Date date, String pattern)`相似。

## DateUtils

`DateUtils`提供了很多很方便的功能，减轻了使用Date的复杂性。把原来需用`Calendar`才能完成的功能统一集中了起来，也就是说没有对应的`CalendarUtils`类。在JDK中，Date与Calendar概念本身就有些混淆，只是为了保持兼容性才引入的Calendar。相对于Calendar提供的方法，DateUtils提供了更加合理的方法，对时间的单个字段操作变得更加的容易。
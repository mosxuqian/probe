# apache commons学习之CharEncoding

标签（空格分隔）： commons Java

---

> 码农不识Apache，码尽一生也枉然。

### 常量

```java
public static final String ISO_8859_1 = "ISO-8859-1";
public static final String US_ASCII = "US-ASCII";
public static final String UTF_16 = "UTF-16";
public static final String UTF_16BE = "UTF-16BE";
public static final String UTF_16LE = "UTF-16LE";
public static final String UTF_8 = "UTF-8";
```

### isSupported(String name)

判断给定的字符集名称是否正确。

```java
boolean b = CharEncoding.isSupported("gbk"); // true
boolean b = CharEncoding.isSupported(""); // false
boolean b = CharEncoding.isSupported("xxx"); // false
boolean b = CharEncoding.isSupported(null); // false
```
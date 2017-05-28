# apache commons学习之StringUtils

标签（空格分隔）： commons Java

---

## 判断空字符串

### isEmpty(CharSequence cs)

判断是否是空字符串，代码示例：

```java
StringUtils.isEmpty(null)      = true
StringUtils.isEmpty("")        = true
StringUtils.isEmpty(" ")       = false
StringUtils.isEmpty("bob")     = false
StringUtils.isEmpty("  bob  ") = false
```

### isNotEmpty(CharSequence cs)

判断是否不是空字符串，与`isEmpty(CharSequence cs)`相反。

### isAnyEmpty(CharSequence... css)

判断是否含有空字符串，代码示例：

```java
StringUtils.isAnyEmpty(null)             = true
StringUtils.isAnyEmpty(null, "foo")      = true
StringUtils.isAnyEmpty("", "bar")        = true
StringUtils.isAnyEmpty("bob", "")        = true
StringUtils.isAnyEmpty("  bob  ", null)  = true
StringUtils.isAnyEmpty(" ", "bar")       = false
StringUtils.isAnyEmpty("foo", "bar")     = false
```

### isNoneEmpty(CharSequence... css)

判断是否都不是空字符串，与`isAnyEmpty(CharSequence... css)`相反。

### isAllEmpty(CharSequence... css)

判断是否都是空字符串，代码示例：

```java
StringUtils.isAllEmpty(null)             = true
StringUtils.isAllEmpty(null, "")         = true
StringUtils.isAllEmpty(new String[] {})  = true
StringUtils.isAllEmpty(null, "foo")      = false
StringUtils.isAllEmpty("", "bar")        = false
StringUtils.isAllEmpty("bob", "")        = false
StringUtils.isAllEmpty("  bob  ", null)  = false
StringUtils.isAllEmpty(" ", "bar")       = false
StringUtils.isAllEmpty("foo", "bar")     = false
```

### isBlank(CharSequence cs)

判断是否是“大空字符串”，代码示例：

```java
StringUtils.isBlank(null)      = true
StringUtils.isBlank("")        = true
StringUtils.isBlank(" ")       = true
StringUtils.isBlank("bob")     = false
StringUtils.isBlank("  bob  ") = false
```

### isNotBlank(CharSequence cs)

判断是否不是“大空字符串”，与`isBlank(CharSequence cs)`相反，与`isNotEmpty(CharSequence cs)`相似。

### isAnyBlank(CharSequence... css)

判断是否有“大空字符串”，与`isAnyEmpty(CharSequence... css)`相似。

### isNoneBlank(CharSequence... css)

判断是否都不是“大空字符串”，与`isAnyBlank(CharSequence... css)`相反，与`isNoneEmpty(CharSequence... css)`相似。

### isAllBlank(CharSequence... css)

判断是否都是“大空字符串”，与`isAllEmpty(CharSequence... css)`相似。

## 截取字符串

### trim(String str)

去除字符串前后的控制符，代码示例：

```java
StringUtils.trim(null)          = null
StringUtils.trim("")            = ""
StringUtils.trim("     ")       = ""
StringUtils.trim("abc")         = "abc"
StringUtils.trim("    abc    ") = "abc"
```

### trimToNull(String str)

去除字符串前后的控制符，如何是空字符串则转为`null`，代码示例：

```java
StringUtils.trimToNull(null)          = null
StringUtils.trimToNull("")            = null
StringUtils.trimToNull("     ")       = null
StringUtils.trimToNull("abc")         = "abc"
StringUtils.trimToNull("    abc    ") = "abc"
```

### trimToEmpty(String str)

去除字符串前后的控制符，如何是`null`则转为空字符串，代码示例：

```java
StringUtils.trimToEmpty(null)          = ""
StringUtils.trimToEmpty("")            = ""
StringUtils.trimToEmpty("     ")       = ""
StringUtils.trimToEmpty("abc")         = "abc"
StringUtils.trimToEmpty("    abc    ") = "abc"
```

### truncate(String str, int maxWidth)

截断字符串，具有以下特点：

- 如果str字符串的长度小于maxWidth，则直接返回str。
- 不满足第一条时，则为`substring(str, 0, maxWidth)`。
- 如果maxWidth小于0，则抛出IllegalArgumentException。
- 在任何情况下都不会返回长度大于maxWidth的字符串。

代码示例：

```java
StringUtils.truncate(null, 0)       = null
StringUtils.truncate(null, 2)       = null
StringUtils.truncate("", 4)         = ""
StringUtils.truncate("abcdefg", 4)  = "abcd"
StringUtils.truncate("abcdefg", 6)  = "abcdef"
StringUtils.truncate("abcdefg", 7)  = "abcdefg"
StringUtils.truncate("abcdefg", 8)  = "abcdefg"
StringUtils.truncate("abcdefg", -1) = throws an IllegalArgumentException
```

### truncate(String str, int offset, int maxWidth)

截断字符串，具有以下特点：

- 如果str字符串的长度小于maxWidth，则直接返回str。
- 不满足第一条时，则为`substring(str, offset, maxWidth)`。
- 如果maxWidth或者offset小于0，则抛出IllegalArgumentException。
- 在任何情况下都不会返回长度大于maxWidth的字符串。

代码示例：

```java
StringUtils.truncate(null, 0, 0) = null
StringUtils.truncate(null, 2, 4) = null
StringUtils.truncate("", 0, 10) = ""
StringUtils.truncate("", 2, 10) = ""
StringUtils.truncate("abcdefghij", 0, 3) = "abc"
StringUtils.truncate("abcdefghij", 5, 6) = "fghij"
StringUtils.truncate("raspberry peach", 10, 15) = "peach"
StringUtils.truncate("abcdefghijklmno", 0, 10) = "abcdefghij"
StringUtils.truncate("abcdefghijklmno", -1, 10) = throws an IllegalArgumentException
StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, 10) = "abcdefghij"
StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, Integer.MAX_VALUE) = "abcdefghijklmno"
StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE) = "abcdefghijklmno"
StringUtils.truncate("abcdefghijklmno", 1, 10) = "bcdefghijk"
StringUtils.truncate("abcdefghijklmno", 2, 10) = "cdefghijkl"
StringUtils.truncate("abcdefghijklmno", 3, 10) = "defghijklm"
StringUtils.truncate("abcdefghijklmno", 4, 10) = "efghijklmn"
StringUtils.truncate("abcdefghijklmno", 5, 10) = "fghijklmno"
StringUtils.truncate("abcdefghijklmno", 5, 5) = "fghij"
StringUtils.truncate("abcdefghijklmno", 5, 3) = "fgh"
StringUtils.truncate("abcdefghijklmno", 10, 3) = "klm"
StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE) = "klmno"
StringUtils.truncate("abcdefghijklmno", 13, 1) = "n"
StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE) = "no"
StringUtils.truncate("abcdefghijklmno", 14, 1) = "o"
StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE) = "o"
StringUtils.truncate("abcdefghijklmno", 15, 1) = ""
StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE) = ""
StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE) = ""
StringUtils.truncate("abcdefghij", 3, -1) = throws an IllegalArgumentException
StringUtils.truncate("abcdefghij", -2, 4) = throws an IllegalArgumentException
```

## 比较字符串

### equals(CharSequence cs1, CharSequence cs2)

判断两字符串相等，代码示例：

```java
StringUtils.equals(null, null)   = true
StringUtils.equals(null, "abc")  = false
StringUtils.equals("abc", null)  = false
StringUtils.equals("abc", "abc") = true
StringUtils.equals("abc", "ABC") = false
```

### equalsIgnoreCase(CharSequence str1, CharSequence str2)

判断两字符串相等，忽略大小写，代码示例：

```java
StringUtils.equalsIgnoreCase(null, null)   = true
StringUtils.equalsIgnoreCase(null, "abc")  = false
StringUtils.equalsIgnoreCase("abc", null)  = false
StringUtils.equalsIgnoreCase("abc", "abc") = true
StringUtils.equalsIgnoreCase("abc", "ABC") = true
```

### equalsAny(CharSequence string, CharSequence... searchStrings)

比较一个字符串是否与其后的某个字符串相等，代码示例：

```java
StringUtils.equalsAny(null, (CharSequence[]) null) = false
StringUtils.equalsAny(null, null, null)    = true
StringUtils.equalsAny(null, "abc", "def")  = false
StringUtils.equalsAny("abc", null, "def")  = false
StringUtils.equalsAny("abc", "abc", "def") = true
StringUtils.equalsAny("abc", "ABC", "DEF") = false
```

### equalsAnyIgnoreCase(CharSequence string, CharSequence...searchStrings)

比较一个字符串是否与其后的某个字符串相等，忽略大小写，代码示例：

```java
StringUtils.equalsAnyIgnoreCase(null, (CharSequence[]) null) = false
StringUtils.equalsAnyIgnoreCase(null, null, null)    = true
StringUtils.equalsAnyIgnoreCase(null, "abc", "def")  = false
StringUtils.equalsAnyIgnoreCase("abc", null, "def")  = false
StringUtils.equalsAnyIgnoreCase("abc", "abc", "def") = true
StringUtils.equalsAnyIgnoreCase("abc", "ABC", "DEF") = true
```

### compare(String str1, String str2)

比较两字符串的大小，代码示例：

```java
StringUtils.compare(null, null)   = 0
StringUtils.compare(null , "a")   < 0
StringUtils.compare("a", null)    > 0
StringUtils.compare("abc", "abc") = 0
StringUtils.compare("a", "b")     < 0
StringUtils.compare("b", "a")     > 0
StringUtils.compare("a", "B")     > 0
StringUtils.compare("ab", "abc")  < 0
```

### compareIgnoreCase(String str1, String str2)

比较两字符串的大小，忽略大小写，代码示例：

```java
StringUtils.compareIgnoreCase(null, null)   = 0
StringUtils.compareIgnoreCase(null , "a")   < 0
StringUtils.compareIgnoreCase("a", null)    > 0
StringUtils.compareIgnoreCase("abc", "abc") = 0
StringUtils.compareIgnoreCase("abc", "ABC") = 0
StringUtils.compareIgnoreCase("a", "b")     < 0
StringUtils.compareIgnoreCase("b", "a")     > 0
StringUtils.compareIgnoreCase("a", "B")     < 0
StringUtils.compareIgnoreCase("A", "b")     < 0
StringUtils.compareIgnoreCase("ab", "ABC")  < 0
```

## 查找元素

### indexOf(CharSequence seq, int searchChar)

查找某个字符在字符串中第一次出现时的索引位置，代码示例：

```java
StringUtils.indexOf("aabaabaa", 'a') = 0
StringUtils.indexOf("aabaabaa", 'b') = 2
```

### indexOf(CharSequence seq, CharSequence searchSeq)

同`indexOf(CharSequence seq, int searchChar)`相似。

```java
StringUtils.indexOf("aabaabaa", "c")  = -1
StringUtils.indexOf("aabaabaa", "a")  = 0
StringUtils.indexOf("aabaabaa", "b")  = 2
StringUtils.indexOf("aabaabaa", "ab") = 1
StringUtils.indexOf("aabaabaa", "")   = 0
```

### indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos)

同`indexOf(CharSequence seq, int searchChar)`相似。

```java
StringUtils.indexOf("aabaabaa", "a", 0)  = 0
StringUtils.indexOf("aabaabaa", "b", 0)  = 2
StringUtils.indexOf("aabaabaa", "ab", 0) = 1
StringUtils.indexOf("aabaabaa", "b", 3)  = 5
StringUtils.indexOf("aabaabaa", "b", 9)  = -1
StringUtils.indexOf("aabaabaa", "b", -1) = 2
StringUtils.indexOf("aabaabaa", "", 2)   = 2
StringUtils.indexOf("abc", "", 9)        = 3
```

### indexOfIgnoreCase(CharSequence str, CharSequence searchStr)

同`indexOf(CharSequence seq, int searchChar)`相似,忽略大小写。

### lastIndexOf(CharSequence seq, int searchChar)

同`indexOf(CharSequence seq, int searchChar)`相似，从后面开始查找。

### contains(CharSequence seq, CharSequence searchSeq)

判断某字符串是否包含某子字符串。

```java
StringUtils.contains("", "")      = true
StringUtils.contains("abc", "")   = true
StringUtils.contains("abc", "a")  = true
StringUtils.contains("abc", "z")  = false
```

### containsIgnoreCase(CharSequence str, CharSequence searchStr)

同`contains(CharSequence seq, CharSequence searchSeq)`相似，忽略大小写。

### containsWhitespace(final CharSequence seq)

是`contains(CharSequence seq, CharSequence searchSeq)`的特殊情形，判断是否包含空白字符。

### containsAny(CharSequence cs, CharSequence... searchCharSequences)

判断某字符串是否包含其后的任意一个字符串。

```java
StringUtils.containsAny("abcd", "ab", null) = true
StringUtils.containsAny("abcd", "ab", "cd") = true
StringUtils.containsAny("abc", "d", "abc")  = true
```

### containsNone(CharSequence cs, String invalidChars)

判断某字符串是否不含其后字符串的任意一个字符。

```java
StringUtils.containsNone("ab", "")      = true
StringUtils.containsNone("abab", "xyz") = true
StringUtils.containsNone("ab1", "xyz")  = true
StringUtils.containsNone("abz", "xyz")  = false
```
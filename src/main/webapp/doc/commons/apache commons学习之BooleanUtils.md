# apache commons学习之BooleanUtils

标签（空格分隔）： commons Java

---

> 码农不识Apache，码尽一生也枉然。

### toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull)

如果bool的值为null，则转成指定的默认布尔值，否则即是bool。

```java
BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false) = true
BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true) = false
BooleanUtils.toBooleanDefaultIfNull(null, true)          = true
```

### int toInteger(boolean bool)

将boolean值转换成int类型的数据。

```java
BooleanUtils.toInteger(true)  = 1
BooleanUtils.toInteger(false) = 0
```

### toBooleanObject(String str)

将String型的字符串转换成Boolean值。

```java
BooleanUtils.toBooleanObject(null)    = null
BooleanUtils.toBooleanObject("true")  = Boolean.TRUE
BooleanUtils.toBooleanObject("T")     = Boolean.TRUE // i.e. T[RUE]
BooleanUtils.toBooleanObject("false") = Boolean.FALSE
BooleanUtils.toBooleanObject("f")     = Boolean.FALSE // i.e. f[alse]
BooleanUtils.toBooleanObject("No")    = Boolean.FALSE
BooleanUtils.toBooleanObject("n")     = Boolean.FALSE // i.e. n[o]
BooleanUtils.toBooleanObject("on")    = Boolean.TRUE
BooleanUtils.toBooleanObject("ON")    = Boolean.TRUE
BooleanUtils.toBooleanObject("off")   = Boolean.FALSE
BooleanUtils.toBooleanObject("oFf")   = Boolean.FALSE
BooleanUtils.toBooleanObject("yes")   = Boolean.TRUE
BooleanUtils.toBooleanObject("Y")     = Boolean.TRUE // i.e. Y[ES]
BooleanUtils.toBooleanObject("blue")  = null
BooleanUtils.toBooleanObject("true ") = null // trailing space (too long)
BooleanUtils.toBooleanObject("ono")   = null // does not match on or no
```

### toStringTrueFalse(Boolean bool)

将Boolean值转换成"真假"字符串。

```java
BooleanUtils.toStringTrueFalse(Boolean.TRUE)  = "true"
BooleanUtils.toStringTrueFalse(Boolean.FALSE) = "false"
BooleanUtils.toStringTrueFalse(null)          = null
```

### toStringOnOff(final Boolean bool)

将Boolean值转换成"开关"字符串。

```java
BooleanUtils.toStringOnOff(Boolean.TRUE)  = "on"
BooleanUtils.toStringOnOff(Boolean.FALSE) = "off"
BooleanUtils.toStringOnOff(null)          = null
```

### toStringYesNo(final Boolean bool)

将Boolean值转换成"是否"字符串。

```java
BooleanUtils.toStringYesNo(Boolean.TRUE)  = "yes"
BooleanUtils.toStringYesNo(Boolean.FALSE) = "no"
BooleanUtils.toStringYesNo(null)          = null
```

### toString(Boolean bool, String trueString, String falseString, String nullString)

将Boolean值转换成自定义的“真假空”字符串。

```java
BooleanUtils.toString(Boolean.TRUE, "true", "false", null)   = "true"
BooleanUtils.toString(Boolean.FALSE, "true", "false", null)  = "false"
BooleanUtils.toString(null, "true", "false", null)           = null
```

### and(boolean... array)

多个布尔值的“与”运算。

```java
BooleanUtils.and(true, true)         = true
BooleanUtils.and(false, false)       = false
BooleanUtils.and(true, false)        = false
BooleanUtils.and(true, true, false)  = false
BooleanUtils.and(true, true, true)   = true
```

### or(boolean... array)

多个布尔值的“或”运算。

```java
BooleanUtils.or(true, true)          = true
BooleanUtils.or(false, false)        = false
BooleanUtils.or(true, false)         = true
BooleanUtils.or(true, true, false)   = true
BooleanUtils.or(true, true, true)    = true
BooleanUtils.or(false, false, false) = false
```
# HashMap 的实现原理

## HashMap 概述

HashMap 是基于哈希表 Map 接口的非同步实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。此类不保证映射的顺序，特别是它不保证该顺序恒久不变。

此实现假定哈希函数将元素适当地分布在各桶之间，可为基本操作（get 和 put）提供稳定的性能。迭代 collection 视图所需的时间与 HashMap 实例的“容量”（桶的数量）及其大小（键-值映射关系数）成比例。所以，如果迭代性能很重要，则不要将初始容量设置得太高或将加载因子设置得太低。

需要注意的是：Hashmap 不是同步的，如果多个线程同时访问一个 HashMap，而其中至少一个线程从结构上（指添加或者删除一个或多个映射关系的任何操作）修改了，则必须保持外部同步，以防止对映射进行意外的非同步访问。

## HashMap 的数据结构

在 Java 编程语言中，最基本的结构就是两种，一个是数组，另外一个是指针（引用），HashMap 就是通过这两个数据结构进行实现。**HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体**。

![HashMap数据结构](http://static.blinkfox.com/hashmap1.jpg)

从上图中可以看出，HashMap 底层就是一个数组结构，数组中的每一项又是一个链表。当新建一个 HashMap 的时候，就会初始化一个数组。

我们通过 JDK 中的 HashMap 源码进行一些学习，首先看一下构造函数：

```java
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

    // Find a power of 2 >= initialCapacity
    int capacity = 1;
    while (capacity < initialCapacity)
        capacity <<= 1;

    this.loadFactor = loadFactor;
    threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
    table = new Entry[capacity];
    useAltHashing = sun.misc.VM.isBooted() && (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
    init();
}
```

我们着重看一下第`18`行代码`table = new Entry[capacity];`。这不就是 Java 中数组的创建方式吗？也就是说在构造函数中，其创建了一个`Entry`的数组，其大小为`capacity`（目前我们还不需要太了解该变量含义），那么`Entry`又是什么结构呢？看一下源码：

```java
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    final int hash;
    ...
}
```

我们目前还是只着重核心的部分，`Entry`是一个`static class`，其中包含了`key`和`value`，也就是键值对，另外还包含了一个`next`的`Entry`指针。我们可以总结出：`Entry`就是数组中的元素，每个`Entry`其实就是一个`key-value` 对，它持有一个指向下一个元素的引用，这就构成了链表。
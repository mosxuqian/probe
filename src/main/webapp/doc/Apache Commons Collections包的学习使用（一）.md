# Apache Commons Collections包的学习使用（一）

## Map的介绍和使用示例

标签： Java Apache

### 一、Commons Collections包和简介

#### 1. 背景介绍

[Apache Commons][1]是Apache软件基金会的项目，曾经隶属于`Jakarta`项目。`Commons`的目的是提供可重用的、解决各种实际的通用问题且开源的Java代码。Commons由三部分组成：`Proper`（是一些已发布的项目）、`Sandbox`（是一些正在开发的项目）和`Dormant`（是一些刚启动或者已经停止维护的项目）。

[Commons Collections][2]包为Java标准的`Collections API`提供了相当好的补充。在此基础上对其常用的数据结构操作进行了很好的封装、抽象和补充。让我们在开发应用程序的过程中，既保证了性能，同时也能大大简化代码。

#### 2.包结构介绍

> 注意：Commons Collections的最新版是4.1，但由于工作中大多还是3.x的版本，这里就以3.x中的最后一个版本3.2.2作使用介绍。

以下是Collections的包结构和简单介绍，如果你想了解更多的各个包下的接口和实现，请参考[Apache Commons Collections 3.2.2 API文档][3]。

- org.apache.commons.collections – CommonsCollections自定义的一组公用的接口和工具类
- org.apache.commons.collections.bag – 实现Bag接口的一组类
- org.apache.commons.collections.bidimap – 实现BidiMap系列接口的一组类
- org.apache.commons.collections.buffer – 实现Buffer接口的一组类
- org.apache.commons.collections.collection –实现java.util.Collection接口的一组类
- org.apache.commons.collections.comparators– 实现java.util.Comparator接口的一组类
- org.apache.commons.collections.functors –Commons Collections自定义的一组功能类
- org.apache.commons.collections.iterators – 实现java.util.Iterator接口的一组类
- org.apache.commons.collections.keyvalue – 实现集合和键/值映射相关的一组类
- org.apache.commons.collections.list – 实现java.util.List接口的一组类
- org.apache.commons.collections.map – 实现Map系列接口的一组类
- org.apache.commons.collections.set – 实现Set系列接口的一组类

### 二、Map的一些介绍

`Collections`包中的“Map”是在`java.util.Map`的基础上扩展的接口和类。有如下常用的Map:

- **LinkedMap**，可以维护条目顺序的map；

- **BidiMap**，即双向Map，可以通过key找到value，也可以通过value找到key。需要注意的是BidiMap中key和value都不可以重复；
- **MultiMap**，一个key指向的是一组对象，add()和remove()的时候跟普通的Map无异，只是在get()时返回一个Collection，实现了一对多；
- **LazyMap**，即Map中的键/值对一开始并不存在，当被调用到时才创建。

### 三、Collections中Map的使用示例

#### 1.Map迭代器之mapIterator

`jdk`中的`Map`接口很难进行迭代。`api`用户总是需要通过`entryset`或者`keyset`进行迭代。`commons-collectons`现在提供了一个新的接口 - `mapIterator`来允许对`maps`进行简单的迭代。示例如下：

(1)、构造Map初始数据的方法：

```java
/**
 * 构建map初始数据
 * @param map
 */
private static Map buildMap(Map map) {
    map.put("one", "1");
    map.put("two", "2");
    map.put("three", "3");
    map.put("four", "4");
    return map;
}
```

(2)、使用MapIterator迭代数据的方法：

```java
/**
 * map迭代器
 * 遍历打印map,使用map.mapIterator()
 * @param map
 * @param mapName
 */
private static void iteratorMap() {
    Map map = this.buildMap(new HashMap());
    // 遍历map,使用MapIterator
    MapIterator it = map.mapIterator();
    while (it.hasNext()) {
        Object key = it.next();
        Object value = it.getValue();
        System.out.println("iterator map key:" + key + ", value: " + value);
    }
}
```

#### 2.有序map之LinkedMap

`LinkedMap`是一个可以维护Map中条目顺序的Map实现，条目顺序由最初的数据插入时来决定。同时也增加上面所说的`MapIterator`功能和一些便利的方法，并允许进行双向迭代。相较于`JDK1.4`中的`LinkedHashMap`效率有所提高，它还实现了`OrderedMap`接口。此外，还提供了非接口方法通过索引来访问Map中的数据。**需要注意的是LinkedMap不是同步的，不是线程安全的**。如果你想使用`LinkedMap`的同时使用多个线程，您必须使用适当的同步操作。最简单的方法是使用`Collections.synchronizedMap(Map)`来包装`LinkedMap`。如果不使用同步操作，当并发线程访问这个类时可能会抛出异常。

LinkedMap的一些代码示例代码如下：

```java
/**
 * 有序map之LinkedMap
 */
private static void linkedMapTest() {
    OrderedMap orderMap = this.buildMap(new LinkedMap());

    // 获取map中相应的值
    System.out.println("LinkedMap firstKey:" + orderMap.firstKey());
    System.out.println("LinkedMap previous key:" + orderMap.previousKey("four"));
    System.out.println("LinkedMap next key:" + orderMap.nextKey("two"));
    System.out.println("LinkedMap last key:" + orderMap.lastKey());
    System.out.println("LinkedMap map Size:" + orderMap.size());
}
```

  [1]: http://commons.apache.org/
  [2]: http://commons.apache.org/proper/commons-collections/
  [3]: http://commons.apache.org/proper/commons-collections/javadocs/api-3.2.2/index.html
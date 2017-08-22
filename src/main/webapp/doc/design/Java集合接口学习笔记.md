# Java集合接口学习笔记

- `Collection`接口继承了`Iterable`接口，依赖了`Predicate`、`Spliterator`、`Stream`接口（这些均为`Java8`新增），`Iterable`接口依赖了`Iterator`接口。
- List接口继承自`Collection`接口,依赖了`UnaryOperator`接口（`Java8`新增）、`ListIterator`、`Comparator`接口
- Set接口继承自`Collection`接口
- Map接口依赖了`Set`、`Collection`、`BiConsumer`、`Function`、`BiFunction`接口，`Map.Entry`是Map中的内部接口
- SortedSet接口继承自`Set`接口，依赖了`Comparator`接口
- SortedMap接口继承自`Map`接口，依赖了`Set`、`Collection`、`Comparator`接口

## Collection接口中的抽象方法

- `int size()`，返回集合的大小
- `boolean isEmpty()`，返回集合是否为空的布尔值
- `boolean contains(Object o)`，返回集合是否包含元素`o`的布尔值
- `Iterator<E> iterator()`，返回该集合中元素的迭代器，继承自`Iterable`接口
- `Object[] toArray()`，返回一个包含此集合中所有元素的数组
- `<T> T[] toArray(T[] a)`，`toArray()`方法的泛型版本，返回一个包含此集合中所有元素的数组，返回类型由传入数组参数的类型决定
- `boolean add(E e)`，返回向集合中插入元素`e`是否成功的布尔值
- `boolean remove(Object o)`，返回从集合中删除元素`o`是否成功的布尔值
- `boolean containsAll(Collection<?> c)`，返回本集合中是否完全包含集合`c`的布尔值，即判断集合`c`是否是本集合子集
- `boolean addAll(Collection<? extends E> c)`，将集合`c`中的所有元素添加到本集合中并返回
- `boolean removeAll(Collection<?> c)`，移除本集合中所有包含集合`c`的所有元素
- `default boolean removeIf(Predicate<? super E> filter)`，Java8新增的接口默认方法。将会批量删除符合filter条件的所有元素，该方法需要一个Predicate对象作为作为参数，Predicate也是函数式接口，因此可使用Lambda表达式作为参数。
- `boolean retainAll(Collection<?> c)`，返回本集合和集合`c`中相同的元素并存到本集合中,集合`c`保持不变，返回值表示的是本集合是否发生过改变。即该方法是用来求两个集合的交集，交集的结果存到本集合中，如果本集合没发生变化则返回`true`
- `void clear()`，清空本集合中的所有元素
- `boolean equals(Object o)`，返回本集合是否和对象`o`相等的布尔值
- `int hashCode()`，返回此集合的`Hash`码值
- `default Spliterator<E> spliterator()`，在集合中创建`Spliterator`对象
  - `Spliterator`是Java 8引入的新接口，顾名思义，`Spliterator`可以理解`Iterator`的`Split`版本（但用途要丰富很多）。使用`Iterator`的时候，我们可以顺序地遍历容器中的元素，使用`Spliterator`的时候，我们可以将元素分割成多份，分别交于不于的线程去遍历，以提高效率。使用`Spliterator`每次可以处理某个元素集合中的一个元素 — 不是从`Spliterator`中获取元素，而是使用`tryAdvance()`或`forEachRemaining()`方法对元素应用操作。但`Spliterator`还可以用于估计其中保存的元素数量，而且还可以像细胞分裂一样变为一分为二。这些新增加的能力让流并行处理代码可以很方便地将工作分布到多个可用线程上完成。
- `default Stream<E> stream()`，返回一个顺序的`Stream`对象。Java8引入了Stream以实现对集合更方便地进行函数式编程。
- `default Stream<E> parallelStream()`，返回一个可能并行的`Stream`对象。Java8新增的方法。流可以是顺序的也可以是并行的。顺序流的操作是在单线程上执行的，而并行流的操作是在多线程上并发执行的。

## List接口中的额外抽象方法

- `boolean addAll(int index, Collection<? extends E> c)`，将指定集合`c`中的所有元素插入到指定索引位置处
- `default void replaceAll(UnaryOperator<E> operator)`，Java8新增的使用`Lambda`的方式，通过应用`UnaryOperator`获得的结果来替换列表中的每个元素
- `default void sort(Comparator<? super E> c)`，在比较器的基础上将本列表排序
- `E get(int index)`，获取本集合中指定索引位置处的元素
- `E set(int index, E element)`，设置或替换本集合中指定索引位置处的元素
- `void add(int index, E element)`，在本集合中的指定索引位置处插入指定的元素
- `E remove(int index)`，移除本集合中指定索引位置处的元素
- `int indexOf(Object o)`，返回指定元素第一次出现的索引位置
- `int lastIndexOf(Object o)`，返回指定元素最后出现的索引位置
- `ListIterator<E> listIterator()`，返回本集合中的`ListIterator`迭代器
- `ListIterator<E> listIterator(int index)`，返回本集合中从指定索引位置开始的`ListIterator`迭代器
- `List<E> subList(int fromIndex, int toIndex)`，返回指定开始和结束索引位置的子集合

## Set接口中的额外抽象方法

无

## Map接口中的抽象方法

- `boolean containsKey`，判断本Map集合中是否包含指定的key键
- `boolean containsValue`，判断本Map集合中是否包含指定的value值
- `V get(Object key)`，根据key获取本Map集合中的value值
- `V get(Object key)`，向本Map集合中存放key键和value值,返回value值
- `V remove(Object key)`，根据key删除本Map集合中的key和value值，并返回删除的value值
- `void putAll(Map<? extends K, ? extends V> m)`，将指定的Map集合添加到本的Map集合当中
- `Set<K> keySet()`，获取本Map集合中的所有key值，并以Set接口的结果作为返回
- `Collection<V> values()`，获取本Map集合中的所有value值，并以Collection接口的结果作为返回
- `Set<Map.Entry<K, V>> entrySet()`，获取本Map集合中的所有key和value值，并以`Set<Map.Entry<K, V>>`的结果作为返回
- `default V getOrDefault(Object key, V defaultValue)`，根据key获取本Map集合中的value值，如果没找到对应的值或者value值是null,则返回`defaultValue`的值
- `default void forEach(BiConsumer<? super K, ? super V> action)`，Java8新增的使用`Lambda`的方式遍历操作Map中的元素的默认接口方法
- `default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)`，Java8新增的使用`Lambda`的方式遍历替换Map中的元素的默认接口方法
- `default V putIfAbsent(K key, V value)`，Java8新增的不用写是否为null值的检测语句向Map中保存key和value的元素的默认接口方法，即如果通过key获取到的value是空的，则在调用`put(key, value)`方法并返回value值
- `default boolean remove(Object key, Object value)`，Java8新增的默认接口方法，删除给定key所对应的元素，如果value不存在、为null或者与参数中的value不等，则不能删除。即删除操作需要满足给定的值需要和map中的值相等的条件
- `default boolean replace(K key, V oldValue, V newValue)`，Java8新增的默认接口方法，替换给定key所对应的元素，如果value不存在、为null或者与参数中的oldValue不等，则不能替换。即替换操作需要满足给定的值需要和map中的值相等的条件
- `default V replace(K key, V value)`，Java8新增的默认接口方法，替换给定key所对应的元素，如果value不为null，则value值与参数中的value值做替换。
- `default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)`，Java8新增的默认接口方法，根据key获取到的value如果不为null，则直接返回value值，否则将`Lambda`表达式中的结果值存放到Map中
- `default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)`，Java8新增的默认接口方法，根据key获取到的value和新计算的值如果不为null，则直接新计算的值，否则移除该key，且返回null
- `default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)`，Java8新增的默认接口方法，将`Lambda`表达式中的结果值存放到Map中，如果计算的新值为null则返回null，且移除以前有的key和value值
- `default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)`，Java8新增的默认接口方法，将新计算的值覆盖Map中原key对应的value值

## SortedSet接口中的额外抽象方法

- `Comparator<? super E> comparator()`，返回本SortedSet集合中的`Comparator`比较器
- `SortedSet<E> subSet(E fromElement, E toElement)`，获取开始元素和结束元素之间的子SortedSet集合
- `SortedSet<E> headSet(E toElement)`，获取开始元素和`toElement`元素之间的子SortedSet集合
- `SortedSet<E> tailSet(E fromElement)`，获取`fromElement`元素和结束元素之间的子SortedSet集合
- `E first()`，获取本SortedSet集合中的第一个元素
- `E last()`，获取本SortedSet集合中的最后一个元素

## SortedMap接口中的额外抽象方法

- `Comparator<? super K> comparator()`，返回本SortedMap集合中的`Comparator`比较器
- `SortedMap<K,V> subMap(K fromKey, K toKey)`，获取开始key和结束key之间的子SortedMap集合
- `SortedMap<K,V> headMap(K toKey)`，获取开始key和`toKey`元素之间的子SortedMap集合
- `SortedMap<K,V> tailMap(K fromKey)`，获取`fromKey`元素和结束key之间的子SortedMap集合
- `K firstKey()`，获取本SortedMap集合中的第一个key
- `K lastKey()`，获取本SortedMap集合中的最后一个key
- `Set<K> keySet()`，获取本SortedMap集合中所有key的Set集合
- `Collection<V> values()`，获取本SortedMap集合中所有value的Collection集合
- `Set<Map.Entry<K, V>> entrySet()`，获取本SortedMap集合中所有key和value的Map集合
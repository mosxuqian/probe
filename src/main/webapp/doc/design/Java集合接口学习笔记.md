# Java集合接口学习笔记

- `Collection`接口继承了`Iterable`接口，依赖了`Spliterator`接口（Java8新增），`Iterable`接口依赖了`Iterator`接口。

## Collection中的抽象方法

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
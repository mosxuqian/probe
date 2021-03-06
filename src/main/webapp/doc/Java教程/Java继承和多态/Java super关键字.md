# Java super关键字

标签： Java基础教程

---

super关键字与`this`类似，this用来表示当前类的实例，super用来表示父类。

super可以用在子类中，通过点号(.)来获取父类的成员变量和方法。super也可以用在子类的子类中，Java能自动向上层类追溯。

父类行为被调用，就好象该行为是本类的行为一样，而且调用行为不必发生在父类中，它能自动向上层类追溯。

super关键字的功能：

- 调用父类中声明为 private 的变量。
- 点取已经覆盖了的方法。
- 作为方法名表示父类构造方法。

## 调用隐藏变量和被覆盖的方法

```java
public class Demo{
    public static void main(String[] args) {
        Dog obj = new Dog();
        obj.move();
    }
}
class Animal{
    private String desc = "Animals are human's good friends";
    // 必须要声明一个 getter 方法
    public String getDesc() { return desc; }

    public void move(){
        System.out.println("Animals can move");
    }
}
class Dog extends Animal{
    public void move(){
        super.move();  // 调用父类的方法
        System.out.println("Dogs can walk and run");
        // 通过 getter 方法调用父类隐藏变量
        System.out.println("Please remember: " + super.getDesc());
    }
}
```

运行结果：

> Animals can move
Dogs can walk and run
Please remember: Animals are human's good friends

`move()`方法也可以定义在某些祖先类中，比如父类的父类，Java具有追溯性，会一直向上找，直到找到该方法为止。

通过super调用父类的隐藏变量，必须要在父类中声明`getter`方法，因为声明为private的数据成员对子类是不可见的。

## 调用父类的构造方法

在许多情况下，使用默认构造方法来对父类对象进行初始化。当然也可以使用super来显示调用父类的构造方法。

```java
public class Demo{
    public static void main(String[] args) {
        Dog obj = new Dog("花花", 3);
        obj.say();
    }
}
class Animal{
    String name;
    public Animal(String name){
        this.name = name;
    }
}
class Dog extends Animal{
    int age;
    public Dog(String name, int age){
        super(name);
        this.age = age;
    }

    public void say(){
        System.out.println("我是一只可爱的小狗，我的名字叫" + name + "，我" + age + "岁了");
    }
}
```

运行结果：

> 我是一只可爱的小狗，我的名字叫花花，我3岁了

**注意：**无论是`super()`还是`this()`，都必须放在构造方法的第一行。

值得注意的是：

- 在构造方法中调用另一个构造方法，调用动作必须置于最起始的位置。
- 不能在构造方法以外的任何方法内调用构造方法。
- 在一个构造方法内只能调用一个构造方法。

如果编写一个构造方法，既没有调用`super()`也没有调用`this()`，编译器会自动插入一个调用到父类构造方法中，而且不带参数。 

**最后注意super与this的区别：super不是一个对象的引用，不能将super赋值给另一个对象变量，它只是一个指示编译器调用父类方法的特殊关键字。**
# Java继承的概念与实现

标签： Java基础教程

---

继承是类与类之间的关系，是一个很简单很直观的概念，与现实世界中的继承（例如儿子继承父亲财产）类似。

继承可以理解为一个类从另一个类获取方法和属性的过程。如果类B继承于类A，那么B就拥有A的方法和属性。

继承使用`extends`关键字。

例如我们已经定义了一个类People：

```java
class People{
    String name;
    int age;
    int height;
   
    void say(){
        System.out.println("我的名字是 " + name + "，年龄是 " + age + "，身高是 " + height);
    }
}
```

如果现在需要定义一个类Teacher，它也有name、age、height属性和`say()`方法，另外还需要增加school、seniority、subject属性和`lecturing()`方法，怎么办呢？我们要重新定义一个类吗？

完全没必要，可以先继承People类的成员，再增加自己的成员即可，例如：

```java
class Teacher extends People{
    String school;  // 所在学校
    String subject;  // 学科
    int seniority;  // 教龄
   
    // 覆盖 People 类中的 say() 方法
    void say(){
        System.out.println("我叫" + name + "，在" + school + "教" + subject + "，有" + seniority + "年教龄");
    }
   
    void lecturing(){
        System.out.println("我已经" + age + "岁了，依然站在讲台上讲课");
    }
}
```

**对程序的说明**：

- name 和 age 变量虽然没有在 Teacher 中定义，但是已在 People 中定义，可以直接拿来用。
- Teacher 是 People 的子类，People 是Teacher 类的父类。
- 子类可以覆盖父类的方法。
- 子类可以继承父类除private以为的所有的成员。
- 构造方法不能被继承。

继承是在维护和可靠性方面的一个伟大进步。如果在People类中进行修改，那么 Teacher 类就会自动修改，而不需要程序员做任何工作，除了对它进行编译。

**单继承性**：Java允许一个类仅能继承一个其它类，即一个类只能有一个父类，这个限制被称做单继承性。后面将会学到接口(interface)的概念，接口允许多继承。

最后对上面的代码进行整理：

```java
public class Demo {
    public static void main(String[] args) {
        Teacher t = new Teacher();
        t.name = "小布";
        t.age = 70;
        t.school = "清华大学";
        t.subject = "Java";
        t.seniority = 12;
        t.say();
        t.lecturing();
    }
}

class People{
    String name;
    int age;
    int height;
   
    void say(){
        System.out.println("我的名字是 " + name + "，年龄是 " + age + "，身高是 " + height);
    }
}

class Teacher extends People{
    String school;  // 所在学校
    String subject;  // 学科
    int seniority;  // 教龄
   
    // 覆盖 People 类中的 say() 方法
    void say(){
        System.out.println("我叫" + name + "，在" + school + "教" + subject + "，有" + seniority + "年教龄");
    }
   
    void lecturing(){
        System.out.println("我已经" + age + "岁了，依然站在讲台上讲课");
    }
}
```

运行结果：

> 我叫小布，在清华大学教Java，有12年教龄
我已经70岁了，依然站在讲台上讲课

**注意：构造方法不能被继承。一个类能得到构造方法，只有两个办法：编写构造方法，或者根本没有构造方法，类有一个默认的构造方法。**
# Java instanceof 运算符

标签： Java基础教程

---

多态性带来了一个问题，就是如何判断一个变量所实际引用的对象的类型 。 C++使用`runtime-type information(RTTI)`，Java使用`instanceof`操作符。

`instanceof`运算符用来判断一个变量所引用的对象的实际类型，注意是它引用的对象的类型，不是变量的类型。请看下面的代码：

```java
public final class Demo{
    public static void main(String[] args) {
        // 引用 People 类的实例
        People obj = new People();
        if(obj instanceof Object){
            System.out.println("我是一个对象");
        }
        if(obj instanceof People){
            System.out.println("我是人类");
        }
        if(obj instanceof Teacher){
            System.out.println("我是一名教师");
        }
        if(obj instanceof President){
            System.out.println("我是校长");
        }

        System.out.println("-----------");  // 分界线
       
        // 引用 Teacher 类的实例
        obj = new Teacher();
        if(obj instanceof Object){
            System.out.println("我是一个对象");
        }
        if(obj instanceof People){
            System.out.println("我是人类");
        }
        if(obj instanceof Teacher){
            System.out.println("我是一名教师");
        }
        if(obj instanceof President){
            System.out.println("我是校长");
        }
    }
}

class People{ }
class Teacher extends People{ }
class President extends Teacher{ }
```

运行结果：

```
我是一个对象
我是人类
-----------
我是一个对象
我是人类
我是一名教师
```

可以看出，如果变量引用的是当前类或它的子类的实例，instanceof 返回true，否则返回false。
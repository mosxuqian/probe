# Java源文件的声明规则

标签： Java基础教程

---

当在一个源文件中定义多个类，并且还有import语句和package语句时，要特别注意这些规则：

- 一个源文件中只能有一个public类。
- 一个源文件可以有多个非public类。
- 源文件的名称应该和public类的类名保持一致。例如：源文件中public类的类名是Employee，那么源文件应该命名为Employee.java。
- 如果一个类定义在某个包中，那么package语句应该在源文件的首行。
- 如果源文件包含import语句，那么应该放在package语句和类定义之间。如果没有package语句，那么import语句应该在源文件中最前面。
- import语句和package语句对源文件中定义的所有类都有效。在同一源文件中，不能给不同的类不同的包声明。
- 类有若干种访问级别，并且类也分不同的类型：抽象类和final类等。这些将在后续章节介绍。
- 除了上面提到的几种类型，Java还有一些特殊的类，如内部类、匿名类。 

## 一个简单的例子

在该例子中，我们创建两个类Employee和EmployeeTest，分别放在包p1和p2中。

Employee类有四个成员变量，分别是name、age、designation和salary。该类显式声明了一个构造方法，该方法只有一个参数。

在Eclipse中，创建一个包，命名为p1，在该包中创建一个类，命名为Employee，将下面的代码复制到源文件中：

```java
package p1;

public class Employee{
    String name;
    int age;
    String designation;
    double salary;
    // Employee 类的构造方法
    public Employee(String name){
        this.name = name;
    }
    // 设置age的值
    public void empAge(int empAge){
        age =  empAge;
    }
    // 设置designation的值
    public void empDesignation(String empDesig){
        designation = empDesig;
    }
    // 设置salary的值
    public void empSalary(double empSalary){
        salary = empSalary;
    }
    // 输出信息
    public void printEmployee(){
        System.out.println("Name:"+ name );
        System.out.println("Age:" + age );
        System.out.println("Designation:" + designation );
        System.out.println("Salary:" + salary);
    }
}
```

程序都是从main方法开始执行。为了能运行这个程序，必须包含main方法并且创建一个对象。

下面给出EmployeeTest类，该类创建两个Employee对象，并调用方法设置变量的值。

在Eclipse中再创建一个包，命名为p2，在该包中创建一个类，命名为EmployeeTest，将下面的代码复制到源文件中：

```java
package p2;
import p1.*;

public class EmployeeTest{
    public static void main(String args[]){
        // 创建两个对象
        Employee empOne = new Employee("James Smith");
        Employee empTwo = new Employee("Mary Anne");

        // 调用这两个对象的成员方法
        empOne.empAge(26);
        empOne.empDesignation("Senior Software Engineer");
        empOne.empSalary(1000);
        empOne.printEmployee();

        empTwo.empAge(21);
        empTwo.empDesignation("Software Engineer");
        empTwo.empSalary(500);
        empTwo.printEmployee();
    }
}
```

编译并运行 EmployeeTest 类，可以看到如下的输出结果：

> Name:James Smith
Age:26
Designation:Senior Software Engineer
Salary:1000.0
Name:Mary Anne
Age:21
Designation:Software Engineer
Salary:500.0
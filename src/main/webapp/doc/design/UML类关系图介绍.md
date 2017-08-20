# UML类关系图介绍

## 继承关系（Generalization）

继承关系用一条带空心箭头的直接表示。如下图所示（A继承自B）：

[A继承自B](http://design-patterns.readthedocs.io/zh_CN/latest/_images/uml_generalization.jpg)

**继承**指的是一个类（称为子类、子接口）继承另外的一个类（称为父类、父接口）的功能，并可以增加它自己的新功能的能力。**继承关系为`is-a`的关系**，是类与类或者接口与接口之间最常见的关系之一，在Java中此类关系通过关键字`extends`来表示。

> **注意**：最终代码中，继承关系表现为**一个类继承另一个类或者一个接口继承另一个接口**。

## 实现关系（Realization）

实现关系是用一条带空心箭头的虚线表示。如下图所示：

[实现抽象类](http://design-patterns.readthedocs.io/zh_CN/latest/_images/uml_realize.jpg)

**实现**指的是一个`class`类实现`interface`接口（可以是多个）的功能。实现是类与接口之间最常见的关系之一，在Java中此类关系通过关键字`implements`来表示。

> **注意**：最终代码中，实现关系表现为**普通类实现某个接口**。

## 关联关系（Association）

**关联**关系体现的是两个类、或者类与接口之间语义级别的一种强依赖的结构关系，是一种长期的静态稳定的关系，通常与运行状态无关，一般由常识等因素决定的。表现在代码层面为**被关联类以类属性的形式出现在关联类中，也可能是关联类引用了一个类型为被关联类的全局变量**。

例如：乘车人和车票之间就是一种关联关系，学生和学校就是一种关联关系。

**关联**关系是用一条直线表示的。关联关系默认不强调方向，表示对象间相互知道；如果特别强调方向，如上图，表示A知道B，但B不知道A；如下图所示：

[A关联B](http://design-patterns.readthedocs.io/zh_CN/latest/_images/uml_association.jpg)
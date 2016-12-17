# Java数组的定义和使用

标签：Java基础教程

---

如果希望保存一组有相同类型的数据，可以使用数组。

### 数组的定义和内存分配

Java 中定义数组的语法有两种：

```java
type arrayName[];
type[] arrayName;
```

type为Java中的任意数据类型，包括基本类型和组合类型，arrayName为数组名，必须是一个合法的标识符，[]指明该变量是一个数组类型变量。例如：

```java
int demoArray[];
int[] demoArray;
```

这两种形式没有区别，使用效果完全一样，读者可根据自己的编程习惯选择。

与C、C++不同，Java在定义数组时并不为数组元素分配内存，因此[]中无需指定数组元素的个数，即数组长度。而且对于如上定义的一个数组是不能访问它的任何元素的，我们必须要为它分配内存空间，这时要用到运算符new，其格式如下：

> arrayName=new type[arraySize];

其中，arraySize为数组的长度，type为数组的类型。如：

```java
demoArray=new int[3];
```

为一个整型数组分配3个int型整数所占据的内存空间。

通常，你可以在定义的同时分配空间，语法为：

> type arrayName[] = new type[arraySize];

例如：

```java
int demoArray[] = new int[3];
```

### 数组的初始化

你可以在声明数组的同时进行初始化（静态初始化），也可以在声明以后进行初始化（动态初始化）。例如：

```java
// 静态初始化
// 静态初始化的同时就为数组元素分配空间并赋值
int intArray[] = {1,2,3,4};
String stringArray[] = {"微学苑", "http://www.weixueyuan.net", "一切编程语言都是纸老虎"};
// 动态初始化
float floatArray[] = new float[3];
floatArray[0] = 1.0f;
floatArray[1] = 132.63f;
floatArray[2] = 100F;
```

### 数组引用

可以通过下标来引用数组：

> arrayName[index];

与C、C++不同，Java对数组元素要进行越界检查以保证安全性。

每个数组都有一个`length`属性来指明它的长度，例如`intArray.length`指明数组`intArray`的长度。

【示例】写一段代码，要求输入任意5个整数，输出它们的和。

```java
import java.util.*;
public class Demo {

    public static void main(String[] args){
        int intArray[] = new int[5];
        long total = 0;
        int len = intArray.length;
       
        // 给数组元素赋值
        System.out.print("请输入" + len + "个整数，以空格为分隔：");
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<len; i++){
            intArray[i] = sc.nextInt();
        }
       
        // 计算数组元素的和
        for(int i=0; i<len; i++){
            total += intArray[i];
        }
       
        System.out.println("所有数组元素的和为：" + total);
    }
    
}
```

运行结果：

> 请输入5个整数，以空格为分隔：10 20 15 25 50
所有数组元素的和为：120

### 数组的遍历

实际开发中，经常需要遍历数组以获取数组中的每一个元素。最容易想到的方法是for循环，例如：

```java
int arrayDemo[] = {1, 2, 4, 7, 9, 192, 100};
for(int i=0,len=arrayDemo.length; i<len; i++){
    System.out.println(arrayDemo[i] + ", ");
} 
```

输出结果：

> 1, 2, 4, 7, 9, 192, 100,

不过，Java提供了”增强版“的for循环，专门用来遍历数组，语法为：

```java
for( arrayType varName: arrayName ){
    // Some Code
}
```

arrayType为数组类型（也是数组元素的类型）；varName是用来保存当前元素的变量，每次循环它的值都会改变；arrayName为数组名称。

每循环一次，就会获取数组中下一个元素的值，保存到varName变量，直到数组结束。即第一次循环varName的值为第0个元素，第二次循环为第1个元素......例如：

```java
int arrayDemo[] = {1, 2, 4, 7, 9, 192, 100};
for(int x: arrayDemo){
    System.out.println(x + ", ");
}
```

输出结果与上面相同。

这种增强版的for循环也被称为**foreach循环**，它是普通for循环语句的特殊简化版。所有的foreach循环都可以被改写成for循环。

但是，如果你希望使用数组的索引，那么增强版的 for 循环无法做到。

### 二维数组

二维数组的声明、初始化和引用与一维数组相似：

```java
int intArray[ ][ ] = { {1,2}, {2,3}, {4,5} };
int a[ ][ ] = new int[2][3];
a[0][0] = 12;
a[0][1] = 34;
// ......
a[1][2] = 93;
```

Java语言中，由于把二维数组看作是数组的数组，数组空间不是连续分配的，所以不要求二维数组每一维的大小相同。例如：

```java
int intArray[ ][ ] = { {1,2}, {2,3}, {3,4,5} };
int a[ ][ ] = new int[2][ ];
a[0] = new int[3];
a[1] = new int[5];
```

【示例】通过二维数组计算两个矩阵的乘积。

```java
public class Demo {
    public static void main(String[] args){
        // 第一个矩阵（动态初始化一个二维数组）
        int a[][] = new int[2][3];
        // 第二个矩阵（静态初始化一个二维数组）
        int b[][] = { {1,5,2,8}, {5,9,10,-3}, {2,7,-5,-18} };
        // 结果矩阵
        int c[][] = new int[2][4];
       
        // 初始化第一个矩阵
        for(int i=0; i<2; i++)
            for(int j=0; j<3 ;j++)
                a[i][j] = (i+1) * (j+2);
       
        // 计算矩阵乘积
        for (int i=0; i<2; i++){
            for (int j=0; j<4; j++){
                c[i][j]=0;
                for(int k=0; k<3; k++)
                    c[i][j] += a[i][k] * b[k][j];
            }
        }
        // 输出结算结果
        for(int i=0; i<2; i++){
            for (int j=0; j<4; j++)
                System.out.printf("%-5d", c[i][j]);
            System.out.println();
        }
    }
}
```

运行结果：
> 25   65   14   -65 
> 50   130  28   -130

几点说明：
- 上面讲的是静态数组。静态数组一旦被声明，它的容量就固定了，不容改变。所以在声明数组时，一定要考虑数组的最大容量，防止容量不够的现象。
- 如果想在运行程序时改变容量，就需要用到数组列表(ArrayList，也称动态数组)或向量(Vector)。
- 正是由于静态数组容量固定的缺点，实际开发中使用频率不高，被 ArrayList 或 Vector 代替，因为实际开发中经常需要向数组中添加或删除元素，而它的容量不好预估。
# Jsp/Servlet相关考题

标签： Servlet Jsp 面试考题

---

### 1. Servlet的生命周期。

> Servlet 生命周期：Servlet 加载--->实例化--->服务--->销毁。

- init()：在Servlet的生命周期中，仅执行一次init()方法。它是在服务器装入Servlet时执行的，负责初始化Servlet对象。可以配置服务器，以在启动服务器或客户机首次访问Servlet时装入Servlet。无论有多少客户机访问Servlet，都不会重复执行init（）。
- service()：它是Servlet的核心，负责响应客户的请求。每当一个客户请求一个HttpServlet对象，该对象的Service()方法就要调用，而且传递给这个方法一个“请求”（ServletRequest）对象和一个“响应”（ServletResponse）对象作为参数。在HttpServlet中已存在Service()方法。默认的服务功能是调用与HTTP请求的方法相应的do功能。
- destroy()：仅执行一次，在服务器端停止且卸载Servlet时执行该方法。当Servlet对象退出生命周期时，负责释放占用的资源。一个Servlet在运行service()方法时可能会产生其他的线程，因此需要确认在调用destroy()方法时，这些线程已经终止或完成。

Tomcat 与 Servlet 是如何工作的：

![Tomcat 与 Servlet][1]

**步骤**：

- Web Client 向Servlet容器（Tomcat）发出Http请求
- Servlet容器接收Web Client的请求
- Servlet容器创建一个HttpRequest对象，将Web Client请求的信息封装到这个对象中。
- Servlet容器创建一个HttpResponse对象
- Servlet容器调用HttpServlet对象的service方法，把HttpRequest对象与HttpResponse对象作为参数传给 HttpServlet 对象。
- HttpServlet调用HttpRequest对象的有关方法，获取Http请求信息。
- HttpServlet调用HttpResponse对象的有关方法，生成响应数据。
- Servlet容器把HttpServlet的响应结果传给Web Client。

### 2. Jsp的编译原理。

- 所有的JSP页面翻译出来的class，都从HttpJspBase继承，并且命名为PageName$jsp
- 在第一次调用`pageservice()`函数的时候，该class会进行一次初始化，而这个初始化函数是`_jspx_init`，如果我们想，我们还可以自定义这个函数，来实现JSP页面的初始化。
- <% %> 这样的代码被转换成什么了？

这样的代码被直接转成Java代码放到`pageservice()`函数里面。

- <%! %> 这样的代码被转换成什么了？

这样的代码被翻译成成员函数和成员变量，也就是说，这些声明在JSP的生命周期内都是存在的。

- HTML代码呢？

HTML代码直接被写到`PrintWriter`里面回馈给用户。非常的直接

- 为什么JSP页面有那么多省写方式，比如说session , out , page , context之类。

这都是在pageservice里面定义的临时变量，具体的初始化可以参看子代码，每一次调用JSP页面，这些变量都会被重新初始化一次。当然我们也可以方便的声明自己的变量。

- 省写方式<%= object.doSomething()%> 怎么理解？

这种省写方式调用doSomething所得到的Object的toString()，然后直接写到out里面。相当于:`out.print(object.doSomethiing().toString())`

- JavaBean 里面的scope定义了作用域范围，这个范围在这里的意思是？ 

这是Bean对象句柄保存的地方的意思。我们可以想象一下，一个page范围的Bean只是pageservice里面的一个局部变量，当一次处理结束后，这个变量就会被Java虚拟机回收。而session变量。而request级别的Bean就应该是JSP页面的成员变量。而session和application则不能在JSP页面class里面保存，而应该保存在JSP页面的调用对象里面。

- 关于<%@ page %>命令，这个就太简单了，只是一个一个的对应为response.setContentType()的语句而已。

- 关于JSP页面转向问题。这个语句被翻译为getServletContext().getRequestDispatcher("/List.jsp").forward(req, res);语句。

- <%@ include file="included.jsp" %> 遇到这个语句，JSP翻译器就会把这个文件的代码和现在文件的代码混合然后一起编译，生成JSP类。这个方法很好，可以让我们统一文档的样式，比如说吧header写成一个文件，，而把footer也写成一个JSP，并且在index.html里面把这两个文件包含近来，这样，不管Content怎么变，上下样式都不会变，有利于样式的统一。

### 3. Jsp的运行原理。

#### (1). jsp运行过程

- WEB容器访问请求JSP页面时，它将把该访问请求交给JSP引擎去处理。Tomcat中的JSP引擎就是一个Servlet程序，它负责解释和执行JSP页面。
- 每个JSP页面在第一次被访问时，JSP引擎先将它翻译成一个Servlet源程序，接着再把这个Servlet源程序编译成Servlet的class类文件，然后再由WEB容器像调用普通Servlet程序一样的方式来装载和解释执行这个由JSP页面翻译成的Servlet程序。

#### (2). jsp生命周期

- 翻译： jsp->java文件 
- 编译： java文件->class文件（servlet程序） 
- 构造方法（第1次访问） 
- init方法（第1次访问）：_jspInit() 
- service方法：_jspService() 
- destroy方法：_jspDestroy()

### 4. Jsp的内置对象。

- request:javax.servlet.http.HttpServletRequest,表示客户请求。具体用法：`request.getParameter("name")`等。
- response：javax.servlet.http.HttpServletResponse,表示服务器回应。
- pageContext：javax.servlet.jsp.pageContext,表示JSP页面。
- session：javax.servlet.http.HttpSession,表示一次会话。
- application：javax.servlet.servletContext，表示所有用户共享信息。
- out：javax.servlet.jsp.jspWriter，写入页面内容。
- page：表示一个页面的实例。
- config：javax.servlet.servletConfig，表示配置文件信息。
- exception：java.lang.Throwable，标识异常信息

### 5. Jsp的四个作用域及基本操作方法。

- page范围(pageContext)：一页中有效，跳转即无效。
- request范围：服务器跳转有效，客户端跳转无效。
- session范围：跳转有效，新开浏览器无效。
- application范围：所有用户有效，重启服务器无效。

这四个对象有3个基本方法：

- void setAttribute(String key,Object o); //设置属性
- Object getAttribute(String key); //取得后要向下转型。
- void removeAttribute(String key); //删除属性

> **注意**：在getAttribute之后一定要转型，将属性设置的保存范围尽可能的小。这样能够提高性能！

举例：

```java
pageContext.setAttribute("name","xiazdong"); //只能在单个页面中进行保存
String name = (String)pageContext.getAttribute("name"); //换了一个页面后就无法取得
```

### 6. get和post的区别

- get是从服务器上获取数据，post是向服务器传送数据。
- get是把参数数据队列加到提交表单的ACTION属性所指的URL中，值和表单内各个字段一一对应，在URL中可以看到。post是通过HTTP post机制，将表单内各个字段与其内容放置在HTML HEADER内一起传送到ACTION属性所指的URL地址。用户看不到这个过程。
- 对于get方式，服务器端用Request.QueryString获取变量的值，对于post方式，服务器端用Request.Form获取提交的数据。
- get传送的数据量较小，不能大于2KB。post传送的数据量较大，一般被默认为不受限制。但理论上，IIS4中最大量为80KB，IIS5中为100KB。
- get安全性非常低，post安全性较高。
- HTTP 定义了与服务器交互的不同方法，最基本的方法是 GET 和 POST。事实上 GET 适用于多数请求，而保留 POST 仅用于更新站点。根据 HTTP 规范，GET 用于信息获取，而且应该是 安全的和幂等的。所谓安全的意味着该操作用于获取信息而非修改信息。换句话说，GET 请求一般不应产生副作用。幂等的意味着对同一 URL 的多个请求应该返回同样的结果。完整的定义并不像看起来那样严格。从根本上讲，其目标是当用户打开一个链接时，她可以确信从自身的角度来看没有改变资源。 比如，新闻站点的头版不断更新。虽然第二次请求会返回不同的一批新闻，该操作仍然被认为是安全的和幂等的，因为它总是返回当前的新闻。反之亦然。POST 请求就不那么轻松了。POST 表示可能改变服务器上的资源的请求。仍然以新闻站点为例，读者对文章的注解应该通过 POST 请求实现，因为在注解提交之后站点已经不同了。
- 在FORM提交的时候，如果不指定Method，则默认为GET请求，Form中提交的数据将会附加在url之后，以?分开与url分开。字母数字字符原样发送，但空格转换为“+“号，其它符号转换为%XX,其中XX为该符号以16进制表示的ASCII（或ISO Latin-1）值。GET请求请提交的数据放置在HTTP请求协议头中，而POST提交的数据则放在实体数据中；GET方式提交的数据最多只能有1024字节，而POST则没有此限制。

![GET vs POST][2]

### 7. JSTL标签的介绍

#### (1). 什么是JSTL？

JSTL是apache对EL表达式的扩展（也就是说JSTL依赖EL），JSTL是标签语言！JSTL标签使用以来非常方便，它与JSP动作标签一样，只不过它不是JSP内置的标签，需要我们自己导包，以及指定标签库而已！

#### (2). JSTL标签库：

JSTL一共包含四大标签库：

- core：核心标签库，我们学习的重点；
- fmt：格式化标签库，只需要学习两个标签即可；
- sql：数据库标签库，不需要学习了，它过时了；
- xml：xml标签库，不需要学习了，它过时了。

#### (3). 使用taglib指令导入标签库

```java
<%@ taglib prefix="c"uri="http://java.sun.com/jstl/core" %>
```

- prefix="c"：指定标签库的前缀，这个前缀可以随便给值，但大家都会在使用core标签库时指定前缀为c；
- uri="http://java.sun.com/jstl/core"：指定标签库的uri，它不一定是真实存在的网址，但它可以让JSP找到标签库的描述文件；

#### (4). core标签库常用标签：

- out和set标签

```java
<c:out value=”${aaa}”/>
<c:set var=”a” value=”hello” scope=”session”/>
```

- remove标签

```java
<c:remove var="a" scope=”page”/>
```

- if标签

if标签的test属性必须是一个boolean类型的值，如果test的值为true，那么执行if标签的内容，否则不执行。

```java
<c:set var="a" value="hello"/>  
<c:if test="${not empty a }">  
    <c:out value="${a }"/>  
</c:if>  
```

- choose标签

choose标签对应Java中的if/else if/else结构。when标签的test为true时，会执行这个when的内容。当所有when标签的test都为false时，才会执行otherwise标签的内容。

```java
<c:set var="score" value="${param.score }"/>  
<c:choose>  
    <c:when test="${score > 100 || score < 0}">错误的分数：${score }</c:when>  
    <c:when test="${score >= 90 }">A级</c:when>  
    <c:when test="${score >= 80 }">B级</c:when>  
    <c:when test="${score >= 70 }">C级</c:when>  
    <c:when test="${score >= 60 }">D级</c:when>  
    <c:otherwise>E级</c:otherwise>  
</c:choose> 
```

- forEach标签

forEach当前就是循环标签了，forEach标签有多种两种使用方式：

- 使用循环变量，指定开始和结束值，类似for(int i = 1; i <= 10; i++) {}；
- 循环遍历集合，类似for(Object o : 集合)；

```java
<c:set var="sum" value="0" />   
<c:forEach var="i" begin="1" end="10">   
    <c:set var="sum" value="${sum + i}" />   
</c:forEach>  
<c:out value="sum = ${sum }"/>  
<c:set var="sum" value="0" />  
<c:forEach var="i" begin="1" end="10" step ="2">  
    <c:set var="sum" value="${sum + i}" />  
</c:forEach>  
<c:out value="sum = ${sum }"/> 
```

- fmt标签库常用标签

格式化时间：

```java
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
......  
<%  
    Date date = new Date();  
    pageContext.setAttribute("d", date);  
%>  
<fmt:formatDate value="${d }" pattern="yyyy-MM-dd HH:mm:ss"/>
```

格式化数字：

```java
<%  
    double d1 = 3.5;  
    double d2 = 4.4;   
    pageContext.setAttribute("d1", d1);  
    pageContext.setAttribute("d2", d2);  
%>  
<fmt:formatNumber value="${d1 }" pattern="0.00"/><br/>  
<fmt:formatNumber value="${d2 }" pattern="#.##"/>
```

### 8. EL表达式的介绍

EL 全名为`Expression Language`。EL主要作用：

#### (1). 获取数据。

EL表达式主要用于替换JSP页面中的脚本表达式，以从各种类型的web域中检索java对象、获取数据。(某个web域中的对象，访问javabean的属性、访问list集合、访问map集合、访问数组)

> 获取数据语法："${标识符}"

#### (2). 执行运算.

利用EL表达式可以在JSP页面中执行一些基本的关系运算、逻辑运算和算术运算，以在JSP页面中完成一些简单的逻辑运算。

> 语法：${运算表达式}


#### (3). 获取web开发常用对象。
EL表达式定义了一些隐式对象，利用这些隐式对象，web开发人员可以很轻松获得对web常用对象的引用，从而获得这些对象中的数据。

> 语法：${隐式对象名称}：获得对象的引用

#### (4). 调用Java方法。EL表达式允许用户开发自定义EL函数，以在JSP页面中通过EL表达式调用Java类的方法。

> 语法：${prefix：method(params)}

### 9. forward和redirect的区别

- 从地址栏显示来说 

forward是服务器请求资源,服务器直接访问目标地址的URL,把那个URL的响应内容读取过来,然后把这些内容再发给浏览器.浏览器根本不知道服务器发送的内容从哪里来的,所以它的地址栏还是原来的地址.
redirect是服务端根据逻辑,发送一个状态码,告诉浏览器重新去请求那个地址.所以地址栏显示的是新的URL.

- 从数据共享来说 

forward:转发页面和转发到的页面可以共享request里面的数据.
redirect:不能共享数据.

- 从运用地方来说 

forward:一般用于用户登陆的时候,根据角色转发到相应的模块.
redirect:一般用于用户注销登陆时返回主页面和跳转到其它的网站等.

- 从效率来说 

forward:高.
redirect:低.

- 本质区别

转发是服务器行为，重定向是客户端行为。

### 10. JSP中动态INCLUDE与静态INCLUDE的区别？

动态INCLUDE用jsp:include动作实现 <jsp:include page="included.jsp" flush="true" />它总是会检查所含文件中的变化，适合用于包含动态页面，并且可以带参数。
静态INCLUDE用include伪码实现,定不会检查所含文件的变化，适用于包含静态页面<%@ include file="included.htm" %>

### 11. 如何现实servlet的单线程模式

<%@ page isThreadSafe=”false”%>。

### 12. JSP和Servlet有哪些相同点和不同点，他们之间的联系是什么？ 

JSP是Servlet技术的扩展，本质上是Servlet的简易方式，更强调应用的外表表达。JSP编译后是"类servlet"。Servlet和JSP最主要的不同点在于，Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML里分离开来。而JSP的情况是Java和HTML可以组合成一个扩展名为.jsp的文件。JSP侧重于视图，Servlet主要用于控制逻辑。

### 13. 四种会话跟踪技术

- Cookie：服务器在一个应答首部传递给浏览器的名称/值对。浏览器保存的时间由cookie的过期时间属性来指定。当浏览器向某个服务器发送一个请求时，它会检查其保存的cookie，并在请求首部中包含从同一台服务器上接收到的所有cookie。
- Session tracking:在浏览器和服务器之间不直接传送所有的状态信息，而只是传递表示符（session ID）。浏览器发送sessionID,服务器跟踪与该会话相关联的所有信息。传递sessionID可以通过cookie和URL复写技术，大部分容器都支持这两种技术。服务器无法分辨用户是否关闭了浏览器，因此关闭浏览器意味着与先前的会话关联的所有会话数据都保留在服务器上，直到会话超时，服务器销毁会话对像。
- URL复写：把会话ID编码在URL中。例：`counter.jjsp;jsessionnid=be8d697876787876befdbde898789098980`。这样，即使浏览器不支持cookie，也能够实现会话跟踪。对于URL复写，服务器从请求的URI中提取出会话ID，并把该请求与相应的会话关联起来，然后在访问会话数据的时候，JSP页面所进行的处理方式就和使用cookie跟踪会话id时所使用的方式完全相同。所以sesssion的实现要依靠cookie或URL复写技术。如果想为不支持cookie的浏览器提供会话跟踪，就必须使用<c:url>行为对应用程序中的所有URL进行复写。这意味着应用程序中的所有页面（至少是那些带有对其他页面引用的页面）都必须是JSP页面，这样页面引用才能以动态方式进行编码，如果遗漏了一个ur，那么服务就会失去对会话的跟踪。
- 隐藏表单域：隐藏表单域是将会话ID添加到HTML的隐藏表单中(类型为hidden的input)。

### 14. Request对象的主要方法：

- setAttribute(String name,Object)：设置名字为name的request的参数值
- getAttribute(String name)：返回由name指定的属性值
- getAttributeNames()：返回request对象所有属性的名字集合，结果是一个枚举的实例
- getCookies()：返回客户端的所有Cookie对象，结果是一个Cookie数组
- getCharacterEncoding()：返回请求中的字符编码方式
- getContentLength()：返回请求的Body的长度
- getHeader(String name)：获得HTTP协议定义的文件头信息
- getHeaders(String name)：返回指定名字的request - Header的所有值，结果是一个枚举的实例
- getHeaderNames()：返回所以request Header的名字，结果是一个枚举的实例
- getInputStream()：返回请求的输入流，用于获得请求中的数据
- getMethod()：获得客户端向服务器端传送数据的方法
- getParameter(String name)：获得客户端传送给服务器端的有name指定的参数值
- getParameterNames()：获得客户端传送给服务器端的所有参数的名字，结果是一个枚举的实例
- getParameterValues(String name)：获得有name指定的参数的所有值
- getProtocol()：获取客户端向服务器端传送数据所依据的协议名称
- getQueryString()：获得查询字符串
- getRequestURI()：获取发出请求字符串的客户端地址
- getRemoteAddr()：获取客户端的IP地址
- getRemoteHost()：获取客户端的名字
- getSession([Boolean create])：返回和请求相关Session
- getServerName()：获取服务器的名字
- getServletPath()：获取客户端所请求的脚本文件的路径
- getServerPort()：获取服务器的端口号
- removeAttribute(String name)：删除请求中的一个属性

### 15. jsp有哪些动作?作用分别是什么?

JSP共有以下6种基本动作

- jsp:include：在页面被请求的时候引入一个文件。
- jsp:useBean：寻找或者实例化一个JavaBean。
- jsp:setProperty：设置JavaBean的属性。
- jsp:getProperty：输出某个JavaBean的属性。
- jsp:forward：把请求转到一个新的页面。
- jsp:plugin：根据浏览器类型为Java插件生成OBJECT或EMBED标记。

  [1]: http://images.cnitblog.com/blog/384192/201302/24114945-4774512d1247438fa58c37399d3999ae.jpg
  [2]: http://static.blinkfox.com/getvspost.png
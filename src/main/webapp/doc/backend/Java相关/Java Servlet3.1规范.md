# 重学Servlet3.1规范

## 1 总览

### 1.1 什么是 Servlet

`servlet`是基于`Java`的`Web`组件，由容器进行管理，来生成动态内容。像其他基于Java的组件技术一样，servlet也是基于平台无关的Java类格式，被编译为平台无关的字节码，可以被基于Java技术的 Web 服务器动态加载并运行。容器（Container），有时候也叫做`servlet引擎`，是Web服务器为支持servlet功能扩展的部分。客户端通过servlet容器实现的`request/response paradigm`（请求/应答模式）与Servlet进行交互。

### 1.2 什么是 Servlet 容器

`Servlet Container`（Servlet 容器）是Web服务器或者应用服务器的一部分，用于提供基于`请求/响应发送模式`的网络服务，解码基于`MIME`的请求，并且格式化基于MIME的响应。Servlet容器同时也包含和管理他们的生命周期里Servle。

Servlet容器可以嵌入到宿主的Web服务器中，或者通过Web服务器的本地扩展`API`单独作为附加组件安装。Servelt容器也可能内嵌或安装到启用Web功能的应用服务器中。

所有的Servlet容器必须支持`HTTP`协议用于请求和响应，但额外的基于`请求/响应`的协议，如`HTTPS`(HTTP over SSL)的支持是可选的。对于HTTP规范需要版本，容器必须支持`HTTP/1.0`和`HTTP/1.1`。因为容器或许支持`RFC2616`(HTTP/1.1)描述的缓存机制，缓存机制可能在将客户端请求交给Servlet处理之前修改它们，也可能在将Servlet生成的响应发送给客户端之前修改它们，或者可能根据RFC2616规范直接对请求作出响应而不交给Servlet进行处理。

Servlet容器应该使Servlet执行在一个安全限制的环境中。在Java平台标准版（`J2SE`, v.1.3或更高）或者Java平台企业版(`Java EE`,v.1.3 或更高) 的环境下，这些限制应该被放置在Java平台定义的安全许可架构中。比如，高端的应用服务器为了保证容器的其他组件不受到负面影响可能会限制 Thread 对象的创建。

`Java SE 7`是构建`Servlet3.1`容器最低的Java平台版本。

### 1.3 一个例子

以下是一个典型的事件序列：

1. 客户端（如web浏览器）要访问Web服务器，并发送一个`HTTP请求`。
2. Web服务器接收到请求并且交给servlet容器处理，servlet容器可以运行在与宿主Web服务器同一个进程中，也可以是同一主机的不同进程，或者位于不同的主机的Web服务器中，对请求进行处理。
3. servlet容器根据servlet配置选择相应的servlet，并调用代表请求和响应的对象。
4. servlet通过请求对象得到远程用户，`HTTP POST`参数和其他有关数据可能作为请求的一部分随请求一起发送过来。Servlet执行我们编写的任意的逻辑，然后动态产生响应内容发送回客户端。发送数据到客户端是通过响应对象完成的。
5. 一旦servlet完成请求的处理，servlet容器必须确保响应正确的输出，并且将控制权还给宿主Web服务器。

### 1.4 Servlet与其他技术的对比

从功能上看，servlet位于`Common Gateway Interface`（公共网关接口，简称`CGI`）程序和私有的服务器扩展如 `Netscape Server API`（NSAPI）或`Apache Modules`这两者之间。

相对于其他服务器扩展机制`Servlet`有如下优势：

- 它们通常比`CGI`脚本更快，因为采用不同的处理模型。
- 它们采用标准的API从而支持更多的Web服务器。
- 它们拥有Java编程语言的所有优势，包括容易开发和平台无关。
- 它们可以访问Java平台提供的大量的API。

### 1.5 与Java EE的关系

`Java Servlet API 3.1`版本是Java平台企业版7版本必须的API。Servlet容器和servlet被部署到平台中时，为了能在`Java EE`环境中执行，必须满足Java EE规范中描述的额外的一些要求。

### 1.6 与Servlet 2.5规范的兼容性

**处理注解**

在`Servlet 2.5`中,`metadata-complete`只影响在部署时的注释扫描。`web-fragments`的概念在servlet 2.5并不存在。然而在`servlet 3.0`和之后,metadata-complete影响扫描所有的在部署时指定部署信息和web-fragments注释。注释的版本的描述符必须不影响你扫描在一个web应用程序。除非metadata-complete指定，规范的一个特定版本的实现必须扫描所有配置的支持的注解。

## 2 Servlet接口

`Servlet接口`是`Java Servlet API`的核心抽象。所有Servlet类必须直接或间接的实现该接口，或者更通常做法是通过继承一个实现了该接口的类从而复用许多共性功能。目前有`GenericServlet`和`HttpServlet`这两个类实现了`Servlet` 接口。大多数情况下，开发者只需要继承`HttpServlet`去实现自己的Servlet即可。

### 2.1 请求处理方法

基础的Servlet接口定义了`service`方法用于处理客户端的请求。当有请求到达时，该方法由`servlet`容器路由到一个 servlet实例来调用。

Web应用的并发请求处理通常需要Web开发人员去设计适合多线程执行的Servlet，从而保证service方法能在一个特定时间点处理多线程并发执行。（译者注：Servlet默认是线程不安全的，需要开发人员处理多线程问题）

通常Web容器对于并发请求将使用同一个servlet处理，并且在不同的线程中并发执行service方法。

#### 基于 HTTP 规范的请求处理方法

`HttpServlet`抽象子类在基本的`Servlet`之上添加了些协议相关的方法，并且这些方法能根据`HTTP`请求类型自动的由HttpServlet中实现的service方法转发到相应的协议相关的处理方法上。这些方法是：

- `doGet`处理`HTTP GET`请求
- `doPost`处理`HTTP POST`请求
- `doPut`处理`HTTP PUT`请求
- `doDelete`处理`HTTP DELETE`请求
- `doHead`处理`HTTP HEAD`请求
- `doOptions`处理`HTTP OPTIONS`请求
- `doTrace`处理`HTTP TRACE`请求

一般的，开发基于HTTP的servlet时,Servlet开发人员只需去实现`doGet`和`doPost`请求处理方法即可。如果开发人员想使用其他处理方法，其使用方式跟之前的是类似的，即HTTP编程都是类似。

#### 附加方法

`doPut`和`doDelete`方法允许`Servlet`开发人员让支持`HTTP/1.1`的客户端使用这些功能。`HttpServlet`中的`doHead`方法可以认为是 doGet方法的一个特殊形式，它仅返回由`doGet`方法产生的`header`信息。`doOptions`方法返回当前servlet支持的HTTP方法。`doTrace`方法返回的响应包含`TRACE请求`的所有头信息。

#### 有条件GET支持

`HttpServlet`定义了用于支持有条件`GET操作`的`getLastModified`方法。**所谓的有条件GET操作是指客户端通过GET请求获取资源时，当资源自第一次获取那个时间点发生更改后才再次发生数据，否则将使用客户端缓存的数据**。在一些适当的场合，实现此方法可以更有效的利用网络资源，减少不必要的数据发送。

### 2.2 实例数量

通过注解描述的（**第8章 注解和可插拔性**）或者在`Web`应用程序的部署描述符（**第14章 部署描述符**）中描述的`servlet`声明，控制着`servlet`容器如何提供servlet实例。

对于未托管在分布式环境中（默认）的`servlet`而言，`servlet`容器对于每一个Servlet声明必须且只能产生一个实例。不过，如果Servlet实现了SingleThreadModel接口，servlet容器可以选择实例化多个实例以便处理高负荷请求或者串行化请求到一个特定实例。

如果servlet以分布式方式进行部署，容器可以为每个`Java Virtual Machine`(JVM™)的每个Servlet声明产生一个实例。但是，如果在分布式环境中servlet实现了`SingleThreadModel`接口，此时容器可以为每个容器的`JVM`实例化多个Servlet实例。

#### 关于`Single Thread Model`

`SingleThreadModel`接口的作用是保证一个特定`servlet`实例的`service`方法在一个时刻仅能被一个线程执行，一定要注意，此保证仅适用于每一个`servlet实例`，因此容器可以选择池化这些对象。有些对象可以在同一时刻被多个`servlet`实例访问，如`HttpSession`实例，可以在一个特定的时间对多个`Servlet`可用，包括那些实现了SingleThreadModel接口的Servlet。

建议开发人员采取其他手段来解决这些问题,而不是实现这个接口,如避免使用实例变量或同步的代码块访问这些资源。S**ingleThreadModel 接口已经在本版本规范中弃用**。

### 2.3 Servlet 生命周期

**`Servlet`是按照一个严格定义的生命周期被管理，该生命周期规定了Servlet如何被加载、实例化、初始化、处理客户端请求，以及何时结束服务**。该声明周期可以通过`javax.servlet.Servlet`接口中的 `init`、`service`和`destroy`这些`API`来表示，所有`Servlet`必须直接或间接的实现`GenericServlet`或`HttpServlet`抽象类。

#### 加载和实例化

**`Servlet`容器负责加载和实例化Servlet**。加载和实例化可以发生在容器启动时，或者延迟初始化直到容器决定有请求需要处理时。

当Servlet引擎启动后，servlet容器必须定位所需要的Servlet类。Servlet容器使用普通的Java类加载设施加载Servlet类。可以从本地文件系统或远程文件系统或者其他网络服务加载。

加载完Servlet类后，容器就可以实例化它并使用了。

#### 初始化

**`servlet`对象实例化后，容器必须初始化`servlet`之后才能处理客户端的请求**。初始化的目的是以便Servlet能读取持久化配置数据，初始化一些代价高的资源（比如`JDBC™ API`连接），或者执行一些一次性的动作。容器通过调用Servlet实例的`init`方法完成初始化，init方法定义在Servlet接口中，并且提供一个唯一的`ServletConfig`接口实现的对象作为参数，该对象每个 Servlet实例一个。配置对象允许Servlet访问由Web应用配置信息提供的键-值对的初始化参数。该配置对象也提供给Servlet去访问一个`ServletContext`对象，ServletContext描述了Servlet的运行时环境。请参考**第4章 “Servlet 上下文”**获取ServletContext接口的更多信息。

#### 初始化时的错误条件

在初始化阶段，servlet实例可能抛出`UnavailableException`或`ServletException`异常。在这种情况下，servlet不能放置到活动服务中，servlet容器必须释放它。如果初始化没有成功，`destroy`方法不应该被调用。

在实例初始化失败后容器可能再实例化和初始化一个新的实例。此规则的例外是，当抛出的`UnavailableException`表示一个不可用的最小时间，容器在创建和初始化一个新的servlet实例之前必须等待一段时间。

#### 使用工具时的注意事项

当一个工具加载并内省某个Web应用时触发的静态初始化，这种用法与调用init初始化方法是有区别的。在Servlet的init方法没被调用，开发人员不应该假定其处于活动的容器环境内。比如，当某个Servlet仅有静态方法被调用时，不应该与数据库或企业级`JavaBean`（EJB）容器建立连接。

#### 请求处理

servlet完成初始化后，servlet容器就可以使用它处理客户端请求了。客户端请求由`ServletRequest`类型的请求对象表示。servlet封装响应并返回给请求的客户端，该响应由`ServletResponse`类型的响应对象表示。这两个对象是由容器通过参数传递到Servlet接口的`service`方法的。

在HTTP请求的场景下，容器提供的请求和响应对象具体类型分别是**`HttpServletRequest`**和**`HttpServletResponse`**。**需要注意的是，由`servlet`容器初始化的某个`servlet实例`在服务期间，可以在其生命周期中不处理任何请求。**

#### 多线程问题

servlet容器可以并发的发送多个请求到`servlet`的`service`方法。为了处理这些请求，Servlet开发者必须为service方法的多线程并发处理做好充足的准备。

一个替代的方案是开发人员实现`SingleThreadModel`接口，由容器保证一个service方法在同一个时间点仅被一个请求线程调用，但是此方案是不推荐的。servlet容器可以通过串行化访问servlet的请求，或者维护一个`servlet`实例池完成该需求。**如果`Web`应用中的 servlet被标注为分布式的，容器应该为每一个分布式应用程序的JVM 维护一个`Servlet实例池`**。

对于那些没有实现`SingleThreadModel`接口的servlet，但是它的service方法（或者是那些HttpServlet中通过service方法分派的`doGet`、`doPost`等分派方法）是通过`synchronized`关键词定义的，`servlet`容器不能使用实例池方案，并且只能使用序列化请求进行处理。**强烈推荐开发人员不要去同步`service`方法（或者那些由service分派的方法），因为这将严重影响性能**。

#### 异步处理

有时候，`Filter`及/或`Servlet`在生成响应之前必须等待一些资源或事件以便完成请求处理。比如，Servlet在进行生成一个响应之前可能等待一个可用的`JDBC`连接，或者一个远程`web`服务的响应，或者一个`JMS`消息，或者一个应用程序事件。在`Servlet`中等待是一个低效的操作，因为这是*阻塞*操作，从而白白占用一个线程或其他一些受限资源。许多线程为了等待一个缓慢的资源比如数据库经常发生阻塞，可能引起线程饥饿，且降低整个`Web`容器的服务质量。

引入了异步处理请求的能力，使线程可以返回到容器，从而执行更多的任务。**当开始异步处理请求时，另一个线程或回调可以或者产生响应，或者调用完成（`complete`）或请求分派（`dispatch`），这样，它可以在容器上下文使用`AsyncContext.dispatch`方法运行**。一个典型的异步处理事件顺序是：

1. 请求被接收到，通过一系列如用于验证的等标准的`filter`之后被传递到`Servlet。
2. servlet处理请求参数及（或）内容体从而确定请求的类型。
3. 该servlet发出请求去获取一些资源或数据，例如，发送一个远程`web`服务请求或加入一个等待`JDBC`连接的队列。
4. servlet不产生响应并返回。
5. 过了一段时间后，所请求的资源变为可用，此时处理线程继续处理事件，要么在同一个线程，要么通过`AsyncContext`分派到容器中的一个资源上。

`Java企业版`的功能，如第15.2.2节，在“Web应用环境”和第15.3.1节，在“EJB调用的安全标识传播”，仅在初始化请求的线程执行，或者请求经过`AsyncContext.dispatch`方法被分派到容器。Java企业版的功能可能支持由`AsyncContext.start(Runnable)`方法使用其他线程直接操作响应对象。

第八章描述的`@WebServlet`和`@WebFilter`注解有一个属性——`asyncSupported`，`boolean`类型,默认值为`false`。**当`asyncSupported`设置为`true`，应用通过执行`startAsync`（见下文）可以启动一个单独的线程中进行异步处理，并把请求和响应的引用传递给这个线程，然后退出原始线程所在的容器**。这意味着响应将遍历（相反的顺序）与进入时相同的过滤器（或过滤器链）。直到`AsyncContext`调用`complete`（见下文）时响应才会被提交。如果异步任务在容器启动的分派之前执行，且调用了`startAsync`并返回给容器，此时应用需负责处理请求和响应对象的并发访问。

从一个`Servlet`分派时，把`asyncSupported=true`设置为`false`是允许的。这种情况下，当servlet的`service`方法不支持异步退出时，响应将被提交，且容器负责调用`AsyncContext`的`complete`，以便所有感兴趣的`AsyncListener`得到触发。过滤器作为清理要完成的异步任务持有的资源的一种机制，也应该使用`AsyncListener.onComplete`触发的结果。

从一个同步Servlet分派到另一个异步Servlet是非法的。不过与该点不同的是当应用调用`startAsync`时将抛出`IllegalStateException`。这将允许servlet只能作为同步的或异步的Servlet。

应用在一个与初始请求所用的不同的线程中等待异步任务直到可以直接写响应，这个线程不知道任何过滤器。如果过滤器想处理新线程中的响应，那就必须在处理进入时的初始请求时包装响应，并且把包装的响应传递给链中的下一个过滤器，并最终交给Servlet。因此，如果响应是包装的（可能被包装多次，每一个过滤器一次），并且应用处理请求并直接写响应，这将只写响应的包装对象，即任何输出的响应都会由响应的包装对象处理。当应用在一个单独的线程中读请求时，写内容到响应的包装对象，这其实是从请求的包装对象读取，并写到响应的包装对象，因此对包装对象操作的所有输入及（或）输出将继续存在。

如果应用选择这样做的话，它将可以使用`AsyncContext`从一个新线程发起到容器资源的分派请求。这将允许在容器范围内使用像JSP`这种内容生成技术。

除了**注解属性**外，我们还添加了如下方法/类：

- **`ServletRequest`**
`public AsyncContext startAsync(ServletRequest req, ServletResponse res)`。这个方法的作用是将请求转换为异步模式，并使用给定的请求及响应对象和`getAsyncTimeout`返回的超时时间初始化它的`AsyncContext`。**`ServletRequest`和`ServletResponse`参数必须是与传递给servlet的`service`或filter的 `doFilter`方法相同的对象，或者是`ServletRequestWrapper`和`ServletResponseWrapper`子类的包装对象**。当应用退出`service`方法时，调用该方法必须确保响应没有被提交。当调用返回的`AsyncContext`的`AsyncContext.complete`或`AsyncContext`超时并且没有监听器处理超时时，它将被提交。异步超时定时器直到请求和它关联的响应从容器返回时才启动。`AsyncContext`可以被异步线程用来写响应，它也能用来通知没有关闭和提交的响应。

**如果请求在不支持异步操作的servlet或filter范围中调用 `startAsync`，或者响应已经被提交或关闭，或者在同一个分派期间重复调用，这些是非法的**。从调用`startAsync`返回的`AsyncContext`可以接着被用来进行进一步的异步处理。调用返回的`AsyncContext`的`hasOriginalRequestResponse()`方法将返回`false`，除非传过去的`ServletRequest`和`ServletResponse`参数是最原始的那个或不是应用提供的包装器。

在请求设置为异步模式后，在入站调用期间添加的一些请求及（或）响应的包装器可能需要在异步操作期间一直保持，并且它们关联的资源可能也不会释放，出站方向调用的所有过滤器可以以此作为一个标志。一个在入站调用期间的过滤器应用的`ServletRequestWrapper`可以被出站调用的过滤器释放，只有当给定的`ServletRequest`是由 `AsyncContext`初始化的且通过调用`AsyncContext.getRequest()`返回的，不包括之前说的`ServletRequestWrapper`。这规则同样适用于`ServletResponseWrapper`实例。

- `public AsyncContext startAsync()`是一个简便方法，使用原始请求和响应对象用于异步处理。请注意，如果它们在你想调用此方法之前被包装了，这个方法的使用者应该刷出（flush）响应，确保数据写到被包装的响应中没有丢失。
- `public AsyncContext getAsyncContext()`–返回由`startAsync`调用创建的或初始化的 `AsyncContext`。如果请求已经被设置为异步模式，调用`getAsyncContext`是非法的。
- `public boolean isAsyncSupported()`–如果请求支持异常处理则返回`true`，否则返回`false`。一旦请求传给了过滤器或`servlet`不支持异步处理（通过指定的注解或声明），异步支持将被禁用。
- `public boolean isAsyncStarted()`–如果请求的异步处理已经开始将返回`true`，否则返回`false`。如果这个请求自从被设置为异步模式后已经使用任意一个`AsyncContext.dispatch`方法分派，或者成功调用了`AsynContext.complete`方法，这个方法将返回`false`。
- `public DispatcherType getDispatcherType()`–返回请求的分派器（dispatcher）类型。容器使用请求的分派器类型来选择需要应用到请求的过滤器。只有匹配分派器类型和`url`模式（url pattern）的过滤器才会被应用。允许一个过滤器配置多个分派器类型，过滤器可以根据请求的不同分派器类型处理请求。请求的初始分派器类型定义为`DispatcherType.REQUEST`。使用`RequestDispatcher.forward(ServletRequest, ServletResponse)`或`RequestDispatcher.include(ServletRequest, ServletResponse)`分派时，它们的请求的分派器类型分别是`DispatcherType.FORWARD`或`DispatcherType.INCLUDE`，当一个异步请求使用任意一个`AsyncContext.dispatch`方法分派时该请求的分派器类型是`DispatcherType.ASYNC`。最后，由容器的错误处理机制分派到错误页面的分派器类型是`DispatcherType.ERROR`。
- `AsyncContext`–该类表示在`ServletRequest`启动的异步操作执行上下文，`AsyncContext`由之前描述的`ServletRequest.startAsync`创建并初始化。**`AsyncContext`的方法**：
- `public ServletRequest getRequest()`–返回调用`startAsync`用于初始化`AsyncContext`的请求对象。当在异步周期之前调用了`complete`或任意一个`dispatch`方法，调用`getRequest`将抛出`IllegalStateException`。
- `public ServletResponse getResponse()`–返回调用`startAsync`用于初始化`AsyncContext`的响应对象。当在异步周期之前调用了`complete`或任意一个`dispatch`方法，调用`getResponse`将抛出`IllegalStateException`。
- `public void setTimeout(long timeoutMilliseconds)`–设置异步处理的超时时间，以毫秒为单位。该方法调用将覆盖容器设置的超时时间。如果没有调用`setTimeout`设置超时时间，将使用容器默认的超时时间。**一个小于等于0的数表示异步操作将永不超时**。当调用任意一个`ServletRequest.startAsync`方法时，一旦容器启动的分派返回到容器，超时时间将应用到`AsyncContext`。当在异步周期开始时容器启动的分派已经返回到容器后，再设置超时时间是非法的，这将抛出一个`IllegalStateException`异常。
- `public long getTimeout()`–获取`AsyncContext`关联的超时时间的毫秒值。该方法返回容器默认的超时时间，或最近一次调用`setTimeout`设置超时时间。
- `public void addListener(AsyncListener listener, ServletRequest req, ServletResponse res)`–注册一个用于接收的 `onTimeout`,`onError`,`onComplete`或`onStartAsync`通知的监听器。前三个是与最近通过调用任意`ServletRequest.startAsync`方法启动的异步周期相关联的。`onStartAsync`是与通过任意`ServletRequest.startAsync`启动的一个新的异步周期相关联的。异步监听器将以它们添加到请求时的顺序得到通知。当`AsyncListener`得到通知，传入到该方法的请求响应对象与`AsyncEvent.getSuppliedRequest()`和`AsyncEvent.getSuppliedResponse()`是完全相同的。不应该对这些对象进行读取或写入，因为自从注册了`AsyncListener`后可能发生了额外的包装，不过可以被用来按顺序释放与它们关联的资源。容器启动的分派在异步周期启动后返回到容器后，或者在一个新的异步周期启动之前，调用该方法是非法的，将抛出`IllegalStateException`。
- `public createListener(Class clazz)`–实例化指定的`AsyncListener`类。返回的`AsyncListener` 实例在使用下文描述的`addListener`方法注册到`AsyncContext`之前可能需要进一步的自定义。给定的`AsyncListener`类必须定义一个用于实例化的空参构造器，该方法支持适用于`AsyncListener`的所有注解。
- `public void addListener(AsyncListener)`–注册给定的监听器用于接收`onTimeout`,`onError`,`onComplete`或`onStartAsync`通知。前三个是与最近通过调用任意`ServletRequest.startAsync`方法启动的异步周期相关联的。`onStartAsync`是与通过任意`ServletRequest.startAsync`启动的一个新的异步周期相关联的。异步监听器将以它们添加到请求时的顺序得到通知。当`AsyncListener`接收到通知，如果在请求时调用`startAsync(req, res)`或`startAsync()`，从`AsyncEvent`会得到同样的请求和响应对象。请求和响应对象可以是或者不是被包装的。异步监听器将以它们添加到请求时的顺序得到通知。容器启动的分派在异步周期启动后返回到容器后，或者在一个新的异步周期启动之前，调用该方法是非法的，将抛出`IllegalStateException`。
- `public void dispatch(String path)`–将用于初始化`AsyncContext`的请求和响应分派到指定的路径的资源。该路径以相对于初始化`AsyncContext`的`ServletContext`进行解析。与请求查询方法相关的所有路径，必须反映出分派的目标，同时原始请求的`URI`，上下文，路径信息和查询字符串都可以从请求属性中获取，请求属性定义在9.7.2章节，“分派的请求参数”。这些属性必须反映最原始的路径元素，即使在多次分派之后。
- `public void dispatch()`–一个简便方法，使用初始化`AsyncContext`时的请求和响应进行分派，如下所示。如果使用`startAsync(ServletRequest, ServletResponse)`初始化`AsyncContext`，且传入的请求是`HttpServletRequest`的一个实例，则使用`HttpServletRequest.getRequestURI()`返回的`URI`进行分派。否则分派的是容器最后分派的请求URI。下面的代码示例分别演示了不同情况下分派的目标 URI是什么。

```java
// REQUEST to /url/A
AsyncContext ac = request.startAsync();
...
ac.dispatch(); // ASYNC dispatch to /url/A
```

```java
// REQUEST to /url/A
// FORWARD to /url/B
request.getRequestDispatcher(“/url/B”).forward(request,
response);
// Start async operation from within the target of the FORWARD
AsyncContext ac = request.startAsync();
ac.dispatch(); // ASYNC dispatch to /url/A
```

```java
// REQUEST to /url/A
// FORWARD to /url/B
request.getRequestDispatcher(“/url/B”).forward(request,
response);
// Start async operation from within the target of the FORWARD
AsyncContext ac = request.startAsync(request, response);
ac.dispatch(); // ASYNC dispatch to /url/B
```

- `public void dispatch(ServletContext context, String path)`-将用于初始化`AsyncContext`的请求和响应分派到指定`ServletContext`的指定路径的资源。
- 上面定义了`dispatch`方法的全部3个变体，调用这些方法且将请求和响应对象传入到容器的一个托管线程后将立即返回，在托管线程中异步操作将被执行。请求的分派器类型设置为异步（ASYNC）。不同于`RequestDispatcher.forward(ServletRequest, ServletResponse)`分派，响应的缓冲区和头信息将不会重置，即使响应已经被提交分派也是合法的。控制委托给分派目标的请求和响应，除非调用了`ServletRequest.startAsync()`或`ServletRequest.startAsync(ServletRequest, ServletResponse)`，否则响应将在分派目标执行完成时被关闭。在调用了`startAsync`方法的容器启动的分派没有返回到容器之前任何dispatch方法的调用将没有任何作用。`AsyncListener.onComplete(AsyncEvent)`,`AsyncListener.onTimeout(AsyncEvent)`和`AsyncListener.onError(AsyncEvent)`的调用将被延迟到容器启动的分派返回到容器之后。通过调用`ServletRequest.startAsync`.启动的每个异步周期至多只有一个异步分派操作。相同的异步周期内任何试图执行其他的异步分派操作是非法的并将导致抛出`IllegalStateException`。如果后来在已分派的请求上调用`startAsync`，那么所有的`dispatch`方法调用将和之上具有相同的限制。
任何在执行`dispatch`方法期间可能抛出的错误或异常必须由容器抓住和处理，如下所示：
- i. 调用所有由`AsyncContext`创建的并注册到`ServletRequest`的`AsyncListener`实例的`AsyncListener.onError(AsyncEvent)`方法， 可以通过`AsyncEvent.getThrowable()`获取到捕获的`Throwable`。
- ii. 如果没有监听器调用`AsyncContext.complete`或任何`AsyncContext.dispatch`方法，然后执行一个状态码为`HttpServletResponse.SC_INTERNAL_SERVER_ERROR`的出错分派，并且可以通过`RequestDispatcher.ERROR_EXCEPTION`请求属性获取`Throwable`值。
- iii. 如果没有找到匹配的错误页面，或错误页面没有调用`AsyncContext.complete()`或任何`AsyncContext.dispatch`方法，则容器必须调用`AsyncContext.complete`。
- `public boolean hasOriginalRequestAndResponse()`–该方法检查`AsyncContext` 是否以原始的请求和响应对象调用`ServletRequest.startAsync()`完成初始化的，或者是否通过调用`ServletRequest.startAsync(ServletRequest, ServletResponse)`完成初始化的，且传入的`ServletRequest`和`ServletResponse`参数都不是应用提供的包装器，这样的话将返回`true`。如果`AsyncContext`使用包装的请求及（或）响应对象调用`ServletRequest.startAsync(ServletRequest, ServletResponse)`完成初始化，那么将返回`false`。在请求处于异步模式后，该信息可以被出站方向调用的过滤器使用，用于决定是否在入站调用时添加的请求及（或）响应包装器需要在异步操作期间被维持或者被释放。
- `public void start(Runnable r)`–该方法导致容器分派一个线程，该线程可能来自托管的线程池，用于运行指定的`Runnable`对象。容器可能传播相应的上下文信息到该`Runnable`对象。
- `public void complete()`–如果调用了`request.startAsync`，则必须调用该方法以完成异步处理并提交和关闭响应。如果请求分派到一个不支持异步操作的`Servlet`，或者由`AsyncContext.dispatch`调用的目标`servlet`之后没有调用`startAsync`，则`complete`方法会由容器调用。这种情况下，容器负责当`servlet`的`service`方法一退出就调用`complete()`。 如果`startAsync`没有被调用则必须抛出`IllegalStateException`。在调用`ServletRequest.startAsync()`或`ServletRequest.startAsync(ServletRequest, ServletResponse)`之后且在调用任意`dispatch`方法之前的任意时刻调用`complete()`是合法的。在调用了`startAsync`方法的容器启动的分派没有返回到容器之前该方法的调用将没有任何作用。`AsyncListener.onComplete(AsyncEvent)`的调用将被延迟到容器启动的分派返回到容器之后。
- `ServletRequestWrapper
public boolean isWrapperFor(ServletRequest req)`-检查该包装器是否递归的包装了给定的 `ServletRequest`，如果是则返回`true`，否则返回`false`。
- `ServletResponseWrapper
public boolean isWrapperFor(ServletResponse res)`-检查该包装器是否递归的包装了给定的`ServletResponse`，如果是则返回`true`，否则返回`false`。
- `AsyncListener
public void onComplete(AsyncEvent event)`–用于通知监听器在`Servlet`上启动的异步操作完成了。
- `public void onError(AsyncEvent event)`–用于通知监听器异步操作未能完成。
- `public void onStartAsync(AsyncEvent event)`–用于通知监听器正在通过调用一个`ServletRequest.startAsync`方法启动一个新的异步周期。正在被重新启动的异步操作对应的`AsyncContext`可以通过调用给定的`event`上调用`AsyncEvent.getAsyncContext`获取。

- 在异步操作超时的情况下，容器必须按照如下步骤运行：
- 当异步操作启动后调用注册到`ServletRequest`的所有`AsyncListener`实例的`AsyncListener.onTimeout`方法。
- 如果没有监听器调用`AsyncContext.complete()`或任何`AsyncContext.dispatch`方法，执行一个状态码为`HttpServletResponse.SC_INTERNAL_SERVER_ERROR`出错分派。
- 如果没有找到匹配的错误页面，或者错误页面没有调用`AsyncContext.complete()`或任何`AsyncContext.dispatch`方法，则容器必须调用`AsyncContext.complete()`。
- 如果在`AsyncListener`中调用方法抛出异常，将记录下来 且将不影响任何其他`AsyncListener`的调用。
- 默认情况下是不支持`JSP`中的异步处理，因为它是用于内容生成且异步处理可能在内容生成之前已经完成。这取决于容器如何处理这种情况。一旦完成了所有的异步活动，使用`AsyncContext.dispatch`分派到的`JSP`页面可以用来生成内容。

#### 线程安全

除了`startAsync`和`complete`方法，请求和响应对象的实现都不保证线程安全。这意味着它们应该仅在请求处理线程范围内使用或应用确保线程安全的访问请求和响应对象。

如果应用使用容器管理对象创建一个线程，例如请求或响应对象，这些对象必须在其生命周期内被访问，就像定义在3.12节的“请求对象的生命周期”和5.7节的“响应对象的生产周期”。请注意，除了`startAsync`和`complete`方法，请求和响应对象不是线程安全的。如果这些对象需要多线程访问，需要同步这些访问或通过包装器添加线程安全语义，比如，同步化调用访问请求属性的方法，或者在线程内为响应对象使用一个局部输出流。

#### 升级处理

在`HTTP/1.1`，`Upgrade`通用头允许客户端指定其支持和希望使用的其他通信协议。如果服务器找到合适的切换协议，那么新的协议将在之后的通信中使用。`Servlet`容器提供了`HTTP`升级机制。不过，`Servlet` \容器本身不知道任何升级协议。协议处理封装在`HttpUpgradeHandler`协议处理器。在容器和`HttpUpgradeHandler`协议处理器之间通过字节流进行数据读取或写入。

当收到一个升级请求，servlet可以调用 `HttpServletRequest.upgrade`方法启动升级处理。该方法实例化给定的`HttpUpgradeHandler`类，返回的`HttpUpgradeHandler`实例可以被进一步的定制。应用准备和发送一个合适的响应到客户端。退出 `servlet service`方法之后，`servlet`容器完成所有过滤器的处理并标记连接已交给`HttpUpgradeHandler`协议处理器处理。然后调用`HttpUpgradeHandler`协议处理器的`init`方法，传入一个`WebConnection`以允许`HttpUpgradeHandler`是协议处理器访问数据流。

`Servlet`过滤器仅处理初始的`HTTP`请求和响应，然后它们将不会再参与到后续的通信中。换句话说，一旦请求被升级，它们将不会被调用。

`HttpUpgradeHandler`可以使用非阻塞`IO（non blocking IO）消费和生产消息。

当处理`HTTP`升级时，开发人员负责线程安全的访问`ServletInputStream`和`ServletOutputStream`。

当升级处理已经完成，将调用`HttpUpgradeHandler.destroy`方法

#### 服务的终止

`servlet`容器没必要保持装载的servlet持续任何特定的一段时间。一个`servlet`实例可能会在servlet容器内保持活跃（active）持续一段时间（以毫秒为单位），servlet容器的寿命可能是几天，几个月，或几年，或者是任何之间的时间。

当`servlet`容器确定servlet应该从服务中移除时，将调用Servlet接口的`destroy`方法以允许servlet释放它使用的任何资源和保存任何持久化的状态。例如，当想要节省内存资源或它被关闭时，容器可以做这个。

在servlet容器调用`destroy`方法之前，它必须让当前正在执行`service`方法的任何线程完成执行，或者超过了服务器定义的时间限制。

一旦调用了`servlet`实例的`destroy`方法，容器无法再路由其他请求到该`servlet`实例了。如果容器需要再次使用该servlet，它必须用该servlet类的一个新的实例。在destroy方法完成后，servlet容器必须释放servlet实例以便被垃圾回收。

## 3 请求

请求对象封装了客户端请求的所有信息。在`HTTP`协议中，这些信息是从客户端发送到服务器请求的HTTP头部和消息体。

### 3.1 HTTP协议参数

`servlet`的请参数以字符串的形式作为请求的一部分从客户端发送到 servlet 容器。当请求是一个`HttpServletRequest`对象，且符合“参数可用时”描述的条件时，容器从`URI`查询字符串和`POST`数据中填充参数。参数以一系列的名-值对（name-value）的形式保存。任何给定的参数的名称可存在多个参数值。`ServletRequest`接口的下列方法可访问这些参数：

- getParameter
- getParameterNames
- getParameterValues
- getParameterMap

`getParameterValues`方法返回一个`String`对象的数组，包含了与参数名称相关的所有参数值。`getParameter`方法的返回值必须是`getParameterValues`方法返回的`String`对象数组中的第一个值。`getParameterMap`方法返回请求参数的一个`java.util.Map`对象，其中以参数名称作为`map`键，参数值作为`map值。

查询字符串和`POST`请求的数据被汇总到请求参数集合中。查询字符串数据在`POST`数据之前发送。例如，如果请求由查询字符串`a=hello`和`POST`数据`a=goodbye&a=world`组成，得到的参数集合顺序将是 `a=(hello, goodbye, world)`。

这些`API`不会暴露`GET`请求（HTTP 1.1所定义的）的路径参数。他们必须从`getRequestURI`方法或`getPathInfo`方法返回的字符串值中解析。

#### 当参数可用时

以下是在`post`表单数据填充到参数集前必须满足的条件：

1. 该请求是一个`HTTP`或`HTTPS`请求。
2. `HTTP`方法是`POST`。
3. 内容类型是`application/x-www-form-urlencoded`。
4. 该`servlet`已经对请求对象的任意`getParameter`方法进行了初始调用。

如果不满足这些条件，而且参数集中不包括`post`表单数据，那么`servlet`必须可以通过请求对象的输入流得到`post`数据。如果满足这些条件，那么从请求对象的输入流中直接读取`post`数据将不再有效。

### 3.2 文件上传
当数据以`multipart/form-data`的格式发送时，`servlet`容器支持文件上传。

如果满足以下任何一个条件，`servlet`容器提供`multipart/form-data`格式数据的处理。

- servlet处理第8.1.5节，定义的注解“@MultipartConfig”标注的请求。
- 为了`servlet`处理请求，部署描述符包含了一个`multipart-config`元素。

请求中的`multipart/form-data`类型的数据如何可用，取决于`servlet`容器是否提供`multipart/form-data`格式数据的处理：

如果`servlet`容器提供`multipart/form-data`格式数据的处理，可通过`HttpServletRequest`中的以下方法得到：
- `public Collection getParts()`
- `public Part getPart(String name)`每个`part`都可通过`Part.getInputStream`方法访问头部，相关的内容类型和内容。对于表单数据的`Content-Disposition`，即使没有文件名，也可使用`part`的名称通过`HttpServletRequest`的`getParameter`和`getParameterValues`方法得到`part`的字符串值。

如果`servlet`的容器不提供`multi-part/form-data`格式数据的处理，这些数据将可通过`HttpServletReuqest.getInputStream`得到。

### 3.3 属性

属性是与请求相关联的对象。属性可以由容器设置来表达信息，否则无法通过`API`表示，或者由`servlet`设置将信息传达给另一个`servlet`（通过`RequestDispatcher`）。属性通过`ServletRequest`接口中下面的方法来访问：

- getAttribute
- getAttributeNames
- setAttribute

只有一个属性值可与一个属性名称相关联。

以前缀`java.`和`javax.`开头的属性名称是本规范的保留定义。同样地，以前缀`sun.`和`com.sun.`，`oracle`和`com.oracle`开头的属性名是`Oracle Corporation`的保留定义。建议属性集中所有属性的命名与Java编程语言的规范为包命名建议的反向域名约定一致。

### 3.4 头

通过下面的`HttpServletRequest`接口方法，`servlet`可以访问`HTTP`请求的头信息：

- getHeader
- getHeaders
- getHeaderNames

`getHeader`方法返回给定头名称的头。多个头可以具有相同的名称，例如`HTTP`请求中的`Cache-Control`头。如果多个头的名称相同，`getHeader`方法返回请求中的第一个头。`getHeaders`方法允许访问所有与特定头名称相关的头值，返回一个`String`对象的`Enumeration`（枚举）。 头可包含由`String`形式的`int`或`Date`数据。`HttpServletRequest`接口提供如下方便的方法访问这些类型的头数据：

- getIntHeader
- getDateHeader

如果`getIntHeader`方法不能转换为`int`的头值，则抛出`NumberFormatException`异常。如果`getDateHeader`方法不能把头转换成一个`Date`对象，则抛出`IllegalArgumentException`异常。

### 3.5请求路径元素

引导`servlet`服务请求的请求路径由许多重要部分组成。以下元素从请求`URI`路径得到，并通过请求对象公开：

- `Context Path`：与`ServletContext`相关联的路径前缀是这个`servlet`的一部分。如果这个上下文是基于`Web`服务器的`URL`命名空间基础上的“默认”上下文，那么这个路径将是一个空字符串。否则，如果上下文不是基于服务器的命名空间，那么这个路径以`/`字符开始，但不以`/`字符结束。
- `Servlet Path`：路径部分直接与激活请求的映射对应。这个路径以`/`字符开头，如果请求与`/*`或`“”`模式匹配，在这种情况下，它是一个空字符串。
- `PathInfo`：请求路径的一部分，不属于`Context Path`或`Servlet Path`。如果没有额外的路径，它要么是`null`，要么是以`/`开头的字符串。 使用`HttpServletRequest`接口中的下面方法来访问这些信息：

- getContextPath
- getServletPath
- getPathInfo
重要的是要注意，除了请求`URI`和路径部分的`URL`编码差异外，下面的等式永远为真：

```
requestURI = contextPath + servletPath + pathInfo
```

### 3.6 路径转换方法
在`API`中有两个方便的方法，允许开发者获得与某个特定的路径等价的文件系统路径。这些方法是：

- `ServletContext.getRealPath`
- `HttpServletRequest.getPathTranslated`

`getRealPath`方法需要一个`String`参数，并返回一个`String`形式的路径，这个路径对应一个在本地文件系统上的文件。`getPathTranslated`方法推断出请求的`pathInfo`的实际路径。这些方法在`servlet`容器无法确定一个有效的文件路径的情况下，如`Web`应用程序从归档中，在不能访问本地的远程文件系统上，或在一个数据库中执行时，这些方法必须返回`null`。`JAR`文件中`META-INF/resources`目录下的资源，只有当调用`getRealPath()`方法时才认为容器已经从包含它的`JAR`文件中解压，在这种情况下，必须返回解压缩后位置。

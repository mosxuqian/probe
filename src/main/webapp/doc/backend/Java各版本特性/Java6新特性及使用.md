# Java6新特性及使用

## 新特性列表

以下是Java6中的引入的部分新特性，相比Java5的新特性就少了很多了。关于Java6更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/features-141434.html)。

- Web Service
- Scripting

## 一、Web Service增强

`WebService`是一种独立于特定语言、特定平台，基于网络的、分布式的模块化组件。是一个能够使用`xml`消息通过网络来访问的接口，这个接口描述了一组可访问的操作。在Java6中，在想要发布为`WebService`的类上加上`@WebService`的注解，这个类的方法就变为`WebService`方法了，再通过`Endpoint.publish()`方法发布这个服务。到此，一个最简单的`WebService`搞定。运行`main`方法，在浏览器里输入`http://localhost:8080/com.thunisoft.sacweq.dataflow.organ.test.Hello?wsdl`，即可查看你WebService的WSDL信息。

```java
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello.
 * @author blinkfox on 2017-11-28.
 */
@WebService
public class Hello {

    private static final Logger log = LoggerFactory.getLogger(Hello.class);

    /**
     * sayHello.
     * @param name 名称
     * @return 结果
     */
    public String sayHello(String name) {
        return "Hello ".concat(name);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/com.thunisoft.sacweq.dataflow.organ.test.Hello", new Hello());
        log.info("调用成功!");
    }

}
```
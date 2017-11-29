# Java6新特性及使用

## 新特性列表

以下是Java6中的引入的部分新特性，相比Java5的新特性就少了很多了。关于Java6更详细的介绍可参考[这里](http://www.oracle.com/technetwork/java/javase/features-141434.html)。

- Web Service
- Scripting
- Compiler API

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

## 二、Scripting

Java6增加了对动态语言的支持，原理上是将脚本语言编译成字节码，这样脚本语言也能享用Java平台的诸多优势，包括可移植性，安全等。另外由于现在是编译成字节码后再执行，所以比原来边解释边执行效率要高很多。可以很好的利用脚本语言的动态特性，主要支持的有`JavaSrcipt`、`Ruby`、`Python`等。

以下使用`JavaScript`的脚本，代码示例如下：

```java
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsTest.
 * @author blinkfox
 * @version 1.0
 *
 */
public class JsTest {

    private static final Logger log = LoggerFactory.getLogger(Hello.class);

    /**
     * main方法.
     * @param args 数组参数
     * @throws ScriptException 脚本异常
     * @throws NoSuchMethodException 无方法异常
     */
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager enjineManager = new ScriptEngineManager();
        ScriptEngine engine = enjineManager.getEngineByName("JavaScript");

        String script="function hello(name){return 'Hello ' + name}";
        engine.eval(script);
        Invocable inv=(Invocable) engine;
        String result = (String) inv.invokeFunction("hello", "blinkfox");
        log.info("脚本执行结果:{}", result);
    }

}
```

## 三、Compiler API

在Java6中提供了一套`Compiler API`，定义在`JSR199`中, 提供在运行期动态编译java代码为字节码的功能。一套API就好比是在java程序中模拟javac程序，将Java源文件编译为class文件；其提供的默认实现也正是在文件系统上进行查找、编译工作的。`Compiler API`结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。

基本使用示例如下：

```java
public class JavaCompilerAPICompiler {

    public void compile(Path src, Path output) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(src.toFile());
            Iterable<String> options = Arrays.asList("-d", output.toString());
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
            boolean result = task.call();
        }
    }

}
```
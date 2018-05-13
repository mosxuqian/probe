# 使用Spring Aop来统计方法的执行时间

标签： Java

---

> 最近在做项目中的压力测试，发现有些方法执行很慢，所以打算写个统计某些方法执行耗时的日志输出功能。

## 一、解决方案

### 1、传统方法

最简单、粗暴的方法是给各个需要统计的方法开始和结尾处加的时间戳，然后差值计算结果即可，代码如下：

```java
long startTime = System.currentTimeMillis();

// 业务代码

long endTime = System.currentTimeMillis();
System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
```

这样的方式需要给很多统计方法都加上耗时时间的代码，这些代码与核心业务无关却大量重复、分散在各处，维护起来也困难。

### 2、面向切面编程的方法

所以，不推荐使用上面坏味道的代码。想了很久，打算利用`Spring aop`的思想来完成这个功能，废话不多说代码和相关的解释如下：

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 检测方法执行耗时的spring切面类
 * 使用@Aspect注解的类，Spring将会把它当作一个特殊的Bean（一个切面），也就是不对这个类本身进行动态代理
 * @author blinkfox
 * @date 2016-07-04
 */
@Aspect
@Component
public class TimeInterceptor {

	private static Log logger = LogFactory.getLog(TimeInterceptor.class);

	// 一分钟，即1000ms
	private static final long ONE_MINUTE = 1000;

	// service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
	public static final String POINT = "execution (* com.blinkfox.test.service.impl.*.*(..))";

	/**
	 * 统计方法执行耗时Around环绕通知
	 * @param joinPoint
	 * @return
	 */
	@Around(POINT)
	public Object timeAround(ProceedingJoinPoint joinPoint) {
		// 定义返回对象、得到方法需要的参数
		Object obj = null;
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();

		try {
			obj = joinPoint.proceed(args);
		} catch (Throwable e) {
			logger.error("统计某方法执行耗时环绕通知出错", e);
		}

		// 获取执行的方法名
		long endTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

		// 打印耗时的信息
		this.printExecTime(methodName, startTime, endTime);

		return obj;
	}

	/**
	 * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
	 * @param methodName
	 * @param startTime
	 * @param endTime
	 */
	private void printExecTime(String methodName, long startTime, long endTime) {
		long diffTime = endTime - startTime;
		if (diffTime > ONE_MINUTE) {
			logger.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
		}
	}

}
```

> **注意**：最后还需要在`applicationContext.xml`文件中加上AOP需要的配置`<aop:aspectj-autoproxy/>`，这样Spring才能识别到它。
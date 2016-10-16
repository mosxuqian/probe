# Spring 基础

## 一、Spring 概述

### （一）Spring 的简史

[Spring][1] 的历史网上有很多的介绍，下面是 Spring 发展历程的一个简介。

#### 1. 第一阶段：xml 配置

在 Spring 1.x 时代，使用 Spring 开发满眼都是 xml 配置的 Bean，随着项目的扩大，我们需要把 xml 配置文件放到不同的配置文件里，那时候需要频繁地在开发的类和配置文件之间切换。

#### 2. 第二阶段：注解配置

在 Spring 2.x 时代，随着 JDK 1.5 带来的注解支持，Spring 提供了声明 Bean 的注解（如：@Component、@Service），大大减少了配置量。这时 Spring 圈子里存在着一种争论：注解配置和 xml 配置究竟哪个更好？我们最终的选择是应用的基本配置（如：数据库配置）用 xml，业务配置用注解。

#### 3. 第三阶段：Java 配置

从 Spring 3.x 到现在，Spring 提供了 Java 配置的能力，使用 Java 配置可以让你更理解你配置的 Bean。我们目前刚好处于这个时代，Spring 4.x 和 Spring Boot 都推荐使用 Java 配置。

### （二）Spring 概述

Spring 框架是一个轻量级的企业级开发的一站式解决方案。所谓解决方案就是可以基于 Spring 解决 JavaEE 开发的所有问题。Spring 框架主要提供了 IoC 容器、AOP、数据访问、Web 开发、消息、测试等相关技术的支持。
Spring 使用简单的 POJO（Plain Old Java Object，即无任何限制的普通Java对象）来进行企业级开发。每一个被 Spring 管理的 Java 对象都被称之为 Bean；而 Spring 提供了一个 IoC 容器用来初始化对象，解决对象间的依赖管理和对象的使用。

[1]: https://spring.io/
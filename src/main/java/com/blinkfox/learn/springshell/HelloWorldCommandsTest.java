package com.blinkfox.learn.springshell;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

/**
 * HelloWorldCommandsTest.
 * Created by blinkfox on 2017/5/17.
 */
public class HelloWorldCommandsTest {

    private static JLineShellComponent shell;

    /**
     * 初始化操作.
     */
    @BeforeClass
    public static void startUp() {
        shell = new Bootstrap().getJLineShellComponent();
    }

    /**
     * 测试简单的情形.
     */
    @Test
    public void testSimple() {
        CommandResult result = shell.executeCommand("hw simple --message 你好");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("消息是：【你好】，位置是：【公司】", result.getResult());
    }

    /**
     * 测试简单的情形2.
     */
    @Test
    public void testSimple2() {
        CommandResult result = shell.executeCommand("hw simple --message 你好 --location");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("消息是：【你好】，位置是：【学校】", result.getResult());
    }

    /**
     * 测试简单的情形3.
     */
    @Test
    public void testSimple3() {
        CommandResult result = shell.executeCommand("hw simple --message 你好 --location 世界");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("消息是：【你好】，位置是：【世界】", result.getResult());
    }

    /**
     * 结束前的操作.
     */
    @AfterClass
    public static void shutdown() {
        shell.stop();
    }

}
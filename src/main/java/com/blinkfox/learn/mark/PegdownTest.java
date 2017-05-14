package com.blinkfox.learn.mark;

import org.pegdown.PegDownProcessor;
import org.pmw.tinylog.Logger;

/**
 * PegdownTest.
 * Created by blinkfox on 2017/5/14.
 */
public class PegdownTest {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        PegDownProcessor peg = new PegDownProcessor();
        String html = peg.markdownToHtml("### name");
        Logger.info("html:{}", html);
    }

}
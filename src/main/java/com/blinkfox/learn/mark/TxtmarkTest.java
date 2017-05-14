package com.blinkfox.learn.mark;

import com.github.rjeschke.txtmark.Processor;
import java.io.File;
import java.io.IOException;
import org.pmw.tinylog.Logger;

/**
 * TxtmarkTest.
 * Created by blinkfox on 2017/5/14.
 */
public class TxtmarkTest {

    /**
     * main方法.
     * @param args 数组参数.
     */
    public static void main(String[] args) {
        String result = Processor.process("This is **TXTMARK**");
        Logger.info("result:{}", result);

        try {
            String fileName = "/Users/blinkfox/Documents/dev/gitrepo/"
                    + "probe/src/main/webapp/doc/interview/Maven相关考题.md";
            String html = Processor.process(new File(fileName));
            Logger.info("result html:{}", html);
        } catch (IOException e) {
            Logger.error(e, "生成html出错!");
        }
    }

}
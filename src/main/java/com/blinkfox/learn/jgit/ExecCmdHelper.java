package com.blinkfox.learn.jgit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.pmw.tinylog.Logger;

/**
 * 使用Java代码执行并获取命令行的结果.
 * Created by blinkfox on 2017-03-21.
 */
public class ExecCmdHelper {

    /**
     * 私有构造方法.
     */
    private ExecCmdHelper() {
        super();
    }

    /**
     * 执行命令行程序.
     * @param cmdStr 命令行的字符串
     */
    public static String execCmd(String cmdStr) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder("");

        try {
            Process p = Runtime.getRuntime().exec(cmdStr);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();
        } catch (IOException e) {
            Logger.error(e, "执行命令行程序出错！");
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Logger.error(e, "关闭BufferedReader失败！");
                }
            }
        }

        return sb.toString();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        String cmdStr = "git config --global user.email";
        Logger.info("命令行执行结果：{}", execCmd(cmdStr));
    }

}

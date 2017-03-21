package com.blinkfox.learn.javafx.archon.helpers;

import java.io.File;
import org.pmw.tinylog.Logger;

/**
 * File文件操作相关的工具类.
 * Created by blinkfox on 2017-03-21.
 */
public final class FileHelper {

    /**
     * 私有构造方法.
     */
    private FileHelper() {
        super();
    }

    /**
     * 创建多级目录,并返回是否创建成功的结果.
     * @param dirPath 目录路径
     * @return 布尔
     */
    public static boolean makeDirs(String dirPath) {
        boolean isSucc = false;
        try {
            File file = new File(dirPath);
            file.mkdirs();
            isSucc = true;
        } catch (Exception e) {
            Logger.error(e, "创建多级目录失败！");
        }

        return isSucc;
    }

}

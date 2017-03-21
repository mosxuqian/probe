package com.blinkfox.learn.javafx.archon.helpers;

import java.io.File;
import javafx.stage.DirectoryChooser;

/**
 * 窗口操作相关的工具类.
 * Created by blinkfox on 2017-03-21.
 */
public class DialogHelper {

    /**
     * 私有构造方法.
     */
    private DialogHelper() {
        super();
    }

    /**
     * 创建目录选择框.
     * @return DirectoryChooser
     */
    public static DirectoryChooser createDirChooser() {
        return createDirChooser(null);
    }

    /**
     * 创建指定目录的目录选择框.
     * @param dirPath 目录绝对路径
     * @return DirectoryChooser
     */
    public static DirectoryChooser createDirChooser(String dirPath) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("请选择一个您Git仓库的默认工作目录");
        String filePath = (dirPath == null || dirPath.isEmpty()) ? System.getProperty("user.home") : dirPath;
        dirChooser.setInitialDirectory(new File(filePath));
        return dirChooser;
    }

}

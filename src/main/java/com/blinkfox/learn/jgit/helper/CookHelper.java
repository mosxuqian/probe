package com.blinkfox.learn.jgit.helper;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.pmw.tinylog.Logger;

/**
 * CookHelper.
 * Created by blinkfox on 2017/5/13.
 */
public final class CookHelper {

    public static final String GIT_DIR = "/Users/blinkfox/Downloads/gittest/";

    /**
     * 私有构造方法.
     */
    private CookHelper() {
        super();
    }

    /**
     * 创建一个新的仓库.
     * @param fileName 新仓库名
     * @return repo
     */
    public static Repository createNewRepo(String fileName) {
        File file = new File(GIT_DIR.concat(fileName));
        try (Repository repo = FileRepositoryBuilder.create(new File(file, ".git"))) {
            repo.create();
            Logger.info("创建仓库'{}'成功，路径为:{}", fileName, file.getAbsolutePath());
            return repo;
        } catch (IOException e) {
            Logger.error(e, "创建新仓库出错！");
            return null;
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        createNewRepo("hello");
    }

}
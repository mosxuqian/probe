package com.blinkfox.learn.jgit;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.pmw.tinylog.Logger;

/**
 * 创建一个新的git仓库的示例代码.
 * Created by blinkfox on 2017/3/21.
 */
public class CreateNewRepo {

    /**
     * 私有构造方法.
     */
    private CreateNewRepo() {
        super();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(final String[] args) {
        Logger.info("开始创建新的git仓库");
        String repo = "/Users/blinkfox/Downloads/test/mynewrepo/.git";

        Repository newRepo = null;
        try {
            newRepo = FileRepositoryBuilder.create(new File(repo));
            newRepo.create();
        } catch (IOException e) {
            Logger.error(e, "创建新的git仓库出错！");
        } finally {
            if (newRepo != null) {
                newRepo.close();
            }
        }
    }

}

package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.pmw.tinylog.Logger;

/**
 * 打开仓库的示例.
 * Created by blinkfox on 2017/5/13.
 */
public class CreateRepo {

    private static File createSampleGitRepo(String fileName) {
        try (Repository repo = CookHelper.createNewRepo(fileName)) {
            if (repo == null) {
                return null;
            }

            // 添加文件和提交
            try (Git git = new Git(repo)) {
                git.add().addFilepattern("README.md").call();
                git.commit().setMessage("第一次提交了file1的文件").call();
                Logger.info("成功添加并提交了file1的文件");
            } catch (GitAPIException e) {
                Logger.error(e, "添加并提交file1文件出错！");
            }

            return repo.getDirectory();
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // 创建一个名为hello的git仓库.
        File repoDir = createSampleGitRepo("hello");

        // 寻找并打开仓库，读取head
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try (Repository repo = builder.setGitDir(repoDir).readEnvironment().findGitDir().build()) {
            Logger.info("找到了Git仓库:{}", repo.getDirectory());

            Ref head = repo.exactRef("refs/heads/master");
            Logger.info("refs/heads/master为:{}", head);
        } catch (IOException e) {
            Logger.error(e, "打开仓库出错！");
        }
    }

}
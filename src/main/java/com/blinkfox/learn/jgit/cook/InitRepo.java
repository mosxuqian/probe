package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;
import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.pmw.tinylog.Logger;

/**
 * 初始化仓库.
 * Created by blinkfox on 2017/5/13.
 */
public class InitRepo {

    public static void main(String[] args) {
        String repoName = "world";
        File dir = new File(CookHelper.GIT_DIR.concat(repoName));
        try (Git git = Git.init().setDirectory(dir).call()) {
            git.add().addFilepattern("test.md").call();
            Status status = git.status().call();
            Logger.info("初始化仓库并添加了test.md文件到暂存区!,status:{}", status);
        } catch (GitAPIException e) {
            Logger.error(e, "初始化仓库失败！");
        }
    }

}
package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.IOException;

/**
 * OpenRepo.
 * Created by blinkfox on 2017/5/13.
 */
public class OpenRepo {

    /**
     * 提交单个文件.
     * @param dir 仓库目录
     */
    public static void commitFile(File dir) {
        try (Git git = Git.open(dir)) {
            git.add().addFilepattern("testaaa.md").call();
            Status status = git.status().call();
            git.commit().setMessage("第一次提交testaaa.md文件").call();
            Logger.info("查看状态status:{}", status);
        } catch (Exception e) {
            Logger.error(e, "第一次提交失败！");
        }
    }

    /**
     * 提交所有修改.
     * @param dir 仓库目录
     */
    public static void commitAllFiles(File dir) {
        try (Git git = Git.open(dir)) {
            // 添加所有代码到暂存区
            git.add().addFilepattern(".").call();

            // 提交所有暂存区的文件
            git.commit().setAll(true).setMessage("提交所有文件").call();
            Status status = git.status().call();
            Logger.info("提交所有文件成功，查看状态status:{}", status);
        } catch (Exception e) {
            Logger.error(e, "提交所有文件失败！");
        }
    }

    public static void main(String[] args) {
        String repoName = "world";
        File dir = new File(CookHelper.GIT_DIR.concat(repoName));
        commitAllFiles(dir);
    }

}
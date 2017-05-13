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

    public static void main(String[] args) {
        String repoName = "world";
        File dir = new File(CookHelper.GIT_DIR.concat(repoName));
        try (Git git = Git.open(dir)) {
            Status status = git.status().call();
            Logger.info("查看状态status:{}", status);
        } catch (Exception e) {
            Logger.error(e, "查看git仓库状态失败！");
        }
    }

}
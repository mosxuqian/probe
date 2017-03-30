package com.blinkfox.learn.jgit;

import com.blinkfox.test.other.TimeUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.pmw.tinylog.Logger;

/**
 * jGit获取历史记录的测试示例.
 * Created by blinkfox on 2017/3/28.
 */
public class JgitHistoryTest {

    /**
     * 私有构造方法.
     */
    private JgitHistoryTest() {
        super();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) throws Exception {
        gitLog();
    }

    /**
     * 使用revWalk方法.
     */
    private static void gitLog() throws Exception {
        Git git = Git.open(new File("F:\\gitrepo\\probe"));
        Iterable<RevCommit> commits = git.log().call();
        for (RevCommit commit: commits) {
            Logger.info("短提交信息：{}；全提交信息：{}；作者：{}<{}>；时间：{}；提交：{}",
                    commit.getShortMessage(), commit.getFullMessage(), commit.getAuthorIdent().getName(),
                    commit.getAuthorIdent().getEmailAddress(), TimeUtils.timeToStr(commit.getCommitTime()),
                    commit.getName().substring(0, 7));
        }
        git.close();
    }

    /**
     * 使用revWalk方法.
     */
    private static void revWalk() throws Exception {
        Repository repo;
        RevWalk revWalk;
        ObjectId commitId;
        try (Git git = Git.open(new File("/Users/blinkfox/Documents/dev/gitrepo/carrier"))) {
            repo = git.getRepository();
        }
        revWalk = new RevWalk(repo);

        commitId = repo.resolve("refs/heads/master");
        revWalk.markStart(revWalk.parseCommit(commitId));
        for (RevCommit commit : revWalk) {
            Logger.info("提交信息：{}", commit.getFullMessage());
        }
    }

}
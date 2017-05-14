package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;
import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.pmw.tinylog.Logger;

/**
 * 列出所有提交.
 * Created by blinkfox on 2017/5/14.
 */
public class ListInfos {

    /**
     * 列出所有提交.
     * @param repoDir 仓库目录
     */
    private static void showLogs(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            // 当前分支所有提交
            Iterable<RevCommit> logs = git.log().call();
            int count = 0;
            for (RevCommit commit: logs) {
                Logger.info("Commit author:{}, email:{}, time:{}, msg:{} ", commit.getAuthorIdent().getName(),
                        commit.getAuthorIdent().getEmailAddress(), commit.getCommitTime(), commit.getShortMessage());
                count++;
            }
            Logger.info("当前分支共:{}次提交.", count);

            // 当前分支bbb.html文件所有提交
            count = 0;
            Iterable<RevCommit> bbbLogs = git.log().addPath("bbb.html").call();
            for (RevCommit commit: bbbLogs) {
                Logger.info("Commit author:{}, email:{}, time:{}, msg:{} ", commit.getAuthorIdent().getName(),
                        commit.getAuthorIdent().getEmailAddress(), commit.getCommitTime(), commit.getShortMessage());
                count++;
            }
            Logger.info("当前分支bbb.html文件共:{}次提交.", count);

            Logger.info("查看所有提交信息成功");
        } catch (Exception e) {
            Logger.error(e, "查看所有提交信息失败！");
        }
    }

    /**
     * 列出所有标签tag.
     * @param repoDir 仓库目录
     */
    private static void showTags(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            List<Ref> tags = git.tagList().call();
            for (Ref ref: tags) {
                Logger.info("Tag:{}, {}", ref.getName(), ref.getObjectId().getName());
            }
            Logger.info("查看所有tags成功");
        } catch (Exception e) {
            Logger.error(e, "查看所有tags失败！");
        }
    }

    /**
     * 列出所有分支branchs.
     * @param repoDir 仓库目录
     */
    private static void showBranchs(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            // 列出本地所有分支
            List<Ref> brs = git.branchList().call();
            for (Ref ref: brs) {
                Logger.info("Tag:{}, {}", ref.getName(), ref.getObjectId().getName());
            }

            // 列出所有分支，包括远程分支
            List<Ref> allBrs = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            for (Ref ref: allBrs) {
                Logger.info("Tag:{}, {}", ref.getName(), ref.getObjectId().getName());
            }
            Logger.info("查看所有分支成功");
        } catch (Exception e) {
            Logger.error(e, "查看所有分支失败！");
        }
    }

    /**
     * revert changes.
     * @param repoDir 仓库目录
     */
    private static void revertChanges(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            git.checkout().addPath("bbb.html").call();
            Logger.info("revert成功");
        } catch (Exception e) {
            Logger.error(e, "revert失败！");
        }
    }

    /**
     * showRemotes.
     * @param repoDir 仓库目录
     */
    private static void showRemotes(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            Config storedConfig = git.getRepository().getConfig();
            Set<String> remotes = storedConfig.getSubsections("remote");
            for (String remoteName : remotes) {
                String url = storedConfig.getString("remote", remoteName, "url");
                Logger.info("remoteName:{}, url:{}", remoteName, url);
            }
            Logger.info("显示所有remotes成功");
        } catch (Exception e) {
            Logger.error(e, "显示所有remotes失败！");
        }
    }

    /**
     * 列出用户信息.
     * @param repoDir 仓库目录
     */
    private static void showUserInfos(File repoDir) {
        try (Git git = Git.open(repoDir)) {
            Config storedConfig = git.getRepository().getConfig();
            String name = storedConfig.getString("user", null, "name");
            String email = storedConfig.getString("user", null, "email");

            // 判断用户、邮箱
            if (name == null || email == null) {
                Logger.info("用户信息未知!");
            } else {
                Logger.info("用户信息name:{}, email:{}", name, email);
            }
            Logger.info("显示用户信息成功!");
        } catch (Exception e) {
            Logger.error(e, "显示用户信息失败！");
        }
    }

    /**
     * main方法.
     * @param args args
     */
    public static void main(String[] args) {
        String repoName = "world";
        File repoDir = new File(CookHelper.GIT_DIR.concat(repoName));
        showLogs(repoDir);
        showTags(repoDir);
        showBranchs(repoDir);
        // revertChanges(repoDir);

        File repoDir2 = new File(CookHelper.GIT_PERSON_DIR);
        showRemotes(repoDir2);
        showUserInfos(repoDir2);
        showUserInfos(repoDir);
    }

}
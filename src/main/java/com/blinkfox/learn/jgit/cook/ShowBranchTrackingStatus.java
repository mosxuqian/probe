package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.pmw.tinylog.Logger;

/**
 * ShowBranchTrackingStatus.
 * Created by blinkfox on 2017/5/14.
 */
public class ShowBranchTrackingStatus {

    /**
     * main方法.
     * @param args 数组参数
     * @throws IOException 异常
     */
    public static void main(String[] args) throws IOException, GitAPIException {
        File repoDir = new File(CookHelper.GIT_PERSON_DIR);
        try (Git git = Git.open(repoDir)) {
            Repository repository = git.getRepository();
            List<Ref> call = git.branchList().call();
            for (Ref ref : call) {
                List<Integer> counts = getCounts(repository, ref.getName());
                Logger.info("For branch: " + ref.getName());
                Logger.info("Commits ahead : " + counts.get(0));
                Logger.info("Commits behind : " + counts.get(1));
            }
        }
    }

    private static List<Integer> getCounts(Repository repository, String branchName) throws IOException {
        BranchTrackingStatus trackingStatus = BranchTrackingStatus.of(repository, branchName);
        List<Integer> counts = new ArrayList<>();
        if (trackingStatus != null) {
            counts.add(trackingStatus.getAheadCount());
            counts.add(trackingStatus.getBehindCount());
        } else {
            Logger.info("返回null，可能没有远程跟踪分支,分支名: {}", branchName);
            counts.add(0);
            counts.add(0);
        }
        return counts;
    }

}
package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.pmw.tinylog.Logger;

/**
 * ReadBlobContents.
 * Created by blinkfox on 2017/5/14.
 */
public class ReadBlobContents {

    /**
     * main方法.
     * @param args 数组参数
     * @throws IOException 异常
     */
    public static void main(String[] args) throws IOException {
        File repoDir = new File(CookHelper.GIT_PERSON_DIR);
        try (Git git = Git.open(repoDir)) {
            Repository repository = git.getRepository();
            // the Ref holds an ObjectId for any type of object (tree, commit, blob, tree)
            Ref head = repository.exactRef("refs/heads/master");
            Logger.info("Ref of refs/heads/master: " + head);

            Logger.info("\nPrint contents of head of master branch, i.e. the latest commit information");
            ObjectLoader loader = repository.open(head.getObjectId());
            loader.copyTo(System.out);

            Logger.info("\nPrint contents of tree of head of master branch, i.e. the latest binary tree information");

            // a commit points to a tree
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                RevTree tree = walk.parseTree(commit.getTree().getId());
                Logger.info("Found Tree: " + tree);
                loader = repository.open(tree.getId());
                Logger.info("start System.out...");
                loader.copyTo(System.out);

                walk.dispose();
            }
        }
    }

}
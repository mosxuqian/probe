package com.blinkfox.learn.jgit.cook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.blinkfox.learn.jgit.helper.CookHelper;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.pmw.tinylog.Logger;

/**
 * ListFilesOfCommitAndTag.
 * Created by blinkfox on 2017/5/14.
 */
public class ListFilesOfCommitAndTag {

    public static void main(String[] args) throws IOException {
        File repoDir = new File(CookHelper.GIT_PERSON_DIR);
        try (Git git = Git.open(repoDir)) {
            Repository repository = git.getRepository();
            List<String> paths = readElementsAt(repository, "ae6ca41d48b5a86b046ef931f84a66800529bbc4",
                    "src/main/java/com/xjy/person/");

            Logger.info("Had paths for commit: {}", paths);

            final ObjectId testbranch = repository.resolve("master");
            paths = readElementsAt(repository, testbranch.getName(), "");

            Logger.info("Had paths for tag: {}", paths);
        }
    }

    private static List<String> readElementsAt(Repository repository, String commit, String path) throws IOException {
        RevCommit revCommit = buildRevCommit(repository, commit);

        // and using commit's tree find the path
        RevTree tree = revCommit.getTree();
        //System.out.println("Having tree: " + tree + " for commit " + commit);

        List<String> items = new ArrayList<>();

        // shortcut for root-path
        if (path == null || path.isEmpty()) {
            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(tree);
                treeWalk.setRecursive(false);
                treeWalk.setPostOrderTraversal(false);

                while (treeWalk.next()) {
                    items.add(treeWalk.getPathString());
                }
            }
        } else {
            // now try to find a specific file
            try (TreeWalk treeWalk = buildTreeWalk(repository, tree, path)) {
                if ((treeWalk.getFileMode(0).getBits() & FileMode.TYPE_TREE) == 0) {
                    throw new IllegalStateException("Tried to read the elements of a non-tree for commit '" + commit + "' and path '" + path + "', had filemode " + treeWalk.getFileMode(0).getBits());
                }

                try (TreeWalk dirWalk = new TreeWalk(repository)) {
                    dirWalk.addTree(treeWalk.getObjectId(0));
                    dirWalk.setRecursive(false);
                    while (dirWalk.next()) {
                        items.add(dirWalk.getPathString());
                    }
                }
            }
        }

        return items;
    }

    private static RevCommit buildRevCommit(Repository repository, String commit) throws IOException {
        // a RevWalk allows to walk over commits based on some filtering that is defined
        try (RevWalk revWalk = new RevWalk(repository)) {
            return revWalk.parseCommit(ObjectId.fromString(commit));
        }
    }

    private static TreeWalk buildTreeWalk(Repository repository, RevTree tree, final String path) throws IOException {
        TreeWalk treeWalk = TreeWalk.forPath(repository, path, tree);

        if (treeWalk == null) {
            throw new FileNotFoundException("Did not find expected file '" + path + "' in tree '" + tree.getName() + "'");
        }

        return treeWalk;
    }

}
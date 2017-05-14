package com.blinkfox.learn.jgit.cook;

import com.blinkfox.learn.jgit.helper.CookHelper;
import java.io.File;
import java.io.IOException;

import com.blinkfox.test.other.TimeUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.pmw.tinylog.Logger;

/**
 * FileAttributes.
 * Created by blinkfox on 2017/5/14.
 */
public class FileAttributes {

    /**
     * main方法.
     * @param args 数组参数
     * @throws IOException 异常
     */
    public static void main(String[] args) throws IOException {
        File repoDir = new File(CookHelper.GIT_PERSON_DIR);
        try (Git git = Git.open(repoDir)) {
            Repository repository = git.getRepository();

            // find the Tree for current HEAD
            RevTree tree = getTree(repository);
            printFile(repository, tree);
            printDirectory(repository, tree);
        }
    }

    private static RevTree getTree(Repository repository) throws IOException {
        ObjectId lastCommitId = repository.resolve(Constants.HEAD);

        // a RevWalk allows to walk over commits based on some filtering
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit commit = revWalk.parseCommit(lastCommitId);
            Logger.info("Time of commit (seconds since epoch): {}",
                    TimeUtils.timeToStr(commit.getCommitTime()));

            // and using commit's tree find the path
            RevTree tree = commit.getTree();
            Logger.info("Having tree: {}", tree);
            return tree;
        }
    }

    private static void printFile(Repository repository, RevTree tree) throws IOException {
        // now try to find a specific file
        try (TreeWalk treeWalk = new TreeWalk(repository)) {
            treeWalk.addTree(tree);
            treeWalk.setRecursive(false);
            treeWalk.setFilter(PathFilter.create("README.md"));
            if (!treeWalk.next()) {
                throw new IllegalStateException("Did not find expected file 'README.md'");
            }

            // FileMode specifies the type of file, FileMode.REGULAR_FILE for normal file, FileMode.EXECUTABLE_FILE for executable bit
            // set
            FileMode fileMode = treeWalk.getFileMode(0);
            ObjectLoader loader = repository.open(treeWalk.getObjectId(0));
            Logger.info("README.md: {}, type: {}, mode: {}, size: {}", getFileMode(fileMode),
                    fileMode.getObjectType(), fileMode, loader.getSize());
        }
    }

    private static void printDirectory(Repository repository, RevTree tree) throws IOException {
        // look at directory, this has FileMode.TREE
        try (TreeWalk treeWalk = new TreeWalk(repository)) {
            treeWalk.addTree(tree);
            treeWalk.setRecursive(false);
            treeWalk.setFilter(PathFilter.create("src"));
            if (!treeWalk.next()) {
                throw new IllegalStateException("Did not find expected file 'README.md'");
            }

            // FileMode now indicates that this is a directory, i.e. FileMode.TREE.equals(fileMode) holds true
            FileMode fileMode = treeWalk.getFileMode(0);
            Logger.info("src: {}, type: {}, mode: {}", getFileMode(fileMode),
                    fileMode.getObjectType(), fileMode);
        }
    }

    private static String getFileMode(FileMode fileMode) {
        if (fileMode.equals(FileMode.EXECUTABLE_FILE)) {
            return "Executable File";
        } else if (fileMode.equals(FileMode.REGULAR_FILE)) {
            return "Normal File";
        } else if (fileMode.equals(FileMode.TREE)) {
            return "Directory";
        } else if (fileMode.equals(FileMode.SYMLINK)) {
            return "Symlink";
        } else {
            // there are a few others, see FileMode javadoc for details
            throw new IllegalArgumentException("Unknown type of file encountered: " + fileMode);
        }
    }

}
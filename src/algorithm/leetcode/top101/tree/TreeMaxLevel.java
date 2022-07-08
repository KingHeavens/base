package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.HashMap;

public class TreeMaxLevel {
    public static void main(String[] args) {
        TreeNode treeNode = Test.buildTree();
        Test.println("树的最大高度:" + maxLevel(treeNode));
    }

    public static int maxLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxLevel(root.left), maxLevel(root.right)) + 1;
    }
}

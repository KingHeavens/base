package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 *  合并两颗二叉树
 */
public class MergeTree {
    public static TreeNode mergeTree(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.value + root2.value);
        root.left = mergeTree(root1.left, root2.left);
        root.right = mergeTree(root1.right, root2.right);
        return root;
    }
}

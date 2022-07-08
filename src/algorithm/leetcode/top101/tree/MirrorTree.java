package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 * 生成镜像二叉树
 */
public class MirrorTree {
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = left;
        return root;
    }
}

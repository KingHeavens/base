package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 *  是否是镜像二叉树
 */
public class IsSymmetryTree {
    public static boolean isMirror(TreeNode node) {
        if (node == null) {
            return true;
        }
        return isMirror(node, node);
    }

    public static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.value != right.value) {
            return false;
        }
        return isMirror(left, right) && isMirror(right, left);
    }
}

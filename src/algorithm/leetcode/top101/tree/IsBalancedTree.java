package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 *  判读是不是平衡二叉树
 *  平衡二叉树 左右节点相差不超过1
 */
public class IsBalancedTree {
    public static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int maxLeft = maxDeep(root.left);
        int maxRight = maxDeep(root.right);
        if (Math.abs(maxLeft - maxRight) > 1) {
            return false;
        }
        return isBalancedTree(root.left) && isBalancedTree(root.right);
    }

    public static int maxDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDeep(root.left), maxDeep(root.right)) + 1;
    }
}

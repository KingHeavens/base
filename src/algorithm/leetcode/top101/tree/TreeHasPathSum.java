package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 * 树种找到根结点到叶子节点的路径和
 */
public class TreeHasPathSum {
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum - root.value == 0) {
            return true;
        }
        return hasPathSum(root.left, sum - root.value)
                || hasPathSum(root.right, sum - root.value);
    }
}

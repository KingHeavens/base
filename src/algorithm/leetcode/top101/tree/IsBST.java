package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 * 判断是不是搜索二叉树
 */
public class IsBST {
    public static int pre = Integer.MIN_VALUE;
    public static boolean isBst(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isBst(root.left)) {
            return false;
        }
        if (pre > root.value) {
            return false;
        }
        pre = root.value;
        return isBst(root.right);
    }
}

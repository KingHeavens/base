package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 * 最近公共祖先
 */
public class LowestCommonAncestor {
    public static int lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        if (root.value > p && root.value < q) {
            return root.value;
        } else if (root.value > q && root.value < p) {
            return root.value;
        } else if (root.value > p && root.value > q) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return root;
        }
        TreeNode leftCommon = lowestCommonAncestor(root.left, p, q);
        TreeNode rightCommon = lowestCommonAncestor(root.right, p, q);
        if (leftCommon != null && rightCommon != null) {
            return root;
        }
        return leftCommon == null ? rightCommon : leftCommon;
    }

    public static int lowestCommonAncestor2(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        if (root.value == p || root.value == q) {
            return root.value;
        }
        int left = lowestCommonAncestor2(root.left, p, q);
        int right = lowestCommonAncestor2(root.right, p, q);
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        return root.value;
    }

}

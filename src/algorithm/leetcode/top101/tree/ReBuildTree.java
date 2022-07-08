package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

import java.util.Arrays;

/**
 * 重建二叉树
 * 根据前序遍历 中序遍历 重建二叉树
 *
 */
public class ReBuildTree {
    public static TreeNode reBuildTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reBuildTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                root.right = reBuildTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}

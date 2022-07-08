package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

/**
 *  将二叉树转换成双向链表的结构，返回中序遍历链表的头节点
 */
public class TreeToDLinked {
    public static TreeNode treeToDLinked(TreeNode root) {
        return process(root).start;
    }

    private static ReturnData process(TreeNode root) {
        if (root == null) {
            return new ReturnData(null, null);
        }
        ReturnData left = process(root.left);
        ReturnData right = process(root.right);
        if (left.end != null) {
            left.end.right = root;
        }
        root.left = left.end;
        root.right = right.start;
        if (right.start != null) {
            right.start.left = root;
        }
        return new ReturnData(left.start == null ? root : left.start
                , right.end == null ? root : right.end);
    }

    static class ReturnData {
        TreeNode start;
        TreeNode end;
        ReturnData(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }
}

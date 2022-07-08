package algorithm.leetcode.top101.tree;

import algorithm.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判读是不是完全二叉树
 * 层序遍历
 */
public class IsCBT {
    public static boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasEmptyNode = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                hasEmptyNode = true;
                continue;
            }
            if (!hasEmptyNode) {
                return false;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return true;
    }
}

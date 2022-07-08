package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.*;

/**
 * 重构二叉树，打印右视图
 */
public class ReBuildTreeAndGetRightNode {
    public static void main(String[] args) {
        int pre[] = new int[] { 1,2,4,5,3 };
        int in[] = new int[] { 4,2,5,1,3 };
        int[] res = getRightNode(pre, in);
        Test.printArray(res);
    }

    public static int[] getRightNode(int[] pre, int[] in) {
        TreeNode root = buildTree(pre, in);
        List<Integer> rightNodes = getRightNode(root);
        if (rightNodes == null) {
            return new int[0];
        }
        int[] res = new int[rightNodes.size()];
        for (int i = 0; i < rightNodes.size(); i++) {
            res[i] = rightNodes.get(i);
        }
        return res;
    }

    private static List<Integer> getRightNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    size--;
                    if (size == 0) {
                        res.add(node.value);
                    }
                }
            }
        }
        return res;
    }

    private static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null
                || pre.length < 1 || in.length < 1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                head.left = buildTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                head.right = buildTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return head;
    }
}

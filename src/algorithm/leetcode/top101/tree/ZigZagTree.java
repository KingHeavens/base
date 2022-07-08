package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 层序遍历树，先从左往右，下一行 从右往左
 */
public class ZigZagTree {
    public static void main(String[] args) {
        TreeNode node = Test.buildTree();
        ArrayList<ArrayList<Integer>> res = zigZagTraversalTree(node);
        for (ArrayList<Integer> list : res) {
            for (Integer num : list) {
                System.out.print(num + ",");
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> zigZagTraversalTree(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> row = new ArrayList<>();
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    row.add(node.value);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            if (reverse) {
                Collections.reverse(row);
            }
            reverse = !reverse;
            res.add(row);
        }
        return res;
    }
}

package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeLevelTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = Test.buildTree();
        ArrayList<ArrayList<Integer>> res = levelTraversal(treeNode);
        for (ArrayList<Integer> row : res) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            Test.println("");
        }
    }
    public static ArrayList<ArrayList<Integer>> levelTraversal(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                root = queue.poll();
                row.add(root.value);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            res.add(row);
        }
        return res;
    }
}

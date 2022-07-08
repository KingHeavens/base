package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializationTree {
    public static void main(String[] args) {
        TreeNode head = Test.buildTree();
        String serialStr = serializationTree(head);
        Test.println(serialStr);
        TreeNode node = deSerializationTree(serialStr);
    }
    public static String serializationTree(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String cur = root.value + "_";
        String left = serializationTree(root.left);
        String right = serializationTree(root.right);
        return cur + left + right;
    }

    public static TreeNode deSerializationTree(String serialStr) {
        if (serialStr == null) {
            return null;
        }
        String[] nodeStr = serialStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (String s : nodeStr) {
            queue.offer(s);
        }
        return deSerial(queue);
    }

    private static TreeNode deSerial(Queue<String> queue) {
        String nodeStr = queue.poll();
        if ("#".equals(nodeStr)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodeStr));
        root.left = deSerial(queue);
        root.right = deSerial(queue);
        return root;
    }
}

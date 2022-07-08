package algorithm.leetcode.top101.tree;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = Test.buildTree();
        int[] array = postOrderTraversal3(root);
        Test.printArray(array);
    }

    public static int[] preOrderTraversal2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).value;
        }
        return res;
    }

    public static int[] preOrderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        list = preOrder(list, root);
        if (list == null) {
            return null;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static int[] inOrderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        list = inOrder(list, root);
        if (list == null) {
            return null;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static int[] inOrderTraversal2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root);
                root = root.right;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).value;
        }
        return res;
    }

    public static int[] postOrderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        list = postOrder(list, root);
        if (list == null) {
            return null;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static int[] postOrderTraversal2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);
            if (root.left != null) {
                stack1.push(root.left);
            }
            if (root.right != null) {
                stack1.push(root.right);
            }
        }

        int size = stack2.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack2.pop().value;
        }
        return res;
    }

    public static int[] postOrderTraversal3(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (cur.left != null && cur.left != root && cur.right != root) {
                stack.push(cur.left);
            } else if (cur.right != null && cur.right != root) {
                stack.push(cur.right);
            } else {
                list.add(stack.pop());
                root = cur;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).value;
        }
        return res;
    }

    public static ArrayList<Integer> preOrder(ArrayList<Integer> list, TreeNode root) {
        if (root == null) {
            return list;
        }
        if (list != null) {
            list.add(root.value);
        }
        preOrder(list, root.left);
        preOrder(list, root.right);
        return list;
    }

    public static ArrayList<Integer> inOrder(ArrayList<Integer> list, TreeNode root) {
        if (root == null) {
            return list;
        }
        inOrder(list, root.left);
        if (list != null) {
            list.add(root.value);
        }
        inOrder(list, root.right);
        return list;
    }

    public static ArrayList<Integer> postOrder(ArrayList<Integer> list, TreeNode root) {
        if (root == null) {
            return list;
        }
        postOrder(list, root.left);
        postOrder(list, root.right);
        if (list != null) {
            list.add(root.value);
        }
        return list;
    }
}

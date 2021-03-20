package algorithm.tree;


import algorithm.tree.node.TNode;

/**
 * 树相关测试帮助类
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/14
 **/
public class TreeTestHelper {
    /**
     * 生成一颗完全二叉树
     *
     * @param arr arr
     * @return root node
     */
    public static TNode createBinaryTree(Integer[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        return createBinaryTree(null, 0, arr);
    }

    private static TNode createBinaryTree(TNode root, int i, Integer[] arr) {
        if (i > arr.length - 1 || arr[i] == null) {
            return null;
        }
        if (root == null) {
            root = new TNode(arr[i]);
        }
        root.left = createBinaryTree(root.left, 2 * i + 1, arr);
        root.right = createBinaryTree(root.right, 2 * i + 2, arr);
        if (root.left != null) {
            root.left.parent = root;
        }
        if (root.right != null) {
            root.right.parent = root;
        }
        return root;
    }
}


package algorithm.tree;

import algorithm.tree.node.TNode;
import algorithm.tree.utils.TreeUtils;
import test.Printer;

/**
 * 二叉树遍历
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/14
 **/
@SuppressWarnings("WeakerAccess")
public class BTreeTraverse {
    public void traverse(TNode node) {
        if (node == null) {
            Printer.println("null");
            return;
        }
        Printer.println("pre:" + node.value);
        traverse(node.left);
        Printer.println("in:" + node.value);
        traverse(node.right);
        Printer.println("post" + node.value);
    }

    public void preTraverse(TNode root) {
        if (root == null) {
            return;
        }
        Printer.println(root.value + ",");
        preTraverse(root.left);
        preTraverse(root.right);
    }

    public void inTraverse(TNode root) {
        if (root == null) {
            return;
        }
        preTraverse(root.left);
        Printer.println(root.value + ",");
        preTraverse(root.right);
    }

    public void postTraverse(TNode root) {
        if (root == null) {
            return;
        }
        preTraverse(root.left);
        preTraverse(root.right);
        Printer.println(root.value + ",");
    }

    public void preTraverseUnRecur(TNode root) {

    }

    public void inTraverseUnRecur(TNode root) {

    }

    public void postTraverseUnRecur(TNode root) {

    }

    public static void main(String[] args) {
        testRecursion();
    }

    private static void testRecursion() {
        Integer[] arr = {
            1, 2, 3, 4, 5, 6, 7
        };
        TNode root = TreeTestHelper.createBinaryTree(arr);

        BTreeTraverse bT = new BTreeTraverse();

        bT.traverse(root);

        TreeUtils.printTree(root);
    }
}

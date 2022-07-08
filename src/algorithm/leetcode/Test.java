package algorithm.leetcode;

import algorithm.leetcode.base.DListNode;
import algorithm.leetcode.base.ListNode;
import algorithm.leetcode.base.TreeNode;

/**
 * Author: Aaron
 * Create Date: 2022/5/20
 * Version: v1.0
 * <p>
 * base
 */
public class Test {
    public static ListNode createListNode() {
        ListNode head = new ListNode(1);
        ListNode node = head;
        for (int i = 2; i < 11; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head;
    }

    public static DListNode createDListNode() {
        DListNode head = new DListNode(1);
        DListNode node = head;
        DListNode pre = null;
        node.pre = pre;
        for (int i = 2; i < 11; i++) {
            node.next = new DListNode(i);
            pre = node;
            node = node.next;
            node.pre = pre;
        }
        return head;
    }

    public static void printList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
    }

    public static void printDList(DListNode head) {
        DListNode node = head;
        while (node != null) {
            System.out.print("[" + (node.pre == null ? "null" : node.pre.value) + "<-]"
                    + node.value + " --> ");
            node = node.next;
        }
    }

    public static void println(String msg) {
        System.out.println();
        System.out.println(msg);
    }

    public static TreeNode buildTree() {
        TreeNode root = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        TreeNode t11 = new TreeNode(11);
        TreeNode t12 = new TreeNode(12);
        TreeNode t13 = new TreeNode(13);


        root.left = t1;
        root.right = t2;

        t1.left = t3;
        t1.right = t4;

        t2.left = t5;
        t2.right = t6;

        t3.left = t7;
        t3.right = t8;

        t4.left = t9;
        t4.right = t10;

        t5.left = t11;
        t5.right = t12;

        t6.left = t13;
        return root;
    }


    public static void printArray(int[] array) {
        System.out.print("[");
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }
}

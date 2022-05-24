package algorithm.leetcode;

import algorithm.leetcode.base.DListNode;
import algorithm.leetcode.base.ListNode;

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
}

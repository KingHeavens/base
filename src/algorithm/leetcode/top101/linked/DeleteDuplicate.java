package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * 删除链表中的重复元素
 * 包含个题：
 * 1、遇到重复元素保留第一个
 * 2、只要是重复的节点全部删除
 */
public class DeleteDuplicate {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        Test.printList(n1);
        ListNode head = deleteDuplicates(n1);
        Test.println("去重后：");
        Test.printList(head);
    }
    public static ListNode deleteDuplicate(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return res.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode cur = res;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.value == cur.next.next.value) {
                int temp = cur.next.value;
                while (cur.next != null && cur.next.value == temp) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return res.next;
    }
}

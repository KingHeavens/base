package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * 1、找到链表倒数第K个元素
 * 2、删除链表倒数第K个元素
 */
public class FindAndDeleteLastK {
    public static void main(String[] args) {
        ListNode head = Test.createListNode();
        Test.println("创建链表:");
        Test.printList(head);
        int k = 100;
//        ListNode lastK = findLastK(head, k);
//        Test.println("找到的节点：" + (lastK == null ? "null" : lastK.value));

        deleteLastK(head, k);
        Test.println("删除后的链表：");
        Test.printList(head);
    }

    public static ListNode findLastK(ListNode head, int k) {
        if (head == null || k < 0) {
            return null;
        }
        ListNode fast = head;
        ListNode cur = head;
        int n = 0;
        while (n < k) {
            if (fast == null) {
                return null;
            } else {
                fast = fast.next;
            }
            n++;
        }

        while (fast != null) {
            fast = fast.next;
            cur = cur.next;
        }
        return cur;
    }

    public static void deleteLastK(ListNode head, int k) {
        if (head == null) {
            return;
        }
        ListNode fast = head;
        ListNode cur = head;
        ListNode pre = null;
        int n = 0;
        while (n < k) {
            if (fast == null) {
                return;
            }
            fast = fast.next;
            n++;
        }

        while (fast != null) {
            fast = fast.next;
            pre = cur;
            cur = cur.next;
        }

        if (pre != null) {
            pre.next = cur.next;
        }
    }
}

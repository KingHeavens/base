package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * 是否是回文链表
 */
public class IsPail {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Test.println("原始链表：");
        Test.printList(n1);

        Test.println("是否是回文：" + isPail(n1));
        Test.printList(n1);
    }


    public static boolean isPail(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        ListNode last = slow;
        fast = head;
        while (slow != null) {
            if (slow.value != fast.value) {
                restoreList(head, last);
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        restoreList(head, last);
        return true;
    }

    private static void restoreList(ListNode head, ListNode last) {
        while(head != null && head.next != null) {
            head = head.next;
        }
        if (head != null) {
            Test.println("debug head:");
            Test.printList(head);
            Test.println("debug last:");
            Test.printList(last);
            ListNode reverse = reverse(last);
            if (reverse != null) {
                head.next = reverse.next;
            }
        }
    }

    /**
     * 反转链表
     * @param head ListNode
     * @return 反转后链表
     */
    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

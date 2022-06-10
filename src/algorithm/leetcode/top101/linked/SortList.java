package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * 链表排序
 */
public class SortList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n3;
        n3.next = n2;
        n2.next = n4;
        n4.next = n5;

        Test.println("排序前：");
        Test.printList(n1);

        Test.println("排序后:");
        ListNode head = sortInList(n1);
        Test.printList(head);

    }
    /**
     * 链表排序，使用递归
     *
     * 终止条件：链表为空或一个节点直接返回，表明已排好序
     * 返回值：返沪已经排好序的链表
     * 本级任务：找到链表中点位置，断开链表分别排序，再合并
     *
     * @param head ListNode
     * @return ListNode
     */
    public static ListNode sortInList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode mid = head.next;
        ListNode fast = head.next.next;
        // 找到链表中点位置
        while (fast != null && fast.next != null) {
            left = left.next;
            mid = mid.next;
            fast = fast.next.next;
        }
        // 断开链表
        left.next = null;
        return merge(sortInList(head), sortInList(mid));
    }

    /**
     * 合并两个有序链表
     * @param head1 ListNode
     * @param head2 ListNode
     * @return ListNode
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 == null) {
            cur.next = head2;
        } else {
            cur.next = head1;
        }
        return head.next;
    }
}

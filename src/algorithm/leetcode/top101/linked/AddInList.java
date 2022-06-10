package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

import javax.xml.soap.Text;

/**
 * 两个链表相加
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 数据范围：0 \le n,m \le 10000000≤n,m≤1000000，链表任意值 0 \le val \le 90≤val≤9
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 *
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
public class AddInList {
    public static void main(String[] args) {
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode head1 = n9;
        n9.next = n3;
        n3.next = n7;

        ListNode head2 = n6;
        n6.next = new ListNode(3);

        Test.println("list1 = ");
        Test.printList(head1);
        Test.println("list2 = ");
        Test.printList(head2);
        Test.println("list1 + list2 = ");
        ListNode res = addInList(head1, head2);
        Test.printList(res);
    }
    /**
     * 思路: 链表当成数字需要从后往前相加，所以相加之前需要反转链表
     * 1、如果有一个链表为null,直接返回另一个
     * 2、反转两个链表
     * 3、将反转后的链表节点依次相加，需要
     *
     * @param head1 head1
     * @param head2 head2
     * @return 相加后的链表
     */
    public static ListNode addInList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode res = new ListNode(-1);
        ListNode head = res;
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int carry = 0;
        while (head1 != null || head2 != null || carry > 0) {
            int value1 = head1 == null ? 0 : head1.value;
            int value2 = head2 == null ? 0 : head2.value;
            int sum = value1 + value2 + carry;
            carry = sum / 10;
            int value = sum % 10;
            head.next = new ListNode(value);;
            head = head.next;
            if (head1 != null) {
                head1 = head1.next;
            }
            if (head2 != null) {
                head2 = head2.next;
            }
        }
        return reverseList(res.next);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * 链表的基偶重排，链表基数偶数重排
 */
public class OddEvenReSort {
    public static void main(String[] args) {
        ListNode head = Test.createListNode();
        Test.println("排序前：");
        Test.printList(head);
        Test.println("奇偶排序后：");
        head = oddEvenSort(head);
        Test.printList(head);
    }

    public static ListNode oddEvenSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        ListNode res = new ListNode(0);
        res.next = odd;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return res.next;
    }
}

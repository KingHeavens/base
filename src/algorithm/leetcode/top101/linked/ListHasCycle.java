package algorithm.leetcode.top101.linked;

import algorithm.leetcode.base.ListNode;

public class ListHasCycle {
    /**
     * 判断链表是否有环
     *
     *
     * @param head 链表头
     * @return 链表是否有环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

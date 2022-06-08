package algorithm.leetcode.top101.linked;

import algorithm.leetcode.base.ListNode;

/**
 *  获取环链表环的入口节点
 */
public class CycleListGetLoopInNode {


    /**
     * 1、先使用快慢指针，慢指针走一步 快指针走两步， 找到在环上相遇的节点
     * 2、快指针返回指针头部，与慢指针一起每次走一步，再次相遇时所在节点就是环的入口节点
     *
     * @param head head
     * @return 链表环入口节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                // 说明链表没有环
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


}

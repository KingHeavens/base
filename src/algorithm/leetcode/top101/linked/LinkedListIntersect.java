package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 *  两条单链表相交问题
 */
public class LinkedListIntersect {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        ListNode h1 = new ListNode(11);
        ListNode h2 = new ListNode(22);
        ListNode h3 = new ListNode(33);
        ListNode h4 = new ListNode(44);
        ListNode h5 = new ListNode(55);
        ListNode h6 = new ListNode(66);
        ListNode h7 = new ListNode(77);
        ListNode h8 = new ListNode(88);

        ListNode head1 = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        // n7.next = h3;


        ListNode head2 = h1;
        h1.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        h5.next = h6;
        h6.next = h7;
        h7.next = h8;
        // h8.next = h5;

//        ListNode intersect = intersect(head1, head2);
        ListNode intersect = findFirstCommonNode(head1, head2);
        Test.println("相交结果：" + (intersect == null ? null : intersect.value));


    }

    public static ListNode intersect(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode loop1 = cycleListInNode(head1);
        ListNode loop2 = cycleListInNode(head2);
        if (loop1 != null && loop2 != null) {
            return cycleListIntersectNode(head1, loop1, head2, loop2);
        }
        if (loop1 == null && loop2 == null) {
            return noCycleListIntersectNode(head1, head2);
        }
        return null;
    }

    /**
     * 获取链表的入环节点,链表没有环返回null
     *
     * @param head ListNode
     * @return 入环节点
     */
    public static ListNode cycleListInNode(ListNode head) {
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
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 获取两个无环节点的相交节点
     * @param head1 ListNode
     * @param head2 ListNode
     * @return 相交节点，不相交返回null
     */
    public static ListNode noCycleListIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int n = 0;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }

        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);

        while (n > 0) {
            cur1 = cur1.next;
            n--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    /**
     * 返回两个有环链表的交点
     *
     * @param head1  链表1
     * @param loop1 链表一入环节点
     * @param head2 链表2
     * @param loop2 链表2入环节点
     * @return 返回两个链表相交节点，不相交返回null
     */
    public static ListNode cycleListIntersectNode(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (loop1 == loop2) {
            ListNode cur1 = head1;
            ListNode cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        ListNode cur1 = loop1.next;
        while (cur1 != loop1) {
            if (cur1 == loop2) {
                return cur1;
            }
            cur1 = cur1.next;
        }
        return null;
    }

    public static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1 != p2) {
            p1 = p1 == null ? head2 : p1.next;
            p2 = p2 == null ? head1 : p2.next;
        }
        return p1;
    }
}

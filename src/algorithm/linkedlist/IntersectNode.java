package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.Node;
import test.Printer;

/**
 * 找两条链表的相交节点。
 * 相交节点
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class IntersectNode {
    /**
     * 获取链表入环节点
     *
     * @param head head
     * @return loop node
     */
    public Node getLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        //快指针走两步慢指针走一步，如果走到头说明没有环，如果相遇说明有环
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //相遇后快指针回到链表头与慢指针一起往前走，相遇的节点就是链表的入环节点。
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 获取两个有环链表的相交节点
     * 两种情况：
     * 1、在环外相交
     * 2、在环内相交
     *
     * @param head1 链表1
     * @param loop1 链表1入环节点
     * @param head2 链表2
     * @param loop2 链表2入环节点
     * @return intersect node
     */
    public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (loop1 == loop2) {//可能在环外相交
            Node n1 = head1;
            int count = 0;
            while (n1 != loop1) {
                n1 = n1.next;
                count ++;
            }
            n1 = head2;
            while (n1 != loop1) {
                n1 = n1.next;
                count--;
            }
            n1 = count > 0 ? head1 : head2;
            Node n2 = count > 0 ? head2 : head1;
            count = Math.abs(count);
            while (count > 0) {
                n1 = n1.next;
                count--;
            }
            while (n1 != loop1) {
                if (n1 == n2) {
                    return n1;
                }
                n1 = n1.next;
                n2 = n2.next;
            }
            return null;
        } else {//可能在环内相交
            Node cur = loop1.next;
            while (cur != loop1) { //从链表一的入环处开始往下走 如果能碰到链表2的入环节点说明连个链表相交
                if (cur == loop2) {
                    return loop2;
                }
                cur = cur.next;
            }
        }
        return null;
    }

    /**
     * 获取两个无环链表的相交节点
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return intersect node
     */
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int count = 0;
        Node n1 = head1;
        while (n1 != null) {//链表一走到头，计数
            n1 = n1.next;
            count++;
        }
        n1 = head2;
        while (n1 != null) {//链表二走到头，计数--
            n1 = n1.next;
            count--;
        }
        n1 = count > 0 ? head1 : head2;
        Node n2 = count > 0 ? head2 : head1;
        count = Math.abs(count);
        while (count > 0) {//较长的链表先走差值步数
            n1 = n1.next;
            count--;
        }
        while (n1 != null) {//然后两个链表同时走，如果相遇就表示两个链表相交了
            if (n1 == n2) {
                return n1;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return null;
    }

    public Node getIntersectNode(Node head1, Node head2) {
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);
        if (loop1 == null && loop2 == null) {//两个链表无环
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {//两个链表有环
            return bothLoop(head1, loop1, head2, loop2);
        }
        //一个有环一个无环肯定不会相交
        return null;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        Node head1 = LinkedTestHelper.createLoopList(10, 5);
        Node head2 = LinkedTestHelper.createLoopList(20, 7);
        LinkedTestHelper.printLinkedList(head1);
        LinkedTestHelper.printLinkedList(head2);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(head1, head2);
        Printer.println("两条有环不相交：IntersectNode:" + result);
        Printer.println();
    }

    private static void test2() {
        Node head1 = LinkedTestHelper.createList(10);
        Node head2 = LinkedTestHelper.createList(15);
        LinkedTestHelper.printLinkedList(head1);
        LinkedTestHelper.printLinkedList(head2);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(head1, head2);
        Printer.println("两条无环不相交：IntersectNode:" + result);
        Printer.println();
    }

    private static void test3() {
        Node head1 = LinkedTestHelper.createLoopList(10, 5);
        Node head2 = LinkedTestHelper.createList(15);
        LinkedTestHelper.printLinkedList(head1);
        LinkedTestHelper.printLinkedList(head2);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(head1, head2);
        Printer.println("一条有环一条无环：IntersectNode:" + result);
        Printer.println();
    }

    private static void test4() {
        Node[] heads = LinkedTestHelper.createIntersectNoLoopList(10, 5, 2);
        LinkedTestHelper.printLinkedList(heads[0]);
        LinkedTestHelper.printLinkedList(heads[1]);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(heads[0], heads[1]);
        Printer.println("两条无环相交：IntersectNode:" + result);
        Printer.println();
    }

    private static void test5() {
        Node[] heads = LinkedTestHelper.createIntersectLoopOutList(10, 5, 3,2);
        LinkedTestHelper.printLinkedList(heads[0]);
        LinkedTestHelper.printLinkedList(heads[1]);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(heads[0], heads[1]);
        Printer.println("两条有环环外相交：IntersectNode:" + result);
        Printer.println();
    }

    private static void test6() {
        Node[] heads = LinkedTestHelper.createIntersectLoopInList(10, 5);
        LinkedTestHelper.printLinkedList(heads[0]);
        LinkedTestHelper.printLinkedList(heads[1]);

        IntersectNode intersectNode = new IntersectNode();
        Node result = intersectNode.getIntersectNode(heads[0], heads[1]);
        Printer.println("两条有环环内相交：IntersectNode:" + result);
        Printer.println();
    }
}

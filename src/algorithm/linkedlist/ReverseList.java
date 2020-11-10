package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.DNode;
import algorithm.linkedlist.node.Node;

/**
 * 翻转链表
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class ReverseList {
    /**
     * 翻转单链表
     * @param head head
     */
    public Node reverseList(Node head) {
        Node next;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 翻转双向链表
     *
     * @param head head
     */
    public DNode reverseList(DNode head) {
        DNode pre = null;
        DNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        //testLink();
        testDLink();
    }

    private static void testLink() {
        Integer[] arr = new Integer[] {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };
        Node node = LinkedTestHelper.arrayToLinkedList(arr);
        LinkedTestHelper.printLinkedList(node);

        ReverseList algorithm = new ReverseList();
        Node reverseList = algorithm.reverseList(node);

        LinkedTestHelper.printLinkedList(reverseList);
    }

    private static void testDLink() {
        Integer[] arr = new Integer[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };
        DNode node = LinkedTestHelper.arrayToDLinkedList(arr);
        LinkedTestHelper.printDoubleList(node);

        ReverseList algorithm = new ReverseList();
        DNode reverseList = algorithm.reverseList(node);

        LinkedTestHelper.printDoubleList(reverseList);
    }
}

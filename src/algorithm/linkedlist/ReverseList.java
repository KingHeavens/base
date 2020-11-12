package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.DNode;
import algorithm.linkedlist.node.Node;
import test.Printer;

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
        //head指向将要翻转的节点
        //pre标识翻转后的链表的头节点
        //next记录将要翻转的下一个节点
        Node next;
        Node pre = null;
        while (head != null) {
            //先记录将要翻转节点的下一个节点
            next = head.next;
            //当前节点指向翻转好的节点的头结点
            head.next = pre;
            //更新翻转好的链表的头结点
            pre = head;
            //更新将要翻转的节点为之前保存的节点
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

    public boolean isDesc(Node head) {
        if (head == null) {
            return false;
        }
        Node cur = head;
        while (cur.next != null && cur.value == cur.next.value) {
            cur = cur.next;
        }
        return cur.next != null && cur.value > cur.next.value;
    }

    public static void main(String[] args) {
        testLink();
        //testDLink();
    }

    private static void testLink() {
        Integer[] arr = new Integer[] {
            2, 2, 1
        };
        Node node = LinkedTestHelper.arrayToLinkedList(arr);
        LinkedTestHelper.printLinkedList(node);
        ReverseList algorithm = new ReverseList();
        Printer.println("isDesc:" + algorithm.isDesc(node));

        Node reverseList = algorithm.reverseList(node);

        LinkedTestHelper.printLinkedList(reverseList);
        Printer.println("isDesc:" + algorithm.isDesc(reverseList));
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

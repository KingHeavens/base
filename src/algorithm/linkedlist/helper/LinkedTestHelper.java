package algorithm.linkedlist.helper;

import algorithm.linkedlist.node.DNode;
import algorithm.linkedlist.node.Node;
import test.Printer;

import java.util.Currency;

/**
 * 链表测试工具类
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
public class LinkedTestHelper {
    public static Node arrayToLinkedList(Integer[] arr) {
        Node head = null;
        Node cur = null;
        for (Integer num : arr) {
            if (head == null) {
                head = new Node(num);
                cur = head;
            } else {
                cur.next = new Node(num);
                cur = cur.next;
            }
        }
        return head;
    }

    public static Integer[] LinkedListToArr(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        Integer[] res = new Integer[count];
        cur = head;
        for (Integer i = 0; i < count; i++) {
            res[i] = cur.value;
            cur = cur.next;
        }
        return res;
    }

    public static DNode arrayToDLinkedList(Integer[] arr) {
        DNode head = null;
        DNode cur = null;
        DNode pre = null;
        for (Integer num : arr) {
            if (head == null) {
                head = new DNode(num);
                cur = head;
            } else {
                cur.next = new DNode(num);
                cur.pre = pre;
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public static Integer[] DLinkedListToArr(DNode head) {
        if (head == null) {
            return null;
        }
        DNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        Integer[] res = new Integer[count];
        cur = head;
        for (Integer i = 0; i < count; i++) {
            res[i] = cur.value;
            cur = cur.next;
        }
        return res;
    }

    public static void printLinkedList(Node head) {
        if (head == null) {
            Printer.print("LinkedList[NULL]");
            return;
        }
        Node cur = head;
        Printer.print("LinkedList:");
        while (cur != null) {
            Printer.print(cur.value + "->");
            cur = cur.next;
        }
        Printer.println("NULL:");
    }

    public static void printDoubleList(DNode head) {
        if (head == null) {
            Printer.print("DLinkedList[NULL]");
            return;
        }
        DNode cur = head;
        Printer.print("DLinkedList:");
        while (cur != null) {
            String preValue = cur.pre != null ? String.valueOf(cur.pre.value) : "NULL";
            Printer.print("[P" + preValue + "|" + cur.value + "]⇋");
            cur = cur.next;
        }
        Printer.println("->NULL:");
    }
}

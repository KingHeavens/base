package algorithm.linkedlist.helper;

import algorithm.linkedlist.node.DNode;
import algorithm.linkedlist.node.Node;
import algorithm.linkedlist.node.RNode;
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

    public static RNode arrayToRLinkedList(Integer[][] arr) {
        RNode head = null;
        RNode cur = null;

        RNode[] nodes = new RNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new RNode(arr[i][0]);
        }

        for (int i = 0; i < arr.length; i++) {
            if (head == null) {
                head = nodes[0];
                if (arr.length > 1) {
                    head.next = nodes[1];
                } else {
                    head.next = null;
                }
                if (arr.length >= arr[0][1]) {
                    head.rand = nodes[arr[0][1] - 1];
                } else {
                    head.rand = null;
                }
                cur = head.next;
            } else if (cur != null) {
                if (arr.length > i + 1) {
                    cur.next = nodes[i + 1];
                }
                if (arr.length >= arr[i][1]) {
                    cur.rand = nodes[arr[i][1] - 1];
                }
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

    public static void printRandomList(RNode head) {
        if (head == null) {
            Printer.print("RLinkedList[NULL]");
            return;
        }
        RNode cur = head;
        Printer.print("RLinkedList:");
        while (cur != null) {
            String randValue = cur.rand != null ? String.valueOf(cur.rand.value) : "NULL";
            Printer.print("[R" + randValue  + "|" + cur.value + "]->");
            cur = cur.next;
        }
        Printer.println("NULL:");
    }
}

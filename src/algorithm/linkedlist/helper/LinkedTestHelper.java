package algorithm.linkedlist.helper;

import algorithm.linkedlist.node.DNode;
import algorithm.linkedlist.node.Node;
import algorithm.linkedlist.node.RNode;
import test.Printer;

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

    public static Integer[] linkedListToArr(Node head) {
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

    public static Integer[] dLinkedListToArr(DNode head) {
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
        Node loopNode = getLoopNode(head);
        Node cur = head;
        Printer.print("LinkedList:");
        int loopNodeFlag = 1;
        while (cur != null) {
            if (loopNode == cur) {
                if (loopNodeFlag-- > 0) {
                    Printer.print("[↕↕ " + cur.value + "->");
                } else {
                    Printer.println(" ↕↕]");
                    return;
                }
            } else {
                Printer.print(cur.value + "->");
            }
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
            Printer.print("[R" + randValue + "|" + cur.value + "]->");
            cur = cur.next;
        }
        Printer.println("NULL:");
    }

    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
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
        return fast;
    }

    //loopIndex start from 1
    public static Node createLoopList(int count, int loopIndex) {
        if (loopIndex > count) {
            return null;
        }
        Node loopNode = new Node(loopIndex);
        Node head = null;
        Node cur = null;
        for (int i = 1; i <= count; i++) {
            if (i == loopIndex) {
                if (head == null) {
                    head = loopNode;
                    cur = head;
                } else {
                    cur.next = loopNode;
                    cur = cur.next;
                }
            } else {
                if (head == null) {
                    head = new Node(i);
                    cur = head;
                } else {
                    cur.next = new Node(i);
                    cur = cur.next;
                }
            }
            if (i == count) {
                cur.next = loopNode;
                cur = cur.next;
            }
        }
        return head;
    }

    public static Node createList(int count) {
        if (count < 1) {
            return null;
        }
        Node head = null;
        Node cur = null;
        for (int i = 1; i <= count; i++) {
            Node node = new Node(i);
            if (head == null) {
                head = node;
                cur = head;
            } else {
                cur.next = node;
                cur = cur.next;
            }
        }
        return head;
    }

    //intersectIndex 短的链表开始数相交的位置

    /**
     * 相交节点 0
     *
     * @param head1OutLength 链表一非公共部分个数 1
     * @param head2OutLength 链表二非公共部分个数 20
     * @param commonCount 公共部分节点个数 300
     * @return head1 head2
     */
    public static Node[] createIntersectNoLoopList(int head1OutLength, int head2OutLength, int commonCount) {
        Node head1 = null;
        Node head2 = null;

        Node head1Out = null;
        int i;
        for (i = 0; i < head1OutLength; i++) {
            if (head1Out == null) {
                head1Out = new Node(i + 1);
                head1 = head1Out;
            } else {
                head1Out.next = new Node(i + 1);
                head1Out = head1Out.next;
            }
        }
        Node head2Out = null;
        for (i = 0; i < head2OutLength; i++) {
            if (head2Out == null) {
                head2Out = new Node(i + 20);
                head2 = head2Out;
            } else {
                head2Out.next = new Node(i + 20);
                head2Out = head2Out.next;
            }
        }

        Node intersect = null;
        Node commonNode = null;
        for (i = 1; i < commonCount; i++) {
            if (commonNode == null) {
                commonNode = new Node(0);
                intersect = commonNode;
            } else {
                commonNode.next = new Node(i + 300);
                commonNode = commonNode.next;
            }
        }

        if (head1Out != null) {
            head1Out.next = intersect;
        }
        if (head2Out != null) {
            head2Out.next = intersect;
        }
        return new Node[]{head1, head2};
    }

    //intersectIndex 短的链表开始数相交的位置

    /**
     * 相交节点 0
     *
     * @param loopLength 环的长度， 1000
     * @param head1OutLength  链表一非公共部分个数 1
     * @param head2OutLength  链表二非公共部分个数 20
     * @param outLoopCommonLength 环外公共部分节点个数 300
     * @return head1 head2
     */
    public static Node[] createIntersectLoopOutList(int loopLength, int head1OutLength, int head2OutLength, int outLoopCommonLength) {
        Node head1 = null;
        Node head2 = null;

        Node head1Out = null;
        int i;
        for (i = 0; i < head1OutLength; i++) {
            if (head1Out == null) {
                head1Out = new Node(i + 1);
                head1 = head1Out;
            } else {
                head1Out.next = new Node(i + 1);
                head1Out = head1Out.next;
            }
        }
        Node head2Out = null;
        for (i = 0; i < head2OutLength; i++) {
            if (head2Out == null) {
                head2Out = new Node(i + 20);
                head2 = head2Out;
            } else {
                head2Out.next = new Node(i + 20);
                head2Out = head2Out.next;
            }
        }

        Node intersect = null;
        Node commonNode = null;
        for (i = 1; i <= outLoopCommonLength; i++) {
            if (commonNode == null) {
                commonNode = new Node(0);
                intersect = commonNode;
            } else {
                commonNode.next = new Node(i + 300);
                commonNode = commonNode.next;
            }
        }

        if (head1Out != null) {
            head1Out.next = intersect;
        }
        if (head2Out != null) {
            head2Out.next = intersect;
        }

        Node loopNode = new Node(1000);
        if (commonNode != null) {
            commonNode.next = loopNode;
        }

        loopNode.next = loopNode;
        Node tailNode = loopNode;
        for (i = 1; i < loopLength; i++) {
            Node node = new Node(1000 + i);
            node.next = tailNode;
            loopNode.next = node;
            loopNode = loopNode.next;
        }

        return new Node[]{head1, head2};
    }

    /**
     * loop1 0
     * loop2 1
     *
     * @param loopLength 环的长度 1000
     * @param head1OutLength 链表一非公共部分长度 1
     * @param head2OutLength 链表二非公共部分长度 20
     * @return head1 head2
     */
    public static Node[] createIntersectLoopInList(int loopLength, int head1OutLength, int head2OutLength) {
        Node head1 = null;
        Node head2 = null;

        Node head1Out = null;
        int i;
        for (i = 0; i < head1OutLength; i++) {
            if (head1Out == null) {
                head1Out = new Node(i + 1);
                head1 = head1Out;
            } else {
                head1Out.next = new Node(i + 1);
                head1Out = head1Out.next;
            }
        }
        Node head2Out = null;
        for (i = 0; i < head2OutLength; i++) {
            if (head2Out == null) {
                head2Out = new Node(i + 20);
                head2 = head2Out;
            } else {
                head2Out.next = new Node(i + 20);
                head2Out = head2Out.next;
            }
        }

        Node loopNode = new Node(1000);
        loopNode.next = loopNode;
        Node tail = loopNode;
        for (i = 0; i < loopLength; i++) {
            Node node = new Node(1000 + i);
            node.next = tail;
            loopNode.next = node;
            loopNode = loopNode.next;
        }

        tail.value = 0;

        if (head1Out != null) {
            head1Out.next = tail;
        }

        for (i = 0; i < head1OutLength; i++) {
            loopNode = loopNode.next;
        }

        if (loopNode == tail) {
            loopNode = loopNode.next;
        }

        loopNode.value = 1;


        if (head2Out != null) {
            head2Out.next = loopNode;
        }

        return new Node[]{head1, head2};
    }
}

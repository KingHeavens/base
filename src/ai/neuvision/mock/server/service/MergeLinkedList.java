package ai.neuvision.mock.server.service;

/**
 * 合并链表，两个单向链表都是有序的，但是不知道是正序还是降序，写一个方法将两个链表合并，可以指定顺序。
 *
 * @author sunlong
 * @since 2020-10-20
 */
public class MergeLinkedList {

    public static class Node {

        public int val;

        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 遍历一个单向链表
     *
     * @param node
     */
    public static void traversalsLinkedList(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + ", ");
        traversalsLinkedList(node.next);
    }

    /**
     * 快速构建一个升序链表，指定长度和增量
     *
     * @param size
     * @param incr
     * @return
     */
    public static Node createAscOrderLinkedList(int size, int incr) {
        Node head = new Node(0);
        Node temp = head;
        for (int i = 1; i < size; i++) {
            Node node = new Node(incr * i);
            temp.next = node;
            temp = node;
        }
        return head;
    }

    /**
     * 快速构建一个降序链表，指定长度和增量
     *
     * @param size
     * @param incr
     * @return
     */
    public static Node createDescOrderLinkedList(int size, int incr) {
        Node head = new Node(size * incr);
        Node temp = head;
        for (int i = size - 1; i > 0; i--) {
            Node node = new Node(incr * i);
            temp.next = node;
            temp = node;
        }
        return head;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 把前三个node拿出来缓存一下；
        Node p1 = head;
        Node p2 = head.next;
        Node p3 = null;

        while (p2 != null) {
            // 缓存第三个node
            p3 = p2.next;
            // 第二个指向第一个
            p2.next = p1;
            // 缓存的向后移动
            p1 = p2;
            p2 = p3;
        }
        // 一开始的头指针指向null
        head.next = null;
        // 返回尾部节点
        return p1;
    }

    /**
     * 合并两个有序链表，可指定 顺序
     *
     * @param a
     * @param b
     * @param desc true=降序，false=升序
     * @return
     */
    public static Node mergeOrderedLinkedList(Node a, Node b, boolean desc) {
        // TODO: 2020-10-20 第一个方法，把两个原链表的顺序调整为一致，不一致的反转过来，然后，使用merge把链表合并；
        // TODO: 2020-10-20 第二个方法，直接新建链表，分别遍历，插入到正确的位置；
        // TODO: 2020-10-20 检测原链表是否和目标链表顺序一致
        if (desc) {
            if (a.val < a.next.val) {
                // a 的方向是反的，需要反转一下
                a = reverseLinkedList(a);
            }
            if(b.val < b.next.val) {
                // b 的方向是反的，需要反转一下
                b = reverseLinkedList(b);
            }
        } else {
            if (a.val > a.next.val) {
                // a 的方向是反的，需要反转一下
                a = reverseLinkedList(a);
            }
            if(b.val > b.next.val) {
                // b 的方向是反的，需要反转一下
                b = reverseLinkedList(b);
            }
        }

        Node aNext = a;
        Node bNext = b;

        Node head = null;
        Node newNext = null;
        Node newNextNext = null;
        // TODO: 2020-10-20 合并两个有序链表到新链表
        if (aNext == null) {
            return a;
        } else if (bNext == null) {
            return b;
        }
        if (desc) {
            if (a.val <= b.val) {
                head = bNext;
                bNext = b.next;
            } else {
                head = aNext;
                aNext = a.next;
            }
        } else {
            if (a.val >= b.val) {
                head = bNext;
                bNext = b.next;
            } else {
                head = aNext;
                aNext = a.next;
            }
        }
        newNext = head;

        while (aNext != null || bNext != null) {
            if (aNext == null) {
                newNextNext = bNext;
                bNext = bNext.next;
            } else if (bNext == null) {
                newNextNext = aNext;
                aNext = aNext.next;
            } else {
                if (desc) {
                    if (aNext.val <= bNext.val) {
                        newNextNext = bNext;
                        bNext = bNext.next;
                    } else {
                        newNextNext = aNext;
                        aNext = aNext.next;
                    }
                } else {
                    if (aNext.val >= bNext.val) {
                        newNextNext = bNext;
                        bNext = bNext.next;
                    } else {
                        newNextNext = aNext;
                        aNext = aNext.next;
                    }
                }
            }
            newNext.next = newNextNext;
            newNext = newNextNext;

        }
//
        return head;
    }


    public static void main(String[] args) {
        Node linkedListA = createAscOrderLinkedList(10, 3);
//        Node linkedListB = createDescOrderLinkedList(20, 5);
        Node linkedListC = createDescOrderLinkedList(20, 5);

        traversalsLinkedList(linkedListA);
//        Node reversedLinkedListA = reverseLinkedList(linkedListA);
        System.out.println("==========反转后==========");
//        traversalsLinkedList(reversedLinkedListA);
        System.out.println("-----------------");
//        traversalsLinkedList(linkedListB);
        traversalsLinkedList(linkedListC);
//        Node reversedLinkedListB = reverseLinkedList(linkedListB);
        System.out.println("==========反转后==========");
//        traversalsLinkedList(reversedLinkedListB);
        Node node = mergeOrderedLinkedList(linkedListA, linkedListC, false);
        System.out.println("==========合并后==========");

        traversalsLinkedList(node);
    }

}
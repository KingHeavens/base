package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.Node;
import test.Printer;

/**
 * 荷兰国旗链表版本
 * <p>
 * 将链表中小于某个值的数放左边，等于的放中间，大于的方右边
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class DutchFlagLinkedList {
    /**
     * 构建数组解决
     *
     * @param head head
     */
    public Node toDutchFlagLinkedList(Node head, int value) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            cur = cur.next;
            i++;
        }
        cur = head;
        Node[] arr = new Node[i];
        for (i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }

        partition(arr, value);

        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[i - 1].next = null;
        return arr[0];
    }

    private void partition(Node[] arr, int value) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int less = L - 1;
        int more = arr.length;
        while (L < more) {
            if (arr[L].value < value) {
                swap(arr, ++less, L++);
            } else if (arr[L].value > value) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
    }

    private void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 构建三个链表，最后合并解决
     *
     *
     * @param head head
     */
    public Node toDutchFlagLinkedList2(Node head, int value) {
        if (head == null) {
            return null;
        }
        Node sH = null;
        Node sT = null;
        Node mH = null;
        Node mT = null;
        Node bH = null;
        Node bT = null;
        while (head != null) {
            if (head.value < value) {
                if (sH == null) {
                    sH = head;
                    sT = sH;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value > value) {
                if (bH == null) {
                    bH = head;
                    bT = bH;
                } else {
                    bT.next = head;
                    bT = bT.next;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = mH;
                } else {
                    mT.next = head;
                    mT = mT.next;
                }
            }
            head = head.next;
        }

        if (sT != null) {
            sT.next = mH == null ? bH : mH;
        }
        if (mT != null) {
            mT.next = bH;
        }
        if (bT != null) {
            bT.next = null;
        }

       return (sH != null ? sH : (mH != null ? mH : bH));
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                6,7,8,1,2,3
        };
        Node head = LinkedTestHelper.arrayToLinkedList(arr);

        DutchFlagLinkedList list = new DutchFlagLinkedList();
        int value = 5;
        Node node1 = list.toDutchFlagLinkedList(head, value);
        LinkedTestHelper.printLinkedList(node1);

        head = LinkedTestHelper.arrayToLinkedList(arr);
        Node node2 = list.toDutchFlagLinkedList2(head, value);
        LinkedTestHelper.printLinkedList(node2);
    }
}

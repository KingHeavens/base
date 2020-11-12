package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.Node;

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
        //将链表转化为数组
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

        //划分 Node 数组为正确结果
        partition(arr, value);

        //将 Node数组转化为链表
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
        Node sH = null;//小于区域链表头
        Node sT = null;//小于区域链表尾
        Node eH = null;//等于区域链表头
        Node eT = null;//等于区域链表尾
        Node bH = null;//大于区域链表头
        Node bT = null;//大于区域链表尾

        //遍历原始链表的每个数值，分别分配到三条相应链表中
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
                if (eH == null) {
                    eH = head;
                    eT = eH;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            }
            head = head.next;
        }

        // 小于链表尾部连接等于链表头部
        // 等于链表尾部连接大于链表头部

        //小于链表如果没有数据不需要连接
        if (sT != null) {
            //等于链表如果没有数据直接连接大于链表头部
            sT.next = eH == null ? bH : eH;
        }
        // 等于链表如果没有数据不需要连
        if (eT != null) {
            eT.next = bH;
        }
        //将大于链表的尾部节点next置空
        if (bT != null) {
            bT.next = null;
        }

       return (sH != null ? sH : (eH != null ? eH : bH));
    }

    //划分为三个链表再合并的其中一种写法
    public Node listPartition(Node head, int value) {
        if (head == null) {
            return null;
        }
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        while(head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value < value) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value > value) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            } else {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                5, 1, 2, 3
        };
        Node head = LinkedTestHelper.arrayToLinkedList(arr);

        DutchFlagLinkedList list = new DutchFlagLinkedList();
        int value = 5;
        Node node1 = list.listPartition(head, value);
        LinkedTestHelper.printLinkedList(node1);

        head = LinkedTestHelper.arrayToLinkedList(arr);
        Node node2 = list.toDutchFlagLinkedList2(head, value);
        LinkedTestHelper.printLinkedList(node2);
    }
}

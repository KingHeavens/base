package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.RNode;
import test.Printer;

import java.util.HashMap;

/**
 * 复制一个带随机指针的链表，随机指针指向本链表上任意节点。
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class CopyRandomList {
    /**
     * 哈希表存储法
     *
     * @param head head
     * @return node
     */
    public RNode copyRandomList(RNode head) {
        if (head == null) {
            return null;
        }
        RNode cur = head;
        HashMap<RNode, RNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new RNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 链表节点后接然后拆分成两个链表
     *
     * @param head head
     * @return node
     */
    public RNode copyRandomList2(RNode head) {
        if (head == null) {
            return null;
        }
        //每隔一个节点添加一个
        RNode cur = head;
        RNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = new RNode(cur.value);
            cur.next.next = next;
            cur = next;
        }
        //赋值rand节点
        cur = head;
        RNode copiedNode;
        while (cur != null) {
            copiedNode = cur.next;
            copiedNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }

        //将原来的节点拆成两个
        cur = head;
        RNode result = head.next;
        while (cur != null) {
            next = cur.next.next;
            copiedNode = cur.next;
            cur.next = next;
            copiedNode.next = next != null ? next.next : null;
            cur = next;
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{
                {1, 10},
                {2, 9},
                {3, 8},
                {4, 7},
                {5, 6},
                {6, 5},
                {7, 4},
                {8, 3},
                {9, 2},
                {10, 1}
        };
        RNode head = LinkedTestHelper.arrayToRLinkedList(arr);

        CopyRandomList algorithm = new CopyRandomList();
        RNode result = algorithm.copyRandomList2(head);

        Printer.println("head:" + head);
        LinkedTestHelper.printRandomList(head);
        Printer.println("result:" + result);
        LinkedTestHelper.printRandomList(result);
    }
}

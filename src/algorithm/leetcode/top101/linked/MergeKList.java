package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并K个升序排列的链表
 *
 * 描述
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 *
 * 数据范围：节点总数满足 0 \le n \le 10^50≤n≤10
 * 5
 *  ，链表个数满足 1 \le k \le 10^5 \1≤k≤10
 * 5
 *    ，每个链表的长度满足 1 \le len \le 200 \1≤len≤200  ，每个节点的值满足 |val| <= 1000∣val∣<=1000
 * 要求：时间复杂度 O(nlogk)O(nlogk)
 * 示例1
 * 输入：
 * [{1,2,3},{4,5,6,7}]
 * 复制
 * 返回值：
 * {1,2,3,4,5,6,7}
 * 复制
 * 示例2
 * 输入：
 * [{1,2},{1,4,5},{6}]
 * 复制
 * 返回值：
 * {1,1,2,4,5,6}
 */
public class MergeKList {
    public static void main(String[] args) {
        ArrayList<ListNode> lists = new ArrayList<>();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        lists.add(n1);

        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        lists.add(n4);

        ListNode list3 = Test.createListNode();

        lists.add(list3);

        Test.println("链表1：");
        Test.printList(n1);

        Test.println("链表2：");
        Test.printList(n4);

        Test.println("链表3：");
        Test.printList(list3);

        ListNode head = mergeKList(lists);
        Test.println("合并后：");
        Test.printList(head);
    }

    /**
     * 使用堆结构，构造小顶堆，先将K个链表头加入堆中。
     * 然后取出堆顶，再将取出的堆顶加入堆中。
     * 时间复杂度 O（Nlogk）
     * 空间复杂度 O（k）
     *
     * @param lists k个链表
     * @return merge后的链表
     */
    public static ListNode mergeKList(ArrayList<ListNode> lists) {
        if (lists == null) {
            return null;
        }

        Queue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        ListNode vNode = new ListNode(0);
        ListNode cur = vNode;
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return vNode.next;
    }

    /***
     * 垃圾解法
     * 时间复杂度O（n*k）
     * 空间复杂度O(logK)
     *
     * @param lists lists
     * @return ListNode
     */
    public static ListNode mergeKList2(ArrayList<ListNode> lists) {
        if (lists == null) {
            return null;
        }
        return merge(lists, 0, lists.size() - 1);
    }

    public static ListNode merge(ArrayList<ListNode> lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists.get(left);
        }
        int mid = left + (right - left) >> 1;
        return merge(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    public static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return head.next;
    }
}

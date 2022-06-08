package algorithm.leetcode.top101.linked;

import algorithm.leetcode.base.DListNode;
import algorithm.leetcode.base.ListNode;

import static algorithm.leetcode.Test.*;
import static algorithm.leetcode.Test.printDList;

/**
 * Author: Aaron
 * Create Date: 2022/5/18
 * Version: v1.0
 * <p>  翻转单链表
 *
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 *
 * 数据范围： 0\leq n\leq10000≤n≤1000
 * 要求：空间复杂度 O(1)O(1) ，时间复杂度 O(n)O(n) 。
 *
 * 如当输入链表{1,2,3}时，
 * 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
 * base
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode head = createListNode();
        println("创建翻链表：");
        printList(head);

        head = reverseList(head);

        println("翻转单链表：");
        printList(head);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            head = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }
        return pre;
    }
}

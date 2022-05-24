package algorithm.leetcode;

import algorithm.leetcode.base.DListNode;
import algorithm.leetcode.base.ListNode;

import static algorithm.leetcode.Test.*;

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
@SuppressWarnings("Duplicates")
public class Top1 {
    public static void main(String[] args) {
        ListNode head = createListNode();
        println("创建翻链表：");
        printList(head);

        head = reverseListRecursion(head);

        println("翻转单链表：");
        printList(head);

        DListNode dHead = createDListNode();
        println("创建双向链表：");
        printDList(dHead);

        dHead = reverseDListRecursion(dHead);

        println("翻转双向链表：");
        printDList(dHead);
    }

    public static ListNode reverseList(ListNode head) {
        // head 定义为需要被反转的链表的头节点
        // 定义反转后链表的头节点
        ListNode pre = null;
        // 定义为当前要反转的节点
        ListNode cur = head;
        // 一直处理当前要反转的节点直到没有需要翻转的节点
        while (head != null) {
            // 进了循环当前节点就要被处理了，
            // 将需要被翻转的链表的头结点改为当前节点的下一个
            head = cur.next;

            // 开始处理当前节点翻转，当前节点指向翻转后的链表的头节点
            cur.next = pre;

            // 被翻转后的链表头已发生变化，更新新的链表头
            pre = cur;

            // 当前节点已处理好，准备翻转下一个要反转的节点（指向需要被翻转链表的头节点）
            cur = head;
        }
        return pre;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        // 如果传入节点是null,返回null
        if (head == null) {
            return null;
        }
        // 如果返回的节点是链表的最后一个节点，返回当前节点，
        // 需要被翻转的最后一个节点就是反转后链表的头结点。
        if (head.next == null) {
            return head;
        }

        // 现将当前节点的子链表翻转，返回反转后链表的头节点
        ListNode newHead = reverseListRecursion(head.next);

        // 开始翻转当前接点 和 翻转后的子链表
        // head.next指向的是翻转后的子链表的最后一个节点
        // 所以这里先将翻转后的子链表的最后一个节点指向当前节点然后将当前节点指向null
        head.next.next = head;
        head.next = null;

        // 返回翻转后的节点的头结点
        return newHead;
    }

    /**
     * 翻转双向链表，与翻转单链表几乎完全一样
     * @param head
     * @return
     */
    public static DListNode reverseDList(DListNode head) {
         DListNode pre = null;
         DListNode cur = head;
         while (cur != null) {
             head = cur.next;

             cur.next = pre;
             cur.pre = head;

             pre = cur;
             cur = head;
         }
         return pre;
    }

    /**
     * 递归翻转双向链表
     * @param head
     * @return
     */
    public static DListNode reverseDListRecursion(DListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head.pre = null;
            return head;
        }
        DListNode newHead = reverseDListRecursion(head.next);

        head.pre = head.next;
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}

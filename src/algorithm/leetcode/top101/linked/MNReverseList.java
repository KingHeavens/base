package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

/**
 * Author: Aaron
 * Create Date: 2022/5/20
 * Version: v1.0
 * <p> 区间翻转List
 * base
 */
public class MNReverseList {
    public static void main(String[] args) {
        ListNode head = Test.createListNode();
        Test.println("创建链表：");
        Test.printList(head);

        int m = 4;
        int n = 5;

        head = reverseBetweenRecursion(head, m, n);
        Test.println("区间翻转后：m:" + m + " n:" + n);
        Test.printList(head);
    }

    /**
     * 常规做法
     * 思路：
     * 1、搞一个虚拟节点指向头结点，防止第一个节点也被反转了找不到翻转后的链表头了
     * ，最后返回虚拟节点的下一个节点就是区间翻转后链表的头结点。
     * 2、定义一个pre节点,翻转前指向m位置的前一个节点，翻转节点时会用到。
     * 3、cur定义当前要翻转的节点
     * 4、next定义当前要反转的下一个节点，cur 与 next 做翻转
     *
     *
     * @param head 要反转的链表头结点
     * @param m 范围m 从1开始数
     * @param n 范围N 小于等于链表的长度
     * @return 区间翻转后的链表的头结点
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode vNode = new ListNode(-1);
        vNode.next = head;

        ListNode pre = vNode;
        ListNode cur = head;

        // 定位cur为N的位置 pre为n - 1的位置
        for (int i = 1; i < m; i++) {
            cur = cur.next;
            pre = pre.next;
        }

        while (m < n) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            m++;
        }

        return vNode.next;
    }


    /**
     *  递归方法翻转 m  --- n 区间链表
     *
     *  整体思路：子问题一，递归方式不断找到m位置作为头节点的节点，找到此位置后进入子问题二，递归翻转链表的1 - n位置
     *
     *  子问题一: 翻转链表中 m - n 位置的节点。
     *  reverseBetweenRecursion(head, m, n)
     * 终止条件：m == 1，找到此位置可以进入子问题二
     * 返回值：区间翻转后的链表的头结点
     * 本级任务：
     * 1、翻转子链表
     * 2、链接当前节点与翻转后的子链表
     *

     *
     * @param head 要反转的链表头结点
     * @param m 范围m 从1开始数
     * @param n 范围N 小于等于链表的长度
     * @return 区间翻转后的链表的头结点
     */
    public static ListNode reverseBetweenRecursion(ListNode head, int m, int n)  {
        if (m == 1) {
            return reverseRecursion(head, n);
        }
        // m - 1, n - 1 表示子链表head.next中要翻转的位置
        head.next = reverseBetweenRecursion(head.next, m - 1, n - 1);
        return head;
    }

    /**
     *  子问题二: 翻转链表从1 - n 位置的节点。
     *  reverseRecursion(head, n);
     *  终止条件：n == 1时，说明找的了n的位置，此事需要记录n 位置节点的下一个节点，后续用于链表拼接
     *  返回值：1 - n位置翻转后的链表的头结点。
     *  本级任务：
     *  1、翻转子链表
     *  2、子链表最后一个节点指向当前节点
     *  3、当前节点指向链表n位置的下一个节点
     * @param head 头结点
     * @param n n
     * @return 翻转后的链表头
     */
    private static ListNode nNext; // 用于记录链表中N位置的下一个位置的节点
    public static ListNode reverseRecursion(ListNode head, int n) {
        if (n == 1) {
            // 说明此时head节点是n位置
            nNext = head.next;
            return head;
        }
        // 翻转子链表 1 - n 位置， 传入的n - 1 是子链表中需要翻转的最后一个节点的位置
        ListNode newList = reverseRecursion(head.next, n - 1);
        // 下面开始翻转当前节点和反转后的子链表
        // head.next 表示newList返还后的N位置的节点
        head.next.next = head;
        head.next = nNext;
        return newList;
    }
}

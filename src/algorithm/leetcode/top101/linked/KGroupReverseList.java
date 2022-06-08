package algorithm.leetcode.top101.linked;

import algorithm.leetcode.Test;
import algorithm.leetcode.base.ListNode;

import java.util.Stack;

/**
 * K个节点一组，反转链表,不足K个不反转
 * 描述
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 *
 * 数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000 ，链表中每个元素都满足 0 \le val \le 10000≤val≤1000
 * 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 例如：
 * 给定的链表是 1\to2\to3\to4\to51→2→3→4→5
 * 对于 k = 2k=2 , 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
 * 对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
 *
 */
public class KGroupReverseList {
    public static void main(String[] args) {
        ListNode head = Test.createListNode();
        Test.println("创建链表：");
        Test.printList(head);
        int k = 3;
        // head = reverseKGroup(head, k);
        // head = reverseKGroup2(head, k);
        head = reverseKGroup3(head, k);
        Test.println("k反转后：");
        Test.printList(head);
    }

    /**
     * 时间复杂度O（n）
     * 空间复杂度O (1) * O(N/K) = O(N)
     *
     * 思路：用递归去做，可以理解成反转链表的前K个节点后 与 k个节点后已经反转好的子链表相连接
     *
     * 终止标志：子链表不足K个节点 或 第K个节点是整个链表的最后一个节点
     * 返回值：已经反转好的链表的链表头
     * 本级操作：从头遍历K个节点并反转，链接本级反转的K个节点和子链表反转好的头节点
     *
     *
     * @param head 链表头节点
     * @param k k
     * @return 返还后的结果
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 定义尾节点
        ListNode tail = head;
        // 找到第k个节点的下一个节点做为尾节点
        for (int i = 0; i < k; i++) {
            // 不足k个节点直接返回链表头
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }

        // 反转本级k个节点的链表
        ListNode pre = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        // 当前链表反转好的链表连接子链表
        head.next = reverseKGroup(tail, k);
        // 返回反转后的链表的头节点
        return pre;
    }


    /**
     * 借助一个大小为K 的栈结构反转此链表
     * 思路：1、不断遍历链表，K个一组装入栈中，直到满k个，并记录这个k个节点的头和尾
     * 2、装满k个后开始反转栈中的k个链表，反转时连接反转后的链表
     * 3、继续遍历加入栈中
     * @param head 链表头
     * @param k k
     * @return 反转后的链表头
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        // 定义要返回的链表的头节点
        ListNode newHead = head;
        // 当前正在操作的节点
        ListNode cur = head;
        // 记录要反转区间的上一个节点
        ListNode pre = null;
        // 记录要反转区间的下一个节点
        ListNode next;
        Stack<ListNode> stack = new Stack<>();
        // 不断遍历节点
        while (cur != null) {
            // 不断更新反转区间下一个节点
            next = cur.next;
            stack.push(cur);
            // 栈中存够k个节点时
            if (stack.size() == k) {
                // 开始反转，返回的是反转后的部分的最后一个节点，作为下一段的前一个节点
                pre = reverseBetweenByStack(stack, pre, next);
                // 更新整条链表的头节点，只在第一次反转时更新即可
                newHead = newHead == head ? cur : newHead;
            }
            // cur的移到下一个节点，不能用cur = cur.next, 因为cur在reverseBetween中已经被改变了cur.next
            cur = next;
        }
        return newHead;
    }

    /**
     * 反转链表存在栈中的节点
     *
     * @param stack 存储链表节点的栈结构
     * @param pre 栈中存储的链表的部分节点的前一个节点
     * @param next 栈中存储的链表的部分节点的后一个节点
     * @return 链表中反转部分的最后一个节点
     */
    public static ListNode reverseBetweenByStack(Stack<ListNode> stack, ListNode pre, ListNode next) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        ListNode cur = stack.pop();
        if (pre != null) {
            pre.next = cur;
        }

        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }

        cur.next = next;
        return cur;
    }

    /***
     * 性能做好的方式 时间 O（n） 空间O（1）
     * 思路：借助几个指针完成K个一组反转
     * cur:当前操作的节点
     * pre:每个要反转的子链表的前一个节点
     * next:当前节点的下一个节点
     * start:要反转子链表的子链表头
     * count:计数用
     *
     * @param head head
     * @param k k
     * @return newHead
     */
    public static ListNode reverseKGroup3(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        // 记录当前要操作的节点
        ListNode cur = head;
        // 记录当前处理的子链的头节点
        ListNode start;
        // 当前处理子链的头节点的前一个节点
        ListNode pre = null;
        // 当前节点的下一个节点
        ListNode next;
        // 计数
        int count = 1;
        while (cur != null) {
            next = cur.next;
            // 处理到第K个节点时
            if (count == k) {
                // 确定start位置
                start = pre == null ? head : pre.next;
                // 确定头节点，pre == null 说明是处理第一节
                head = pre == null ? cur : head;
                // 确定了start 可以反转当当前子链
                reverseBetween(pre, start, next);
                // 反转玩之后再更新pre的位置
                pre = start;
                // 计数清零
                count = 0;
            }
            count ++;
            cur = next;
        }
        return head;
    }

    /**
     * 反转从start 到 end段的链表
     * @param startPre 要反转子链表的前一个节点
     * @param start 当前子链表的头节点
     * @param endNext 要反转子链表的后一个节点
     */
    private static void reverseBetween(ListNode startPre, ListNode start, ListNode endNext) {
        ListNode cur = start.next;
        ListNode pre = start;
        ListNode next;
        while (cur != endNext) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (startPre != null) {
            startPre.next = pre;
        }
        start.next = endNext;
    }
}

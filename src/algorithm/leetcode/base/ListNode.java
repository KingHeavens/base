package algorithm.leetcode.base;

/**
 * Author: Aaron
 * Create Date: 2022/5/18
 * Version: v1.0
 * <p> 单链表节点
 * base
 */
public class ListNode {
    public int value;
    public ListNode next = null;
    public ListNode(int val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

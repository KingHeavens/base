package algorithm.leetcode.base;

/**
 * Author: Aaron
 * Create Date: 2022/5/18
 * Version: v1.0
 * <p>
 * base 双向链表节点
 */
public class DListNode {
    public int value;
    public DListNode pre;
    public DListNode next;
    public DListNode(int value) {
        this.value = value;
    }
}

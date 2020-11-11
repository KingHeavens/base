package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.Node;
import test.Printer;

import java.util.Stack;

/**
 * 回文字符串
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class PalindromeList {
    /**
     * 将链表放入栈中，出栈与原链表比较是否一样
     * 额外空间复杂度O(n)
     *
     * @param head head
     * @return 是否回文字符串
     */
    public boolean isPalindromeList(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 将链表的一半发放入栈中，出栈与链表前半部分比较是否相同
     * 额外空间复杂度O(n/2)
     *
     * @param head head
     * @return 是否回文字符串
     */
    public boolean isPalindromeList2(Node head) {
        if (head == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        fast = head;
        while (!stack.isEmpty()) {
            if (stack.pop().value != fast.value) {
                return false;
            }
            fast = fast.next;
        }
        return true;
    }

    /**
     * 将链表后半部分翻转，然后一个指针从头开始，一个指针从尾部开始依次比较看是否相等
     *
     * @param head head
     * @return 是否回文字符串
     */
    public boolean isPalindromeList3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针找中心点
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node tail = slow;//左边部分的尾部
        Node cur = tail.next;//右边部分的头部
        tail.next = null;//将链表分成两部分

        //翻转右边的链表
        Node pre = null;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        //左右两边链表逐个比较元素是否相等
        Node left = head;
        cur = pre;
        boolean res = true;
        while (pre != null) {
            if (pre.value != left.value) {
                res = false;
                break;
            }
            pre = pre.next;
            left = left.next;
        }

        //将原来的链表复原
        pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        tail.next = pre;
        return res;
    }

    public static void main(String[] args) {
        Integer[] arr = {
            1, 2, 1, 1, 1, 2, 1
        };
        Node head = LinkedTestHelper.arrayToLinkedList(arr);
        PalindromeList algorithm = new PalindromeList();
        boolean isPalindrome = algorithm.isPalindromeList3(head);
        Printer.println("IsPalindrome:" + isPalindrome);

        LinkedTestHelper.printLinkedList(head);
    }
}

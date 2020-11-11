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
     * 额外控件复杂度O(1)
     *
     * 额外4个指针节点，比较好理解。
     * @param head head
     * @return 是否回文字符串
     */
    public boolean isPalindromeList3(Node head) {
        //0个或1个节点认为是回文字符串
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针寻找中间位置
        Node left = head;//慢指针
        Node right = head;//快指针
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }
        right = left.next;//找到右边链表第一个节点
        left.next = null;//中间位置往后断开

        //翻转中间的链表
        Node next;
        Node pre = left;//指向中间节点
        while (right != null) {
            next = right.next;
            right.next = pre;
            pre = right;
            right = next;
        }

        //比较左右两个链表是否相同
        right = pre;
        left = head;
        boolean res = true;
        while (left != null) {
            if (left.value != right.value) {
                res = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        //恢复原来的链表（翻转右边的链表）
        right = pre.next;
        pre.next = null;
        while (right != null) {
            next = right.next;
            right.next = pre;
            pre = right;
            right = next;
        }
        return res;
    }


    /**
     * 比较精简的办法
     * 额外空间复杂度 O(1)
     * 3个指针节点
     * @param head head
     */
    public void isPalindromeList4(Node head) {

    }

    public static void main(String[] args) {
        Integer[] arr = {
            1, 2, 1, 1, 1, 1, 2, 1
        };
        Node head = LinkedTestHelper.arrayToLinkedList(arr);
        PalindromeList algorithm = new PalindromeList();
        boolean isPalindrome = algorithm.isPalindromeList3(head);
        Printer.println("IsPalindrome:" + isPalindrome);

        LinkedTestHelper.printLinkedList(head);
    }
}

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
    public boolean isPalindromeList4(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针发找到链表中点位置
        Node n1 = head;//慢指针
        Node n2 = head;//快指针
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //n1 是 中点位置
        n2 = n1.next; //n2记录链表的右半部分
        n1.next = null;//将左半部分链表的结尾指向null

        //将右半部分链表翻转，翻转后的链表末尾指向左边链表的尾部节点
        //此时 n1是pre指针，n3是next指针
        Node n3;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        //n3记录右边指针的头结点
        n3 = n1;
        n2 = head;//将n2节点指向头结点
        //比较两个链表的数据是否相同
        // n1是指向右边节点的指针，n2是指向左边节点的指针
        boolean res = true;
        while (n2 != null) {
            if (n2.value != n1.value) {
                res = false;
                break;
            }
            n2 = n2.next;
            n1 = n1.next;
        }

        //恢复之前的链表，把翻转后的链表翻转回来
        n1 = n3.next;//从右边链表的第二个节点开始不断翻转 n1标识将要翻转的节点
        n3.next = null;//将头结点指向null, n3当做pre指针，n2当做next指针
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] arr = {
            1, 2, 1
        };
        Node head = LinkedTestHelper.arrayToLinkedList(arr);
        PalindromeList algorithm = new PalindromeList();
        boolean isPalindrome = algorithm.isPalindromeList4(head);
        Printer.println("IsPalindrome:" + isPalindrome);

        LinkedTestHelper.printLinkedList(head);
    }
}
